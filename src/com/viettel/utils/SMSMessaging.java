package com.viettel.utils;

import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;

import com.viettel.view.BaseActivity;

public class SMSMessaging {
	private String _phoneNumber;
	private String _message;
	private BaseActivity _MyActivity;
	
	/*
	 * constructor with 3 parameter 
	 */
	public SMSMessaging(String phoneNumber, String message, BaseActivity baseActi){
		set_phoneNumber(phoneNumber);
		set_message(message);
		set_MyActivity(baseActi);
	}
	public boolean check_phoneNumber(){
		/*
		 * after update
		 */
		return true;
	}
	public final boolean check_meessage(){
		/*
		 * after update
		 */
		return true;
	}
	public boolean check_sendSMS(){
		/*
		 * After update
		 */
		if(check_phoneNumber() && check_meessage())
			return true;
		else
			return false;
	}
	
	/*
	 * Send sms with 3 parameter: phoneNumber, message, BaseActivity
	 */
	public boolean sendSMS(String phoneNumber, String message, BaseActivity MyActivity)
    {        
		boolean kq = false;
		if(check_sendSMS()){
	        PendingIntent pi = PendingIntent.getActivity(MyActivity, 0,
	        new Intent(MyActivity, SMSMessaging.class), 0);                
	        SmsManager sms = SmsManager.getDefault();
	        sms.sendTextMessage(phoneNumber, null, message, pi, null);
	        kq = true;
		}
		return kq;
    }
	
	/*
	 * Send sms with attribute of SMSMessaging object
	 */
	public boolean sendSMS(){
		boolean kq = false;
		try{
			if(check_sendSMS()){
				PendingIntent pi = PendingIntent.getActivity(_MyActivity, 0,new Intent(_MyActivity, SMSMessaging.class), 0);
				SmsManager sms = SmsManager.getDefault();
				sms.sendTextMessage(_phoneNumber, null, _message, pi, null);
				kq = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return kq;
	}

	public void set_phoneNumber(String _phoneNumber) {
		this._phoneNumber = _phoneNumber;
	}

	public String get_phoneNumber() {
		return _phoneNumber;
	}

	public void set_message(String _message) {
		this._message = _message;
	}

	public String get_message() {
		return _message;
	}
	public void set_MyActivity(BaseActivity _MyActivity) {
		this._MyActivity = _MyActivity;
	}
	public BaseActivity get_MyActivity() {
		return _MyActivity;
	}
}
