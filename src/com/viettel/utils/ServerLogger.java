/**
 /*
 *@(#)WelcomeView.java 1.0 May 18, 2011
 *
 * Copyright 2006 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.utils;

import java.util.Vector;

import com.viettel.common.KunKunInfo;
import com.viettel.common.ServerPath;
import com.viettel.viettellib.network.http.HTTPListenner;
import com.viettel.viettellib.network.http.HTTPMessage;
import com.viettel.viettellib.network.http.HTTPRequest;
import com.viettel.viettellib.network.http.HTTPResponse;
import com.viettel.viettellib.network.http.HttpAsyncTask;
import com.viettel.viettellib.network.http.NetworkUtil;

/**
 * 
 * @author: AnhND
 * @since : May 18, 2011
 * 
 */
public class ServerLogger implements HTTPListenner {

	private static ServerLogger logger;

	protected ServerLogger() {

	}

	/**
	 * 
	 * get instance of ServerLogger
	 * @author: AnhND
	 * @since: May 18, 2011
	 * @Param: @return
	 * @Return: ServerLogger
	 * @Modified by:
	 * @Modified date: 
	 * @Description:
	 */
	private static ServerLogger getInstance() {
		if (logger == null) {
			logger = new ServerLogger();
		}
		return logger;
	}

	/**
	 * 
	 * send log message to server
	 * @author: AnhND
	 * @since: May 18, 2011
	 * @Param: @param logMsg
	 * @Return: void
	 * @Modified by:
	 * @Modified date: 
	 * @Description:
	 */
	synchronized public static void sendLog(LogMsg logMsg) {
		if (logMsg == null) {
			return;
		}
		ServerLogger svrLogger = ServerLogger.getInstance();

		Vector<String> data = new Vector<String>();
		data.addElement("platform");
		data.addElement(KunKunInfo.getInstance().getPlatForm());
		data.addElement("model");
		data.addElement(KunKunInfo.getInstance().PHONE_MODEL_SDK);
		data.addElement("version");
		data.addElement(KunKunInfo.getInstance().VERSION);
		data.addElement("errName");
		data.addElement(logMsg.getName());
		data.addElement("description");
		data.addElement(logMsg.getDes());

		String strJson = NetworkUtil.getJSONString("common.createTracking", data);

		HTTPRequest httpReq = new HTTPRequest();
		httpReq.setObserver(svrLogger);
		httpReq.setUrl(ServerPath.SERVER_PATH);
		httpReq.setMethod("POST");
		httpReq.setContentType(HTTPMessage.CONTENT_JSON);
		httpReq.setDataText(strJson);
		// sending to server
		new HttpAsyncTask(httpReq).execute();
	}

	@Override
	public void onReceiveData(HTTPMessage mes) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReceiveError(HTTPResponse response) {
		// TODO Auto-generated method stub

	}
}
