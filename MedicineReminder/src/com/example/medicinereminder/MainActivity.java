package com.example.medicinereminder;

import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import android.widget.LinearLayout;
import android.widget.ViewFlipper;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	protected static String date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		NumberPicker np = (NumberPicker) findViewById(R.id.np);
		np.setMaxValue(12);
		np.setMinValue(0);
		String[] vals = new String[13];
		for (int i = 0; i < vals.length; i++) {
			vals[i] = Integer.toString(i * 5) + " min";
		}
		np.setWrapSelectorWheel(false);
		np.setDisplayedValues(vals);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
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
		ViewFlipper flip = (ViewFlipper) findViewById(R.id.view_flipper);
		flip.showNext();
	}
	
	public void sumbitForm(View v){
		String firstName = ((EditText) findViewById(R.id.EditFirstName)).getText().toString();
		String lastName = ((EditText) findViewById(R.id.EditLastName)).getText().toString();
		String infectionDate = date;
		String viralLoad = ((EditText) findViewById(R.id.EditViralLoad)).getText().toString();
		String phone = ((EditText) findViewById(R.id.EditPhone)).getText().toString();
		String provider = ((EditText) findViewById(R.id.EditProviderPhone)).getText().toString();
		
		ArrayList<String> medicineNames = new ArrayList<String>();
		LinearLayout medicines = (LinearLayout)findViewById(R.id.medicineBoxes);
		for( int i = 0; i<medicines.getChildCount(); i++ )
		    medicineNames.add(((EditText) medicines.getChildAt(i)).getText().toString());
		
		ArrayList<String> medicineTimes = new ArrayList<String>();
		LinearLayout times = (LinearLayout)findViewById(R.id.timeBoxes);
		for( int i = 0; i<times.getChildCount(); i++ )
		    medicineTimes.add(((EditText) times.getChildAt(i)).getText().toString());
		
		int remindertime = ((NumberPicker) findViewById(R.id.np)).getValue();
		
		Intent intent = new Intent(this, HomePageActivity.class);
		startActivity(intent);
		MyUser.getUser().setFirstName("JFONT");
	}
	
	
	public void toPrevPage(View v){
		ViewFlipper flip = (ViewFlipper) findViewById(R.id.view_flipper);
		flip.showPrevious();
	}
	
	public void addMedicineBox(View v){
		LinearLayout medicines = (LinearLayout) findViewById(R.id.medicineBoxes);
		EditText medicinebox = new EditText(this);
		medicinebox.setText("Medicine Name");
		medicines.addView(medicinebox);
		
		LinearLayout times = (LinearLayout) findViewById(R.id.timeBoxes);
		EditText timebox = new EditText(this);
		timebox.setText("Time");
		timebox.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);
		times.addView(timebox);
	}
	
}
