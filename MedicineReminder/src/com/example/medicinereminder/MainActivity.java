package com.example.medicinereminder;

import java.util.Calendar;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
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
			
		}
	}
	
	public void toNextPage(View v){
		ViewFlipper flip = (ViewFlipper) findViewById(R.id.view_flipper);
		flip.showNext();
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
