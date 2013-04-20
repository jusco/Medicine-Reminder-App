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
import android.text.InputType;
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
	private int currentTag;//keeps track of the tags to assign to each box
	private int medicineTag;
	private static String lastTag; //Keeps track of the selected timebox

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_medication);
		alarmtimes = new ArrayList<int[]>();
		currentTag = 1;
		medicineTag = 1;
		ArrayList<String> meds;
		meds = AlarmTracker.getTracker().medicines;
		if(meds != null){
			if(meds.size() > 0){
				EditText editText = (EditText)findViewById(R.id.EditMedicine);
				editText.setText(meds.get(0));
				View medview = findViewById(R.id.medicineBoxes);
				for(int i = 1; i<meds.size();i++){
					addMedicineBox(findViewById(R.id.EditMedicine));
					editText = (EditText)medview.findViewWithTag(Integer.toString(i+1));
					editText.setText(meds.get(i));
				}
			}	
		}
		ArrayList<int[]> times;
		times = AlarmTracker.getTracker().alarmTimes;
		if(times != null){
			if(times.size()>0){
				EditText editText = (EditText)findViewById(R.id.EditTime);
				editText.setText(convertToString(times.get(0)[0],times.get(0)[1]));
				alarmtimes.add(times.get(0));
				View timeview = findViewById(R.id.timeBoxes);
				for(int i = 1; i<times.size();i++){
					addTimeBox(findViewById(R.id.EditTime));
					editText = (EditText)timeview.findViewWithTag(Integer.toString(i+1));
					editText.setText(convertToString(times.get(i)[0],times.get(i)[1]));
					alarmtimes.add(times.get(i));
				}
				
			}
		}
		
	}
	
	public String convertToString(int hourOfDay, int minute){
		String output = "";
		String half = "am";
		String zero = "";
		if (hourOfDay>12){
			hourOfDay -= 12;
			half = "pm";
		}
		if(minute <10)
			 zero = "0";
		output = Integer.toString(hourOfDay) + ":" + zero + Integer.toString(minute) 
				+ " " + half;
		return output;
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
		lastTag = (String)lastTimeBox.getTag();
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
		
		public String convertToString(int hourOfDay, int minute){
			String output = "";
			String half = "am";
			String zero = "";
			if (hourOfDay>12){
				hourOfDay -= 12;
				half = "pm";
			}
			if(minute <10)
				 zero = "0";
			output = Integer.toString(hourOfDay) + ":" + zero + Integer.toString(minute) 
					+ " " + half;
			return output;
		}
		
		
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			int last = Integer.parseInt(lastTag);
			int [] arr = {hourOfDay,minute};
			if (alarmtimes.size() >= last) //Get rid of the old time;
				alarmtimes.remove(last-1);
			alarmtimes.add(arr);
			lastTimeBox.setText(convertToString(hourOfDay,minute));
			System.out.println(alarmtimes.toString());
		}
	}
	
	public void toNextPage(View v){
		ArrayList<String> medicineNames = new ArrayList<String>();
		LinearLayout medicines = (LinearLayout)findViewById(R.id.medicineBoxes);
		for( int i = 0; i<medicines.getChildCount(); i++ )
		    medicineNames.add(((EditText) medicines.getChildAt(i)).getText().toString());
		
		AlarmTracker.getTracker().setMedicines(medicineNames);
		AlarmTracker.getTracker().setAlarmTime(alarmtimes);
		AlarmTracker.getTracker().setAlarmCount(0);
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
		medicineTag++;
		medicinebox.setTag(Integer.toString(medicineTag));
		
		medicines.addView(medicinebox);
	}
	
	public void addTimeBox(View v){
		LinearLayout times = (LinearLayout) findViewById(R.id.timeBoxes);
		EditText timebox = new EditText(this);
		timebox.setText("Time");
		timebox.setId(R.id.EditTime);
		timebox.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);
		timebox.setFocusableInTouchMode(false);
		timebox.setHint(R.string.medicinetime);
		currentTag++;
		timebox.setTag(Integer.toString(currentTag));
		timebox.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v){
				showTimePickerDialog(v);
			}	
		});
		times.addView(timebox);
	}

}
