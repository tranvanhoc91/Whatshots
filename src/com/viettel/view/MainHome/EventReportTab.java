/**
 * Copyright 2009 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.view.MainHome;

import java.util.ArrayList;

import com.viettel.R;
import com.viettel.dto.MemberJoinDTO;
import com.viettel.dto.TabReportEventDTO;
import com.viettel.view.MainHome.MemberList.Whatshosts_Member_Join_List_Adapter.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HOCTB
 *  @version: 1.0
 *  @since: Oct 2, 2012
 */
public class EventReportTab extends Activity {
	View menu;
    View app;
    ListView listEventContent;
    ArrayList<TabReportEventDTO> arrListData = new ArrayList<TabReportEventDTO>();
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = LayoutInflater.from(this);
		app = inflater.inflate(com.viettel.R.layout.layout_tab_report_event_list, null);
		setContentView(app);
		
		
		//list view
		initListView();
		
		final View[] children = new View[] { app };

        // Scroll to app (view[1]) when layout finished.
        int scrollToViewIdx = 1;
	}
	
	
	public void initListView(){
		listEventContent = (ListView) app.findViewById(com.viettel.R.id.listEventContent);
		listEventContent.setDividerHeight(0);
		listEventContent.setDivider(null);
		
		//get data 
		arrListData.add(new TabReportEventDTO(null, "Hoang Quoc Viet", "Live show ki niem 15 nam ca hat cua Dam Vinh Hung mang ten So Phan", 5, 10));
		arrListData.add(new TabReportEventDTO(null, "Hoang Quoc Viet", "Live show ki niem 15 nam ca hat cua Dam Vinh Hung mang ten So Phan", 5, 10));
		arrListData.add(new TabReportEventDTO(null, "Hoang Quoc Viet", "Live show ki niem 15 nam ca hat cua Dam Vinh Hung mang ten So Phan", 5, 10));
		
		//adapter
		listEventContent.setAdapter(new Whatshosts_Tab_Report_Event_Adapter(getApplicationContext(), arrListData));
		listEventContent.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
			}
		});
		
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
