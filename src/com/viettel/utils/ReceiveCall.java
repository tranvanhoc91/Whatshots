package com.viettel.utils;

import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;

public class ReceiveCall extends PhoneStateListener{

	   private String TAG = "d3x";
	   
	   @Override
	   public void onCallStateChanged(int state,String incomingNumber){
	        switch(state){
	        case TelephonyManager.CALL_STATE_IDLE:
	              KunKunLog.i(TAG, "MyPhoneStateListener->onCallStateChanged() -> CALL_STATE_IDLE "+incomingNumber);
	              break;
	        case TelephonyManager.CALL_STATE_OFFHOOK:
	              KunKunLog.i(TAG, "MyPhoneStateListener->onCallStateChanged() -> CALL_STATE_OFFHOOK "+incomingNumber);
	              break;
	        case TelephonyManager.CALL_STATE_RINGING:
	              KunKunLog.i(TAG, "MyPhoneStateListener->onCallStateChanged() -> CALL_STATE_RINGING "+incomingNumber);
	              break;
	        default:
	           KunKunLog.i(TAG, "MyPhoneStateListener->onCallStateChanged() -> default -> "+Integer.toString(state));
	           break;
	        }
	   }
	   
	   @Override
	   public void onServiceStateChanged (ServiceState serviceState){
	      switch(serviceState.getState()){
	           case ServiceState.STATE_IN_SERVICE:
	                KunKunLog.i(TAG, "MyPhoneStateListener->onServiceStateChanged() -> STATE_IN_SERVICE");
	                serviceState.setState(ServiceState.STATE_IN_SERVICE);
	                break;
	           case ServiceState.STATE_OUT_OF_SERVICE:
	                KunKunLog.i(TAG, "MyPhoneStateListener->onServiceStateChanged() -> STATE_OUT_OF_SERVICE");
	                serviceState.setState(ServiceState.STATE_OUT_OF_SERVICE);
	                break;
	           case ServiceState.STATE_EMERGENCY_ONLY:
	              KunKunLog.i(TAG, "MyPhoneStateListener->onServiceStateChanged() -> STATE_EMERGENCY_ONLY");
	              serviceState.setState(ServiceState.STATE_EMERGENCY_ONLY);
	              break;
	           case ServiceState.STATE_POWER_OFF:
	              KunKunLog.i(TAG, "MyPhoneStateListener->onServiceStateChanged() -> STATE_POWER_OFF");
	              serviceState.setState(ServiceState.STATE_POWER_OFF);
	              break;
	           default:
	              KunKunLog.i(TAG, "MyPhoneStateListener->onServiceStateChanged() -> default -> "+Integer.toString(serviceState.getState()));
	              break;
	        }
	   }
}
