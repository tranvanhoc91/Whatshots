package com.viettel.model;

import java.util.Vector;

import android.graphics.Bitmap;

import com.viettel.common.ActionEvent;
import com.viettel.common.KunKunInfo;
import com.viettel.common.ServerPath;
import com.viettel.constants.Constants;
import com.viettel.viettellib.network.http.HTTPListenner;
import com.viettel.viettellib.network.http.HTTPMessage;
import com.viettel.viettellib.network.http.HTTPRequest;
import com.viettel.viettellib.network.http.HTTPResponse;
import com.viettel.viettellib.network.http.HttpAsyncTask;
import com.viettel.viettellib.network.http.NetworkUtil;

public abstract class AbstractModelService implements HTTPListenner{

	public void onReceiveError(HTTPResponse response) {
		// TODO Auto-generated method stub
		
	}

	public void onReceiveData(HTTPMessage mes) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	*  Request text
	*  @author: HieuNH
	*  @param method
	*  @param data
	*  @param actionEvent
	*  @return: void
	*  @throws:
	 */
	protected HTTPRequest sendHttpRequest(String method, Vector data, ActionEvent actionEvent) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(NetworkUtil.getJSONString(method, data));
		HTTPRequest re = new HTTPRequest();
		re.setUrl(ServerPath.SERVER_PATH);		
		re.setAction(actionEvent.action);
		re.setContentType(HTTPMessage.CONTENT_JSON);
		re.setMethod(Constants.HTTPCONNECTION_POST);
		re.setDataText(strBuffer.toString());
		re.setObserver(this);
		re.setUserData(actionEvent);
		new HttpAsyncTask(re).execute();
		return re;
	}
	
	/**
	*  Request text
	*  @author: HieuNH
	*  @param method
	*  @param data
	*  @param actionEvent
	*  @return: void
	*  @throws:
	 */
	protected HTTPRequest sendHttpRequest(String method, Vector data, ActionEvent actionEvent, int timeout) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(NetworkUtil.getJSONString(method, data));
		HTTPRequest re = new HTTPRequest();
		re.setUrl(ServerPath.SERVER_PATH);		
		re.setAction(actionEvent.action);
		re.setContentType(HTTPMessage.CONTENT_JSON);
		re.setMethod(Constants.HTTPCONNECTION_POST);
		re.setDataText(strBuffer.toString());
		re.setObserver(this);
		re.setUserData(actionEvent);
		new HttpAsyncTask(re, timeout).execute();
		return re;
	}
	
	/**
	*  Request text
	*  @author: DoanDM
	*  @param method
	*  @param data
	*  @param actionEvent
	*  @return: void
	*  @throws:
	 */
	protected HTTPRequest sendRSAHttpRequest(String method, Vector data, ActionEvent actionEvent,boolean usingAES, int timeout) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(NetworkUtil.getJSONString(method, data));
		HTTPRequest re = new HTTPRequest();
		re.setUrl(ServerPath.SERVER_PATH);		
		re.setAction(actionEvent.action);
		re.setContentType(HTTPMessage.CONTENT_JSON);
		re.setMethod(Constants.HTTPCONNECTION_POST);
		re.setDataText(strBuffer.toString());
		re.setObserver(this);
		re.setUserData(actionEvent);
		new HttpAsyncTask(re, timeout).execute();
		return re;
	}
	


	

}

