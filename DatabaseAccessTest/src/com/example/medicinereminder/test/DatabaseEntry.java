package com.example.medicinereminder.test;

import android.content.Context;

import android.test.ActivityInstrumentationTestCase2;
import com.example.medicinereminder.*;
import com.example.medicinereminder.sql.CursorHolder;
import com.example.medicinereminder.sql.DatabaseAccess;

public class DatabaseEntry extends ActivityInstrumentationTestCase2<MainActivity> {

	@SuppressWarnings("deprecation")
	public DatabaseEntry() {
		super("com.example.medicinereminder", MainActivity.class);
	}
	
	MainActivity mainActivity;
	Context mContext;
	DatabaseAccess dbAccess;
  
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mainActivity = getActivity();
		mContext = mainActivity;
		dbAccess = new DatabaseAccess(mContext);
		dbAccess.open();
		dbAccess.clearTables();
		
	}
	
	
	public void testAddGetUser(){
		CursorHolder result = dbAccess.addUserData("Smith", "Will", 33, 2323, 34, 22, 4, 2013, "Dr House", "9084343434");
		CursorHolder get_result = dbAccess.getUserData(result.getString(1));
		assertEquals(result.getInt(0), get_result.getInt(0));
		assertEquals(result.getString(1), get_result.getString(1));
		assertEquals(result.getString(2), get_result.getString(2));
		assertEquals(result.getInt(3), get_result.getInt(3));
		assertEquals(result.getInt(4), get_result.getInt(4));
		assertEquals(result.getInt(5), get_result.getInt(5));
		assertEquals(result.getInt(6), get_result.getInt(6));
		assertEquals(result.getInt(7), get_result.getInt(7));
		assertEquals(result.getInt(8), get_result.getInt(8));
		assertEquals(result.getString(9), get_result.getString(9));
		assertEquals(result.getString(10), get_result.getString(10));
	}
	
	public void testAddUserRobust(){
		assertNull(dbAccess.addUserData(null, null, -123, 233, 134, 3322, 444, 13, null, null));
	}
	
	public void testAddGetMedicine(){
		CursorHolder result = dbAccess.addMedicine("Varivax", 92);
		CursorHolder get_result = dbAccess.getMedicine(result.getString(1));
		assertEquals(result.getInt(0), get_result.getInt(0));
		assertEquals(result.getString(1), get_result.getString(1));
		assertEquals(result.getInt(2), get_result.getInt(2));
	}
	
	public void testAddMedicineRobust(){
		assertNull(dbAccess.addMedicine(null, -1));
	}
	
	public void testAddGetSchedule(){
		CursorHolder result = dbAccess.addMedicine("Varivax", 92);
		result = dbAccess.addSchedule(result.getInt(0), 33, 2, 23, 2, 2013);
		CursorHolder get_result = dbAccess.getSchedule(result.getInt(0));
		assertEquals(result.getInt(0), get_result.getInt(0));
		assertEquals(result.getInt(1), get_result.getInt(1));
		assertEquals(result.getInt(2), get_result.getInt(2));
		assertEquals(result.getInt(3), get_result.getInt(3));
		assertEquals(result.getInt(4), get_result.getInt(4));
		assertEquals(result.getInt(5), get_result.getInt(5));
		assertEquals(result.getInt(6), get_result.getInt(6));
	}
	
	public void testAddScheduleRobust(){
		assertNull(dbAccess.addSchedule(-1, -1, 2, 23, 2, 2013));
		assertNull(dbAccess.addSchedule(1, 33, -1, 23, 2, 2013));
	}
	
	public void testAddGetAppt(){
		CursorHolder result = dbAccess.addAppt("UPENN Health", 34, 3, 23, 2, 2013);
		CursorHolder get_result = dbAccess.getAppt(result.getInt(0));
		assertEquals(result.getInt(0), get_result.getInt(0));
		assertEquals(result.getString(1), get_result.getString(1));
		assertEquals(result.getInt(2), get_result.getInt(2));
		assertEquals(result.getInt(3), get_result.getInt(3));
		assertEquals(result.getInt(4), get_result.getInt(4));
		assertEquals(result.getInt(5), get_result.getInt(5));
		assertEquals(result.getInt(6), get_result.getInt(6));
	}
	
	public void testAddApptRobust(){
		assertNull(dbAccess.addAppt(null, 34, 3, 23, 2, 2013));
	}
	
	public void testAddGetRefill(){
		CursorHolder result = dbAccess.addMedicine("Varivax", 92);
		result = dbAccess.addRefill(result.getInt(0), 33, 2, 23, 2, 2013);
		CursorHolder get_result = dbAccess.getRefill(result.getInt(0));
		assertEquals(result.getInt(0), get_result.getInt(0));
		assertEquals(result.getInt(1), get_result.getInt(1));
		assertEquals(result.getInt(2), get_result.getInt(2));
		assertEquals(result.getInt(3), get_result.getInt(3));
		assertEquals(result.getInt(4), get_result.getInt(4));
		assertEquals(result.getInt(5), get_result.getInt(5));
		assertEquals(result.getInt(6), get_result.getInt(6));
	}
	
	public void testAddRefillRobust(){
		assertNull(dbAccess.addRefill(-1, 23, 4342, 3, 2, 2013));
	}
	
	public void testAddGetLog(){
		CursorHolder result = dbAccess.addLog("Rantests", "1:00PM");
		CursorHolder get_result = dbAccess.getLog(result.getInt(0));
		assertEquals(result.getInt(0), get_result.getInt(0));
		assertEquals(result.getString(1), get_result.getString(1));
		assertEquals(result.getString(2), get_result.getString(2));
	
	}
	
	public void testAddLogRobust(){
		assertNull(dbAccess.addLog(null, "1232132"));
	}
	
	public void testPrintMethod(){
		CursorHolder result =null;
		dbAccess.addUserData("Smith", "Will", 33, 2323, 34, 22, 4, 2013, "Dr House", "9084343434");
		result = dbAccess.addMedicine("Varivax", 100);
		dbAccess.addSchedule(result.getInt(0), 33, 2, 23, 2, 2013);
		dbAccess.addAppt("UPENN Health", 34, 3, 23, 2, 2013);
		dbAccess.addRefill(result.getInt(0), 33, 2, 23, 2, 2013);
		dbAccess.addLog("Rantests", "1:00PM");
		dbAccess.printDatabase();
		assertTrue(true);
	}
	

	
	

}
