package com.example.medicinereminder;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class HomePageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		
		TextView fullNameText = (TextView)findViewById(R.id.fullNameView);
		TextView phoneNumberText = (TextView)findViewById(R.id.phoneNumberView);

		fullNameText.setText(MyGuy.getUser().firstName);
		phoneNumberText.setText(MyGuy.getUser().phoneNumer);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		String temp = "";
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home_page, menu);
		return true;
	}

}
