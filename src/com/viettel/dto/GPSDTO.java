package com.viettel.dto;

import java.io.Serializable;


@SuppressWarnings("serial")
public class GPSDTO implements Serializable{
	// lat, long
	private double longitude = -1;
	private double latitude = -1;
	private boolean isShowNoticeGPS = true;
	/**
	 * @return the isShowNoticeGPS
	 */
	public boolean isShowNoticeGPS() {
		return isShowNoticeGPS;
	}

	/**
	 * @param isShowNoticeGPS the isShowNoticeGPS to set
	 */
	public void setShowNoticeGPS(boolean isShowNoticeGPS) {
		this.isShowNoticeGPS = isShowNoticeGPS;
	}
	// info cell
	public String cellId;
	public String MNC;
	public String LAC;
	public String cellType;
	
	public GPSDTO(){
	}
	
	public double getLongtitude(){
		return longitude;
	}
	public double getLatitude(){
		return latitude;
	}
		
	public void setLongtitude(double longtitude){
		this.longitude = longtitude;
	}
	public void setLattitude(double lattitude){
		this.latitude = lattitude;
	}
}
