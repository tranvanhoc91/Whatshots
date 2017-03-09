package com.viettel.view.MainHome;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.korovyansk.android.slideout.SlideoutActivity;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;
import com.viettel.R;
import com.viettel.utils.AlertDialogManager;
import com.viettel.view.common.CommonView;
import com.viettel.view.common.ViewUtils;
import com.whathots.menu.MenuActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: VietHQ
 *  @version: 1.0
 *  @since: May 17, 2012
 */
public class CreateNewEvent extends Activity {
    View menu;
    View app;
    Handler handle = new Handler();
    EditText txt_event_name;
    EditText txt_event_description;
    EditText txt_event_location;
    Button btnTimeStart, btnDateStart, btnTimeEnd, btnDateEnd;
    int sHour,sMinute, sYear, sMonth, sDay;
    int eHour,eMinute, eYear, eMonth, eDay;
    ImageButton btnOpenSelectPlace, btnOpenSelectCategory;
    
    static final int TIME_START_DIALOG_ID = 1;
    static final int TIME_END_DIALOG_ID = 2;
    static final int DATE_START_DIALOG_ID = 3;
    static final int DATE_END_DIALOG_ID = 4;
    AlertDialogManager alertDialog = new AlertDialogManager();
    
    Calendar calendar = Calendar.getInstance();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        app = inflater.inflate(R.layout.layout_create_new_event, null);
        setContentView(app);

        bindComponents();
        
        Bundle bumdle = getIntent().getExtras();
        if (bumdle != null) {
        	String placeDetail = bumdle.getString("EVENT_PLACE");
            txt_event_location.setText(placeDetail);
		}
        
        final View[] children = new View[] { app };
    }

	/**
	*  Mo ta chuc nang cua ham
	*  @author: HocTB
	*  @return: void
	*  @throws: 
	*/
	private void bindComponents() {
		  //actionBar
        ActionBar actionBar = (ActionBar) app.findViewById(R.id.actionbar);
        actionBar.setTitle(getString(R.string.EVENT_CREATE_NEW));
        txt_event_name = (EditText) app.findViewById(R.id.txt_event_name);
	    txt_event_description = (EditText) app.findViewById(R.id.txt_event_description);;
	    txt_event_location = (EditText) app.findViewById(R.id.txt_event_location);
	    btnTimeStart = (Button) app.findViewById(R.id.btnTimeStart);
	    btnTimeEnd = (Button) app.findViewById(R.id.btnTimeEnd);
	    btnDateStart = (Button) app.findViewById(R.id.btnDateStart);
	    btnDateEnd = (Button) app.findViewById(R.id.btnDateEnd);
	    
	    btnOpenSelectCategory = (ImageButton) app.findViewById(R.id.btnOpenSelectCategory);
	    btnOpenSelectCategory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    
	    btnOpenSelectPlace = (ImageButton) app.findViewById(R.id.btnOpenSelectPlace);
	    btnOpenSelectPlace.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(CreateNewEvent.this, SelectEventPlace.class));
			}
		});
	    
	    setCurrentDateTimeOnView();
	    btnTimeStart.setOnClickListener(TimeStartListener);
	    btnTimeEnd.setOnClickListener(TimeEndListener);
	    btnDateStart.setOnClickListener(DateStartListener);
	    btnDateEnd.setOnClickListener(DateEndListener);
	}
    
	private OnClickListener TimeStartListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			showDialog(TIME_START_DIALOG_ID);
		}
	};
	
	private OnClickListener TimeEndListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			showDialog(TIME_END_DIALOG_ID);
		}
	};
	
	private OnClickListener DateStartListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			showDialog(DATE_START_DIALOG_ID);
		}
	};
	
	private OnClickListener DateEndListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			showDialog(DATE_END_DIALOG_ID);
		}
	};
	
	
	/**
	*  Mo ta chuc nang cua ham
	*  @author: HocTb
	*  @return: void
	*  @throws: 
	*/
	private void setCurrentDateTimeOnView() {
		eHour = sHour = calendar.get(Calendar.HOUR_OF_DAY);
		eMinute = sMinute = calendar.get(Calendar.MINUTE);
		
		sYear = eYear = calendar.get(Calendar.YEAR);
		sMonth = eMonth = calendar.get(Calendar.MONTH);
		sDay = eDay = calendar.get(Calendar.DAY_OF_MONTH);
				
		// set current time into textview
		updateTimeStartLable();
		updateTimeEndLable();
		updateDateStartLable();
		updateDateEndLable();
	}

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
	
	
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			case TIME_START_DIALOG_ID:
				// set time picker as current time
				return new TimePickerDialog(this, timeStartPicker, sHour, sMinute,
						false);
			case TIME_END_DIALOG_ID:
				// set time picker as current time
				return new TimePickerDialog(this, timeEndPicker, eHour, eMinute,
						false);
			case DATE_START_DIALOG_ID:
				return new DatePickerDialog(this, 
						dateStartPicker, sYear, sMonth, sDay);
			case DATE_END_DIALOG_ID:
				return new DatePickerDialog(this, 
						dateEndPicker, eYear, eMonth, eDay);
		}
		
		return null;
	}
	
	
	private TimePickerDialog.OnTimeSetListener timeStartPicker = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			
			//Kiem tra ngay/thang/nam bat dau va ket thuc truc. ROi moi kiem tra den gio/phut
			int checkDate = DateStartValidateDateEnd(sYear, sMonth, sDay, eYear, eMonth, eDay);
			if (checkDate == 1) { //ngay bat dau < ngay ket thuc
				sHour = selectedHour;
				sMinute = selectedMinute;
				updateTimeStartLable();
			}else if (checkDate == 0) { // ngay bat dau = ngay ket thuc
				//kiem tra hour/minute
				int checkTime = TimeStartValidateTimeEnd(selectedHour, selectedMinute, eHour, eMinute);
				if (checkTime == -1) { //hour/minute bat dau > hour/minute ket thuc
					alertDialog.show(CreateNewEvent.this, getResources().getString(R.string.ERROR), 
							getResources().getString(R.string.ERROR_START_END_TIME_EVENT), false);
				}else{
					sHour = selectedHour;
					sMinute = selectedMinute;
					updateTimeStartLable();
				}
			}
		}
	};
	
	private TimePickerDialog.OnTimeSetListener timeEndPicker = new TimePickerDialog.OnTimeSetListener() {
		
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			
			int checkDate = DateStartValidateDateEnd(sYear, sMonth, sDay, eYear, eMonth, eDay);
			if (checkDate == 1) { //ngay bat dau < ngay ket thuc
				eHour = selectedHour;
				eMinute = selectedMinute;
				updateTimeEndLable();
			}else if (checkDate == 0) { // ngay bat dau = ngay ket thuc
				//kiem tra hour/minute
				int checkTime = TimeStartValidateTimeEnd(sHour, sMinute, selectedHour, selectedMinute);
				if (checkTime == -1) { //hour/minute bat dau > hour/minute ket thuc
					alertDialog.show(CreateNewEvent.this, getResources().getString(R.string.ERROR), 
							getResources().getString(R.string.ERROR_START_END_TIME_EVENT), false);
				}else{
					eHour = selectedHour;
					eMinute = selectedMinute;
					updateTimeEndLable();
				}
			}
		}
	};
	
	
	private DatePickerDialog.OnDateSetListener dateStartPicker = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			//Kiem tra xem ngay/thang/nam bat dau co nho hon ngay/thang/nam ket  thuc khong
			int result = DateStartValidateDateEnd(year, monthOfYear, dayOfMonth, eYear, eMonth, eDay);
			
			if (result == -1) { //tuc : ngay bat dau lon hon ngay ket thuc
				alertDialog.show(CreateNewEvent.this, getResources().getString(R.string.ERROR), 
						getResources().getString(R.string.ERROR_START_END_TIME_EVENT), false);
			}else if (result == 0) { //ngay/thang/nam bat dau = ngay/thang/nam ket thuc. Can kiem tra them xem hour/minute bat dau co dang lon hon hour/minute khong? (dc setup tu truoc) 
				int checkTime = TimeStartValidateTimeEnd(sHour, sMinute, eHour, eMinute);
				if (checkTime == -1) { //hour/minute bat dau > hour/minute ket thuc
					//set lai time bat dau va time ket thuc hien tai
					eHour = sHour = calendar.get(Calendar.HOUR_OF_DAY);
					eMinute = sMinute = calendar.get(Calendar.MINUTE);
					// set current time into textview
					updateTimeStartLable();
					updateTimeEndLable();
				}
				
				sYear = year;
				sMonth = monthOfYear;
				sDay = dayOfMonth;
				updateDateStartLable();
			}else{
				sYear = year;
				sMonth = monthOfYear;
				sDay = dayOfMonth;
				updateDateStartLable();
			}
		}
	};
	
	private DatePickerDialog.OnDateSetListener dateEndPicker = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			
			int result = DateStartValidateDateEnd(sYear, sMonth, sDay, year, monthOfYear, dayOfMonth);
			
			if (result == -1) { //tuc : ngay ket thuc < ngay bat dau
				alertDialog.show(CreateNewEvent.this, getResources().getString(R.string.ERROR), 
						getResources().getString(R.string.ERROR_START_END_TIME_EVENT), false);
			}else if (result == 0) {
				int checkTime = TimeStartValidateTimeEnd(sHour, sMinute, eHour, eMinute);
				if (checkTime == -1) { //hour/minute bat dau > hour/minute ket thuc
					//set lai time bat dau va time ket thuc hien tai
					eHour = sHour = calendar.get(Calendar.HOUR_OF_DAY);
					eMinute = sMinute = calendar.get(Calendar.MINUTE);
					// set current time into textview
					updateTimeStartLable();
					updateTimeEndLable();
				}
				
				eYear = year;
				eMonth = monthOfYear;
				eDay = dayOfMonth;
				updateDateEndLable();
			}else {
				eYear = year;
				eMonth = monthOfYear;
				eDay = dayOfMonth;
				updateDateEndLable();
			}
		}
	};
	
	private void updateTimeStartLable() {
		btnTimeStart.setText(new StringBuilder().append(pad(sHour)).append(":")
				.append(pad(sMinute)));
	}
	
	private void updateTimeEndLable() {
		btnTimeEnd.setText(new StringBuilder().append(pad(eHour)).append(":")
				.append(pad(eMinute)));
	}
	
	private void updateDateStartLable() {
		if (sYear == calendar.get(Calendar.YEAR) && sMonth == calendar.get(Calendar.MONTH) && sDay == calendar.get(Calendar.DAY_OF_MONTH)) {
			btnDateStart.setText(getResources().getString(R.string.TODAY));
		}else{
			btnDateStart.setText(new StringBuilder().append(pad(sDay)).append("/").append(pad(sMonth)).append("/").append(pad(sYear)));
		}
	}
	
	private void updateDateEndLable() {
		if (eYear == calendar.get(Calendar.YEAR) && eMonth == calendar.get(Calendar.MONTH) && eDay == calendar.get(Calendar.DAY_OF_MONTH)) {
			btnDateEnd.setText(getResources().getString(R.string.TODAY));
		}else{
			btnDateEnd.setText(new StringBuilder().append(pad(eDay)).append("/").append(pad(eMonth)).append("/").append(pad(eYear)));
		}
	}
	
	
	/**
	 * Kiem tra xem ngay/thang/nam bat dau so voi ngay/thang/nam ket thuc
	 * @param startYear
	 * @param startMonth
	 * @param startDay
	 * @param endYear
	 * @param endMonth
	 * @param endDay
	 * @return  1 neu ngay/thang/nam bat dau < ngay/thang/nam ket thuc
	 * 			0 neu ngay/thang/nam bat dau = ngay/thang/nam ket thuc
	 * 			-1 neu ngay/thang/nam bat dau > ngay/thang/nam ket thuc
	 */
	private int DateStartValidateDateEnd(int startYear, int startMonth,int startDay, int endYear,int  endMonth,int endDay){
		int result = 0;
		if (startYear < endYear) {
			result = 1;
		}else if (startYear > endYear) {
			result = -1;
		}else if (startYear == endYear) {
			if (startMonth < endMonth) {
				result = 1;
			}else if (startMonth > endMonth) {
				result = -1;
			}else if (startMonth == endMonth) {
				if (startDay < endDay) {
					result = 1;
				}else if (startDay > endDay) {
					result = -1;
				}else if (startDay == endDay) {
					result = 0;
				}
			}
		}
		
		return result;
	}
	
	
	/**
	 * Kiem tra hour/minute bat dau co hop le so voi hour/minute ket thuc hay khong?
	 * @param startHour
	 * @param startMinute
	 * @param endHour
	 * @param endMinute
	 * @return
	 */
	private int TimeStartValidateTimeEnd(int startHour,int  startMinute,int endHour,int endMinute){
		int result = 0;
		
		if (startHour < endHour) {
			result = 1;
		}else if (startHour > endHour) {
			result = -1;
		}else if (startHour == endHour){
			if (startMinute < endMinute) {
				result = 1;
			}else {
				result = -1;
			}
		}
		
		return result;
	}
}
