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
 *  @since: Sep 14, 2012
 */
public class PlaceDTO {
	Bitmap bm_place;
	String txt_name;
	String txt_address;
	
	/**
	 * Constructor
	 */
	public PlaceDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public PlaceDTO(Bitmap icon, String name, String address){
		this.bm_place	=	icon;
		this.txt_name		=	name;
		this.txt_address	=	address;
	}
	
	public void setBm_place(Bitmap bm){
		bm_place	=	bm;
	}
	
	public Bitmap getBm_place(){
		return bm_place;
	}
	
	public void setTxt_Name(String name){
		txt_name = name;
	}
	
	public String getTxt_Name(){
		return txt_name;
	}
	
	public void setTxt_Address(String address){
		txt_address	=	address;
	}
	
	public String getTxt_Address(){
		return txt_address;
	}
}
