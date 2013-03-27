package com.example.medicinereminder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_settings, menu);
		return true;
	}
	
	public void editBasicInfo(View v){
		Intent intent = new Intent(this, SettingsBasicInfoActivity.class);
		startActivity(intent);
	}	
	
	public void editReminders(View v){
		Intent intent = new Intent(this, SettingsRemindersActivity.class);
		startActivity(intent);
	}	
	
	public void editRefills(View v){
		Intent intent = new Intent(this, SettingsRefillsActivity.class);
		startActivity(intent);
	}	
	
	public void editAppointments(View v){
		Intent intent = new Intent(this, SettingsAppointmentsActivity.class);
		startActivity(intent);
	}	
	
	public void editMedications(View v){
		Intent intent = new Intent(this, SettingsMedicationActivity.class);
		startActivity(intent);
	}	

}
