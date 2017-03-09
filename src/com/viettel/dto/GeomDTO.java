package com.viettel.dto;

import java.io.Serializable;

import com.viettel.viettellib.json.me.JSONException;
import com.viettel.viettellib.json.me.JSONObject;


@SuppressWarnings("serial")
public class GeomDTO  implements Serializable{
	public double lng;//toa do long
	public double lat;//toa do lat
	public void parseFromJson(String data){
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(data);			
			lng = jsonObject.getDouble("lng");
			lat = jsonObject.getDouble("lat");			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
