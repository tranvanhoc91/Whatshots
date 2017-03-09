/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.view.MainHome;

import com.markupartist.android.widget.ActionBar;
import com.viettel.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HocTb
 *  @version: 1.0
 *  @since: Nov 14, 2012
 */
public class SettingConnection extends Activity {
	View app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutInflater lf = LayoutInflater.from(this);
		app = lf.inflate(com.viettel.R.layout.layout_setting_connected, null);
		setContentView(app);
		
		ActionBar actionBar = (ActionBar) app.findViewById(R.id.actionbar);
        actionBar.setTitle(getResources().getString(R.string.SETTING_CONNECTION));
	}
}
