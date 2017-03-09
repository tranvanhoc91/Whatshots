package com.viettel.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.viettel.constants.IntentConstants;
import com.viettel.view.BaseActivity;

public class Call extends BaseActivity{
	public String phone_number = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			phone_number = extras.getString(IntentConstants.INTENT_PHONENUMBER);	
		}
		callAction(phone_number);
		super.onCreate(savedInstanceState);
	}
	public void callAction(String phoneNumber){
		Intent intentCall = new Intent(Intent.ACTION_CALL);
		intentCall.setData(Uri.parse("tel:" + phoneNumber));
		startActivityForResult(intentCall,0);
	}
	public static final int REQUEST_CODE_CALL = 0;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_CODE_CALL && resultCode == RESULT_OK) {
			finish();
		}
		else if(requestCode == REQUEST_CODE_CALL && resultCode == RESULT_CANCELED){
			finish();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
}
