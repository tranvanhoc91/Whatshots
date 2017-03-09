package com.whathots.menu;

import java.util.ArrayList;

import com.viettel.R;
import com.viettel.dto.MainHomeDTO;
import com.viettel.dto.MainMenuDTO;
import com.viettel.utils.AlertDialogManager;
import com.viettel.view.MainHome.SearchEvent;
import com.viettel.view.MainHome.EventCategory;
import com.viettel.view.MainHome.UserPage;
import com.viettel.view.common.ViewUtils;
import com.viettel.view.common.ViewUtils.Whatshots_Main_Addapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MenuFragment extends ListFragment {
	ArrayList<MainMenuDTO> listMenus;
	ImageButton btnSearch;
	AlertDialogManager alertDialog = new AlertDialogManager();
	EditText txtSearch;
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		setListAdapter(new ArrayAdapter<String>(getActivity(),
//				android.R.layout.simple_list_item_1, new String[] { " First", " Second", " Third", " Fourth", " Fifth", " Sixth"}));
//        listView.setAdapter(new ArrayAdapter<String>(context, layout, arr));
		View header = LayoutInflater.from(getActivity()).inflate(R.layout.main_menu_item3, null);
		this.getListView().addHeaderView(header);
		
		txtSearch = (EditText) header.findViewById(R.id.txtSearch);
		
		//button search
		btnSearch =(ImageButton) header.findViewById(R.id.btn_Search);
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (txtSearch.getText().toString().equals("") || txtSearch.getText().toString().equals(null)) {
					alertDialog.show(getActivity(),
							getResources().getString(R.string.ERROR),
							getResources().getString(R.string.PLEASE_ENTER_TITLE), 
							false);
				}else{
					Intent intent = new Intent(getActivity(), SearchEvent.class);
					intent.putExtra("TXT_SEARCH_EVENT", txtSearch.getText().toString());
					startActivity(intent);
				}
			}
		});
		
		
		//init menu
       listMenus = new ArrayList<MainMenuDTO>();
       listMenus.add(new MainMenuDTO(MainMenuDTO.MENU_TYPE_AVATAR, BitmapFactory.decodeResource(getResources(), R.drawable.avatar), "Ho\u00E0ng Qu\u1ED1c Vi\u1EC7t"));
       listMenus.add(new MainMenuDTO(MainMenuDTO.MENU_TYPE_ICON_TEXT, BitmapFactory.decodeResource(getResources(), R.drawable.ic_capnhat), getString(R.string.MENU_UPDATE_EVENT)));
       listMenus.add(new MainMenuDTO(MainMenuDTO.MENU_TYPE_TEXT_SEPARATOR, BitmapFactory.decodeResource(getResources(), R.drawable.avatar), getString(R.string.MENU_EVENT)));
       listMenus.add(new MainMenuDTO(MainMenuDTO.MENU_TYPE_ICON_TEXT, BitmapFactory.decodeResource(getResources(), R.drawable.ic_menu_quantam), getString(R.string.MENU_CARE)));
       listMenus.add(new MainMenuDTO(MainMenuDTO.MENU_TYPE_ICON_TEXT, BitmapFactory.decodeResource(getResources(), R.drawable.ic_menu_danghot), getString(R.string.MENU_HOT)));
       listMenus.add(new MainMenuDTO(MainMenuDTO.MENU_TYPE_ICON_TEXT, BitmapFactory.decodeResource(getResources(), R.drawable.ic_menu_thethao), getString(R.string.MENU_SPORT)));
       listMenus.add(new MainMenuDTO(MainMenuDTO.MENU_TYPE_ICON_TEXT, BitmapFactory.decodeResource(getResources(), R.drawable.ic_menu_giaitri), getString(R.string.MENU_MUSIC)));
       listMenus.add(new MainMenuDTO(MainMenuDTO.MENU_TYPE_ICON_TEXT, BitmapFactory.decodeResource(getResources(), R.drawable.ic_menu_quantam), getString(R.string.MENU_CONFERENCE)));

		setListAdapter(new Whatshots_Main_Addapter(getActivity(),listMenus));
        
		getListView().setCacheColorHint(R.color.TRANSPARENT);
		getListView().setDividerHeight(0);
		getListView().setBackgroundResource(R.color.BACK_GROUND_MENU_COLOR);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		((MenuActivity)getActivity()).getSlideoutHelper().close();
		
		 MainMenuDTO dto = listMenus.get(position);
		 
		 switch (dto.getType()) {
			case  MainMenuDTO.MENU_TYPE_AVATAR:{
				startActivity(new Intent((MenuActivity)getActivity(), UserPage.class));
				break;
			}
			case  MainMenuDTO.MENU_TYPE_ICON_TEXT:{
				startActivity(new Intent((MenuActivity)getActivity(), EventCategory.class));
				break;
			}
		}
	}

	
	/**
	 * 
	 * @author SEVEN
	 *
	 */
    public class Whatshots_Main_Addapter extends BaseAdapter  {
        private LayoutInflater mInflater;
        private ArrayList<MainMenuDTO> list;
        class ViewHolder {
           public TextView txt_Name;
           public ImageView img_Avatar;
           public EditText txt_Search;
           public ImageButton btn_Search;
           public void reset(){
        	   txt_Name = null;
        	   img_Avatar = null;
        	   txt_Search = null;
        	   btn_Search = null;
           }
        }

        public Whatshots_Main_Addapter(Context context) {
        	super();
            mInflater = LayoutInflater.from(context);                  

        }
        public Whatshots_Main_Addapter(Context context,ArrayList<MainMenuDTO> list) {
            mInflater = LayoutInflater.from(context);                  
            this.list = list;
        }
        
        public int getCount() {
            return list.size();
        }

//        public void publicView(ViewHolder view,int position){
//        	MainMenuDTO dto = list.get(position);
//        	view.txt_Name.setText(dto.getTxt_Name());
//        	
//        }

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
            ViewHolder holder =null;
            MainMenuDTO dto = list.get(position);
            switch(dto.type){
            case MainMenuDTO.MENU_TYPE_ICON_TEXT:{
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.main_menu_item1, null);
                    holder = new ViewHolder();
                    convertView.setTag(holder);
//                    if(dto.type == MainMenuDTO.MENU_TYPE_AVATAR)
//                    	holder.txt_Name.setGravity(Gravity.TOP);

                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.txt_Name = (TextView) convertView.findViewById(R.id.txt_main_menu_text);
                holder.img_Avatar =(ImageView) convertView.findViewById(R.id.img_text);

                LinearLayout l1 = (LinearLayout)convertView.findViewById(R.id.ln_img_text);
                LinearLayout l2 = (LinearLayout)convertView.findViewById(R.id.ln_text);
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);

                convertView.setBackgroundResource(R.drawable.menu_list_row);

                if(holder.txt_Name != null) {
                	holder.txt_Name.setText(dto.getText());
                }
                
                if(holder.img_Avatar != null) holder.img_Avatar.setImageBitmap(dto.getImg());
                break;
            }
            case MainMenuDTO.MENU_TYPE_AVATAR:{
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.main_menu_item1, null);
                    holder = new ViewHolder();
                    convertView.setTag(holder);
//                    if(dto.type == MainMenuDTO.MENU_TYPE_AVATAR)
//                    	holder.txt_Name.setGravity(Gravity.TOP);

                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.txt_Name = (TextView) convertView.findViewById(R.id.txt_main_menu_text);
                holder.img_Avatar =(ImageView) convertView.findViewById(R.id.img_text);

                LinearLayout l1 = (LinearLayout)convertView.findViewById(R.id.ln_img_text);
                LinearLayout l2 = (LinearLayout)convertView.findViewById(R.id.ln_text);
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);
                convertView.setBackgroundResource(R.drawable.menu_list_row);

                if(holder.txt_Name != null) holder.txt_Name.setText(dto.getText());
                if(holder.img_Avatar != null) holder.img_Avatar.setImageBitmap(dto.getImg());
               break;
            }
            case MainMenuDTO.MENU_TYPE_TEXT_SEPARATOR:{
//                if (convertView == null) {
//                    convertView = mInflater.inflate(R.layout.main_menu_item2, null);
//                    holder = new ViewHolder();
//                    holder.txt_Name = (TextView) convertView.findViewById(R.id.txt_main_menu);
//                    convertView.setTag(holder);
//                } else {
//                    holder = (ViewHolder) convertView.getTag();
//                }
//                if(holder.txt_Name != null) holder.txt_Name.setText(dto.getText());
//            
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.main_menu_item1, null);
                    holder = new ViewHolder();
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.txt_Name = (TextView) convertView.findViewById(R.id.txt_main_menu_2);

                LinearLayout l1 = (LinearLayout)convertView.findViewById(R.id.ln_img_text);
                LinearLayout l2 = (LinearLayout)convertView.findViewById(R.id.ln_text);
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.VISIBLE);


                if(holder.txt_Name != null) holder.txt_Name.setText(dto.getText());
            	
            break;
            }
        }
            return convertView;
		}

        
    }
}
