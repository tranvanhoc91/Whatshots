package com.viettel.utils;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * 
 *  Mo ta muc dich cua lop (interface)
 *  @author: HOC
 *  @version: 1.0
 *  @since: Sep 23, 2012
 */
public class AlertDialogManager {
	/**
	 * Function to display simple Alert Dialog
	 * @param context - application context
	 * @param title - alert dialog title
	 * @param message - alert message
	 * @param status - success/failure (used to set icon)
	 * 				 - pass null if you don't want icon
	 * */
	public void show(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		if(status != null)
			// Setting alert dialog icon
			alertDialog.setIcon((status) ? com.viettel.R.drawable.success : com.viettel.R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
}
