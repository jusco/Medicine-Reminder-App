package com.example.medicinereminder;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.medicinereminder.MainActivity.DatePickerFragment;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ViewFlipper;

public class RegistrationBasicInfoActivity extends FragmentActivity  {
	protected static String date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_basic_info);
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
			date = Integer.toString(year)+Integer.toString(month)+Integer.toString(day);
		}
	}
	
	public void toNextPage(View v){
		String firstName = ((EditText) findViewById(R.id.EditFirstName)).getText().toString();
		String lastName = ((EditText) findViewById(R.id.EditLastName)).getText().toString();
		String infectionDate = date;
		String viralLoad = ((EditText) findViewById(R.id.EditViralLoad)).getText().toString();
		String phone = ((EditText) findViewById(R.id.EditPhone)).getText().toString();
		String provider = ((EditText) findViewById(R.id.EditProviderPhone)).getText().toString();
		//Set Static User Attributes based off submission form
		MyGuy.getUser().setFirstName(firstName);
		MyGuy.getUser().setLastName(lastName);
		MyGuy.getUser().setPhoneNumer(phone);
		MyGuy.getUser().setProviderPhoneNumer(provider);
		MyGuy.getUser().setViralCount(viralLoad);
		Intent intent = new Intent(this, RegistrationMedicationActivity.class);
		startActivity(intent);
		finish();
	}
	


}
