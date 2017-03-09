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
 *  @since: Nov 14, 2012
 */
public class UnfamiliarFriendDTO {
	private Bitmap  _avatar;
	private String _txtName;
	private String _txtAddress;
	private int _numCommonFriend;
	
	
	public UnfamiliarFriendDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public UnfamiliarFriendDTO(Bitmap bm, String name, String address, int num){
		this._avatar = bm;
		this._txtName = name;
		this._txtAddress = address;
		this._numCommonFriend = num;
	}
	
	public Bitmap getAvatar(){
		return this._avatar;
	}
	
	public String getName(){
		return this._txtName;
	}
	
	public String getAddress(){
		return this._txtAddress;
	}
	
	public int getNumCommonFriend(){
		return this._numCommonFriend;
	}
	
	
	
	public void setAvatar(Bitmap bm){
		this._avatar = bm;
	}
	
	public void setName(String name){
		this._txtName = name;
	}
	
	public void setAddress(String address){
		this._txtAddress = address;
	}
	
	public void setNumCommonFriend(int num){
		this._numCommonFriend = num;
	}
}

