/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.view.common;

import java.util.ArrayList;
import java.util.HashMap;

import com.viettel.view.MainHome.UserEventInvitedTab;
import com.viettel.view.MainHome.UserEventJoinTab;
import com.viettel.view.MainHome.UserEventTrackingTab;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HocTB
 *  @version: 1.0
 *  @since: Nov 12, 2012
 */
public class TabUtils {
	private static Context mContext;
	private static TabHost tabHost;
	static ArrayList<String> _titleList;
	static ArrayList<Class<?>> _classList;
	
	
	public TabUtils() {
		
	}
	
	/*public static void registerTab(Context context, TabHost tab){
		TabUtils.mContext = context;
		TabUtils.tabHost = tab;
	}*/
	
	public static void initTab(Context context,TabHost tab, ArrayList<String> titleTabList, ArrayList<Class<?>> classTabList) {
		TabUtils.mContext = context;
		TabUtils.tabHost = tab;
		_titleList = titleTabList;
		_classList = classTabList;
		
		tabHost.setup();
		tabHost.getTabWidget().setDividerDrawable(com.viettel.R.drawable.im_border_white);
		for (int i = 0; i < titleTabList.size(); i++) {
			setupTab(new TextView(context),titleTabList.get(i),classTabList.get(i));
		}
		
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	
	public static void setupTab(View view, final String title, final Class<?> cls) {
		View tabView = TabUtils.createTabView(title);
		TabHost.TabSpec spec;   
		Intent intent;
		intent = new Intent().setClass(mContext, _classList.get(0));
		intent = new Intent().setClass(mContext, cls);
		
		
		//register tab
		TabSpec tabspec = tabHost.newTabSpec(title).setIndicator(tabView).setContent(intent);
		tabHost.addTab(tabspec);
	}
	
	
	private static View createTabView(String text){
		View view = LayoutInflater.from(mContext).inflate(com.viettel.R.layout.tabs_bg, null);
		//textview-display text of tab
		TextView textview = (TextView) view.findViewById(com.viettel.R.id.tabsText);
		textview.setText(text);
		return view;
	}
}


