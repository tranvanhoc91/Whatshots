/**
  * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.view.MainHome;

import java.util.ArrayList;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;
import com.viettel.R;
import com.viettel.dto.TabReportEventDTO;
import com.viettel.view.MainHome.DetailReportEvent.Whatshosts_Tab_Report_Event_Adapter;
import com.viettel.view.MainHome.DetailReportEvent.Whatshosts_Tab_Report_Event_Adapter.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HocTB
 *  @version: 1.0
 *  @since: Nov 13, 2012
 */
public class UserPage extends Activity {
	View app;
	ListView listEventContent;
    ArrayList<TabReportEventDTO> arrListData = new ArrayList<TabReportEventDTO>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutInflater lf = LayoutInflater.from(this);
		app = lf.inflate(com.viettel.R.layout.layout_user_page, null);
		setContentView(app);
		

        ActionBar actionBar = (ActionBar) app.findViewById(R.id.actionbar);
        actionBar.setTitle("Hoang Quoc Viet");
        
        Action readMsgAction = new IntentAction(this,new Intent(this, EventDetail.class), R.drawable.ic_friend2);
        Action settingAction = new IntentAction(this,new Intent(this, SettingConnection.class), R.drawable.ic_setting);
        actionBar.addAction(readMsgAction);
        actionBar.addAction(settingAction);
        
        listEventContent = (ListView) app.findViewById(com.viettel.R.id.listEventContent);
        listEventContent.setDivider(null);
		listEventContent.setDividerHeight(0);
		
		//get data 
		arrListData.add(new TabReportEventDTO(null, "Hoang Quoc Viet", "Live show ki niem 15 nam ca hat cua Mr.Dam", 5, 10));
		arrListData.add(new TabReportEventDTO(null, "Hoang Quoc Viet", "Live show ki niem 15 nam ca hat cua Dam Vinh Hung mang ten SO Phan", 5, 10));
		arrListData.add(new TabReportEventDTO(null, "Hoang Quoc Viet", "Live show ki niem 15 nam ca hat cua Dam Vinh Hung mang ten SO Phan", 5, 10));
		
		//adapter
		listEventContent.setAdapter(new Whatshosts_Tab_Report_Event_Adapter(getApplicationContext(), arrListData));
		listEventContent.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
			}
		});
		
		
        final View[] children = new View[] { app };
	}
	
	
	//Create Adapter for listview location
			public class Whatshosts_Tab_Report_Event_Adapter extends BaseAdapter{
				private Context mContext;
				private LayoutInflater lf;
				private ArrayList<TabReportEventDTO> listData;
				class ViewHolder{
					ImageView img_Avatar;
					TextView txt_Username, txt_Content, txt_num_comment, txt_num_like;
				}
				
				
				/**
				 * Constructor
				 */
				public Whatshosts_Tab_Report_Event_Adapter(Context context) {
					lf = LayoutInflater.from(context);
				}
				
				
				/**
				 * Constructor
				 */
				public Whatshosts_Tab_Report_Event_Adapter(Context context, ArrayList<TabReportEventDTO> list) {
					//mContext		=	context;
					lf = LayoutInflater.from(context);
					listData	=	list;
				}
				
				
				@Override
				public int getCount() {
					// TODO Auto-generated method stub
					if (listData != null) {
						return listData.size();
					}
					else{
						return 0;
					}
				}

				@Override
				public Object getItem(int position) {
					// TODO Auto-generated method stub
					return listData.get(position);
				}

				@Override
				public long getItemId(int position) {
					// TODO Auto-generated method stub
					return position;
				}

				
				/**
				*  Mo ta chuc nang cua ham
				*  @author: HocTb
				*  @param viewHolder
				*  @param position
				*  @return: void
				*  @throws: 
				*/
				private void publicView(ViewHolder view, int position) {
					TabReportEventDTO dto = listData.get(position);
					//view.icon_Place.setImageBitmap(dto.getBm_place());
		        	view.txt_Username.setText(dto.getTxt_Username());
		        	view.txt_Content.setText(dto.getTxt_Content());
		        	view.txt_num_comment.setText(""+dto.getNum_Comment());
		        	view.txt_num_like.setText(""+dto.getNum_Like());
				}
				
				
				@Override
				public View getView(int position, View convertView, ViewGroup parent) {
					ViewHolder viewHolder;
					
					if (convertView == null) {
		                convertView = lf.inflate(R.layout.layout_tab_report_event_item, null);
		                viewHolder = new ViewHolder();
		                viewHolder.img_Avatar 			= (ImageView) convertView.findViewById(R.id.img_Avatar);
		                viewHolder.txt_Username 		= (TextView) convertView.findViewById(R.id.txtUsername);
		                viewHolder.txt_Content 			= (TextView) convertView.findViewById(R.id.txtUsername);
		                viewHolder.txt_num_comment 		= (TextView) convertView.findViewById(R.id.num_comment);
		                viewHolder.txt_num_like 		= (TextView) convertView.findViewById(R.id.num_like);
		                
		                convertView.setTag(viewHolder);
		            } else {
		            	viewHolder = (ViewHolder) convertView.getTag();
		            }
		            publicView(viewHolder,position);
		            return convertView;
				}
			}//End Whatshosts_Member_Join_List_Adapter
	
}
