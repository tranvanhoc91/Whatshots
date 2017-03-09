package com.viettel.utils;


import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.viettel.dto.GPSDTO;
import com.viettel.view.BaseActivity;


public class GPSListenner implements LocationListener {

	public String action;
	public BaseActivity ViewActivity;
	private GPSDTO GPS;
	public GPSListenner(BaseActivity ac){
		GPS = new GPSDTO();
		ViewActivity = ac;
	}
	
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		GPS.setLattitude(location.getLatitude());
		GPS.setLongtitude(location.getLongitude());
		if (ViewActivity != null && ViewActivity instanceof BaseActivity) {
			//((KunKunBaseActivity) ViewActivity).updatePosition(GPS, action);
		}
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	
}
