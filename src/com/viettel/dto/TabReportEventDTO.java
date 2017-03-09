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
 *  @since: Oct 4, 2012
 */
public class TabReportEventDTO {
	private Bitmap bm_Avatar;
	private String txt_Username;
	private String txt_Content;
	private	int num_comment;
	private int num_like;
	
	public TabReportEventDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public TabReportEventDTO(Bitmap bm, String username, String content, int num_comment, int num_like) {
		this.bm_Avatar = bm;
		this.txt_Username = username;
		this.txt_Content = content;
		this.num_comment = num_comment;
		this.num_like = num_like;
	}
	
	
	public Bitmap getBm_Avatar(){
		return this.bm_Avatar;
	}
	
	public String getTxt_Username(){
		return this.txt_Username;
	}
	
	public String getTxt_Content(){
		return this.txt_Content;
	}
	
	
	public int getNum_Comment(){
		return this.num_comment;
	}
	
	public int getNum_Like(){
		return this.num_like;
	}
	
	
	public void setBm_Avatar(Bitmap bm){
		this.bm_Avatar = bm;
	}
	
	public void setTxt_Username(String username){
		this.txt_Username = username;
	}
	
	public void setTxt_Content(String content){
		this.txt_Content = content;
	}
	
	public void setNum_Comment(int num){
		this.num_comment = num;
	}
	
	public void setNum_Like(int num){
		this.num_like = num;
	}
	
	
	
	
	
	
}
