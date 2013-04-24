package com.example.medicinereminder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class RegistrationStartupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_startup);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration_startup, menu);
		return true;
	}
	
	public void onStart(View v){
		Intent intent;
		if (MyGuy.firstTime) {
			intent = new Intent(this, RegistrationBasicInfoActivity.class);
		} else {
			intent = new Intent(this, HomePageActivity.class);
		}
		startActivity(intent);
		finish();
	}

}
