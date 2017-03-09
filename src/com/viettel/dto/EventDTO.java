/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.dto;

import android.graphics.Bitmap;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HocTB
 *  @version: 1.0
 *  @since: Nov 11, 2012
 */
public class EventDTO {
	private String _name;
	private String _place;
	private String _time;
	private int _numJoin;
	private int  _thumb;
	private String _whoInvite;
	private int _numReport;
	private int _numUpdate;
	
	
	
	public EventDTO(int thumb,String name, String place, String time, int numJoin,String who_invite, int num_report,int num_update){
		this._name = name;
		this._place = place;
		this._time = time;
		this._numJoin = numJoin;
		this._thumb = thumb;
		this._whoInvite = who_invite;
		this._numReport = num_report;
		this._numUpdate = num_update;
	}
	
	public void setName(String name){
		this._name = name;
	}
	
	public void setPlace(String place){
		this._place = place;
	}
	
	public void setTime(String time){
		this._time = time;
	}
	
	public void setNumJoin(int num){
		this._numJoin = num;
	}
	
	public void setThumbnail(int resId){
		this._thumb = resId;
	}
	
	public void setWhoInvite(String name){
		this._whoInvite = name;
	}
	
	public void setNumReport(int num){
		this._numReport = num;
	}
	
	public void setNumUpdate(int num){
		this._numUpdate = num;
	}
	
	
	
	public String getName(){
		return this._name;
	}
	
	public String getTime(){
		return this._time;
	}
	
	public String getPlace(){
		return this._place;
	}
	
	public int getNumJoin(){
		return this._numJoin;
	}
	
	public int getThumbnail(){
		return this._thumb;
	}
	
	public String getWhoInvite(){
		return this._whoInvite;
	}
	
	public int getNumReport(){
		return this._numReport;
	}
	
	public int getNumUpdate(){
		return this._numUpdate;
	}
}
