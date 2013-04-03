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

public class RegistrationRefillsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_refills);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration_refills, menu);
		return true;
	}
	
	public void toNextPage(View v){
		
		//TODO Need to removed deprecated constructor for Date.
		ArrayList<Date> refills = new ArrayList<Date>();
		LinearLayout refs = (LinearLayout)findViewById(R.id.refillBoxes);
		for( int i = 0; i<refs.getChildCount(); i++ ){
			DatePicker dp = (DatePicker) refs.getChildAt(i);
			refills.add(new Date(dp.getYear(),dp.getMonth(),dp.getDayOfMonth()));
		}
		
		Intent intent = new Intent(this, RegistrationAvatarActivity.class);
		startActivity(intent);
		finish();
	}
	

	public void toPrevPage(View v){
		Intent intent = new Intent(this, RegistrationAppointmentsActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void addRefillBox(View v){
		LinearLayout refills = (LinearLayout) findViewById(R.id.refillBoxes);
		DatePicker refbox = new DatePicker(this);
		refbox.setCalendarViewShown(false);
		refills.addView(refbox);
}

}
