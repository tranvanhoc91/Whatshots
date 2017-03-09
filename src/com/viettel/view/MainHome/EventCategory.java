/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.view.MainHome;

import java.util.ArrayList;
import java.util.Date;

import com.korovyansk.android.slideout.SlideoutActivity;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.ActionList;
import com.markupartist.android.widget.ActionBar.IntentAction;
import com.viettel.R;
import com.viettel.dto.EventDTO;
import com.viettel.view.MainHome.MainHome.ClickListenerForScrolling;
import com.viettel.view.custome.EventAdapter;
import com.whathots.menu.MenuActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HocTB
 *  @version: 1.0
 *  @since: Nov 11, 2012
 */
public class EventCategory extends Activity {
	View app;
	ImageView photoEvent;
	TextView txtName,txtPlace, txtTime, txtNumJoin;
	GridView gridViewEvent;
	ArrayList<EventDTO> evenList;
	View menu;
    boolean menuOut = false;
    int btnWidth;
    Handler handle = new Handler(); 
    ClickListenerForScrolling homeClick;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutInflater lf = LayoutInflater.from(this);
		app = lf.inflate(R.layout.layout_event_by_category, null);
		setContentView(app);
		
		ActionBar actionBar = (ActionBar) app.findViewById(R.id.actionbar);
		actionBar.setTitle("Whatshot");
        
        
        Action searchEventAction = new IntentAction(this,new Intent(this, SearchEvent.class), R.drawable.ic_search);
        Action newEventAction = new IntentAction(this,new Intent(this, CreateNewEvent.class), R.drawable.ic_new_event);
        actionBar.addAction(searchEventAction);
        actionBar.addAction(newEventAction);
        
        
        
		bindComponents();
		
		evenList = new ArrayList<EventDTO>();
		evenList.add(new EventDTO(R.drawable.test2, "Le hoi Halloween huyen bi", "Cong vien Dam Sen", "16h-20h", 500, "Dan Truong", 0, 0));
		evenList.add(new EventDTO(R.drawable.test2, "Le hoi ma", "Cong vien Dam Sen", "16h-20h", 500, "Dan Truong", 0, 0));
		evenList.add(new EventDTO(R.drawable.test2, "Le hoi tat nuoc", "Cong vien Dam Sen", "16h-20h", 500, "Dan Truong", 0, 0));
		
		displayEventHighlight();
		displayGridViewEvent();
		
		final View[] children = new View[] { app };
	}

	/**
	*  Mo ta chuc nang cua ham
	*  @author: HocTB
	*  @return: void
	*  @throws: 
	*/
	private void displayGridViewEvent() {
		EventAdapter adapter = new EventAdapter(this, R.id.gridView, evenList);
		gridViewEvent.setAdapter(adapter);
		gridViewEvent.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
			}
		});
	}

	/**
	*  Mo ta chuc nang cua ham
	*  @author: HocTB
	*  @return: void
	*  @throws: 
	*/
	private void displayEventHighlight() {
		EventDTO eventFirst = evenList.get(0);
		photoEvent.setBackgroundResource(eventFirst.getThumbnail());
		txtName.setText(eventFirst.getName());
		txtPlace.setText(eventFirst.getPlace());
		txtTime.setText(eventFirst.getTime());
		txtNumJoin.setText(""+eventFirst.getNumJoin()+" " + getResources().getString(R.string.PARTICIPANTS));
	}

	/**
	*  Mo ta chuc nang cua ham
	*  @author: HocTb
	*  @return: void
	*  @throws: 
	*/
	private void bindComponents() {
		photoEvent = (ImageView) app.findViewById(R.id.photoEvent);
		txtName = (TextView) app.findViewById(R.id.eventName);
		txtPlace = (TextView) app.findViewById(R.id.eventPlace);
		txtTime = (TextView) app.findViewById(R.id.eventTime);
		txtNumJoin = (TextView) app.findViewById(R.id.eventNumJoin);
		gridViewEvent = (GridView) app.findViewById(R.id.gridViewEvent);
	}
	
	
	private  class MyIntentAction extends AbstractAction {
        private Context mContext;
        private Intent mIntent;

        public MyIntentAction(Context context, Intent intent, int drawable) {
            super(drawable);
            mContext = context;
            mIntent = intent;
        }

        @Override
        public void performAction(View view) {
        	startActivity(new Intent(EventCategory.this,MainHome.class));
        }
    }
	
	
	 /**
     * Helper for examples with a HSV that should be scrolled by a menu View's width.
     */
    static class ClickListenerForScrolling implements OnClickListener {
        HorizontalScrollView scrollView;
        View menu;
        /**
         * Menu must NOT be out/shown to start with.
         */
        boolean menuOut = false;

        public ClickListenerForScrolling(HorizontalScrollView scrollView, View menu) {
            super();
            this.scrollView = scrollView;
            this.menu = menu;
        }

        @Override
        
        public void onClick(View v) {
            Context context = menu.getContext();
            String msg = "Slide " + new Date();
            int menuWidth = menu.getMeasuredWidth();
            // Ensure menu is visible
            menu.setVisibility(View.VISIBLE);
            if (!menuOut) {
                // Scroll to 0 to reveal menu
                int left = 0;
                scrollView.smoothScrollTo(left, 0);
            } else {
                // Scroll to menuWidth so menu isn't on screen.
                int left = menuWidth;
                scrollView.smoothScrollTo(left, 0);
            }
            menuOut = !menuOut;
        }
    }
    
    
    
}
