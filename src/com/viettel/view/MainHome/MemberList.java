/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.view.MainHome;

import java.util.ArrayList;

import com.markupartist.android.widget.ActionBar;
import com.viettel.R;
import com.viettel.dto.MemberJoinDTO;
import com.viettel.dto.PlaceDTO;
import com.viettel.place.GPSTracker;
import com.viettel.viettellib.network.http.ConnectionDetector;
import com.viettel.view.MainHome.SelectEventPlace.Whatshosts_Search_Place_Adapter;
import com.viettel.view.MainHome.SelectEventPlace.Whatshosts_Search_Place_Adapter.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HocTB
 *  @version: 1.0
 *  @since: Oct 3, 2012
 */
public class MemberList extends Activity {
	View menu;
    View app;
	ListView listView;
	ArrayList<MemberJoinDTO> memberDataList = new ArrayList<MemberJoinDTO>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutInflater lf = LayoutInflater.from(this);
		app = lf.inflate(R.layout.layout_member_list, null);
		setContentView(app);
		
		//init actionBar
		ActionBar actionBar = (ActionBar) app.findViewById(R.id.actionbar);
		actionBar.setTitle(getResources().getString(R.string.MEMBER_JOIN_LIST));
		
		//listview
		listView = (ListView) app.findViewById(R.id.listView);
		listView.setDividerHeight(0);
		
		
		memberDataList.add(new MemberJoinDTO(null, "Hoang Quoc Viet", "Cong ty Viettel Viet Nam"));
		memberDataList.add(new MemberJoinDTO(null, "Hoang Quoc Viet", "Cong ty Viettel Viet Nam"));
		memberDataList.add(new MemberJoinDTO(null, "Hoang Quoc Viet", "Cong ty Viettel Viet Nam"));
		memberDataList.add(new MemberJoinDTO(null, "Hoang Quoc Viet", "Cong ty Viettel Viet Nam"));
		
		listView.setAdapter(new Whatshosts_Member_Join_List_Adapter(this, memberDataList));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int postion,long id) {
				
			}
		});
		
		final View[] children = new View[] { app };
	}
	
	
	
	//Create Adapter for listview location
	public class Whatshosts_Member_Join_List_Adapter extends BaseAdapter{
		private Context mContext;
		private LayoutInflater lf;
		private ArrayList<MemberJoinDTO> arrListMember;
		class ViewHolder{
			ImageView img_Avatar;
			TextView txt_Name;
			TextView txt_Profile;
		}
		
		
		/**
		 * Constructor
		 */
		public Whatshosts_Member_Join_List_Adapter(Context context) {
			lf = LayoutInflater.from(context);
		}
		
		
		/**
		 * Constructor
		 */
		public Whatshosts_Member_Join_List_Adapter(Context context, ArrayList<MemberJoinDTO> list) {
			//mContext		=	context;
			lf = LayoutInflater.from(context);
			arrListMember	=	list;
		}
		
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if (arrListMember != null) {
				return arrListMember.size();
			}
			else{
				return 0;
			}
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return arrListMember.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		
		/**
		*  Mo ta chuc nang cua ham
		*  @author: BangHN
		*  @param viewHolder
		*  @param position
		*  @return: void
		*  @throws: 
		*/
		private void publicView(ViewHolder view, int position) {
			MemberJoinDTO dto = arrListMember.get(position);
			//view.icon_Place.setImageBitmap(dto.getBm_place());
        	view.txt_Name.setText(dto.getTxt_Name());
        	view.txt_Profile.setText(dto.getTxt_Profile());
		}
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			
			if (convertView == null) {
                convertView = lf.inflate(R.layout.layout_member_item, null);
                viewHolder = new ViewHolder();
                viewHolder.img_Avatar 			= (ImageView) convertView.findViewById(R.id.img_Avatar);
                viewHolder.txt_Name 			= (TextView) convertView.findViewById(R.id.txtName);
                viewHolder.txt_Profile 			= (TextView) convertView.findViewById(R.id.txtProfile);
                convertView.setTag(viewHolder);
            } else {
            	viewHolder = (ViewHolder) convertView.getTag();
            }
            publicView(viewHolder,position);
            return convertView;
		}
	}//End Whatshosts_Member_Join_List_Adapter
}
