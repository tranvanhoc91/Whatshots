/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.view.MainHome;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import junit.framework.Test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.w3c.dom.Text;
import org.xbill.DNS.tests.primary;

import com.korovyansk.android.slideout.SlideoutActivity;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;
import com.viettel.R;
import com.viettel.dto.PlaceDTO;
import com.viettel.dto.MainHomeDTO;
import com.viettel.place.GPSTracker;
import com.viettel.utils.AlertDialogManager;
import com.viettel.viettellib.json.me.JSONException;
import com.viettel.viettellib.json.me.JSONObject;
import com.viettel.viettellib.network.http.ConnectionDetector;
import com.viettel.view.common.ViewUtils;
import com.whathots.menu.MenuActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HOC
 *  @version: 1.0
 *  @since: Sep 14, 2012
 */
@SuppressLint("ResourceAsColor")
public class SelectEventPlace extends Activity  {
	View menu;
    View app;
	EditText txt_place;
	ImageButton btnSearchPlace;
	ListView listViewPlace;
	Handler handle = new Handler();
	AlertDialogManager alertDialog = new AlertDialogManager();
	ArrayList<PlaceDTO> placeDataList = new ArrayList<PlaceDTO>();
	
	// GPS Location
	GPSTracker gps;	
	
	// Connection detector class
	ConnectionDetector cd;
		
	// flag for Internet connection status
	Boolean isInternetPresent = false;
		
	LinearLayout viewParent;	
	Button btnDefaultPlace;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LayoutInflater lf = LayoutInflater.from(this);
		app = lf.inflate(R.layout.layout_select_event_place, null);
		setContentView(app);
		
		cd = new ConnectionDetector(getApplicationContext());

		// Check if Internet present
		isInternetPresent = cd.isConnectingToInternet();
		if (!isInternetPresent) {
			// Internet Connection is not present
			alertDialog.show(this,
					getResources().getString(R.string.INTERNET_CONNECTION_ERROR),
					getResources().getString(R.string.SETUP_CONNECTION), 
					false);
			// stop executing code by return
			return;
		}
		
		// creating GPS Class object
		gps = new GPSTracker(SelectEventPlace.this);
		
		// check if GPS location can get
		if (!gps.canGetLocation()) {
			// Can't get user's current location
			alertDialog.show(this,
					getResources().getString(R.string.GPS_STATUS),
					getResources().getString(R.string.REQUIRE_ENABLE_GPS), 
					false);
			return;
		} 
		
		bindComponents();
		btnSearchPlace.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				new LoadPlaces().execute();
			}
		});
		
		listViewPlace.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int postion,long id) {
				String placeDetail = placeDataList.get(postion).getTxt_Name() + "," + placeDataList.get(postion).getTxt_Address();
				Intent intent = new Intent(SelectEventPlace.this, CreateNewEvent.class);
				intent.putExtra("EVENT_PLACE", placeDetail);
				startActivity(intent);
			}
		});
		
		btnDefaultPlace.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String placeDetail = getResources().getString(R.string.PLACE_DEFAULT);
				Intent intent = new Intent(SelectEventPlace.this, CreateNewEvent.class);
				intent.putExtra("EVENT_PLACE", placeDetail);
				startActivity(intent);
			}
		});
		
		
		final View[] children = new View[] { app };
	}

	
	
	
	/**
	*  Mo ta chuc nang cua ham
	*  @author: HocTB
	*  @return: void
	*  @throws: 
	*/
	private void bindComponents() {
		ActionBar actionBar = (ActionBar) app.findViewById(R.id.actionbar);
		actionBar.setTitle(getResources().getString(R.string.SELECT_PLACE));
		
		txt_place 		= (EditText) app.findViewById(R.id.txt_place);
		btnSearchPlace 	= (ImageButton) app.findViewById(R.id.btnSearchPlace);
		//listview
		listViewPlace = (ListView) app.findViewById(R.id.listViewPlace);
		listViewPlace.setDividerHeight(0);
		
		btnDefaultPlace = (Button) app.findViewById(R.id.btnDefaultPlace);
		btnDefaultPlace.setText(Html.fromHtml( getResources().getString(R.string.PLACE_NOT_FOUND) + "<br/>" + 
									getResources().getString(R.string.USE_ADDRESS) +
									"<b>\"" + getResources().getString(R.string.PLACE_DEFAULT) + "\"</b>" ));
		btnDefaultPlace.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
			}
		});
	}


	class LoadPlaces extends AsyncTask<Void, Void, String>{
		ProgressDialog pDialog;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(SelectEventPlace.this);
			pDialog.setMessage(Html.fromHtml("<b>"+ getResources().getString(R.string.SELECT_PLACE) + "</b><br/>" + getResources().getString(R.string.LOADING_PLACE)));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		
		
		@Override
		protected String doInBackground(Void... params) {
			String placeText = txt_place.getText().toString();
			placeDataList.add(new PlaceDTO(BitmapFactory.decodeResource(getResources(), R.drawable.ic_location),null, "532D, XVNT,p.25,quan Binh Thanh"));
			placeDataList.add(new PlaceDTO(BitmapFactory.decodeResource(getResources(), R.drawable.ic_office),"Cao Thai SOn", "532D, XVNT,p.25,quan Binh Thanh"));
			placeDataList.add(new PlaceDTO(BitmapFactory.decodeResource(getResources(), R.drawable.ic_house),"Cao Thai SOn", "532D, XVNT,p.25,quan Binh Thanh"));
			placeDataList.add(new PlaceDTO(BitmapFactory.decodeResource(getResources(), R.drawable.ic_coffe),"Trieu Gia Thang", "532D, XVNT,p.25,quan Binh Thanh"));
			placeDataList.add(new PlaceDTO(BitmapFactory.decodeResource(getResources(), R.drawable.ic_coffe),"Trieu Gia Thang", "532D, XVNT,p.25,quan Binh Thanh"));
			placeDataList.add(new PlaceDTO(BitmapFactory.decodeResource(getResources(), R.drawable.ic_coffe),"Trieu Gia Thang", "532D, XVNT,p.25,quan Binh Thanh"));
			placeDataList.add(new PlaceDTO(BitmapFactory.decodeResource(getResources(), R.drawable.ic_coffe),"Trieu Gia Thang", "532D, XVNT,p.25,quan Binh Thanh"));
			
			return placeText;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if(pDialog.isShowing()){
				pDialog.dismiss();
			}
			
			if (result.equals("") || result.equals(null)){
				alertDialog.show(SelectEventPlace.this, "Places Error",
						getResources().getString(R.string.ENTER_PLACE),false);
			}else if (result.equals("haha")) {
				listViewPlace.setVisibility(View.GONE);
				btnDefaultPlace.setVisibility(View.VISIBLE);
			}else{
				listViewPlace.setVisibility(View.VISIBLE);
				btnDefaultPlace.setVisibility(View.GONE);
				
				listViewPlace.setAdapter(new Whatshosts_Search_Place_Adapter(SelectEventPlace.this, placeDataList));
			}
		}
		
	}
	
	
	
	public Bitmap getBitmapPlaceIcon(String urlIcon){
		Bitmap bm = BitmapFactory.decodeFile(urlIcon);
		return bm;
	}
	
	

	//Create Adapter for listview location
	public class Whatshosts_Search_Place_Adapter extends BaseAdapter{

		private Context mContext;
		private LayoutInflater lf;
		private ArrayList<PlaceDTO> arrListPlaces;
		class ViewHolder{
			ImageView icon_Place;
			TextView txt_Name;
			TextView txt_Address;
		}
		
		
		/**
		 * Constructor
		 */
		public Whatshosts_Search_Place_Adapter(Context context) {
			//mContext		=	context;
			lf = LayoutInflater.from(context);
		}
		
		
		/**
		 * Constructor
		 */
		public Whatshosts_Search_Place_Adapter(Context context, ArrayList<PlaceDTO> listPlaces) {
			//mContext		=	context;
			lf = LayoutInflater.from(context);
			arrListPlaces	=	listPlaces;
		}
		
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if (arrListPlaces != null) {
				return arrListPlaces.size();
			}
			else{
				return 0;
			}
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return arrListPlaces.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		
		/**
		*  Mo ta chuc nang cua ham
		*  @author: HocTB
		*  @param viewHolder
		*  @param position
		*  @return: void
		*  @throws: 
		*/
		private void publicView(ViewHolder view, int position) {
			PlaceDTO dto = arrListPlaces.get(position);
			view.icon_Place.setImageBitmap(dto.getBm_place());
        	view.txt_Name.setText(dto.getTxt_Name());
        	view.txt_Address.setText(dto.getTxt_Address());
		}
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if (convertView == null) {
                convertView = lf.inflate(R.layout.layout_select_event_place_item, null);
                viewHolder = new ViewHolder();
                viewHolder.icon_Place 		= (ImageView) convertView.findViewById(R.id.icon_place);
                viewHolder.txt_Name 			= (TextView) convertView.findViewById(R.id.txtName);
                viewHolder.txt_Address 			= (TextView) convertView.findViewById(R.id.txtAddress);
                convertView.setTag(viewHolder);
            } else {
            	viewHolder = (ViewHolder) convertView.getTag();
            }
            publicView(viewHolder,position);
            return convertView;
		}
	}//End Whatshosts_Search_Location_Adapter
}
