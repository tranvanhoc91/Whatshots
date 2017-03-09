/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.view.custome;

import java.util.ArrayList;

import com.viettel.R;
import com.viettel.dto.MainHomeDTO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HocTB
 *  @version: 1.0
 *  @since: Nov 6, 2012
 */
public class Whatshots_Main_Addapter extends BaseAdapter {
	 private LayoutInflater mInflater;
     private ArrayList<MainHomeDTO> list;
     class ViewHolder {
         TextView txt_Name;
         TextView txt_Detail;
         TextView txt_Time;
         ImageView img_Avatar;
     }

     public Whatshots_Main_Addapter(Context context) {
     	super();
         mInflater = LayoutInflater.from(context);                  

     }
     public Whatshots_Main_Addapter(Context context,ArrayList<MainHomeDTO> list) {
         mInflater = LayoutInflater.from(context);                  
         this.list = list;
     }
     
     public int getCount() {
         return list.size();
     }

     public void publicView(ViewHolder view,int position){
     	MainHomeDTO dto = list.get(position);
     	view.txt_Name.setText(dto.getTxt_Name());
     	view.txt_Detail.setText(dto.getTxt_Detail());
     	view.txt_Time.setText(dto.getTxt_Time());
     	
     }

     public long getItemId(int position) {
         return position;
     }

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
     ViewHolder holder;

     if (convertView == null) {
         convertView = mInflater.inflate(R.layout.wh_main_page_item, null);
         holder = new ViewHolder();
         holder.txt_Name = (TextView) convertView.findViewById(R.id.txtName);
         holder.txt_Detail = (TextView) convertView.findViewById(R.id.txtDetail);
         holder.txt_Time = (TextView) convertView.findViewById(R.id.txtTime);
         holder.img_Avatar =(ImageView) convertView.findViewById(R.id.img_Avatar);
         convertView.setTag(holder);
     } else {
         holder = (ViewHolder) convertView.getTag();
     }
     publicView(holder,position);
     return convertView;
	}
}
