package com.example.medicinereminder.test;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import com.example.medicinereminder.RegistrationBasicInfoActivity;

public class BasicInfoActivityTest extends ActivityInstrumentationTestCase2<RegistrationBasicInfoActivity> {

	@SuppressWarnings("deprecation")
	public BasicInfoActivityTest() {
		super("com.example.medicinereminder", RegistrationBasicInfoActivity.class);
	}
	
	RegistrationBasicInfoActivity mainActivity;
	Context mContext;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mainActivity = getActivity();
		mContext = mainActivity;
	}
	
	public void testValidate(){
		//Changed visibility of validate for testing
		mainActivity.date = "Filler Date";
		assertTrue(mainActivity.validate("Will","Smith", "1232", "9083454543","9085674567"));
		
		mainActivity.date = null;
		assertFalse(mainActivity.validate("Will","Smith", "1232", "9083454543","9085674567"));
	}

}
