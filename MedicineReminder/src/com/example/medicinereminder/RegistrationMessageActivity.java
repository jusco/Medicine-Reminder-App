package com.example.medicinereminder;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ViewFlipper;

public class RegistrationMessageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_message);
		
		if(AlarmTracker.getTracker().alarmMessage != null){
			EditText text = (EditText) findViewById(R.id.EditReminderMessage);
			text.setText(AlarmTracker.getTracker().alarmMessage);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration_message, menu);
		return true;
	}
	
	public void toNextPage(View v){
		String custommessage = ((EditText) findViewById(R.id.EditReminderMessage)).getText().toString();
		AlarmTracker.getTracker().setAlarmMessage(custommessage);
		Intent intent = new Intent(this, RegistrationAppointmentsActivity.class);
		startActivity(intent);
		finish();
	}
	
	
	
	
	public void toPrevPage(View v){
		Intent intent;
		if(android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.HONEYCOMB)
			intent = new Intent(this, RegistrationRemindersActivity.class);
		else
			intent = new Intent(this, RegistrationRemindersGBActivity.class);
		
		startActivity(intent);
		finish();
	}

}
