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
import android.widget.EditText;
import android.widget.LinearLayout;

public class RegistrationRefillsActivity extends Activity {
	ArrayList<Date> refills;
	private int currentTag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_refills);
		
		refills = new ArrayList<Date>();
		currentTag=1;
		ArrayList<Date> refill;
		refill = AlarmTracker.getTracker().refills;
		if(refill != null){
			if(refill.size()>0){
				DatePicker dp = (DatePicker)findViewById(R.id.EditRefill);
				dp.updateDate(refill.get(0).getYear(), refill.get(0).getMonth(), refill.get(0).getDate());
				View refillview = findViewById(R.id.refillBoxes);
				for(int i = 1; i<refill.size();i++){
					addRefillBox(findViewById(R.id.EditRefill));
					dp = (DatePicker)refillview.findViewWithTag(Integer.toString(i+1));
					dp.updateDate(refill.get(i).getYear(), refill.get(i).getMonth(), refill.get(i).getDate());
				}
				
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration_refills, menu);
		return true;
	}
	
	public void toNextPage(View v){
		
		//TODO Need to removed deprecated constructor for Date.
		LinearLayout refs = (LinearLayout)findViewById(R.id.refillBoxes);
		for( int i = 0; i<refs.getChildCount(); i++ ){
			DatePicker dp = (DatePicker) refs.getChildAt(i);
			refills.add(new Date(dp.getYear(),dp.getMonth(),dp.getDayOfMonth()));
		}
		AlarmTracker.getTracker().setRefill(refills);
		Intent intent = new Intent(this, RegistrationAvatarActivity.class);
		startActivity(intent);
		finish();
	}
	

	public void toPrevPage(View v){
		Intent intent = new Intent(this, RegistrationAppointmentsActivity.class);
		startActivity(intent);
		finish();
	}
	
	@SuppressLint("NewApi")
	public void addRefillBox(View v){
		LinearLayout refills = (LinearLayout) findViewById(R.id.refillBoxes);
		DatePicker refbox = new DatePicker(this);
		//refbox.setCalendarViewShown(false);
		currentTag++;
		refbox.setTag(Integer.toString(currentTag));
		refills.addView(refbox);
}

}
