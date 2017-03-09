package com.viettel.utils.ui;


import android.view.View;
import android.view.animation.TranslateAnimation;

import com.viettel.listener.TimeoutListener;
import com.viettel.utils.Timeout;

public class SnapAnimation implements TimeoutListener {

	private final View view;
	TranslateAnimation slide;
	Timeout timeoutAnimation;// timeout de lang nghe broadcast luong dinh vi
	int timeAnimation;
	int timeClose;
	
	public SnapAnimation(View rl, int timeAnimation, int timeClose) {
		this.timeAnimation = timeAnimation;
		this.timeClose = timeClose;
		this.view = rl;		   
	}
	
	public void startAnimation(){
		slide = new TranslateAnimation(0, 0, view.getHeight(), 0);   
	    slide.setDuration(this.timeAnimation);   
	    slide.setFillAfter(true);	
		this.view.startAnimation(slide);
	    if(timeoutAnimation == null){
	    	timeoutAnimation = new Timeout(this);
	    }else{
	    	timeoutAnimation.stopTimeout();
	    }		    		    
	    timeoutAnimation.starTimeout(this.timeClose);
	}

	/* (non-Javadoc)
	 * @see com.viettel.kunkun.utils.TimeoutListener#onTimeout(com.viettel.kunkun.utils.Timeout)
	 */
	@Override
	public void onTimeout(Timeout timeout) {
		// TODO Auto-generated method stub
		this.view.clearAnimation();
		this.view.setVisibility(View.GONE);
	}

	
}