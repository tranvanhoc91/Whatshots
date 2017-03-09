package com.viettel.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class ServiceReceiver extends BroadcastReceiver {
	   private String TAG = "d3x";
	   
	   @Override
	   public void onReceive(Context context, Intent intent) {
	      KunKunLog.i(TAG, "ServiceReceiver->onReceive();");
	      ReceiveCall phoneListener = new ReceiveCall();
	      TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
	        telephony.listen(phoneListener, PhoneStateListener.LISTEN_SERVICE_STATE);
	        telephony.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
	   }
	   
	}
