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
 *  @since: Jun 16, 2012
 */
public class MainMenuDTO {
	public int type;
	public Bitmap img;
	public String text;
	public static final int MENU_TYPE_AVATAR =0;
	public static final int MENU_TYPE_TEXT_SEPARATOR =1;
	public static final int MENU_TYPE_ICON_TEXT =2;
	public static final int MENU_TYPE_TEXTVIEW =3;
	
	
	
	/**
	 * @param type
	 * @param img
	 * @param text
	 */
	public MainMenuDTO(int type, Bitmap img, String text) {
		super();
		this.type = type;
		this.img = img;
		this.text = text;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the img
	 */
	public Bitmap getImg() {
		return img;
	}
	/**
	 * @param img the img to set
	 */
	public void setImg(Bitmap img) {
		this.img = img;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
}
