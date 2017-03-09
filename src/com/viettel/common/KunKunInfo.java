package com.viettel.common;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;

import com.viettel.constants.Constants;
import com.viettel.utils.StatusNotificationHandler;
import com.viettel.view.BaseActivity;

public class KunKunInfo extends Application {
	// phan khong luu xuong file
	public static final String KUNKUN_CATEGORY = "kunkunCategory";
	public final String VERSION = "1.1";
//	public final String PLATFORM_SDK_STRING = android.os.Build.VERSION.SDK;
	public final String PHONE_MODEL_SDK = android.os.Build.MODEL +"_API Level "+ android.os.Build.VERSION.SDK;
	Context appContext;// application context
	Context activityContext;//activity context
	private static KunKunInfo instance = null;
	private boolean isAppActive;
	private boolean isNeedToReloadChatList = false;
	// AnhND: dung kiem tra man hinh chi tiet chat/feed on top
	public BaseActivity lastShowActivity = null;
	// bien dung de debug
	public static final boolean ISDEBUG = false;

	// resource dang nhap xmpp
	public String xmppResource = "";
	// xu ly realtime ngoai chuong trinh
	private StatusNotificationHandler statusNotifier;

	// phan luu xuong file
	private KunKunProfile profile = new KunKunProfile();

	private Handler mHandler = new Handler();

	private Bitmap bmpData = null;//luu giu data bitmap khi dinh kem hinh anh

	// Methods
	public KunKunInfo() {
		// ring = this.getClass().getResourceAsStream("/ring.mid");
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

	}

	public static KunKunInfo getInstance() {
		if (instance == null) {
			instance = new KunKunInfo();
			instance.initialize();
		}
		return instance;
	}
	
	/**
	*  get PlatForm
	*  @author: TruongHN
	*  @return: String
	*  @throws:
	 */
	public String getPlatForm(){
		return Constants.STR_ANDROID;
	}
	

	private void initialize() {
		statusNotifier = new StatusNotificationHandler();
	}

	
	/**
	*  set bitmap data
	*  @author: BangHN
	*  @return: Bitmap
	*/
	public void setBitmapData(Bitmap data){
		bmpData = data;
	}
	
	
	/**
	*  get bitmap data
	*  @author: BangHN
	*  @return: Bitmap
	*/
	public Bitmap getBitmapData(){
		return bmpData;
	}
	
	
	/**
	*  huy bo doi tuong bitmap dta
	*  @author: BangHN
	*  @return: void
	*  @throws:
	 */
	public void recycleBitmapData(){
		if(bmpData != null){
			bmpData.recycle();
			bmpData = null;
		}
	}
	
	
	/**
	*  Lay thong tin profile
	*  @author: DoanDM
	*  @return
	*  @return: KunKunProfile
	*  @throws:
	 */
	public KunKunProfile getProfile() {
		Object temp;
//		if (profile == null || profile.getUserData() == null
//				|| profile.getUserData().id == -1) {// nghia la bien o trang
//													// thai ban dau hoac da bi
//													// reset
//			if ((temp = KunKunUtils.readObject(
//					KunKunProfile.KUNKUN_PROFILE)) != null) {
//				profile = (KunKunProfile) temp;// bi out memory
//				System.setProperty("networkaddress.cache.ttl","0");
//				System.setProperty("networkaddress.cache.negative.ttl" , "0");
//			}
//		}
		return profile;
	}


	public void setAppContext(Context context) {
		this.appContext = context;

	}

	public Context getAppContext() {
		if(appContext == null){
			appContext = new KunKunInfo();
		}
		return appContext;
	}
	
	public void setActivityContext(Context context) {
		this.activityContext = context;

	}

	public Context getActivityContext() {		
		return activityContext;
	}


	public void setAppActive(boolean isActive) {

		this.isAppActive = isActive;
	}


	public Handler getAppHandler() {
		return mHandler;
	}

	public void setNeedToReloadChatList(boolean isNeed) {

		isNeedToReloadChatList = isNeed;
	}


	public boolean isNeedToReloadChatList() {

		return isNeedToReloadChatList;
	}


	public boolean isAppActive() {

		return isAppActive;
	}

	
	public StatusNotificationHandler getStatusNotifier() {
		if (statusNotifier == null) {
			statusNotifier = new StatusNotificationHandler();
		}
		return statusNotifier;
	}

	public void setStatusNotification(StatusNotificationHandler vl) {
		statusNotifier = vl;
	}
}
