/**
 * Copyright 2011 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.utils;

import java.util.Vector;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import com.viettel.common.KunKunInfo;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: AnhND
 *  @version: 1.0
 *  @since: Jun 7, 2011
 */

public class ContactListLoader extends AsyncTask<Void, Void, Void>{
	/**
	 * 
	 *  Mo ta muc dich cua lop (interface)
	 *  @author: AnhND
	 *  @version: 1.0
	 *  @since: Jun 7, 2011
	 */
	public interface ContactListLoaderListener {
		void onLoadComplete(Vector<Bundle> contactList);
		void onLoadError();
	}
	
	Vector<Bundle> arrContact = new Vector<Bundle>();
	ContactListLoaderListener listener;	
	boolean isError = false;
	
	public ContactListLoader(ContactListLoaderListener listener){
		this.listener = listener;		
	}
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected Void doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		Cursor cur = null;
		boolean isError = false;
		try {
			arrContact.removeAllElements();
			ContentResolver cr = KunKunInfo.getInstance().getAppContext().getContentResolver();
			cur = cr.query(ContactFieldConstant.CONTENT_URI, null, null, null, null);
			int count = cur.getCount();
//            if (Integer.parseInt(cur.getString(
//                    cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
//                 Cursor pCur = cr.query(
//  		    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
//  		    null, 
//  		    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
//  		    new String[]{id}, null);
//  	        while (pCur.moveToNext()) {
//  		    // Do something with phones
//  	        } 
//  	        pCur.close();
//  	    }


			if (count > 0) {
				while (cur.moveToNext()) {
					Bundle phoneItem = new Bundle();
					String id = cur.getString(cur
							.getColumnIndex(ContactFieldConstant._ID));
					String name = cur.getString(cur
							.getColumnIndex(ContactFieldConstant.DISPLAY_NAME));
					String hasPhone = cur.getString(cur
							.getColumnIndex(ContactFieldConstant.HAS_PHONE_NUMBER));
					if (hasPhone.equalsIgnoreCase("1")) {
						Cursor phones = KunKunInfo.getInstance().getAppContext().getContentResolver().query(
								ContactFieldConstant.Phone.CONTENT_URI, null,
								ContactFieldConstant.Phone.CONTACT_ID + " = ?",
								new String[]{id}, null);
						try {
							while (phones.moveToNext()) {
								String phoneNumber = phones
										.getString(phones
												.getColumnIndex(ContactFieldConstant.Phone.NUMBER));
								phoneItem.putString("phoneNumber", phoneNumber);
								phoneItem.putString("name", name);
								arrContact.addElement(phoneItem);
							}
						} catch (Exception e) {
							// TODO: handle exception
						} finally {
							if (phones != null){
								phones.close();
							}
						}
					}
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			isError = true;
		}finally {
			if (cur != null){
				cur.close();
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		if (isError) {
			if (listener != null){
				listener.onLoadError();
			}
		} else {
			if (listener != null) {
				listener.onLoadComplete(this.arrContact);
			}
		}
	}
	
	/**
	 * 
	*  set listener
	*  @author: AnhND
	*  @param listener
	*  @return: void
	*  @throws:
	 */
	public void setListener(ContactListLoaderListener listener) {
		this.listener = listener;
	}
}
