package com.viettel.view.MainHome;

import java.util.ArrayList;
import java.util.Date;

import com.korovyansk.android.slideout.SlideoutActivity;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;
import com.viettel.R;
import com.viettel.dto.MainHomeDTO;
import com.viettel.utils.AlertDialogManager;
import com.viettel.view.common.CommonView;
import com.viettel.view.common.ViewUtils;
import com.viettel.view.custome.Whatshots_Main_Addapter;
import com.whathots.menu.MenuActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: VietHQ
 *  @version: 1.0
 *  @since: May 17, 2012
 */
public class SearchEvent extends Activity {
    View app;
    EditText txtEventName;
    ImageButton btnSearch;
    ListView listViewEvent;
    LinearLayout eventNotFound;
    ArrayList<MainHomeDTO> listEvents;
    AlertDialogManager alertDialog = new AlertDialogManager();
	
    //Mang chua nhung song item da dc sort khi search
	ArrayList<MainHomeDTO> listEventFound = new ArrayList<MainHomeDTO>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        app = inflater.inflate(R.layout.layout_search_event, null);
        setContentView(app);

        final ActionBar actionBar = (ActionBar) app.findViewById(R.id.actionbar);
        actionBar.setTitle(getString(R.string.YOUR_EVENT_QUESTION));
        
        bindComponents();
        
        String txt_event_name_entered = getIntent().getExtras().getString("TXT_SEARCH_EVENT");
        txtEventName.setText(txt_event_name_entered);
        
        initEventListView();
        
        btnSearch.setOnClickListener(searchEventListener);
        
        searchEvent();
        
        final View[] children = new View[] { app };
    }
    
    
    /**
	*  Mo ta chuc nang cua ham
	*  @author: HocTB
	*  @return: void
	*  @throws: 
	*/
	private void searchEvent() {
		txtEventName.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//get text search length
				int txtSearchLength = txtEventName.getText().length();
				
				listEventFound.clear();
				
				for (int i = 0; i < listEvents.size(); i++) {
					if (txtSearchLength <= listEvents.get(i).getTxt_Name().length()) {
						if (txtEventName.getText().toString().equalsIgnoreCase(listEvents.get(i).getTxt_Name().substring(0, txtSearchLength))) {
							listEventFound.add(listEvents.get(i));
						}
					}
				}
				
				if (listEventFound.size() == 0) {
					listViewEvent.setVisibility(View.GONE);
					eventNotFound.setVisibility(View.VISIBLE);
					eventNotFound.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							startActivity(new Intent(SearchEvent.this, CreateNewEvent.class));
						}
					});
				}else{
					listViewEvent.setVisibility(View.VISIBLE);
					eventNotFound.setVisibility(View.GONE);
					//set lai adapter
					listViewEvent.setAdapter(new Whatshots_Main_Addapter(SearchEvent.this, listEventFound));
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int cont,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	}


	/**
	*  Mo ta chuc nang cua ham
	*  @author: HocTb
	*  @return: void
	*  @throws: 
	*/
	private void initEventListView() {
		//Data
		listEvents = new ArrayList<MainHomeDTO>();
		listEvents.add(new MainHomeDTO(null, "LiveShow Dam Vinh Hung", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h"));
		listEvents.add(new MainHomeDTO(null, "LiveShow Dan Truong", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h"));
		listEvents.add(new MainHomeDTO(null, "LiveShow Cam Ly", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h"));
		listEvents.add(new MainHomeDTO(null, "LiveShow Doan Trang", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h"));
		listEvents.add(new MainHomeDTO(null, "Tau hai Hoai Linh", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h"));
		listEvents.add(new MainHomeDTO(null, "Giao luu sinh vien Viet Nam - Han Quoc", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h"));
		listEvents.add(new MainHomeDTO(null, "Hoi Thao Anh Le Trainning", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h"));
		
		//Adatepr 
		Whatshots_Main_Addapter adapter = new Whatshots_Main_Addapter(SearchEvent.this, listEvents);
		
		listViewEvent.setAdapter(adapter);
	}


	/**
	*  Mo ta chuc nang cua ham
	*  @author: HocTB
	*  @return: void
	*  @throws: 
	*/
	private void bindComponents() {
		listViewEvent = (ListView) app.findViewById(R.id.listEvent);
		txtEventName = (EditText) app.findViewById(R.id.txtEventName);
		eventNotFound = (LinearLayout) app.findViewById(R.id.eventNotFound);
        btnSearch = (ImageButton) app.findViewById(R.id.btnSearch);
	}
	
	private OnClickListener searchEventListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String eventName = txtEventName.getText().toString();
			if (eventName.equals(null) || eventName.equals("||")) {
				alertDialog.show(SearchEvent.this,
						getResources().getString(R.string.ERROR),
						getResources().getString(R.string.PLEASE_ENTER_TITLE), 
						false);
			}
		}
	};
}
