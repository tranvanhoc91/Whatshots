/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.view.MainHome;

import com.viettel.R;
import com.viettel.view.common.ViewUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HocTB
 *  @version: 1.0
 *  @since: Nov 12, 2012
 */
public class UserEventJoinTab extends Activity {
	View app;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 LayoutInflater inflater = LayoutInflater.from(this);
		 app = inflater.inflate(R.layout.whatshots_listview, null);
		 setContentView(app);
	        
		 ListView listView = (ListView) app.findViewById(R.id.listView);
		 listView.setDividerHeight(0);
		 ViewUtils.initListView(this, listView, "Item ", 30, android.R.layout.simple_list_item_1);

		 final View[] children = new View[] { app };
	}
}
