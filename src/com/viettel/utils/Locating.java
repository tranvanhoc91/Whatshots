/**
 * Copyright 2011 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.utils;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.viettel.common.KunKunInfo;

/**
 * 
 *  lang nghe cac su kien dinh vi co time out (interface)
 *  @author: AnhND
 *  @version: 1.0
 *  @since: Jul 16, 2011
 */
interface LocatingListener extends LocationListener{
	public void onTimeout (Locating locating);
}

/**
 *  Lay toa do hien hanh, ho tro timeout
 *  @author: AnhND
 *  @version: 1.0
 *  @since: Jul 16, 2011
 */
class Locating implements LocationListener{
	
	class TimeOutTask extends TimerTask {
		public boolean isCancel = false;		
		/* (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			(KunKunInfo.getInstance().getAppHandler()).post(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if (!isCancel) {						
						LocationManager locManager = (LocationManager)KunKunInfo.getInstance().getAppContext().getSystemService(Context.LOCATION_SERVICE);
						locManager.removeUpdates(Locating.this);
						Locating.this.resetTimer();
						listener.onTimeout(Locating.this);						
					}
				}
			});
		}
	}

	String providerName = "";//ten provider dinh vi(gps, lbs)
	Timer timeOutTimer = null;//quan ly timeout	
	LocatingListener listener;//listener
	TimeOutTask timeOutTask;
	
//	long startTime = 0;
//	long stopTime = 0;
	
	public Locating (String locationProviderName, LocatingListener listener) {		
		providerName = locationProviderName;
		this.listener = listener;
	}
	
	public void resetTimer() {
		// TODO Auto-generated method stub
		if (timeOutTimer != null) {
			timeOutTimer.cancel();
			timeOutTimer = null;
			
			timeOutTask.isCancel = true;
			timeOutTask.cancel();
			timeOutTask = null;
			
			LocationManager locManager = (LocationManager)KunKunInfo.getInstance().getAppContext().getSystemService(Context.LOCATION_SERVICE);
			locManager.removeUpdates(Locating.this);
		}
	}
	
	public boolean isEnableGPS(){
		if (KunKunInfo.getInstance().getAppContext() != null) {
			LocationManager locManager = (LocationManager)KunKunInfo.getInstance().getAppContext()
					.getSystemService(Context.LOCATION_SERVICE);
			if (locManager.isProviderEnabled(providerName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	*  thuc hien lay toa do
	*  @author: AnhND
	*  @param timeout
	*  @return
	*  @return: boolean
	*  @throws:
	 */
	public boolean requestLocating(int timeout) {
		// TODO Auto-generated method stub
		boolean result = false;
		resetTimer();
		LocationManager locManager = (LocationManager)KunKunInfo.getInstance().getAppContext().getSystemService(Context.LOCATION_SERVICE);
		if (locManager.isProviderEnabled(providerName)) {
			locManager.requestLocationUpdates(providerName, 0, 0.0f, Locating.this);
			timeOutTimer = new Timer();
			timeOutTask = new TimeOutTask();			
			timeOutTimer.schedule(timeOutTask, timeout);
			result = true;
		}else {
			result = false;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onLocationChanged(android.location.Location)
	 */
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub		
		if(location.getAccuracy() < PositionManager.ACCURACY){
			resetTimer();
		}
		if (listener != null) {
			listener.onLocationChanged(location);
		}
	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onProviderDisabled(java.lang.String)
	 */
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		resetTimer();
		if (listener != null) {
			listener.onProviderDisabled(provider);
		}
	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onProviderEnabled(java.lang.String)
	 */
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		if (listener != null) {
			listener.onProviderEnabled(provider);
		}
	}

	/* (non-Javadoc)
	 * @see android.location.LocationListener#onStatusChanged(java.lang.String, int, android.os.Bundle)
	 */
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		if (listener != null) {
			listener.onStatusChanged(provider, status, extras);
		}
	}
}
