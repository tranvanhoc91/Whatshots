/**
 * Copyright 2011 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.utils;

import android.util.Log;

import com.google.code.microlog4android.LoggerFactory;
import com.viettel.common.KunKunInfo;

/**
 *  Show log trace(interface)
 *  @author: TamPQ
 *  @version: 1.1
 *  @since: 1.0
 */
public class KunKunLog{

	public static final com.google.code.microlog4android.Logger logger = LoggerFactory.getLogger();
	
	public static void d(String tag, String msg){
		if(KunKunInfo.getInstance().getProfile().isDebugMode()){
			Log.d(tag, msg);
		}
	}
	
	public static void d(String tag, String msg, Throwable tr){
		if(KunKunInfo.getInstance().getProfile().isDebugMode()){
			Log.d(tag, msg, tr);
		}
	}
	
	public static void e(String tag, String msg){
		if(KunKunInfo.getInstance().getProfile().isDebugMode()){
			Log.e(tag, msg);
		}
	}
	
	public static void e(String tag, String msg, Throwable tr) {
		if(KunKunInfo.getInstance().getProfile().isDebugMode()){
			Log.e(tag, msg, tr);
		}
	}
	
	public static void i(String tag, String msg){
		if(KunKunInfo.getInstance().getProfile().isDebugMode()){
			Log.i(tag, msg);
		}
	}
	
	public static void i(String tag, String msg, Throwable tr){
		if(KunKunInfo.getInstance().getProfile().isDebugMode()){
			Log.i(tag, msg, tr);
		}
	}	

	public static synchronized void logToFile(String title, String content) {
		if(KunKunInfo.getInstance().getProfile().isDebugMode()){
			logger.debug(title + " : " + content);
			logger.debug("-------------------------------------");
		}
	}
	
	public static void v(String tag, String msg){
		if(KunKunInfo.getInstance().getProfile().isDebugMode()){
			Log.v(tag, msg);
		}
	}
	
	public static void v(String tag, String msg, Throwable tr){
		if(KunKunInfo.getInstance().getProfile().isDebugMode()){
			Log.v(tag, msg, tr);
		}
	}
	
	public static void w(String tag, String msg){
		if(KunKunInfo.getInstance().getProfile().isDebugMode()){
			Log.w(tag, msg);
		}
	}
	
	public static void w(String tag, String msg, Throwable tr){
		if(KunKunInfo.getInstance().getProfile().isDebugMode()){
			Log.w(tag, msg, tr);
		}
	}
}
