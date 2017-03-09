/**
 * Copyright 2011 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.dto;

import java.io.Serializable;

/**
 *  Bo mau RGB
 *  @author: TamPQ
 *  @version: 1.1
 *  @since: 1.0
 */
@SuppressWarnings("serial")
public class ColorDTO implements Serializable{
	public int RED;
	public int GREEN;
	public int BLUE;
	public int ALPHA;
	
	public ColorDTO(){
		
		//default color is black
		RED = 0;
		GREEN = 0;
		BLUE = 0;
		ALPHA = 255;
	}
}
