package com.example.medicinereminder;

import java.util.ArrayList;
import java.util.Date;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

public class RegistrationAppointmentsActivity extends Activity {
	ArrayList<Date> appointments;
	private int currentTag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_appointments);
		appointments = new ArrayList<Date>();
		currentTag=1;
		ArrayList<Date> appts;
		appts = AlarmTracker.getTracker().appointments;
		if(appts != null){
			System.out.println(appts.size());
			if(appts.size()>0){
				DatePicker dp = (DatePicker)findViewById(R.id.EditAppointment);
				dp.updateDate(appts.get(0).getYear(), appts.get(0).getMonth(), appts.get(0).getDate());
				View apptview = findViewById(R.id.appointmentBoxes);
				for(int i = 1; i<appts.size();i++){
					addAppointmentBox(findViewById(R.id.EditAppointment));
					dp = (DatePicker)apptview.findViewWithTag(Integer.toString(i+1));
					dp.updateDate(appts.get(i).getYear(), appts.get(i).getMonth(), appts.get(i).getDate());
				}
				
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration_appointments, menu);
		return true;
	}
	
	@SuppressLint("NewApi")
	public void addAppointmentBox(View v){
		LinearLayout appointments = (LinearLayout) findViewById(R.id.appointmentBoxes);
		DatePicker appbox = new DatePicker(this);
		//appbox.setCalendarViewShown(false);
		currentTag++;
		appbox.setTag(Integer.toString(currentTag));
		appointments.addView(appbox);
	}
	
	public void toNextPage(View v){
		//TODO Need to removed deprecated constructor for Date.
		LinearLayout apps = (LinearLayout)findViewById(R.id.appointmentBoxes);
		for( int i = 0; i<apps.getChildCount(); i++ ){
			DatePicker dp = (DatePicker) apps.getChildAt(i);
			appointments.add(new Date(dp.getYear(),dp.getMonth(),dp.getDayOfMonth()));
		}
		AlarmTracker.getTracker().setAppt(appointments);
		
		Intent intent = new Intent(this, RegistrationRefillsActivity.class);
		startActivity(intent);
		finish();
	}
	

	public void toPrevPage(View v){
		Intent intent = new Intent(this, RegistrationMessageActivity.class);
		startActivity(intent);
		finish();
	}

}
