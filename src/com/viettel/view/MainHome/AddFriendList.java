package com.viettel.view.MainHome;

import java.util.ArrayList;

import com.markupartist.android.widget.ActionBar;
import com.viettel.R;
import com.viettel.dto.FriendDTO;
import com.viettel.dto.MemberJoinDTO;
import com.viettel.dto.UnfamiliarFriendDTO;
import com.viettel.view.MainHome.MemberList.Whatshosts_Member_Join_List_Adapter;
import com.viettel.view.MainHome.MemberList.Whatshosts_Member_Join_List_Adapter.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HocTB
 *  @version: 1.0
 *  @since: Nov 14, 2012
 */
public class AddFriendList extends Activity {
	View menu;
    View app;
	ListView listView;
	ArrayList<UnfamiliarFriendDTO> friendList = new ArrayList<UnfamiliarFriendDTO>();
	ArrayList<UnfamiliarFriendDTO> friendListFound = new ArrayList<UnfamiliarFriendDTO>();
	TextView txtSearch;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		super.onCreate(savedInstanceState);
		LayoutInflater lf = LayoutInflater.from(this);
		app = lf.inflate(R.layout.layout_friend_list, null);
		setContentView(app);
		
		//init actionBar
		ActionBar actionBar = (ActionBar) app.findViewById(R.id.actionbar);
		actionBar.setTitle(getResources().getString(R.string.YOU_CAN_FAMILIAR));
		
		//listview
		initListView();
		
		searchFriend();
		
		final View[] children = new View[] { app };
	}
	
	
	
	/**
	*  Mo ta chuc nang cua ham
	*  @author: HocTB
	*  @return: void
	*  @throws: 
	*/
	private void searchFriend() {
		txtSearch = (TextView) app.findViewById(R.id.txtSearch);
		txtSearch.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String txtSearchString = txtSearch.getText().toString();
				int txtSearchLengh = txtSearchString.length();
				
				friendListFound.clear();
				
				for (int i = 0; i < friendList.size(); i++) {
					if (txtSearchString.equalsIgnoreCase(friendList.get(i).getName().substring(0, txtSearchLengh))) {
						friendListFound.add(friendList.get(i));
					}
				}
				
				//cap nhat lai listview
				listView.setAdapter(new Whatshosts_Friend_List_Adapter(AddFriendList.this, friendListFound));
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
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
	*  @author: HocTB
	*  @return: void
	*  @throws: 
	*/
	private void initListView() {
		// TODO Auto-generated method stub
		listView = (ListView) app.findViewById(R.id.listView);
		listView.setDividerHeight(0);
		listView.setDivider(null);
		
		//data
		friendList.add(new UnfamiliarFriendDTO(BitmapFactory.decodeResource(getResources(), R.drawable.test2), "Hoang Quoc Viet", "Ho Chi Minh", 12));
		friendList.add(new UnfamiliarFriendDTO(BitmapFactory.decodeResource(getResources(), R.drawable.test2), "Cam Tu", "Ho Chi Minh", 12));
		friendList.add(new UnfamiliarFriendDTO(BitmapFactory.decodeResource(getResources(), R.drawable.test2), "Viet Huong", "Ha Noi", 22));
		friendList.add(new UnfamiliarFriendDTO(BitmapFactory.decodeResource(getResources(), R.drawable.test2), "Dinh La Thang", "Hai Phong", 12));
		friendList.add(new UnfamiliarFriendDTO(BitmapFactory.decodeResource(getResources(), R.drawable.test2), "Cao Thai Son", "Ho Chi Minh", 12));
		
		
		listView.setAdapter(new Whatshosts_Friend_List_Adapter(this, friendList));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int postion,long id) {
				
			}
		});
	}

	


	//Create Adapter for listview location
	public class Whatshosts_Friend_List_Adapter extends BaseAdapter{
		private Context mContext;
		private LayoutInflater lf;
		private ArrayList<UnfamiliarFriendDTO> _friendList;
		class ViewHolder{
			ImageView avatar;
			TextView txtName, txtAddress, txtCommonFriend;
		}
		
		public Whatshosts_Friend_List_Adapter(Context context) {
			lf = LayoutInflater.from(context);
		}
		
		
		public Whatshosts_Friend_List_Adapter(Context context, ArrayList<UnfamiliarFriendDTO> list) {
			mContext		=	context;
			lf = LayoutInflater.from(context);
			_friendList	=	list;
		}
		
		
		@Override
		public int getCount() {
			if (_friendList != null) 
				return _friendList.size();
			else
				return 0;
		}

		@Override
		public Object getItem(int position) {
			return _friendList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		
		private void publicView(ViewHolder view, int position) {
			UnfamiliarFriendDTO dto = _friendList.get(position);
			view.avatar.setImageBitmap(dto.getAvatar());
			view.txtName.setText(dto.getName());
			view.txtAddress.setText(dto.getAddress());
			view.txtCommonFriend.setText(" "+dto.getNumCommonFriend()+ " " +getResources().getString(R.string.COMMON_FRIEND));
		}
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			
			if (convertView == null) {
                convertView = lf.inflate(R.layout.layout_add_friend_item, null);
                viewHolder = new ViewHolder();
                viewHolder.avatar 			= (ImageView) convertView.findViewById(R.id.avatar);
                viewHolder.txtName 			= (TextView) convertView.findViewById(R.id.txtName);
                viewHolder.txtAddress 			= (TextView) convertView.findViewById(R.id.txtAddress);
                viewHolder.txtCommonFriend 			= (TextView) convertView.findViewById(R.id.txtNumCommonFriend);
                convertView.setTag(viewHolder);
            } else {
            	viewHolder = (ViewHolder) convertView.getTag();
            }
            publicView(viewHolder,position);
            return convertView;
		}
	}//End Whatshosts_
}
