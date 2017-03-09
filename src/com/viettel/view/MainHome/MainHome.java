package com.viettel.view.MainHome;

import java.util.Date;

import com.korovyansk.android.slideout.SlideoutActivity;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;
import com.viettel.R;
import com.viettel.view.common.CommonView;
import com.viettel.view.common.ViewUtils;
import com.whathots.menu.MenuActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: VietHQ
 *  @version: 1.0
 *  @since: May 17, 2012
 */
public class MainHome extends Activity {
    View menu;
    View app;
    boolean menuOut = false;
    int btnWidth;
    Handler handle = new Handler(); 
    ClickListenerForScrolling homeClick;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
       // scrollView = (MyHorizontalScrollView) inflater.inflate(R.layout.horz_scroll_with_list_menu, null);
       // menu = inflater.inflate(R.layout.menu_main_home, null);
        app = inflater.inflate(R.layout.horz_scroll_app, null);
        setContentView(app);

        initActionbar();
       
        ListView listView = (ListView) app.findViewById(R.id.list);
        listView.setDividerHeight(0);
        ViewUtils.initListView(this, listView, "Item ", 30, android.R.layout.simple_list_item_1);

        
        final View[] children = new View[] { app };
        // Scroll to app (view[1]) when layout finished.
        int scrollToViewIdx = 1;
    }
    
    
    private void initActionbar() {
    	final ActionBar actionBar = (ActionBar) app.findViewById(R.id.actionbar);
        actionBar.setHomeAction(new MyIntentAction(this, null, R.drawable.ic_menu));
        actionBar.setTitle("Whatshot");
        
        final Action newEventAction = new IntentAction(this,new Intent(this, CreateNewEvent.class), R.drawable.ic_new_event);
        actionBar.addAction(newEventAction);
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
            try {
            	handle.post(new Runnable(){
            		public void run(){
            		//Update UI
         			int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
        			SlideoutActivity.prepare(MainHome.this, R.id.inner_content, width);
        			startActivity(new Intent(MainHome.this,
        								MenuActivity.class));
        			overridePendingTransition(0, 0);
            		}
            		});
            } catch (ActivityNotFoundException e) {
                Log.e(this.getClass().toString(), e.getMessage());
            }
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
            //Toast.makeText(context, msg, 1000).show();
            System.out.println(msg);
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
