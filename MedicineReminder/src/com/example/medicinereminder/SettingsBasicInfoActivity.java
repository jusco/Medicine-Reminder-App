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
import android.widget.TextView;

public class SettingsBasicInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_basic_info);
		
		EditText firstNameText = (EditText)findViewById(R.id.EditFirstName1);
		firstNameText.setText(MyGuy.getUser().firstName);
		
		EditText lastNameText = (EditText)findViewById(R.id.EditLastName1);
		lastNameText.setText(MyGuy.getUser().lastName);
		
		EditText viralLoadText = (EditText)findViewById(R.id.EditViralLoad1);
		viralLoadText.setText(MyGuy.getUser().firstName);
		
		EditText phoneText = (EditText)findViewById(R.id.EditPhone1);
		phoneText.setText(MyGuy.getUser().phoneNumer);
		
		EditText providerText = (EditText)findViewById(R.id.EditPhone1);
		providerText.setText(MyGuy.getUser().firstName);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_settings_basic_info, menu);
		return true;
	}
	
	public void saveInfo(View v){
		String firstName = ((EditText) findViewById(R.id.EditFirstName1)).getText().toString();
		String lastName = ((EditText) findViewById(R.id.EditLastName1)).getText().toString();
		String viralLoad = ((EditText) findViewById(R.id.EditViralLoad1)).getText().toString();
		String phone = ((EditText) findViewById(R.id.EditPhone1)).getText().toString();
		String provider = ((EditText) findViewById(R.id.EditProviderPhone1)).getText().toString();
		
		
		
		//Set Static User Attributes based off submission form
		MyGuy.getUser().setFirstName(firstName);
		MyGuy.getUser().setLastName(lastName);
		MyGuy.getUser().setPhoneNumer(phone);
	}

}
