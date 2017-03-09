/**
 * Copyright 2011 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.utils;

import java.util.Timer;
import java.util.TimerTask;

import com.viettel.common.KunKunInfo;
import com.viettel.listener.TimeoutListener;

/**
 *  Timeout
 *  @author: HieuNH
 *  @version: 1.1
 *  @since: Oct 29, 2011
 */
public class Timeout implements TimeoutListener{
	Timer timeOutTimer = null;//quan ly timeout
	//Activity context;//context	
	TimeoutListener listener;
	TimeOutTask timeOutTask;
	class TimeOutTask extends TimerTask {
		public boolean isCancel = false;		
		/* (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			KunKunInfo.getInstance().getAppHandler().post(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if (!isCancel) {						
						Timeout.this.stopTimeout();
						listener.onTimeout(Timeout.this);						
					}
				}
			});
		}
	}
	
	public Timeout (TimeoutListener listener) {
		//this.context = (Activity)KunKunInfo.getInstance().getAppContext();		
		this.listener = listener;
	}	
	
	public void starTimeout(int time){
		timeOutTimer = new Timer();
		timeOutTask = new TimeOutTask();			
		timeOutTimer.schedule(timeOutTask, time);
	}
	
	public void stopTimeout() {
		// TODO Auto-generated method stub
		if (timeOutTimer != null) {
			timeOutTimer.cancel();
			timeOutTimer = null;
			
			timeOutTask.isCancel = true;
			timeOutTask.cancel();
			timeOutTask = null;			
		}
	}
	
	/* (non-Javadoc)
	 * @see com.viettel.kunkun.utils.TimeoutListener#onTimeout(com.viettel.kunkun.utils.Timeout)
	 */
	@Override
	public void onTimeout(Timeout timeout) {
		// TODO Auto-generated method stub
		
	}
}