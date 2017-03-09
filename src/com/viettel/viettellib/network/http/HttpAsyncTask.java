/**
 * Copyright 2011 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.viettellib.network.http;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.jivesoftware.smack.sasl.SASLMechanism.Response;

import android.os.AsyncTask;
import android.util.Log;

import com.viettel.utils.*;//com.viettel.android.utils.KunKunLog;
import com.viettel.viettellib.network.http.DataSupplier.Data;

/**
 * 
 *  DataSuplier interface
 *  @author: AnhND
 *  @version: 1.0
 *  @since: Jun 10, 2011
 */
interface DataSupplier {
	public class Data {
		byte[] buffer;
		boolean isFinish;
		int length;
	}
	void getNextPart(Data data);
	void releaseData();
	int overallDataSize();
	void reset();
}

/**
 * 
 *  request http
 *  @author: AnhND
 *  @version: 1.0
 *  @since: Jun 10, 2011
 */
public class HttpAsyncTask extends AsyncTask<Void, Void, Void> {
	private static int TIME_OUT = 10000;//miliseconds
	private HTTPRequest request;
	private HTTPResponse response;
	private boolean isSuccess;
	private static final String LOG_TAG = "HttpAsyncTask";
	private static final int CONNECT_TIMEOUT = 100;
	
	private int readTimeout = TIME_OUT;
	private int connectTimeout = CONNECT_TIMEOUT;
	
	
	public HttpAsyncTask(HTTPRequest re) {
		request = re;
		this.readTimeout = TIME_OUT;
	}
	
	
	public HttpAsyncTask(HTTPRequest re, int timeout) {
		this.request = re;
		this.readTimeout = timeout;
	}
	
	
	@Override
	protected Void doInBackground(Void... params) {
		if (request == null || request.isAlive() == false) {
			if (request == null) {
				KunKunLog.i(LOG_TAG, "Request null");
			}else {
				KunKunLog.i(LOG_TAG, "Request NOT alive");
			}
			
			return null;
		}
		
		int countRetry = 0;
		final int NUM_RETRY = 1;
		boolean isRetry = false;
		do {
			isRetry = false;
			countRetry++;
			HttpURLConnection connection = null;
			OutputStream outputStream = null;
			InputStream inputStream = null;
			isSuccess = true;
			//bug sometime response code = -1
			System.setProperty("http.keepAlive", "false");
			try {
				response = new HTTPResponse(request);
				URL _url = new URL(request.getUrl());
				
				connection = (HttpURLConnection)_url.openConnection();
				connection.setConnectTimeout(TIME_OUT);
				connection.setReadTimeout(TIME_OUT);
				if (request.getContentType() != null) {
					connection.setRequestProperty("Content-type", request.contentType);
				}
				connection.setRequestMethod(request.getMethod());
				
				if (HTTPClient.sessionID != null) {
					connection.setRequestProperty("Cookie", HTTPClient.sessionID);
				}
				
				connection.setDoInput(true);
				if (HTTPRequest.POST.equals(request.getMethod())){
					connection.setDoOutput(true);
					connection.connect();
					
					outputStream = connection.getOutputStream();
					
					writeData(outputStream, request);
					
					outputStream.close();
					
					outputStream = null;
				} else if (HTTPRequest.GET.equals(request.getMethod())){
					connection.setDoOutput(false);
					connection.connect();
					
					KunKunLog.i(LOG_TAG, "GET - connect" + "; request:" + request.getAction());
				}
				
				inputStream = connection.getInputStream();				
				response.readData(inputStream);				
				if (response.getDataText() == null && response.getDataBinary() == null && request.isAlive()) {
					isSuccess = false;
					isRetry = true;
					StringBuffer strBuffer = new StringBuffer();
					
					strBuffer.append("ResponseCode: " + connection.getResponseCode());
					strBuffer.append("/ResponseMsg: " + connection.getResponseMessage());
					response.setError(HTTPClient.ERR_UNKNOWN, strBuffer.toString());
				}
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				isSuccess = false;
				response.setError(HTTPClient.ERR_INVALID_URL, e.getMessage() + "/" + e.toString());
				KunKunLog.i(LOG_TAG, "MalformedURLException - " + e.getMessage());
			} catch (FileNotFoundException e) {
				// TODO: handle exception
				isSuccess = false;
				response.setError(HTTPClient.ERR_UNKNOWN, e.getMessage() + "/" + e.toString());
				KunKunLog.i(LOG_TAG, "FileNotFoundException - " + e.getMessage() + "/" + e.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				isSuccess = false;
				isRetry = true;
				response.setError(HTTPClient.ERR_UNKNOWN, e.getMessage() + "/" + e.toString());
				KunKunLog.i(LOG_TAG, "IOException - " + e.getMessage() + "/" + e.toString());
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				isSuccess = false;
				isRetry = true;
				response.setError(HTTPClient.ERR_UNKNOWN, e.getMessage() + "/" + e.toString());
				KunKunLog.i(LOG_TAG, "Throwable - " + e.getMessage() + "/" + e.toString());
			} finally {
				if (outputStream != null){
					try {
						outputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						KunKunLog.i(LOG_TAG, "close outputStream - IOException - " + e.getMessage() + "/" + e.toString());
					}
				}
				if (inputStream != null){
					try {
						inputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						KunKunLog.i(LOG_TAG, "close inputStream - IOException - " + e.getMessage() + "/" + e.toString());
					}
				}
				if (connection != null){
					KunKunLog.i(LOG_TAG, "disconnect");
					connection.disconnect();
				}
			}
		}while (countRetry <= NUM_RETRY && isRetry);
		
		HTTPListenner listenner = null;
		boolean isAlive = true;
		if (response != null) {
			listenner = response.getObserver();
			isAlive = response.request.isAlive();
		}
		if (listenner != null && isAlive) {
			if (isSuccess) {
				listenner.onReceiveData(response);
			} else {
				listenner.onReceiveError(response);
			}
		}
		return null;
	}
		
	/**
	 * 
	*  send data
	*  @author: AnhND
	*  @param outputStream
	*  @param dataSupplier
	*  @throws IOException
	*  @return: void
	*  @throws:
	 */
	void writeData(OutputStream outputStream, DataSupplier dataSupplier) throws IOException{
		Data data = new Data(); 
		while (true){
			dataSupplier.getNextPart(data);
			if (data.buffer != null && data.length > 0) {
				outputStream.write(data.buffer, 0, data.length);
				outputStream.flush();
				dataSupplier.releaseData();
			}
			if (data.isFinish){
				break;
			}
		}
		
	}	
}
