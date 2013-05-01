package com.example.medicinereminder.test;



import java.util.ArrayList;

import com.example.medicinereminder.RegistrationMedicationActivity;
import com.example.medicinereminder.sql.DatabaseAccess;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

public class MedicationActivityTest extends ActivityInstrumentationTestCase2<RegistrationMedicationActivity> {

	@SuppressWarnings("deprecation")
	public MedicationActivityTest() {
		super("com.example.medicinereminder", RegistrationMedicationActivity.class);
	}
	
	RegistrationMedicationActivity mainActivity;
	Context mContext;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mainActivity = getActivity();
		mContext = mainActivity;
	}
	
	
	public void testDateFormat(){
		String result = mainActivity.convertToString(0, 22);
		assertEquals("12:22 am", result);
		result = mainActivity.convertToString(12,01);
		assertEquals("12:01 pm", result);
		result = mainActivity.convertToString(15,01);
		assertEquals("3:01 pm", result);
		result = mainActivity.convertToString(23,01);
		assertEquals("11:01 pm", result);
		result = mainActivity.convertToString(0,01);
		assertEquals("12:01 am", result);
		result = mainActivity.convertToString(1,01);
		assertEquals("1:01 am", result);
		result = mainActivity.convertToString(2,01);
		assertEquals("2:01 am", result);
		result = mainActivity.convertToString(3,01);
		assertEquals("3:01 am", result);
	}
	
	public void testSortTimes(){
		//have to make alarmtimes and sortTimes() public for this test
		int [] test = {12,2};
		mainActivity.alarmtimes.add(test);
		int [] test2 = {3,23};
		mainActivity.alarmtimes.add(test2);
		int [] test3 = {3,22};
		mainActivity.alarmtimes.add(test3);
		int [] test4 = {13,22};
		mainActivity.alarmtimes.add(test4);
		mainActivity.sortTimes();
		ArrayList<int[]> testlist = new ArrayList<int[]>();
		testlist.add(test3);
		testlist.add(test2);
		testlist.add(test);
		testlist.add(test4);
		assertEquals(testlist, mainActivity.alarmtimes);
	
	}
	
}
