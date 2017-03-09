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
public class FriendDTO {
	private Bitmap  _avatar;
	private String _txtName;
	
	
	public FriendDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public FriendDTO(Bitmap bm, String name){
		this._avatar = bm;
		this._txtName = name;
	}
	
	public Bitmap getAvatar(){
		return this._avatar;
	}
	
	public String getName(){
		return this._txtName;
	}
	
	
	public void setAvatar(Bitmap bm){
		this._avatar = bm;
	}
	
	public void setName(String name){
		this._txtName = name;
	}
}
