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
 *  @since: Oct 3, 2012
 */
public class MemberJoinDTO {
	private Bitmap  bm_Avatar;
	private String txt_Name;
	private String txt_Profile;
	
	
	public MemberJoinDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberJoinDTO(Bitmap bm, String name, String profile ){
		this.bm_Avatar = bm;
		this.txt_Name = name;
		this.txt_Profile = profile;
	}
	
	public Bitmap getBm_Avatar(){
		return this.bm_Avatar;
	}
	
	public String getTxt_Name(){
		return this.txt_Name;
	}
	
	public String getTxt_Profile(){
		return this.txt_Profile;
	}
	
	public void setBm_Avatar(Bitmap bm){
		this.bm_Avatar = bm;
	}
	
	public void setTxt_Name(String name){
		this.txt_Name = name;
	}
	
	public void setTxt_Profile(String profile){
		this.txt_Profile = profile;
	}
}
