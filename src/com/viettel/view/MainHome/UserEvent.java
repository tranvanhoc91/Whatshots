package com.viettel.view.MainHome;
import java.util.ArrayList;
import java.util.HashMap;

import com.korovyansk.android.slideout.SlideoutActivity;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;
import com.viettel.view.common.TabUtils;
import com.whathots.menu.MenuActivity;

import android.R;
import android.app.Activity;
import android.app.LauncherActivity;
import android.app.TabActivity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HocTB
 *  @version: 1.0
 *  @since: Oct 2, 2012
 */
public class UserEvent extends TabActivity implements OnTabChangeListener {
	View menu;
    View app;
    Handler handle = new Handler();
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = LayoutInflater.from(this);
		app = inflater.inflate(com.viettel.R.layout.layout_detail_event, null);
		setContentView(app);
		
		//actionbar
		ActionBar actionBar = (ActionBar) app.findViewById(com.viettel.R.id.actionbar);
		actionBar.setTitle("Hoang Quoc Viet - Su kien");
		
		initTab();
		
		final View[] children = new View[] { app };
        int scrollToViewIdx = 1;
	}


	
	private void initTab() {
		TabHost tabHost = (TabHost) app.findViewById(R.id.tabhost);
		ArrayList<String> titleTabs= new ArrayList<String>();
		titleTabs.add(getResources().getString(com.viettel.R.string.JOIN));
		titleTabs.add(getResources().getString(com.viettel.R.string.INVITED));
		titleTabs.add(getResources().getString(com.viettel.R.string.TRACKING));
		
		ArrayList<Class<?>> classTabs = new ArrayList<Class<?>>();
		classTabs.add(UserEventJoinTab.class);
		classTabs.add(UserEventInvitedTab.class);
		classTabs.add(UserEventTrackingTab.class);
		
 		TabUtils.initTab(this, tabHost, titleTabs, classTabs);
		tabHost.setOnTabChangedListener(this);
	}

	

	@Override
	public void onTabChanged(String tag) {
		
	}
	
}
