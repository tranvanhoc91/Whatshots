/**
 * Copyright 2011 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.utils;

import java.io.Serializable;
import java.util.Vector;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.viettel.common.KunKunInfo;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.dto.RealTimeDTO;
import com.viettel.view.BaseActivity;

/**
 *  xu ly cac su kien xmpp khi chuong trinh inactive (interface)
 *  @author: AnhND
 *  @version: 1.0
 *  @since: 1.0
 */
public class StatusNotificationHandler implements Serializable{
	static final int NOTIFICATION_MESSAGE_ID = 1;//id cua notification post len status notification
	static final int NOTIFICATION_CHAT_ID = 2;//id cua notification post len status notification
	private Object chatMsg;//objec chat, co the la ChatDTO hay GroupChatDTO
	private Object other;//cac loai notification, NoticeDTO
	private int countChatMsg;//so luong message chat
	private int countOther;//so luong notification
	private int lastProcessedAction;//ActionEventConstant cuoi cung da xu ly
	private Vector refObjIDNotice;
	private Vector refObjIDChat;
	
	/**
	 * 
	*  xu ly cac ActionEventConstant realtime
	*  @author: AnhND
	*  @param action
	*  @param bundle
	*  @param appContext
	*  @param activity
	*  @return: void
	*  @throws:
	 */
	public void handleAction(int action, Bundle bundle, BaseActivity activity){
		if (bundle != null){
			
			try{
				RealTimeDTO realTimeDTO = (RealTimeDTO)bundle.getSerializable(IntentConstants.INTENT_DATA);
				if (realTimeDTO == null){
					return;
				}
				
				switch (action){
				
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	private boolean checkExistId(Vector v, String s){		
		for(int i = 0, size = v.size(); i < size; i++){//kiem tra co trung feedId hay kg?
			if(s.equals(v.elementAt(i))){
				return true;
			}
		}		
		return false;
	}
	
	/**
	 * 
	*  tao chuoi thong bao tuong ung du lieu luu lai
	*  @author: AnhND
	*  @return
	*  @return: String
	*  @throws:
	 */
	public String getContentNotification(){
		StringBuffer strBuffer = new StringBuffer();
		if (getCountOther() >= 1 && getCountChatMsg() >= 1) {
			//Co x thong bao va y tin nhan
			strBuffer.append(Constants.STR_THERE_IS);
			strBuffer.append(Constants.STR_WHITE_SPACE);
			
			strBuffer.append(getCountOther());
			strBuffer.append(Constants.STR_WHITE_SPACE);
			
			strBuffer.append(Constants.STR_NOTIFY);
			strBuffer.append(Constants.STR_WHITE_SPACE);
			
			strBuffer.append(Constants.STR_AND);
			strBuffer.append(Constants.STR_WHITE_SPACE);
			
			strBuffer.append(getCountChatMsg());
			strBuffer.append(Constants.STR_WHITE_SPACE);
			
			strBuffer.append(Constants.STR_MSG);
		}else if (getCountOther() >= 1) {
			//co x thong bao
			strBuffer.append(Constants.STR_THERE_IS);
			strBuffer.append(Constants.STR_WHITE_SPACE);
			
			strBuffer.append(getCountOther());
			strBuffer.append(Constants.STR_WHITE_SPACE);
			
			strBuffer.append(Constants.STR_NOTIFY);
		}else if (getCountChatMsg() >= 1) {
			//Co y tin nhan
			strBuffer.append(Constants.STR_THERE_IS);
			strBuffer.append(Constants.STR_WHITE_SPACE);
			
			strBuffer.append(getCountChatMsg());
			strBuffer.append(Constants.STR_WHITE_SPACE);
			
			strBuffer.append(Constants.STR_MSG);
		}
		return strBuffer.toString();
	}
	
		
	private Intent initIntentMessage(){
		Intent notificationIntent = null;
		return notificationIntent;
	}
	
	private Intent initIntentChat(){
		Intent intent = null;
		return intent;
	}
	
	
	/**
	 * 
	*  reset cac gia tri
	*  @author: AnhND
	*  @return: void
	*  @throws:
	 */
	public void reset(){
		refObjIDNotice = null;
		refObjIDChat = null;
		setChatMsg(null);
		setOther(null);
		setCountChatMsg(0);
		setCountOther(0);
		setLastProcessedAction(-1);
	}

	/**
	 * 
	*  cancel notifications
	*  @author: AnhND
	*  @param appContext
	*  @return: void
	*  @throws:
	 */
	public void cancelNotifications() {
		NotificationManager notificationManager = (NotificationManager) KunKunInfo.getInstance().getAppContext().getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancelAll();
	}
	
	void setChatMsg(Object chatMsg) {
		this.chatMsg = chatMsg;
	}

	public Object getChatMsg() {
		return chatMsg;
	}

	void setOther(Object other) {
		this.other = other;
	}

	public Object getOther() {
		return other;
	}

	void setCountChatMsg(int countChatMsg) {
		this.countChatMsg = countChatMsg;
	}

	public int getCountChatMsg() {
		return countChatMsg;
	}

	void setCountOther(int countOther) {
		this.countOther = countOther;
	}

	public int getCountOther() {
		return countOther;
	}

	void setLastProcessedAction(int lastProcessedAction) {
		this.lastProcessedAction = lastProcessedAction;
	}

	public int getLastProcessedAction() {
		return lastProcessedAction;
	}
}
