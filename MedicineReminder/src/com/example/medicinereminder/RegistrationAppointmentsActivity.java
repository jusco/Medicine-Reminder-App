package com.example.medicinereminder;

import java.util.ArrayList;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

public class RegistrationAppointmentsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_appointments);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration_appointments, menu);
		return true;
	}
	
	public void addAppointmentBox(View v){
		LinearLayout appointments = (LinearLayout) findViewById(R.id.appointmentBoxes);
		DatePicker appbox = new DatePicker(this);
		appbox.setCalendarViewShown(false);
		appointments.addView(appbox);
	}
	
	public void toNextPage(View v){
		//TODO Need to removed deprecated constructor for Date.
		ArrayList<Date> appointments = new ArrayList<Date>();
		LinearLayout apps = (LinearLayout)findViewById(R.id.appointmentBoxes);
		for( int i = 0; i<apps.getChildCount(); i++ ){
			DatePicker dp = (DatePicker) apps.getChildAt(i);
			appointments.add(new Date(dp.getYear(),dp.getMonth(),dp.getDayOfMonth()));
		}
		
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
