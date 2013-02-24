package com.example.medicinereminder;

import java.util.Calendar;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicinereminder.sql.*;

public class MainActivity extends Activity {
	
	Button submitButton;
	DatabaseAccess dbAccess;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		submitButton = (Button)findViewById(R.id.SubmitButton);        
		submitButton.setOnClickListener(submitButtonListener); 
		
		dbAccess = new DatabaseAccess(this);
		dbAccess.open();
		CursorHolder input = dbAccess.addLog("Started app", "7:50PM");
		System.out.println(input.getInt(0)+ " " + input.getString(1) + " " + input.getString(2));
		
	}
	
	//Submit Form button listner
	private OnClickListener submitButtonListener = new OnClickListener() {
        public void onClick(View v) {
//          Toast.makeText(this, "Submitting Form", Toast.LENGTH_LONG).show();
        	EditText firstName = (EditText)findViewById(R.id.EditFirstName);
        	EditText viralLoad = (EditText)findViewById(R.id.EditViralLoad);
        	EditText providerNumber = (EditText)findViewById(R.id.EditProviderPhone);
        	
        	
//        	String providerNumberString = viralLoad.getText().toString();
//        	int viralLoadInt = Integer.getInteger(providerNumberString);
        	
        	dbAccess.addUserData(firstName.getText().toString(), 19, 22, 999, "Doctor Dre", providerNumber.getText().toString());
        	dbAccess.printToConsole();
        }
    };

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	

	@SuppressLint("NewApi")
	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}

	@SuppressLint("NewApi")
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
			// Do something with the date chosen by the user
		}
	}
	


}
