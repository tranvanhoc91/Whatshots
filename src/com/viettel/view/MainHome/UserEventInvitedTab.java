/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.view.MainHome;

import java.util.ArrayList;

import com.viettel.R;
import com.viettel.dto.EventDTO;
import com.viettel.view.common.EventViewInvited;
import com.viettel.view.common.ViewUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HocTb
 *  @version: 1.0
 *  @since: Nov 12, 2012
 */
public class UserEventInvitedTab extends Activity {
	ArrayList<EventDTO> eventList;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.whatshots_listview);
        
	    //data test
        eventList = new ArrayList<EventDTO>();
        eventList.add(new EventDTO(R.drawable.test2, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h", 500, "Viet", 0, 0));
        eventList.add(new EventDTO(R.drawable.test2, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h", 500, "Viet", 0, 0));
        eventList.add(new EventDTO(R.drawable.test2, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h", 500, "Viet", 0, 0));
        eventList.add(new EventDTO(R.drawable.test2, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h", 500, "Viet", 0, 0));
        eventList.add(new EventDTO(R.drawable.test2, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h", 500, "Viet", 0, 0));
        eventList.add(new EventDTO(R.drawable.test2, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h", 500, "Viet", 0, 0));
        eventList.add(new EventDTO(R.drawable.test2, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h", 500, "Viet", 0, 0));
        
		ListView listView = (ListView) findViewById(R.id.listView);
        listView.setDividerHeight(0);
        EventViewInvited.initListView(this, listView, eventList, "Item ", 20, android.R.layout.simple_list_item_1);
	}
}
