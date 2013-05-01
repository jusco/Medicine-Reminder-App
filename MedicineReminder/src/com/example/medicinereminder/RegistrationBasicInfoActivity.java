package com.example.medicinereminder;

import java.util.ArrayList;
import java.util.Calendar;


import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class RegistrationBasicInfoActivity extends FragmentActivity  {
	//Changed for testing
	public static String date;
	protected static EditText dateText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_basic_info);

		//If we are returning back to this page - regenerate info
		if(MyGuy.getUser().firstName != null){
			EditText firstNameText = (EditText)findViewById(R.id.EditFirstName);
			firstNameText.setText(MyGuy.getUser().firstName);
		}
		if(MyGuy.getUser().lastName != null){
			EditText lastNameText = (EditText)findViewById(R.id.EditLastName);
			lastNameText.setText(MyGuy.getUser().lastName);
		}
		if(MyGuy.getUser().diagDate != null){
			EditText diagDateText = (EditText)findViewById(R.id.EditDate);
			diagDateText.setText(MyGuy.getUser().diagDate);
		}
		if(MyGuy.getUser().viralCount != null){
			EditText viralLoadText = (EditText)findViewById(R.id.EditViralLoad);
			viralLoadText.setText(MyGuy.getUser().viralCount);
		}
		if(MyGuy.getUser().phoneNumer != null){
			EditText phoneText = (EditText)findViewById(R.id.EditPhone);
			phoneText.setText(MyGuy.getUser().phoneNumer);
		}
		if(MyGuy.getUser().providerPhoneNumber != null){
			EditText providerText = (EditText)findViewById(R.id.EditProviderPhone);
			providerText.setText(MyGuy.getUser().providerPhoneNumber);
		}
		if(!MyGuy.firstTime){
			Button submit = (Button) findViewById(R.id.NextButton);
			submit.setText("Save");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration_basic_info, menu);
		return true;
	}

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		FragmentManager fg= getSupportFragmentManager();
		newFragment.show(fg, "datePicker");
		dateText = (EditText)findViewById(R.id.EditDate);
		setDateText();
	}

	public static class DatePickerFragment extends DialogFragment
	implements DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			date = Integer.toString(month+1)+ "/" + Integer.toString(day) +
					"/" + Integer.toString(year);
			dateText.setText(date);
		}
	}

	public void toNextPage(View v){
		String firstName = ((EditText) findViewById(R.id.EditFirstName)).getText().toString();
		String lastName = ((EditText) findViewById(R.id.EditLastName)).getText().toString();
		String viralLoad = ((EditText) findViewById(R.id.EditViralLoad)).getText().toString();
		String phone = ((EditText) findViewById(R.id.EditPhone)).getText().toString();
		String provider = ((EditText) findViewById(R.id.EditProviderPhone)).getText().toString();
		//Set Static User Attributes based off submission form
		if (validate(firstName,lastName,viralLoad,phone,provider)){
			MyGuy.getUser().setFirstName(firstName);
			MyGuy.getUser().setLastName(lastName);
			MyGuy.getUser().setPhoneNumer(phone);
			MyGuy.getUser().setProviderPhoneNumer(provider);
			MyGuy.getUser().setViralCount(viralLoad);
			MyGuy.getUser().setDiagDate(date);
			if(MyGuy.firstTime){
				Intent intent = new Intent(this, RegistrationMedicationActivity.class);
				startActivity(intent);
			}
			else{
				Intent intent = new Intent(this, SettingsActivity.class);
				startActivity(intent);
			}
			finish();
		}
	}

	//Changed to public for testing
	public boolean validate(String firstName, String lastName, String viralLoad, 
			String phone, String provider){
		Dialog dialog = new Dialog(this);
		dialog.setTitle("Error");
		TextView box = new TextView(this);
		dialog.setContentView(box);
		if (firstName==null || firstName.trim().equals("")){
			box.setText("Please enter your first name.");
			dialog.show();
			return false;
		}
		if (lastName==null || lastName.trim().equals("")){
			box.setText("Please enter your last name.");
			dialog.show();
			return false;
		}
		if(date==null){
			box.setText("Please choose a date.");
			dialog.show();
			return false;
		}
		if(viralLoad==null || viralLoad.equals("") || !viralLoad.matches("\\d+")){
			box.setText("Please enter your viral load.");
			dialog.show();
			return false;
		}
		if(phone==null || phone.equals("") || !phone.matches("[0-9]{3}[-\\s]?[0-9]{3}[-\\s]?[0-9]{4}")){
			box.setText("Please enter a correct phone number.");
			dialog.show();
			return false;
		}
		if(provider==null || provider.equals("") || !provider.matches("[0-9]{3}[-\\s]?[0-9]{3}[-\\s]?[0-9]{4}")){
			box.setText("Please enter a correct provider's phone number.");
			dialog.show();
			return false;
		}

		return true;
	}

	private void setDateText(){
		EditText diagDateText = (EditText)findViewById(R.id.EditDate);
		diagDateText.setText(date);
	}



}
