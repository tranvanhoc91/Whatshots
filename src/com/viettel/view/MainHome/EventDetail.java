/**
 * Copyright 2009 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.view.MainHome;
import java.util.ArrayList;

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
public class EventDetail extends TabActivity implements OnTabChangeListener {
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
		actionBar.setTitle("Live show Che Linh : 30 nam tai xuat...");
		
		final Action newEventAction = new IntentAction(this,new Intent(this, DetailReportEvent.class), com.viettel.R.drawable.share_icon);
		actionBar.addAction(newEventAction);
		 
 		
		initTab();
		
		final View[] children = new View[] { app };
        int scrollToViewIdx = 1;
	}


	
	
	/**
	*  Mo ta chuc nang cua ham
	*  @author: HocTB
	*  @return: void
	*  @throws: 
	*/
	private void initTab() {
		TabHost tabHost = (TabHost) app.findViewById(R.id.tabhost);
		
		ArrayList<String> titleTabs= new ArrayList<String>();
		titleTabs.add(getResources().getString(com.viettel.R.string.INFO));
		titleTabs.add(getResources().getString(com.viettel.R.string.REPORT));
		titleTabs.add(getResources().getString(com.viettel.R.string.PHOTO));
		
		ArrayList<Class<?>> classTabs = new ArrayList<Class<?>>();
		classTabs.add(EventInfoTab.class);
		classTabs.add(EventReportTab.class);
		classTabs.add(EventPhotoTab.class);
		
 		TabUtils.initTab(this, tabHost, titleTabs, classTabs);
		tabHost.setOnTabChangedListener(this);
	}


	@Override
	public void onTabChanged(String tag) {
		
	}
	
}
