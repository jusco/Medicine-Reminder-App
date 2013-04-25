package com.example.medicinereminder;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

public class RegistrationRefillsActivity extends Activity {
	ArrayList<GregorianCalendar> refills;
	private int currentTag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_refills);

		refills = new ArrayList<GregorianCalendar>();
		currentTag=1;
		ArrayList<GregorianCalendar> refill;
		refill = AlarmTracker.getTracker().refills;
		if(refill != null){
			if(refill.size()>0){
				DatePicker dp = (DatePicker)findViewById(R.id.EditRefill);
				GregorianCalendar ref = refill.get(0);
				dp.updateDate(ref.get(GregorianCalendar.YEAR), ref.get(GregorianCalendar.MONTH), 
						ref.get(GregorianCalendar.DATE));
				View refillview = findViewById(R.id.refillBoxes);
				for(int i = 1; i<refill.size();i++){
					addRefillBox(findViewById(R.id.EditRefill));
					dp = (DatePicker)refillview.findViewWithTag(Integer.toString(i+1));
					ref = refill.get(i);
					dp.updateDate(ref.get(GregorianCalendar.YEAR), 
							ref.get(GregorianCalendar.MONTH), ref.get(GregorianCalendar.DATE));
				}

			}
		}
		if(!MyGuy.firstTime){
			Button submit = (Button) findViewById(R.id.NextButton6);
			submit.setText("Save");
			Button prev = (Button) findViewById(R.id.PrevButton6);
			prev.setVisibility(View.GONE);
			prev.setClickable(false);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration_refills, menu);
		return true;
	}

	public void toNextPage(View v){
		LinearLayout refs = (LinearLayout)findViewById(R.id.refillBoxes);
		for( int i = 0; i<refs.getChildCount(); i++ ){
			DatePicker dp = (DatePicker) refs.getChildAt(i);
			refills.add(new GregorianCalendar(dp.getYear(),dp.getMonth(),dp.getDayOfMonth()));
		}
		AlarmTracker.getTracker().setRefill(refills);
		if(MyGuy.firstTime){
			Intent intent = new Intent(this, RegistrationAvatarActivity.class);
			startActivity(intent);
		}
		else{
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
		}
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
		if(android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.HONEYCOMB)
			refbox.setCalendarViewShown(false);
		currentTag++;
		refbox.setTag(Integer.toString(currentTag));
		refills.addView(refbox);
	}

}
