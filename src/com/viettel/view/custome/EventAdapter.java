/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.view.custome;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.viettel.dto.EventDTO;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HocTb
 *  @version: 1.0
 *  @since: Nov 11, 2012
 */
public class EventAdapter extends ArrayAdapter<EventDTO> {
	private Context mContext;
	private ArrayList<EventDTO> eventList;
	
	
	public EventAdapter(Context context, int textViewResourceId,
			List<EventDTO> objects) {
		super(context, textViewResourceId, objects);
		
		mContext = context;
		eventList = (ArrayList<EventDTO>) objects;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater lf = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
		View itemView = lf.inflate(com.viettel.R.layout.event_grid_item, null);
		TextView txtEventName = (TextView) itemView.findViewById(com.viettel.R.id.txtEvenName);
		TextView txtEventTime = (TextView) itemView.findViewById(com.viettel.R.id.txtEventTime);
		
		txtEventName.setText(eventList.get(position+1).getName());
		txtEventTime.setText(eventList.get(position+1).getTime());
		
		return itemView;
	}
}


