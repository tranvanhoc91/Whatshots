/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.viettellib.json.me;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.net.Uri;
import android.util.Log;

import com.viettel.viettellib.json.me.JSONArray;
import com.viettel.viettellib.json.me.JSONException;
import com.viettel.viettellib.json.me.JSONObject;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: BangHN
 *  @version: 1.0
 *  @since: Sep 16, 2012
 */
public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static JSONArray jArr = null;
	
	
	public JSONParser(){
		
	}
	
	/**
	 * 
	*  Mo ta chuc nang cua ham
	*  @author: BangHN
	*  @param address 
	*  @param method
	*  @return
	*  @throws URISyntaxException
	*  @return: json string
	*  @throws:
	 */
	public String excuteHttpRequest(String address, String method){
		String jsonStr = "";
		
		String url = null;
		//Tao duong dan truy cap
		try {
			URI uri = new URI(
						"http",
						"maps.googleapis.com",
						"/maps/api/geocode/json",
						"address="+address+"&sensor=true",
						null);
			url = uri.toASCIIString();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
			
		//1.truy vấn server http://10.0.0.103/ltjoomla2106/index.php?option=com_json&format=json&id=15&task=requestdb
	    /*tạo 1 client: chức năng giống như trình duyệt*/
	    DefaultHttpClient client = new DefaultHttpClient();
		    
	    /*tạo biến post để đẩy dữ liệu: --> tạo header của giao thức http*/
	    if (method == "POST") {
	    	 HttpPost postObj = new HttpPost(url);
	    	 /*đặt các giá trị cần post vào biến http header*/
	 		try {
	 			//lấy vể response là dữ liệu chuỗi JSON
	 			/*bấm nút submit form trên trình duyệt*/
	 			HttpResponse jsonString = client.execute(postObj);
	 			
	 			jsonStr = EntityUtils.toString(jsonString.getEntity());
	 			
	 			Log.e("JSON STRiNG",jsonStr);
	 		} catch (ClientProtocolException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		} catch (IOException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
		}
	    if (method == "GET") {
	    	 HttpGet getObj = new HttpGet(url);
	    	 /*đặt các giá trị cần post vào biến http header*/
 			 try {
	 			//lấy vể response là dữ liệu chuỗi JSON
	 			/*bấm nút submit form trên trình duyệt*/
	 			HttpResponse jsonString = client.execute(getObj);
	 			
	 			jsonStr = EntityUtils.toString(jsonString.getEntity());
	 			
	 			Log.e("JSON STRiNG",jsonStr);
	 		} catch (ClientProtocolException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		} catch (IOException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
		}
	    
        return jsonStr;
	}
	
	
	
	/**
	 * 
	*  Mo ta chuc nang cua ham
	*  @author: BangHN
	*  @param jsonString
	*  @return
	*  @return: JSONObject
	*  @throws:
	 */
	public JSONObject getJSONObject(String jsonString){
		// try parse the string to a JSON object
        try {
            jObj = new JSONObject(jsonString);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
 
        // return JSON String
        return jObj;
	}
	
	
	
	/**
	 * 
	*  Mo ta chuc nang cua ham
	*  @author: BangHN
	*  @param jsonString
	*  @return
	*  @return: JSONArray
	*  @throws:
	 */
	public JSONArray getJSONArray(String jsonString){
		// try parse the string to a JSON object
        try {
            jArr = new JSONArray(jsonString);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
 
        // return JSON String
        return jArr;
	}
}
