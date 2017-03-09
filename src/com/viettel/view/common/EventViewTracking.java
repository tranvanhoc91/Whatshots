/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.view.common;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.viettel.R;
import com.viettel.dto.EventDTO;
import com.viettel.view.common.EventViewInvited.Event_Invited_Addapter;
import com.viettel.view.common.EventViewInvited.Event_Invited_Addapter.ViewHolder;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HocTB
 *  @version: 1.0
 *  @since: Nov 12, 2012
 */
public class EventViewTracking {
	public EventViewTracking() {
		// TODO Auto-generated constructor stub
	}
	
	public static void initListView(Context context, ListView listView,ArrayList<EventDTO> eventList, String prefix, int numItems, int layout) {
        String[] arr = new String[numItems];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = prefix + (i + 1);
        }
        
        listView.setAdapter(new ArrayAdapter<String>(context, layout, arr));
        listView.setAdapter((new EventViewTracking()).new Event_Tracking_Addapter(context, eventList));
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = view.getContext();
                
                String msg = "item[" + position + "]=" + parent.getItemAtPosition(position);
                Toast.makeText(context, msg, 1000).show();
                System.out.println(msg);
            }
		});
    }
	
	
	public class Event_Tracking_Addapter extends BaseAdapter  {
        private LayoutInflater mInflater;
        private ArrayList<EventDTO> list;
        private Context mContext;
        
        class ViewHolder {
            TextView txtName;
            TextView txtTime;
            TextView txtNumReport, txtNumUpdate;
            ImageView img_Avatar;
        }

        public Event_Tracking_Addapter(Context context) {
        	super();
            mInflater = LayoutInflater.from(context);    
            mContext = context;

        }
        public Event_Tracking_Addapter(Context context,ArrayList<EventDTO> list) {
            mInflater = LayoutInflater.from(context);                  
            this.list = list;
            mContext = context;
        }
        
        public int getCount() {
            return list.size();
        }

        public void publicView(ViewHolder view,int position){
        	EventDTO dto = list.get(position);
        	view.txtName.setText(dto.getName());
        	view.txtTime.setText(dto.getTime());
        	view.txtNumReport.setText(""+dto.getNumReport()+ " " + mContext.getResources().getString(R.string.report));
        	view.txtNumReport.setText(""+dto.getNumReport()+ " " + mContext.getResources().getString(R.string.report));
        	view.img_Avatar.setBackgroundResource(dto.getThumbnail());
        }

        public long getItemId(int position) {
            return position;
        }

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.wh_event_tracking_item, null);
                holder = new ViewHolder();
                holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
                holder.txtTime = (TextView) convertView.findViewById(R.id.txtTime);
                holder.txtNumReport = (TextView) convertView.findViewById(R.id.txtNumReport);
                holder.txtNumUpdate = (TextView) convertView.findViewById(R.id.txtNumUpdate);
                holder.img_Avatar =(ImageView) convertView.findViewById(R.id.img_Avatar);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            publicView(holder,position);
            return convertView;
		}
    }
}
