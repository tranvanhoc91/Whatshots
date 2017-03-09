/**
 * Copyright 2011 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.dto;

import java.io.Serializable;

import org.jivesoftware.smack.packet.Message;

/**
 *  Realtime DTO
 *  @author: BangHN
 *  @version: 1.0
 *  @since: Jun 4, 2011
 */
@SuppressWarnings("serial")
public class RealTimeDTO implements Serializable{
	public boolean isNotify = true;
	public String fromUserId = "";
	
	/**
	 * 
	*  parse xmpp
	*  @author: AnhND
	*  @param msg
	*  @return: void
	*  @throws:
	 */
	public void fromXMPPMessage(Message msg){
		Object obj = msg.getProperty("isNotify");
		if (obj != null) {
			isNotify = ((Boolean)obj).booleanValue();
		}
		
		String from = msg.getFrom();
		if (from != null && from.length() > 0) {
			//fromUserId = StringUtils.parseName(from);	
			fromUserId = from.split("@")[0];	
		}
	}
	
	public void fromXMPPNewChatOwnerMessage(Message msg){
		Object obj = msg.getProperty("isNotify");
		if (obj != null) {
			isNotify = ((Boolean)obj).booleanValue();
		}
		
		String from = msg.getFrom();
		if (from != null && from.length() > 0) {
			//fromUserId = StringUtils.parseName(from);	
			fromUserId = from.split("@")[0];	
		}
	}
	public void fromXMPPNewChatOthersMessage(Message msg){
		Object obj = msg.getProperty("isNotify");
		if (obj != null) {
			isNotify = ((Boolean)obj).booleanValue();
		}
		
		String from = msg.getFrom();
		if (from != null && from.length() > 0) {
			//fromUserId = StringUtils.parseName(from);	
			fromUserId = from.split("@")[0];	
		}
	}
}
