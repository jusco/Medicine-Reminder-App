package com.example.medicinereminder;

import com.example.medicinereminder.AlarmPage.SpinnerActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class RegistrationRemindersGBActivity extends Activity {
	String time;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_reminders_gb);
		Spinner spinner = (Spinner) findViewById(R.id.editReminder);
// 		Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.rem_array, android.R.layout.simple_spinner_item);
// 		Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// 		Apply the adapter to the spinner
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(new SpinnerActivity2());
	}
	
	public class SpinnerActivity2 extends Activity implements OnItemSelectedListener {


		public void onItemSelected(AdapterView<?> parent, View view,
				int pos, long id) {
			// An item was selected. You can retrieve the selected item using
			time =(String)parent.getItemAtPosition(pos);
		}

		public void onNothingSelected(AdapterView<?> parent) {
			time = "0";
		}
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration_reminders, menu);
		return true;
	}
	public void toNextPage(View v){
		int remindertime = Integer.parseInt(time);
		remindertime= remindertime /5 ;
		
		//Don't set the alarm here either
		//AlarmSet alarmset = new AlarmSet(this);
		//alarmset.setAlarm(remindertime);
		AlarmTracker.getTracker().setReminder(remindertime);
		
		Intent intent = new Intent(this, RegistrationMessageActivity.class);
		startActivity(intent);
		finish();
	}
	

	public void toPrevPage(View v){
		Intent intent = new Intent(this, RegistrationMedicationActivity.class);
		startActivity(intent);
		finish();
	}

}
