/**
 * Copyright 2011 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.utils;

import android.net.Uri;

/**
 * back compatible API moi va cu khi get contact
 * 
 * @author: AnhND
 * @version: 1.0
 * @since: 1.0
 */
public class ContactFieldConstant {

	public static Uri CONTENT_FILTER_URI;// CONTENT_FILTER_URI
	public static Uri CONTENT_URI;// CONTENT_URI

	// Column name
	public static String _ID;// ID
	public static String DISPLAY_NAME;// DISPLAY_NAME
	public static String TIMES_CONTACTED;// TIMES_CONTACTED
	public static String LAST_TIME_CONTACTED;// LAST_TIME_CONTACTED
	public static String STARRED;// STARRED
	public static String CUSTOM_RINGTONE;// CUSTOM_RINGTONE
	public static String SEND_TO_VOICEMAIL;// SEND_TO_VOICEMAIL
	public static String HAS_PHONE_NUMBER;// HAS_PHONE_NUMBER

	static {
		String className = "";
		if (android.os.Build.VERSION.SDK_INT >= 5) {
			className = "android.provider.ContactsContract$Contacts";
		} else {
			className = "android.provider.Contacts$People";
		}

		try {
			Class<?> clazz = Class.forName(className);
			CONTENT_FILTER_URI = (Uri) clazz.getField("CONTENT_FILTER_URI")
					.get(clazz);
			CONTENT_URI = (Uri) clazz.getField("CONTENT_URI").get(clazz);

			_ID = (String) clazz.getField("_ID").get(clazz);
			DISPLAY_NAME = (String) clazz.getField("DISPLAY_NAME").get(clazz);
			TIMES_CONTACTED = (String) clazz.getField("TIMES_CONTACTED").get(
					clazz);
			LAST_TIME_CONTACTED = (String) clazz
					.getField("LAST_TIME_CONTACTED").get(clazz);
			STARRED = (String) clazz.getField("STARRED").get(clazz);
			CUSTOM_RINGTONE = (String) clazz.getField("CUSTOM_RINGTONE").get(
					clazz);
			SEND_TO_VOICEMAIL = (String) clazz.getField("SEND_TO_VOICEMAIL")
					.get(clazz);
			HAS_PHONE_NUMBER = (String) clazz.getField("HAS_PHONE_NUMBER").get(
					clazz);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Phone
	 * 
	 * @author: AnhND
	 * @since : May 20, 2011
	 * 
	 */
	public static class Phone {
		public static Uri CONTENT_URI;
		// column name
		public static String NUMBER;
		public static String TYPE;
		public static String CONTACT_ID;

		static {
			String className = "";
			if (android.os.Build.VERSION.SDK_INT >= 5) {
				className = "android.provider.ContactsContract$CommonDataKinds$Phone";
			} else {
				className = "android.provider.Contacts$Phones";
			}
			try {
				Class<?> clazz = Class.forName(className);
				CONTENT_URI = (Uri) clazz.getField("CONTENT_URI").get(clazz);
				CONTACT_ID = (String) clazz.getField("CONTACT_ID").get(clazz);
				NUMBER = (String) clazz.getField("NUMBER").get(clazz);
				TYPE = (String) clazz.getField("TYPE").get(clazz);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Email
	 * 
	 * @author: AnhND
	 * @since : May 20, 2011
	 * 
	 */
	public static class Email {
		public static Uri CONTENT_URI;
		public static String CONTACT_ID;
		public static String DATA;
		public static String TYPE;

		static {
			String className = "";
			String contentURIName = "";
			String contactIDName = "";
			if (android.os.Build.VERSION.SDK_INT >= 5) {
				className = "android.provider.ContactsContract$CommonDataKinds$Email";
				contentURIName = "CONTENT_URI";
				contactIDName = "CONTACT_ID";
			} else {
				className = "android.provider.Contacts$ContactMethods";
				contentURIName = "CONTENT_EMAIL_URI";
				contactIDName = "PERSON_ID";
			}
			try {
				Class<?> clazz = Class.forName(className);
				CONTENT_URI = (Uri) clazz.getField(contentURIName).get(clazz);

				CONTACT_ID = (String) clazz.getField(contactIDName).get(clazz);
				DATA = (String) clazz.getField("DATA").get(clazz);
				TYPE = (String) clazz.getField("TYPE").get(clazz);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
