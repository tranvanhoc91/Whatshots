/**
 * Copyright 2009 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.view.MainHome;

import com.viettel.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HocTB
 *  @version: 1.0
 *  @since: Oct 2, 2012
 */
public class EventPhotoTab extends Activity {
	View app;
	GridView gridView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = LayoutInflater.from(this);
		app = inflater.inflate(com.viettel.R.layout.layout_tab_photo_event, null);
		setContentView(app);
		
		
		gridView = (GridView) app.findViewById(R.id.gridView);
		
		//data
		int[] resImgId = new int[] {R.drawable.test2,R.drawable.test2, 
									R.drawable.test2,R.drawable.test2,
									R.drawable.test2,R.drawable.test2};
		
		gridView.setAdapter(new PhotoAdapter(this, resImgId));
		
		final View[] children = new View[] { app };
	}
	
	
	
	/**
	 * 
	 *  Mo ta muc dich cua lop (interface)
	 *  @author: HocTb
	 *  @version: 1.0
	 *  @since: Nov 10, 2012
	 */
	public class PhotoAdapter extends BaseAdapter{
		Context mContext;
		int[] resourceImageId;
		
		/**
		 * 
		 */
		public PhotoAdapter(Context ct, int[] resImgId) {
			// TODO Auto-generated constructor stub
			mContext = ct;
			resourceImageId = resImgId;
		}
		
		/* (non-Javadoc)
		 * @see android.widget.Adapter#getCount()
		 */
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return resourceImageId.length;
		}

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getItem(int)
		 */
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getItemId(int)
		 */
		@Override
		public long getItemId(int id) {
			// TODO Auto-generated method stub
			return 0;
		}

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View imgView = convertView;
			
			LayoutInflater lf = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
			imgView = lf.inflate(com.viettel.R.layout.photo_item, null);
			ImageView photo = (ImageView) imgView.findViewById(com.viettel.R.id.photo);
			photo.setBackgroundResource(resourceImageId[position]);
			return imgView;
		}
		
	}
}
