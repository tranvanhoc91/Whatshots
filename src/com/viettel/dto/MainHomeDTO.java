/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.dto;

import android.graphics.Bitmap;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: VietHQ
 *  @version: 1.0
 *  @since: Jun 9, 2012
 */
public class MainHomeDTO {
	Bitmap bm_Avatar;
	String txt_Name;
	String txt_Detail;
	String txt_Time;
	/**
	 * @param bm_Avatar
	 * @param txt_Name
	 * @param txt_Detail
	 * @param txt_Time
	 */
	public MainHomeDTO(Bitmap bm_Avatar, String txt_Name, String txt_Detail,
			String txt_Time) {
		super();
		this.bm_Avatar = bm_Avatar;
		this.txt_Name = txt_Name;
		this.txt_Detail = txt_Detail;
		this.txt_Time = txt_Time;
	}
	/**
	 * @return the bm_Avatar
	 */
	public Bitmap getBm_Avatar() {
		return bm_Avatar;
	}
	/**
	 * @param bm_Avatar the bm_Avatar to set
	 */
	public void setBm_Avatar(Bitmap bm_Avatar) {
		this.bm_Avatar = bm_Avatar;
	}
	/**
	 * @return the txt_Name
	 */
	public String getTxt_Name() {
		return txt_Name;
	}
	/**
	 * @param txt_Name the txt_Name to set
	 */
	public void setTxt_Name(String txt_Name) {
		this.txt_Name = txt_Name;
	}
	/**
	 * @return the txt_Detail
	 */
	public String getTxt_Detail() {
		return txt_Detail;
	}
	/**
	 * @param txt_Detail the txt_Detail to set
	 */
	public void setTxt_Detail(String txt_Detail) {
		this.txt_Detail = txt_Detail;
	}
	/**
	 * @return the txt_Time
	 */
	public String getTxt_Time() {
		return txt_Time;
	}
	/**
	 * @param txt_Time the txt_Time to set
	 */
	public void setTxt_Time(String txt_Time) {
		this.txt_Time = txt_Time;
	}
	
	
}
