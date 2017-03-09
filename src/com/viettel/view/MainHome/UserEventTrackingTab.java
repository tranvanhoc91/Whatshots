/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.view.MainHome;

import java.util.ArrayList;

import com.viettel.R;
import com.viettel.dto.EventDTO;
import com.viettel.view.common.EventViewInvited;
import com.viettel.view.common.EventViewTracking;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: BangHN
 *  @version: 1.0
 *  @since: Nov 12, 2012
 */
public class UserEventTrackingTab extends Activity {
	ArrayList<EventDTO> eventList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.whatshots_listview);
		
		
		//data test
        eventList = new ArrayList<EventDTO>();
        eventList.add(new EventDTO(R.drawable.test2, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h", 500, "Viet", 200, 3));
        eventList.add(new EventDTO(R.drawable.test2, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h", 500, "Viet", 200, 3));
        eventList.add(new EventDTO(R.drawable.test2, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h", 500, "Viet", 200, 3));
        eventList.add(new EventDTO(R.drawable.test2, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h", 500, "Viet", 200, 3));
        eventList.add(new EventDTO(R.drawable.test2, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h", 500, "Viet", 200, 3));
        eventList.add(new EventDTO(R.drawable.test2, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h", 500, "Viet", 200, 3));
        eventList.add(new EventDTO(R.drawable.test2, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h", 500, "Viet", 200, 3));
        eventList.add(new EventDTO(R.drawable.test2, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h", 500, "Viet", 200, 3));
        eventList.add(new EventDTO(R.drawable.test2, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h", 500, "Viet", 200, 3));
        
        
		ListView listView = (ListView) findViewById(R.id.listView);
        listView.setDividerHeight(0);
        EventViewTracking.initListView(this, listView, eventList, "Item ", 20, android.R.layout.simple_list_item_1);
	}
}
