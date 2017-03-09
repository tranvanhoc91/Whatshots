package com.viettel.utils;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.ErrorConstants;
import com.viettel.common.KunKunInfo;
import com.viettel.common.ModelEvent;
import com.viettel.common.ServerPath;
import com.viettel.constants.Constants;
import com.viettel.viettellib.json.me.JSONObject;
import com.viettel.viettellib.network.http.HTTPListenner;
import com.viettel.viettellib.network.http.HTTPMessage;
import com.viettel.viettellib.network.http.HTTPRequest;
import com.viettel.viettellib.network.http.HTTPResponse;
import com.viettel.viettellib.network.http.HttpAsyncTask;
import com.viettel.viettellib.network.http.NetworkUtil;
import com.viettel.view.BaseActivity;


public class PositionManager extends Activity implements LocatingListener, HTTPListenner{	
	final int GPS_TIME_OUT = 90000;//thoi gian time out lay gps
	final int LBS_TIME_OUT = 7000;//7s thoi gian time out lay lbs
	final int TIME_LOC_PERIOD = 300000;//dinh thoi goi dinh vi
	public static final int ACCURACY = 50;
	
	final String LOG_TAG = "PositionManager";
	
	public static final int LOCATING_NONE = 0;//trang thai none
	static final int LOCATING_GPS_START = 1;//trang thai start gps
	static final int LOCATING_GPS_ON_PROGRESS = 2;//trang thai dang lay gps
	static final int LOCATING_GPS_FAILED = 3;//trang thai lay gps that bai
	static final int LOCATING_LBS_START = 4;//trang thai start lbs
	static final int LOCATING_LBS_ON_PROGRESS = 5;//trang thai dang lay lbs
	static final int LOCATING_LBS_FAILED = 6;//trang thai lay lbs that bai
	static final int LOCATING_VIETTEL = 7;//trang thai dinh vi viettel
	public int actionSuccess;//id action dinh vi 1 lan thanh cong
	public int actionFail;//id action dinh vi 1 lan that bai
	private static PositionManager instance;
	private boolean isStart = false;
	public boolean usingLBS = false;//co su dung lbs cua Viettel khong
	Locating gpsLocating;//dinh vi gps
	Locating lbsLocating;//dinh vi lbs	
	int locatingState = LOCATING_NONE;//trang thai dinh vi
	private Timer locTimer = new Timer();//timer dinh thoi lay toa do
	private KunKunTimerTask locTask;//task lay toa do dung kem voi timer	
	private boolean isFirstLBS;//bat luong LBS truoc
	
	public static PositionManager getInstance(){
		if(instance == null){
			instance = new PositionManager();
		}
		return instance;
	}
	
	class KunKunTimerTask extends TimerTask {
		public boolean isCancled = false;
		@Override
		public void run() {
			// TODO Auto-generated method stub
		}
	}
	
	
	
	public PositionManager() {
		// TODO Auto-generated constructor stub		
		instance = this;
	}

	/**
	 * 
	*  khoi dong lay toa do
	*  @author: AnhND
	*  @return: void
	*  @throws:
	 */
	public void start() {
		// TODO Auto-generated method stub		
		KunKunLog.logToFile(Constants.LOG_LBS, "startLBS, lng = " + KunKunInfo.getInstance().getProfile().getMyGPSInfo().getLongtitude() + " lat = "  + KunKunInfo.getInstance().getProfile().getMyGPSInfo().getLatitude());
		KunKunInfo.getInstance().getAppContext().getSystemService(Context.LOCATION_SERVICE);
		usingLBS = !KunKunInfo.getInstance().getProfile().getUserData().isVT;
		isStart = true;
		isFirstLBS = true;		
		//run 1 luong lbs song song voi luong dinh vi
		if(usingLBS){
			getLBSGoogle();//lay lbs google
		}else{
			onLocationChanged(null);//lay luong lbs truoc, viettel truoc, google sau
		}
		locTimer = new Timer();
		locTask = new KunKunTimerTask() {
			@Override
			public void run() {
				if (! isCancled) {
					(KunKunInfo.getInstance().getAppHandler()).post(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub	
							if(KunKunInfo.getInstance().isAppActive()){	
								locatingState = LOCATING_NONE;
								requestLocationUpdates();
							}else{
								KunKunInfo.getInstance().getProfile().getMyGPSInfo().setShowNoticeGPS(true);
								stop();
							}														
						}
					});
				}
			}
		};
		locTimer.schedule(locTask, 0, TIME_LOC_PERIOD);				
	}	
	
	/**
	 * 
	*  Dung lay toa do
	*  @author: AnhND
	*  @return: void
	*  @throws:
	 */
	public void stop() {
		isStart = false;		
		KunKunLog.logToFile(Constants.LOG_LBS, "stopLBS, lng = " + KunKunInfo.getInstance().getProfile().getMyGPSInfo().getLongtitude() + " lat = "  + KunKunInfo.getInstance().getProfile().getMyGPSInfo().getLatitude());
		KunKunInfo.getInstance().getProfile().getMyGPSInfo().setLongtitude(-1);
		KunKunInfo.getInstance().getProfile().getMyGPSInfo().setLattitude(-1);						
		KunKunInfo.getInstance().getProfile().save();
		if (locTask != null) {
			locTask.isCancled = true;
		}
		if (locTimer != null) {
			locTimer.cancel();
		}
		if (gpsLocating != null) {
			gpsLocating.resetTimer();
		}
		if (lbsLocating != null) {
			lbsLocating.resetTimer();
		}
	}

	/**
	 * 
	*  quan ly trang thai va goi lay toa do
	*  @author: AnhND
	*  @return: void
	*  @throws:
	 */
	private void requestLocationUpdates() {
		synchronized (this) {			
			switch (locatingState) {
			case LOCATING_NONE:
				KunKunLog.i(LOG_TAG, "requestLocationUpdates() - LOCATING_NONE");
				locatingState = LOCATING_GPS_START;
				requestLocationUpdates();
				break;

			case LOCATING_GPS_START:
				KunKunLog.i(LOG_TAG, "requestLocationUpdates() - LOCATING_GPS_START");
				if (gpsLocating == null) {
					gpsLocating = new Locating(LocationManager.GPS_PROVIDER, this);
				}
				if (!gpsLocating.requestLocating(GPS_TIME_OUT)) {
					locatingState = LOCATING_GPS_FAILED;					
					requestLocationUpdates();
				} else {					
					locatingState = LOCATING_GPS_ON_PROGRESS;
				}
				break;

			case LOCATING_GPS_ON_PROGRESS:
				KunKunLog.i(LOG_TAG, "requestLocationUpdates() - LOCATING_GPS_ON_PROGRESS");
				break;

			case LOCATING_GPS_FAILED:
				KunKunLog.i(LOG_TAG, "requestLocationUpdates() - LOCATING_GPS_FAILED");
				if (usingLBS) {
					locatingState = LOCATING_LBS_START;
				} else {
					locatingState = LOCATING_VIETTEL;
				}
				requestLocationUpdates();
				break;

			case LOCATING_LBS_START:
				KunKunLog.i(LOG_TAG, "requestLocationUpdates() - LOCATING_LBS_START");
				if (lbsLocating == null) {
					lbsLocating = new Locating(LocationManager.NETWORK_PROVIDER, this);
				}
				if (!lbsLocating.requestLocating(LBS_TIME_OUT)) {// dinh vi lbs
					locatingState = LOCATING_LBS_FAILED;
					requestLocationUpdates();
				} else {
					locatingState = LOCATING_LBS_ON_PROGRESS;
				}
				break;

			case LOCATING_LBS_ON_PROGRESS:
				KunKunLog.i(LOG_TAG, "requestLocationUpdates() - LOCATING_LBS_ON_PROGRESS");
				break;
				
			case LOCATING_LBS_FAILED:
				KunKunLog.i(LOG_TAG, "requestLocationUpdates() - LOCATING_LBS_FAILED");			
				locatingState = LOCATING_NONE;
				break;
				
			case LOCATING_VIETTEL:
				KunKunLog.i(LOG_TAG, "requestLocationUpdates() - LOCATING_VIETTEL");
				onLocationChanged(null);
				break;
			}
		}
	}
	
	private void getLBSGoogle(){
		isFirstLBS = false;
		(KunKunInfo.getInstance().getAppHandler()).post(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub	
				Locating lbs = new Locating(LocationManager.NETWORK_PROVIDER, PositionManager.this);
				lbs.requestLocating(LBS_TIME_OUT);														
			}
		});			
	}

	/**
	 * 
	*  chuyen trang thai khi dinh vi that bai
	*  @author: AnhND
	*  @return: void
	*  @throws:
	 */
	private void handleLocatingFailed () {		
		synchronized (this) {
			switch (locatingState) {
			case LOCATING_GPS_ON_PROGRESS:
				KunKunLog.i(LOG_TAG, "handleLocatingFailed() - GPS TIME OUT");
				locatingState = LOCATING_GPS_FAILED;
				requestLocationUpdates();
				break;
			case LOCATING_LBS_ON_PROGRESS:
				KunKunLog.i(LOG_TAG, "handleLocatingFailed() - LBS TIME OUT");				
				locatingState = LOCATING_LBS_FAILED;
				requestLocationUpdates();
				break;
			case LOCATING_VIETTEL://lay lbs Viettel error thi lay lbs Google
				KunKunLog.i(LOG_TAG, "handleLocatingFailed() - LBS TIME OUT");
				locatingState = LOCATING_LBS_START;
				(KunKunInfo.getInstance().getAppHandler()).post(new Runnable() {
					@Override
					public void run() {
						requestLocationUpdates();
				}});				
				break;				
			}
		}		
	}
	
	/* (non-Javadoc)
	 * @see android.location.LocationListener#onLocationChanged(android.location.Location)
	 */
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if (location != null) {			
			updatePosition(location.getLongitude(), location.getLatitude());
			if(locatingState == LOCATING_GPS_ON_PROGRESS){//neu co 1 truong hop gps success thi chay xong gps se dung tien trinh kg lam gi tiep nua
				locatingState = LOCATING_NONE;				
			}
		} else {
			requestActionUpdatePosition();
		}		
	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onProviderDisabled(java.lang.String)
	 */
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		handleLocatingFailed();
	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onProviderEnabled(java.lang.String)
	 */
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub			
	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onStatusChanged(java.lang.String, int, android.os.Bundle)
	 */
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub		
	}

	/* (non-Javadoc)
	 * @see com.viettel.kunkun.utils.LocatingListener#onTimeout(com.viettel.kunkun.utils.Locating)
	 */
	@Override
	public void onTimeout(Locating locating) {
		// TODO Auto-generated method stub
		if(!isFirstLBS){
			handleLocatingFailed();
		}
	}

	/* (non-Javadoc)
	 * @see com.viettel.kunkun.kunkunlibrary.network.http.HTTPListenner#onReceiveData(com.viettel.kunkun.kunkunlibrary.network.http.HTTPMessage)
	 */
	@Override
	public void onReceiveData(HTTPMessage mes) {		
		// TODO Auto-generated method stub
		ActionEvent actionEvent = (ActionEvent) mes.getUserData();
		ModelEvent model = new ModelEvent();
		model.setDataText(mes.getDataText());
		model.setParams(((HTTPResponse) mes).getRequest().getDataText());
		model.setActionEvent(actionEvent);
		switch (mes.getAction()) {
		case ActionEventConstant.ACTION_UPDATE_POSITION:
			processActionUpdatePosition(model, mes.getDataText());
			break;
		default:
			break;
		}		
	}

	/* (non-Javadoc)
	 * @see com.viettel.kunkun.kunkunlibrary.network.http.HTTPListenner#onReceiveError(com.viettel.kunkun.kunkunlibrary.network.http.HTTPResponse)
	 */
	@Override
	public void onReceiveError(HTTPResponse response) {
		// TODO Auto-generated method stub		
		KunKunLog.logToFile(Constants.LOG_LBS, "onReceiveError PositionManager, dataText = " + ((HTTPResponse) response).getRequest().getDataText());
		if(isFirstLBS){//chi vo day luc luong LBS dau tien va LbsViettel fail					
			getLBSGoogle();					
		}else{
			handleLocatingFailed();
		}	
		
		ActionEvent actionEvent = (ActionEvent) response.getUserData();
		ModelEvent model = new ModelEvent();
		model.setDataText(response.getDataText());
		model.setParams(((HTTPResponse) response).getRequest().getDataText());
		model.setActionEvent(actionEvent);
		
		model.setModelCode(ErrorConstants.ERROR_NO_CONNECTION);
		model.setModelMessage("Không kết nối mạng được.");
		sendLogToServer(model);
		
	}
	
	private void sendLogToServer(ModelEvent modelEvent){		
		LogMsg log;
		String versionName = Constants.STR_BLANK;
		int userId = -1;
		int action = -1;
		if(KunKunInfo.getInstance().getProfile() != null){
			versionName = KunKunInfo.getInstance().getProfile().getVersionName();
			userId = KunKunInfo.getInstance().getProfile().getUserData().id;
		}
		if(modelEvent.getActionEvent() != null){
			action = modelEvent.getActionEvent().action;
		}				
		log = new LogMsg();
    	if (modelEvent.getModelCode() == ErrorConstants.ERROR_SESSION_RESET){
    		log.setName("NOT_LOGIN");
    	}else{
    		log.setName("action:" + action + " versionName : " + versionName +
    				" userId:" + userId);
    	}
		log.appendDes("para: ");
		log.appendDes(modelEvent.getParams());		
		log.appendDes("result: ");
		log.appendDes(modelEvent.getDataText());
		log.appendDes("error code client: ");
		log.appendDes(String.valueOf(modelEvent.getModelCode()));
		// check: chi ghi log khi loi server
		if (modelEvent.getModelCode() == ErrorConstants.ERROR_COMMON || (modelEvent.getModelCode() != ErrorConstants.ERROR_COMMON)){
			ServerLogger.sendLog(log);
		}				
		// end 
	}
	
	private void requestActionUpdatePosition(){
		ActionEvent e = new ActionEvent();
		e.action = ActionEventConstant.ACTION_UPDATE_POSITION;
		e.sender = this;			
		HTTPRequest request = null;
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(NetworkUtil.getJSONString("profile.getMyPosition",
				null));
		request = new HTTPRequest();
		request.setUrl(ServerPath.SERVER_PATH);
		request.setMethod("POST");
		request.setAction(e.action);
		request.setContentType(HTTPMessage.CONTENT_JSON);
		request.setDataText(strBuffer.toString());
		request.setObserver(this);
		request.setUserData(e);
		new HttpAsyncTask(request).execute();
	}
	
	private void processActionUpdatePosition(ModelEvent model, String dataText){
		try {			
			JSONObject json = new JSONObject(dataText);
			JSONObject result = json.getJSONObject("result");
			int errCode = result.getInt("errorCode");
			model.setModelCode(errCode);
			if (errCode == ActionEventConstant.ERROR_CODE_SUCCESS) {
				JSONObject jsonObject = result.getJSONObject("response");				
				updatePosition(jsonObject.getDouble("lng"), jsonObject.getDouble("lat"));				
				isFirstLBS = false;
				locatingState = LOCATING_NONE;
			} else {				
				KunKunLog.logToFile(Constants.LOG_LBS, "processActionUpdatePosition error PositionManager, dataText = " + dataText);
				if(isFirstLBS){//chi vo day luc luong LBS dau tien va LbsViettel fail					
					getLBSGoogle();					
				}else{
					handleLocatingFailed();
				}
				if (model.getModelCode() != ErrorConstants.ERROR_SESSION_RESET){
					sendLogToServer(model);
				}
			}
		} catch (Exception ex) {
			Log.i("HieuNH", "processActionUpdatePosition Exception PositionManager, dataText = " + dataText);
			KunKunLog.logToFile(Constants.LOG_LBS, "processActionUpdatePosition Exception PositionManager, dataText = " + dataText);
			model.setModelCode(ErrorConstants.ERROR_COMMON);
			if(isFirstLBS){//chi vo day luc luong LBS dau tien va LbsViettel fail					
				getLBSGoogle();					
			}else{
				handleLocatingFailed();
			}
			if (model.getModelCode() != ErrorConstants.ERROR_SESSION_RESET){
				sendLogToServer(model);
			}
		}		
	}
	
	private void updatePosition(double lng, double lat){
		KunKunInfo.getInstance().getProfile().getMyGPSInfo().setLongtitude(lng);
		KunKunInfo.getInstance().getProfile().getMyGPSInfo().setLattitude(lat);
		KunKunLog.logToFile(Constants.LOG_LBS, "updatePosition, lng = " + KunKunInfo.getInstance().getProfile().getMyGPSInfo().getLongtitude() + " lat = "  + KunKunInfo.getInstance().getProfile().getMyGPSInfo().getLatitude());
		KunKunInfo.getInstance().getProfile().save();	
		Bundle bd = new Bundle();
		if(KunKunInfo.getInstance().getActivityContext() instanceof BaseActivity){
			((BaseActivity)KunKunInfo.getInstance().getActivityContext()).sentBroadcast(ActionEventConstant.ACTION_UPDATE_POSITION, bd);
		}		
	}
		
	public boolean getIsStart(){
		return isStart;
	}
	
	public int getLocatingState(){
		return locatingState;
	}
	
	public boolean getIsFirstLBS(){
		return isFirstLBS;
	}
	
	/**
	 * @return the enableGPS
	 */
	public boolean isEnableGPS() {
		return new Locating(LocationManager.GPS_PROVIDER, this).isEnableGPS();		
	}
}
