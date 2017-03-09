package com.viettel.view;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.commonsware.cwac.cache.HashMapManager;
import com.commonsware.cwac.cache.SimpleWebImageCache;
import com.commonsware.cwac.thumbnail.ThumbnailBus;
import com.commonsware.cwac.thumbnail.ThumbnailMessage;
import com.viettel.R;
import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.ErrorConstants;
import com.viettel.common.KunKunInfo;
import com.viettel.common.KunKunProfile;
import com.viettel.common.ModelEvent;
import com.viettel.common.OnCompositeControlListener;
import com.viettel.constants.IntentConstants;
import com.viettel.dto.UserDTO;
import com.viettel.listener.OnDialogControlListener;
import com.viettel.utils.KunKunLog;
import com.viettel.utils.PositionManager;
import com.viettel.utils.StringUtil;
import com.viettel.utils.ui.SnapAnimation;
import com.viettel.viettellib.network.http.HTTPClient;
import com.viettel.viettellib.network.http.HTTPRequest;

public abstract class BaseActivity extends Activity implements
		OnCompletionListener, DialogInterface.OnCancelListener, OnCompositeControlListener, OnDialogControlListener{
	// chuoi action cua cac broadcast message
	public static final String VT_ACTION = "viettel.com.project.action";
	// thoi gian rung khi co notification, chat
	private static final int VIBRATION_DURATION = 300;
	protected static final int HIDE_VIEW_NOTIFICATION = 1;
	protected final int ICON_RIGHT_PARENT = 0;
	protected final int ICON_NEXT_1 = 1;
	protected final int ICON_BUTTON = -1;
	
	// broadcast receiver, nhan broadcast
	BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			int action = intent.getExtras().getInt("project.action");
			int hasCode = intent.getExtras().getInt("project.hashCode");
			if (hasCode != BaseActivity.this.hashCode()) {
				receiveBroadcast(action, intent.getExtras());
			}
		}
	};

	// dialog hien thi khi request
	private static ProgressDialog progressDlg;
	// progressBar tren header
	private ProgressBar pbHeaderLoading;
	// header kunkun
	private View rlHeader;

	// kiem tra activity da finish hay chua
	public boolean isFinished = false;
	// co nhan broadcast hay khong
	private boolean broadcast;

	public ActionEvent actionEventBeforReLogin = null; // save actionevent
	// mang cac request dang xu ly do activity request
	private ArrayList<HTTPRequest> unblockReqs = new ArrayList<HTTPRequest>();
	private ArrayList<HTTPRequest> blockReqs = new ArrayList<HTTPRequest>();
	// activity co dang active
	private boolean isActive;
	// thuc hien hieu ung rung khi co notification, chat
	private Vibrator vibrator;
	// am thanh khi co notification, chat
	private MediaPlayer soundPlayer;
	
	SnapAnimation noticeInAppAnimation;
	
	public static SimpleWebImageCache thumnail;	
	
	private LinearLayout rootView;
	protected LinearLayout mainContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 KunKunInfo.getInstance().setAppContext(getApplicationContext());
		 KunKunInfo.getInstance().setActivityContext(this);
		if(savedInstanceState!=null){
			if(savedInstanceState.containsKey(IntentConstants.INTENT_GLOBAL_BUNDLE)){
				getIntent().putExtras(savedInstanceState.getBundle(IntentConstants.INTENT_GLOBAL_BUNDLE));
			}
			if (StringUtil.isNullOrEmpty(HTTPClient.sessionID)&&savedInstanceState
					.containsKey(IntentConstants.INTENT_SESSION_ID)) {
				HTTPClient.sessionID = savedInstanceState
						.getString(IntentConstants.INTENT_SESSION_ID);
			}
		}

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		try {
			IntentFilter filter = new IntentFilter(BaseActivity.VT_ACTION);
			registerReceiver(receiver, filter);
			this.broadcast = true;
		} catch (Exception e) {
		}
	
	}

	/**
	 * 
	 * ham onCreate voi tham so co dang ky lang nghe broadcast khong
	 * 
	 * @author: Unknown
	 * @param savedInstanceState
	 * @param broadcast
	 * @return: void
	 * @throws:
	 */
	protected void onCreate(Bundle savedInstanceState, boolean broadcast) {
		super.onCreate(savedInstanceState);
		KunKunInfo.getInstance().setAppContext(getApplicationContext());
		KunKunInfo.getInstance().setActivityContext(this);
		if(savedInstanceState!=null){
			if(savedInstanceState.containsKey(IntentConstants.INTENT_GLOBAL_BUNDLE)){
				getIntent().putExtras(savedInstanceState.getBundle(IntentConstants.INTENT_GLOBAL_BUNDLE));
			}
			if (StringUtil.isNullOrEmpty(HTTPClient.sessionID)&&savedInstanceState
					.containsKey(IntentConstants.INTENT_SESSION_ID)) {
				HTTPClient.sessionID = savedInstanceState
						.getString(IntentConstants.INTENT_SESSION_ID);
			}
		}

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		this.broadcast = broadcast;
		if (broadcast) {
			try {
				IntentFilter filter = new IntentFilter(
						BaseActivity.VT_ACTION);
				registerReceiver(receiver, filter);
			} catch (Exception e) {
			}
		}

		
	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		if(getIntent().getExtras()!=null){
			outState.putBundle(IntentConstants.INTENT_GLOBAL_BUNDLE, getIntent().getExtras());
		}
		if(!StringUtil.isNullOrEmpty(HTTPClient.sessionID)){
			outState.putString(IntentConstants.INTENT_SESSION_ID, HTTPClient.sessionID);
		}
		super.onSaveInstanceState(outState);
	}

	@Override
	public void finish() {
		isFinished = true;
		super.finish();
	}

	@Override
	public void finishActivity(int requestCode) {
		isFinished = true;
		super.finishActivity(requestCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onLowMemory()
	 */
	@Override
	public void onLowMemory() {
		HashMapManager.getInstance().clearAllHashMapExcept(this.toString());
		super.onLowMemory();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (this == KunKunInfo.getInstance().lastShowActivity) {
			KunKunInfo.getInstance().lastShowActivity = null;
		}
		removeAllProcessingRequest();
		try {
			unregisterReceiver(receiver);
		} catch (Exception e) {
		}
		HashMapManager.getInstance().clearHashMapById(this.toString());
		System.gc();
		System.runFinalization();
		
		super.onDestroy();

	}

	private void initialize() {
		}
	

	

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(R.layout.layout_kunkun);

	}

	@Override
	public void setContentView(View view) {
		// TODO Auto-generated method stub
		super.setContentView(R.layout.layout_kunkun);

	}

	/**
	*  xoa view
	*  @author: DoanDM
	*  @param view
	*  @return: void
	*  @throws:
	 */
	public void removeView(View view){
		mainContent.removeView(view);
	}
	@Override
	protected void onResume() {
		super.onResume();		
		isActive = true;
		KunKunInfo.getInstance().setAppActive(true);
		KunKunInfo.getInstance().lastShowActivity = this;		
		if(!PositionManager.getInstance().getIsStart() && KunKunInfo.getInstance().getProfile().isLogin()){
			KunKunInfo.getInstance().getProfile().getMyGPSInfo().setShowNoticeGPS(true);
			PositionManager.getInstance().start();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		isActive = false;
		KunKunInfo.getInstance().setAppActive(false);
		System.gc();
	}
	
	public void setLogoVisible(int visible) {

		rlHeader.setVisibility(visible);
	}
	

	public View getLogoHeader() {
		return rlHeader;
	}

	public void setLogoLoading(boolean visible) {
		if (pbHeaderLoading != null){
			if (visible) {
				pbHeaderLoading.setVisibility(View.VISIBLE);
			} else {
				pbHeaderLoading.setVisibility(View.GONE);
			}
		}
	}


	/**
	 * 
	*  Xu ly khi keyboard show/hide voi ugc
	*  @author: DoanDMN
	*  @param isShow
	*  @return: void
	*  @throws:
	 */
	public void onSoftKeyboardShown(boolean isShow){
	}

	public AlertDialog showDialog(final String mes) {
		AlertDialog alertDialog = null;
		try {
			alertDialog = new AlertDialog.Builder(this).create();
			// alertDialog.setTitle("Thông báo");
			alertDialog.setMessage(mes);
			alertDialog.setButton("Đóng", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					return;
				}
			});
			alertDialog.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alertDialog;
	}
	
	/**
	*  Show dialog voi msg thong bao, khi click ok thi dong va finsih activity
	*  @author: BangHN
	*  @param mes
	*  @param isFinish
	*  @return: AlertDialog
	*/
	public AlertDialog showDialogWithFinsihActivity(final String mes,boolean isFinish) {
		AlertDialog alertDialog = null;
		try {
			alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setMessage(mes);
			alertDialog.setButton("Đóng", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					finish();
					return;
					
				}
			});
			alertDialog.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alertDialog;
	}

	public void handleModelViewEvent(ModelEvent modelEvent) {
		ActionEvent e = modelEvent.getActionEvent();
			switch (e.action) {
				case ActionEventConstant.RE_LOGIN_XMPP: {
					KunKunInfo.getInstance().getProfile().getMyGPSInfo().setShowNoticeGPS(true);
					if(thumnail==null){
						ThumbnailBus bus = new ThumbnailBus();
						thumnail = new SimpleWebImageCache<ThumbnailBus, ThumbnailMessage>(
								null, null, 101, bus);
					}
					break;
				}				
				
				case ActionEventConstant.LOG_OUT:
					// sentBroadcast(ActionEventConstant.ACTIVITY_FINISH, new Bundle());
					break;
				case ActionEventConstant.LOG_OUT_EXIT:
					// sentBroadcast(ActionEventConstant.ACTIVITY_FINISH, new Bundle());
					// finish();
					break;				
			}
		
		
	}
	/**
	 * 
	 * lay cache chung cho tat ca man hinh lien quan toi avatar cua dia diem
	 * @author: HaiTC
	 * @return
	 * @return: SimpleWebImageCache<ThumbnailBus,ThumbnailMessage>
	 * @throws:
	 */
	public static SimpleWebImageCache<ThumbnailBus, ThumbnailMessage> getCacheAvatarLocation() {
		if(thumnail==null){
			ThumbnailBus bus = new ThumbnailBus();
			thumnail = new SimpleWebImageCache<ThumbnailBus, ThumbnailMessage>(
					null, null, 101, bus);
		}
		return thumnail;
	}
				

	public void handleErrorModelViewEvent(ModelEvent modelEvent) {		
	
	}

	public void showProgressDialog(String content) {
		showProgressDialog(content, true);
	}

	/**
	 * 
	 * show progress dialog
	 * 
	 * @author: AnhND
	 * @param content
	 * @param cancleable
	 * @return: void
	 * @throws:
	 */
	public void showProgressDialog(String content, boolean cancleable) {
		if(progressDlg!=null&&progressDlg.isShowing()){
			closeProgressDialog();
		}
		progressDlg = ProgressDialog.show(this, "", content, true, true);
		progressDlg.setCancelable(cancleable);
		progressDlg.setCanceledOnTouchOutside(false);
		progressDlg.setOnCancelListener(this);
	}

	public void closeProgressDialog() {
		if (progressDlg != null) {
			try{
				progressDlg.dismiss();
				progressDlg = null;
			}catch (Exception e) {
				KunKunLog.i("Exception", e.toString());
			}
		}
	}

	public void showProgressPercentDialog(String content) {
		progressDlg = new ProgressDialog(this);
		progressDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDlg.setMessage(content);
		progressDlg.setCancelable(false);
		progressDlg.show();
	}

	public void updateProgressPercentDialog(int percent) {
		if (progressDlg != null) {
			progressDlg.setProgress(percent);
		}
	}

	public void closeProgressPercentDialog() {
		if (progressDlg != null) {
			progressDlg.dismiss();
			progressDlg = null;
		}
	}
	
	public void receiveBroadcast(int action, Bundle bundle) {		
		try {
			KunKunProfile profile = KunKunInfo.getInstance().getProfile();
			if(profile.getUserData()== null){
				return;
			}
			switch (action) {
			default:
				break;
			}
		} catch (Exception e) {
			KunKunLog.i(this.getClass().getName() + " - receiveBroadcast", e
					.getMessage()
					+ "/" + e.toString());
		}
	}	
	
	
	
	
	public void sentBroadcast(int action, Bundle bundle) {
		Intent intent = new Intent(VT_ACTION);
		bundle.putInt("project.action", action);
		bundle.putInt("project.hashCode",intent.getClass().hashCode());
		intent.putExtras(bundle);
		sendBroadcast(intent);
	}
	
	
	/**
	 * bat am thanh
	 * @author: AnhND
	 * @return: void
	 * @throws:
	 */
	public void playSound() {
		try {
			Uri alert = RingtoneManager
					.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			if (alert != null) {
				stopPlayingSound();
				soundPlayer = new MediaPlayer();
				soundPlayer.setOnCompletionListener(this);

				soundPlayer.setDataSource(this, alert);
				final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
				if (audioManager
						.getStreamVolume(AudioManager.STREAM_NOTIFICATION) != 0) {
					soundPlayer
							.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
					soundPlayer.setLooping(false);
					soundPlayer.prepare();
					soundPlayer.start();
				}
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			if (soundPlayer != null) {
				soundPlayer.release();
				soundPlayer = null;
			}
		}
	}

	/**
	 * 
	 * tat am thanh
	 * 
	 * @author: AnhND
	 * @return: void
	 * @throws:
	 */
	protected void stopPlayingSound() {
		if (soundPlayer != null && soundPlayer.isPlaying()) {
			soundPlayer.stop();
			soundPlayer.release();
			soundPlayer = null;
		}
	}

	/**
	 * 
	 * bat hieu ung rung
	 * 
	 * @author: AnhND
	 * @return: void
	 * @throws:
	 */
	public void vibrate() {
		stopVibrating();
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(VIBRATION_DURATION);
	}

	/**
	 * 
	 * ngung hieu ung rung
	 * 
	 * @author: AnhND
	 * @return: void
	 * @throws:
	 */
	protected void stopVibrating() {
		if (vibrator != null) {
			vibrator.cancel();
		}
	}


	
	public int getStatusBarHeight() {
	    Rect r = new Rect();
	    Window w = getWindow();
	    w.getDecorView().getWindowVisibleDisplayFrame(r);
	    return r.top;
	}
	
	public int getTitleBarHeight() {
	    int viewTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
	    return (viewTop - getStatusBarHeight());
	}
	
	

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		if(soundPlayer != null){
			soundPlayer.release();
		}
		soundPlayer = null;
	}

	public boolean isActive() {
		return isActive;
	}

	/**
	 * 
	 * add processing request
	 * 
	 * @author: AnhND
	 * @param req
	 *            , isBlock
	 * @return: void
	 * @throws:
	 */
	public void addProcessingRequest(HTTPRequest req, boolean isBlock) {
		if (isBlock) {
			blockReqs.add(req);
		} else {
			unblockReqs.add(req);
		}
	}

	/**
	 * 
	 * remove all processing request
	 * 
	 * @author: AnhND
	 * @return: void
	 * @throws:
	 */
	public void removeAllProcessingRequest() {
		cancelRequest(blockReqs);
		cancelRequest(unblockReqs);
	}

	/**
	 * 
	 * remove processing request
	 * 
	 * @author: AnhND
	 * @param req
	 * @return: void
	 * @throws:
	 */
	public void removeProcessingRequest(HTTPRequest req, boolean isBlock) {
		if (isBlock) {
			cancelRequest(blockReqs, req);
		} else {
			cancelRequest(unblockReqs, req);
		}
	}


	private void cancelRequest(ArrayList<HTTPRequest> arrReq) {
		HTTPRequest req = null;
		for (int i = 0, n = arrReq.size(); i < n; i++) {
			req = arrReq.get(i);
			req.setAlive(false);
		}
		arrReq.clear();
	}

	private void cancelRequest(ArrayList<HTTPRequest> arrReq, HTTPRequest req) {
		HTTPRequest curReq = null;
		for (int i = 0, n = arrReq.size(); i < n; i++) {
			curReq = arrReq.get(i);
			if (curReq == req) {
				arrReq.remove(i);
				req.setAlive(false);
				break;
			}
		}
		arrReq.clear();
	}
	
	/**
	*  Kiem tra co ton tai request dang xu ly hay khong
	*  @author: TruongHN
	*  @param reqAction
	*  @return: boolean
	*  @throws:
	 */
	public boolean checkExistRequestProcessing(int reqAction){
		boolean res = false;
		HTTPRequest curReq = null;
		for (int i = 0, n = blockReqs.size(); i < n; i++) {
			curReq = blockReqs.get(i);
			if (curReq.isAlive() && curReq.getAction() == reqAction) {
				res = true;
				break;
			}
		}
		return res;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see
	 * android.content.DialogInterface.OnCancelListener#onCancel(android.content
	 * .DialogInterface)
	 */
	public void onCancel(DialogInterface dialog) {
		// dang hien thi progressDialog => bam nut back
		// TODO Auto-generated method stub
		if (dialog == progressDlg) {
			cancelRequest(this.blockReqs);
		}
	}

	/**
	 * 
	 * Kiem tra progress dialog dang show
	 * 
	 * @author: AnhND
	 * @return
	 * @return: boolean
	 * @throws:
	 */
	public boolean isProgressDialogShowing() {
		boolean result = false;
		if (progressDlg != null) {
			result = progressDlg.isShowing();
		}
		return result;
	}	
	
	/* (non-Javadoc)
	 * @see com.viettel.kunkun.common.OnCompositeControlListener#onEvent(int, android.view.View, java.lang.Object)
	 */
	@Override
	public void onEvent(int eventType, View control, Object data) {
		// TODO Auto-generated method stub
	}	
	
	/* (non-Javadoc)
	 * @see com.viettel.kunkun.view.OnDialogControlListener#onDialogListener(int, int, java.lang.Object)
	 */
	@Override
	public void onDialogListener(int eventType, int eventCode, Object data) {
		// TODO Auto-generated method stub
		
	}
	
	public int getHeaderHeight(){
		return rlHeader.getMeasuredHeight();
	}
	
}
