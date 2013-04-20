package com.example.medicinereminder;

import java.util.ArrayList;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ViewFlipper;


public class RegistrationRemindersActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_reminders);
		NumberPicker np = (NumberPicker) findViewById(R.id.np);
		np.setMaxValue(12);
		np.setMinValue(0);
		String[] vals = new String[13];
		for (int i = 0; i < vals.length; i++) {
			vals[i] = Integer.toString(i * 5) + " min";
		}
		np.setWrapSelectorWheel(false);
		np.setDisplayedValues(vals);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration_reminders, menu);
		return true;
	}
	public void toNextPage(View v){
		
			int remindertime = ((NumberPicker) findViewById(R.id.np)).getValue();
		//Don't set the alarm here either
		//AlarmSet alarmset = new AlarmSet(this);
		//alarmset.setAlarm(remindertime);
		AlarmTracker.getTracker().setReminder(remindertime);
		Intent intent = new Intent(this, RegistrationMessageActivity.class);
		startActivity(intent);
		finish();
	}
	

	public void toPrevPage(View v){
		Intent intent = new Intent(this, RegistrationMedicationActivity.class);
		startActivity(intent);
		finish();
	}

}
