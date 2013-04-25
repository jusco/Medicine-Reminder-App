package com.example.medicinereminder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

public class RegistrationAppointmentsActivity extends Activity {
	ArrayList<GregorianCalendar> appointments;
	private int currentTag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_appointments);
		appointments = new ArrayList<GregorianCalendar>();
		currentTag=1;
		ArrayList<GregorianCalendar> appts;
		appts = AlarmTracker.getTracker().appointments;
		if(appts != null){
			System.out.println(appts.size());
			if(appts.size()>0){
				DatePicker dp = (DatePicker)findViewById(R.id.EditAppointment);
				GregorianCalendar appt = appts.get(0);
				dp.updateDate(appt.get(GregorianCalendar.YEAR), appt.get(GregorianCalendar.MONTH), 
						appt.get(GregorianCalendar.DATE));
				View apptview = findViewById(R.id.appointmentBoxes);
				for(int i = 1; i<appts.size();i++){
					addAppointmentBox(findViewById(R.id.EditAppointment));
					dp = (DatePicker)apptview.findViewWithTag(Integer.toString(i+1));
					appt = appts.get(i);
					dp.updateDate(appt.get(GregorianCalendar.YEAR), 
							appt.get(GregorianCalendar.MONTH), appt.get(GregorianCalendar.DATE));
				}

			}
		}
		if(!MyGuy.firstTime){
			Button submit = (Button) findViewById(R.id.NextButton5);
			submit.setText("Save");
			Button prev = (Button) findViewById(R.id.PrevButton5);
			prev.setVisibility(View.GONE);
			prev.setClickable(false);
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
		appbox.setCalendarViewShown(false);
		currentTag++;
		appbox.setTag(Integer.toString(currentTag));
		appointments.addView(appbox);
	}

	public void toNextPage(View v){
		LinearLayout apps = (LinearLayout)findViewById(R.id.appointmentBoxes);
		for( int i = 0; i<apps.getChildCount(); i++ ){
			DatePicker dp = (DatePicker) apps.getChildAt(i);
			int year = dp.getYear();
			int month = dp.getMonth();
			int day = dp.getDayOfMonth();
			GregorianCalendar cal = new GregorianCalendar(year,month,day);
			appointments.add(cal);
			Intent intent = new Intent(Intent.ACTION_EDIT);
			intent.setType("vnd.android.cursor.item/event");
			intent.putExtra("beginTime", cal.getTimeInMillis());
			intent.putExtra("allDay", true);
			intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
			intent.putExtra("title", "Appointment!");
			startActivity(intent);
		}
		AlarmTracker.getTracker().setAppt(appointments);
		if(MyGuy.firstTime){
			Intent intent = new Intent(this, RegistrationRefillsActivity.class);
			startActivity(intent);
		}
		else{
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
		}
		finish();
	}


	public void toPrevPage(View v){
		Intent intent = new Intent(this, RegistrationMessageActivity.class);
		startActivity(intent);
		finish();
	}

}
