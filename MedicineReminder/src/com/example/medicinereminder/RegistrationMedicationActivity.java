package com.example.medicinereminder;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.medicinereminder.MainActivity.TimePickerFragment;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.ViewFlipper;

public class RegistrationMedicationActivity extends FragmentActivity {
	protected static String lastTime;
	protected static ArrayList<int[]> alarmtimes;
	protected static EditText lastTimeBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_medication);
		alarmtimes = new ArrayList<int[]>();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration_medication, menu);
		return true;
	}
	
	public void showTimePickerDialog(View v) {
		DialogFragment newFragment = new TimePickerFragment();
		newFragment.show(getSupportFragmentManager(), "timePicker");
		lastTimeBox = (EditText) v.findViewById(R.id.EditTime);
	}

	public static class TimePickerFragment extends DialogFragment
	implements TimePickerDialog.OnTimeSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
	        int hour = c.get(Calendar.HOUR_OF_DAY);
	        int minute = c.get(Calendar.MINUTE);


			// Create a new instance of DatePickerDialog and return it
	        return new TimePickerDialog(getActivity(), this, hour, minute,
	                DateFormat.is24HourFormat(getActivity()));

		}
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			int [] arr = {hourOfDay,minute};
			alarmtimes.add(arr);
		}
	}
	
	public void toNextPage(View v){
		ArrayList<String> medicineNames = new ArrayList<String>();
		LinearLayout medicines = (LinearLayout)findViewById(R.id.medicineBoxes);
		for( int i = 0; i<medicines.getChildCount(); i++ )
		    medicineNames.add(((EditText) medicines.getChildAt(i)).getText().toString());
		
		MyGuy.getUser().setAlarmTime(alarmtimes);
		MyGuy.getUser().setAlarmCount(0);
		//AlarmSet alarmset = new AlarmSet(this);
		//alarmset.setAlarm(remindertime); //Do this when we get the reminder time
		Intent intent;
		if(android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.HONEYCOMB)
			intent = new Intent(this, RegistrationRemindersActivity.class);
		else
			intent = new Intent(this, RegistrationRemindersGBActivity.class);
		
		startActivity(intent);
		finish();
	}
	
	public void toPrevPage(View v){
		Intent intent = new Intent(this, RegistrationBasicInfoActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void addMedicineBox(View v){
		LinearLayout medicines = (LinearLayout) findViewById(R.id.medicineBoxes);
		EditText medicinebox = new EditText(this);
		medicinebox.setText("Medicine Name");
		medicines.addView(medicinebox);
		
//		LinearLayout times = (LinearLayout) findViewById(R.id.timeBoxes);
//		EditText timebox = new EditText(this);
//		timebox.setText("Time");
//		timebox.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);
//		times.addView(timebox);
	}

}
