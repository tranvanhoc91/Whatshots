package com.viettel.view.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint.Join;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.R;
import com.viettel.dto.*;
import com.viettel.view.MainHome.AddFriendList;
import com.viettel.view.MainHome.CreateNewEvent;
import com.viettel.view.MainHome.DetailReportEvent;
import com.viettel.view.MainHome.EventDetail;
import com.viettel.view.MainHome.FriendList;
import com.viettel.view.MainHome.MemberList;
import com.viettel.view.MainHome.ReportEvent;
import com.viettel.view.MainHome.SearchEvent;
import com.viettel.view.MainHome.SelectEventPlace;
import com.viettel.view.MainHome.SettingConnection;
import com.viettel.view.MainHome.SeventeenActivity;
import com.viettel.view.MainHome.EventCategory;
import com.viettel.view.MainHome.UserEvent;
import com.viettel.view.MainHome.UserPage;
/**
 * Utility methods for Views.
 */
public class ViewUtils {
    private ViewUtils() {
    }

    public static void setViewWidths(View view, View[] views) {
        int w = view.getWidth();
        int h = view.getHeight();
        for (int i = 0; i < views.length; i++) {
            View v = views[i];
            v.layout((i + 1) * w, 0, (i + 2) * w, h);
            printView("view[" + i + "]", v);
        }
    }

    public static void printView(String msg, View v) {
        System.out.println(msg + "=" + v);
        if (null == v) {
            return;
        }
        System.out.print("[" + v.getLeft());
        System.out.print(", " + v.getTop());
        System.out.print(", w=" + v.getWidth());
        System.out.println(", h=" + v.getHeight() + "]");
        System.out.println("mw=" + v.getMeasuredWidth() + ", mh=" + v.getMeasuredHeight());
        System.out.println("scroll [" + v.getScrollX() + "," + v.getScrollY() + "]");
    }

    public static void initListView(final Context context, ListView listView, String prefix, int numItems, int layout) {
        // By using setAdpater method in listview we an add string array in list.
        String[] arr = new String[numItems];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = prefix + (i + 1);
        }
        
        ArrayList<MainHomeDTO> list = new ArrayList<MainHomeDTO>();
        list.add(new MainHomeDTO(null, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h"));
        list.add(new MainHomeDTO(null, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h"));
        list.add(new MainHomeDTO(null, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h"));
        list.add(new MainHomeDTO(null, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h"));
        list.add(new MainHomeDTO(null, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h"));
        list.add(new MainHomeDTO(null, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h"));
        list.add(new MainHomeDTO(null, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h"));
        list.add(new MainHomeDTO(null, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h"));
        list.add(new MainHomeDTO(null, "Live show che linh 30 Nam Live show che linh 30 Nam", "Tai trung tam hoi nghi quoc gia vao ngay  Live show che linh 30 Nam", "12h- 17h"));
        
        listView.setAdapter(new ArrayAdapter<String>(context, layout, arr));
        listView.setAdapter((new ViewUtils()).new Whatshots_Main_Addapter(context,list));
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = view.getContext();
                
               /* String msg = "item[" + position + "]=" + parent.getItemAtPosition(position);
                Toast.makeText(context, msg, 1000).show();
                System.out.println(msg);*/
                
                Intent it = new Intent(context, EventDetail.class);
                it.putExtra("ID_EVENT", position+1); // test
                context.startActivity(it);
            }
        });
        
        
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				
				final CharSequence[] items = {"2-3", "4","6","8","10","11",
						"13","16","17","18","21","22","23"};
				builder.setTitle("Run activity");
				builder.setItems(items, new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//Toast.makeText(context, "[item] " + items[which], Toast.LENGTH_LONG).show();
						switch (which) {
							case 0:
								Intent intent = new Intent(context, SearchEvent.class);
								intent.putExtra("TXT_SEARCH_EVENT", "haha");
								context.startActivity(intent);
								break;
							case 1:
								context.startActivity(new Intent(context, CreateNewEvent.class));
								break;
							case 2:
								context.startActivity(new Intent(context, SelectEventPlace.class));
								break;
							case 3:
								context.startActivity(new Intent(context, ReportEvent.class));
								break;
							case 4:
								context.startActivity(new Intent(context, EventDetail.class));
								break;
							case 5:
								context.startActivity(new Intent(context, MemberList.class));
								break;
							case 6 :
								context.startActivity(new Intent(context, DetailReportEvent.class));
								break;
							case 7:
								context.startActivity(new Intent(context, EventCategory.class));
								break;
							case 8:
								context.startActivity(new Intent(context, UserPage.class));
								break;
							case 9:
								context.startActivity(new Intent(context, UserEvent.class));
								break;
							case 10:
								context.startActivity(new Intent(context, FriendList.class));
								break;
							case 11:
								context.startActivity(new Intent(context, AddFriendList.class));
								break;
							case 12:
								context.startActivity(new Intent(context, SettingConnection.class));
								break;
						}
					}
					
				});
				
				
				AlertDialog alertDialog = builder.create();
				alertDialog.show();

				
				return true;
			}
		});
    }
    
    
    
    public class Whatshots_Main_Addapter extends BaseAdapter  {
        private LayoutInflater mInflater;
        private ArrayList<MainHomeDTO> list;
        class ViewHolder {
            TextView txt_Name;
            TextView txt_Detail;
            TextView txt_Time;
            ImageView img_Avatar;
        }

        public Whatshots_Main_Addapter(Context context) {
        	super();
            mInflater = LayoutInflater.from(context);                  

        }
        public Whatshots_Main_Addapter(Context context,ArrayList<MainHomeDTO> list) {
            mInflater = LayoutInflater.from(context);                  
            this.list = list;
        }
        
        public int getCount() {
            return list.size();
        }

        public void publicView(ViewHolder view,int position){
        	MainHomeDTO dto = list.get(position);
        	view.txt_Name.setText(dto.getTxt_Name());
        	view.txt_Detail.setText(dto.getTxt_Detail());
        	view.txt_Time.setText(dto.getTxt_Time());
        	
        }

        public long getItemId(int position) {
            return position;
        }

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getItem(int)
		 */
		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.wh_main_page_item, null);
                holder = new ViewHolder();
                holder.txt_Name = (TextView) convertView.findViewById(R.id.txtName);
                holder.txt_Detail = (TextView) convertView.findViewById(R.id.txtDetail);
                holder.txt_Time = (TextView) convertView.findViewById(R.id.txtTime);
                holder.img_Avatar =(ImageView) convertView.findViewById(R.id.img_Avatar);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            publicView(holder,position);
            return convertView;
		}
    }
}
