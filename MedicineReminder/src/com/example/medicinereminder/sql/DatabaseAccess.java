package com.example.medicinereminder.sql;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAccess {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumnsUser = { MySQLiteHelper.COLUMN_ID, 
			MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_AGE,
			MySQLiteHelper.COLUMN_VIRAL, MySQLiteHelper.COLUMN_CD4,
			MySQLiteHelper.COLUMN_DOCTOR, MySQLiteHelper.COLUMN_DOCTOR_NO};
	private String[] allColumnsMedicine = { MySQLiteHelper.COLUMN_MEDICINE_ID, 
			MySQLiteHelper.COLUMN_MEDICINE, MySQLiteHelper.COLUMN_DOSAGE };
	private String[] allColumnsSchedule = { MySQLiteHelper.COLUMN_SCHEDULE_ID, 
			MySQLiteHelper.COLUMN_MEDICINE_ID, MySQLiteHelper.COLUMN_MINUTE,
			MySQLiteHelper.COLUMN_HOUR, MySQLiteHelper.COLUMN_DAY,
			MySQLiteHelper.COLUMN_MONTH, MySQLiteHelper.COLUMN_YEAR};
	private String[] allColumnsAppt = { MySQLiteHelper.COLUMN_APPT_ID, 
			MySQLiteHelper.COLUMN_CLINIC, MySQLiteHelper.COLUMN_MINUTE,
			MySQLiteHelper.COLUMN_HOUR, MySQLiteHelper.COLUMN_DAY,
			MySQLiteHelper.COLUMN_MONTH, MySQLiteHelper.COLUMN_YEAR};
	private String[] allColumnsLog = {MySQLiteHelper.COLUMN_MESSAGE_ID,
			MySQLiteHelper.COLUMN_MESSAGE,
			MySQLiteHelper.COLUMN_TIME};
	
	
	public DatabaseAccess(Context context){
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public CursorHolder addUserData(String name, int age,
			int viral_load, int cd4, String doctor,
			String doctor_number){
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NAME, name);
		values.put(MySQLiteHelper.COLUMN_AGE, age);
		values.put(MySQLiteHelper.COLUMN_VIRAL, viral_load);
		values.put(MySQLiteHelper.COLUMN_CD4, cd4);
		values.put(MySQLiteHelper.COLUMN_DOCTOR, doctor);
		values.put(MySQLiteHelper.COLUMN_DOCTOR_NO, doctor_number);
		long insertId = database.insert(MySQLiteHelper.TABLE_USERINFO, null,
				values);
		int insert = ((Long)insertId).intValue();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_USERINFO,
				allColumnsUser, MySQLiteHelper.COLUMN_ID + " = " + insert,
				null,null,null,null);
		cursor.moveToFirst();
		CursorHolder newData = cursorToData(cursor);
		cursor.close();
		return newData;
	}
	
	public void deleteNameEntry(String name) {
	    System.out.println("UserDataEntry deleted with name: " + name);
	    database.delete(MySQLiteHelper.TABLE_USERINFO, MySQLiteHelper.COLUMN_NAME
	        + " = '" + name + "'", null);
	 }
	
	public CursorHolder getUserData(String name){
		Cursor cursor = database.query(MySQLiteHelper.TABLE_USERINFO, allColumnsUser,
				MySQLiteHelper.COLUMN_NAME + " = '" + name +"'", null, null, null, null);
		cursor.moveToFirst();
		CursorHolder newData = cursorToData(cursor);
		cursor.close();
		return newData;
	}
	
	public List<CursorHolder> getAllData(){
		List<CursorHolder> datas = new ArrayList<CursorHolder>();	
		Cursor cursor = database.query(MySQLiteHelper.TABLE_USERINFO,
		        allColumnsUser, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			CursorHolder data= cursorToData(cursor);
		      datas.add(data);
		      cursor.moveToNext();
		}
		 // Make sure to close the cursor
		cursor.close(); 
		return datas;
	}
	
	
	public CursorHolder addMedicine(String medicine, int dosage){
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_MEDICINE, medicine);
		values.put(MySQLiteHelper.COLUMN_DOSAGE, dosage);
		long insertId = database.insert(MySQLiteHelper.TABLE_MEDICINE, null,
				values);
		int insert = ((Long)insertId).intValue();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_MEDICINE,
				allColumnsMedicine, MySQLiteHelper.COLUMN_MEDICINE_ID + " = " + insert,
				null,null,null,null);
		cursor.moveToFirst();
		CursorHolder newData = cursorToMedicine(cursor);
		cursor.close();
		return newData;	
	}
	
	public void deleteMedicine(String medicine) {
	    System.out.println("Medicine deleted with nane: " + medicine);
	    database.delete(MySQLiteHelper.TABLE_MEDICINE, MySQLiteHelper.COLUMN_MEDICINE
	        + " = '" + medicine + "'", null);
	 }
	
	public CursorHolder getMedicine(String medicine){
		Cursor cursor = database.query(MySQLiteHelper.TABLE_MEDICINE, allColumnsMedicine,
				MySQLiteHelper.COLUMN_MEDICINE + " = '" + medicine +"'", null, null, null, null);
		cursor.moveToFirst();
		CursorHolder newData = cursorToMedicine(cursor);
		cursor.close();
		return newData;
	}
	
	public List<CursorHolder> getAllMedicine(){
		List<CursorHolder> meds = new ArrayList<CursorHolder>();	
		Cursor cursor = database.query(MySQLiteHelper.TABLE_MEDICINE,
		        allColumnsMedicine, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			CursorHolder medicine= cursorToMedicine(cursor);
		     meds.add(medicine);
		     cursor.moveToNext();
		}
		 // Make sure to close the cursor
		cursor.close(); 
		return meds;
	}
	
	public CursorHolder addSchedule(int medicine_id, int minute,
			int hour, int day, int month, int year){
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_MEDICINE_ID, medicine_id);
		values.put(MySQLiteHelper.COLUMN_MINUTE, minute);
		values.put(MySQLiteHelper.COLUMN_HOUR, hour);
		values.put(MySQLiteHelper.COLUMN_DAY, day);
		values.put(MySQLiteHelper.COLUMN_MONTH, month);
		values.put(MySQLiteHelper.COLUMN_YEAR, year);
		long insertId = database.insert(MySQLiteHelper.TABLE_SCHEDULE, null,
				values);
		int insert = ((Long)insertId).intValue();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_SCHEDULE,
				allColumnsSchedule, MySQLiteHelper.COLUMN_SCHEDULE_ID + " = " + insert,
				null,null,null,null);
		cursor.moveToFirst();
		CursorHolder newData = cursorToSchedule(cursor);
		cursor.close();
		return newData;	
	}
	
	public void deleteSchedule(int schedule_id) {
	    System.out.println("Schedule deleted with id: " + schedule_id);
	    database.delete(MySQLiteHelper.TABLE_SCHEDULE, MySQLiteHelper.COLUMN_SCHEDULE_ID
	        + " = " + schedule_id, null);
	 }
	
	public CursorHolder getSchedule(int schedule_id) {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_SCHEDULE, allColumnsSchedule,
				MySQLiteHelper.COLUMN_SCHEDULE_ID + " = " + schedule_id, null, null, null, null);
		cursor.moveToFirst();
		CursorHolder newData = cursorToSchedule(cursor);
		cursor.close();
		return newData;
	}
	
	public List<CursorHolder> getAllSchedules(){
		List<CursorHolder> scheds = new ArrayList<CursorHolder>();	
		Cursor cursor = database.query(MySQLiteHelper.TABLE_SCHEDULE,
		        allColumnsSchedule, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			CursorHolder schedule= cursorToSchedule(cursor);
		      scheds.add(schedule);
		      cursor.moveToNext();
		}
		 // Make sure to close the cursor
		cursor.close(); 
		return scheds;
	}
	
	public CursorHolder addAppt(String clinic, int minute,
			int hour, int day, int month, int year){
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_CLINIC, clinic);
		values.put(MySQLiteHelper.COLUMN_MINUTE, minute);
		values.put(MySQLiteHelper.COLUMN_HOUR, hour);
		values.put(MySQLiteHelper.COLUMN_DAY, day);
		values.put(MySQLiteHelper.COLUMN_MONTH, month);
		values.put(MySQLiteHelper.COLUMN_YEAR, year);
		long insertId = database.insert(MySQLiteHelper.TABLE_APPT, null,
				values);
		int insert = ((Long)insertId).intValue();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_APPT,
				allColumnsAppt, MySQLiteHelper.COLUMN_APPT_ID + " = " + insert,
				null,null,null,null);
		cursor.moveToFirst();
		CursorHolder newData = cursorToAppt(cursor);
		cursor.close();
		return newData;	
	}
	
	public void deleteAppt(int appt_id) {
	    System.out.println("Appointment deleted with id: " + appt_id);
	    database.delete(MySQLiteHelper.TABLE_APPT, MySQLiteHelper.COLUMN_APPT_ID
	        + " = " + appt_id, null);
	 }
	
	public CursorHolder getAppt(int appt_id) {
		Cursor cursor = database.query(MySQLiteHelper.TABLE_SCHEDULE, allColumnsAppt,
				MySQLiteHelper.COLUMN_APPT_ID + " = " + appt_id, null, null, null, null);
		cursor.moveToFirst();
		CursorHolder newData = cursorToAppt(cursor);
		cursor.close();
		return newData;
	}
	
	public List<CursorHolder> getAllAppts(){
		List<CursorHolder> appointments = new ArrayList<CursorHolder>();	
		Cursor cursor = database.query(MySQLiteHelper.TABLE_APPT,
		        allColumnsAppt, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			CursorHolder appt= cursorToAppt(cursor);
		      appointments.add(appt);
		      cursor.moveToNext();
		}
		 // Make sure to close the cursor
		cursor.close(); 
		return appointments;
	}
	
	public CursorHolder addLog(String message, String time){
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_MESSAGE, message);
		values.put(MySQLiteHelper.COLUMN_TIME, time);
		long insertId = database.insert(MySQLiteHelper.TABLE_LOG, null,
				values);
		int insert = ((Long)insertId).intValue();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_LOG,
				allColumnsLog, MySQLiteHelper.COLUMN_MESSAGE_ID + " = " + insert,
				null,null,null,null);
		cursor.moveToFirst();
		CursorHolder newData = cursorToLog(cursor);
		cursor.close();
		return newData;	
	}
	
	public List<CursorHolder> getAllLogs(){
		List<CursorHolder> logs = new ArrayList<CursorHolder>();	
		Cursor cursor = database.query(MySQLiteHelper.TABLE_APPT,
		        allColumnsAppt, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			CursorHolder log= cursorToLog(cursor);
		      logs.add(log);
		      cursor.moveToNext();
		}
		 // Make sure to close the cursor
		cursor.close(); 
		return logs;
	}
	
	
	
	private CursorHolder cursorToData(Cursor cursor) {
		CursorHolder data = new CursorHolder();
	    data.add(cursor.getInt(0));
	    data.add(cursor.getString(1));
	    data.add(cursor.getInt(2));
	    data.add(cursor.getInt(3));
	    data.add(cursor.getInt(4));
	    data.add(cursor.getString(5));
	    data.add(cursor.getString(6));
	    return data;
	}
	  
	private CursorHolder cursorToMedicine(Cursor cursor){
		CursorHolder data = new CursorHolder();
		data.add(cursor.getInt(0));
		data.add(cursor.getString(1));
		data.add(cursor.getInt(2));
		return data;
	}
	  
	private CursorHolder cursorToSchedule(Cursor cursor){
		CursorHolder data = new CursorHolder();
		data.add(cursor.getInt(0));
		data.add(cursor.getInt(1));	
		data.add(cursor.getInt(2));
		data.add(cursor.getInt(3));
		data.add(cursor.getInt(4));
		data.add(cursor.getInt(5));
		data.add(cursor.getInt(6));
		return data;
	}
	
	private CursorHolder cursorToAppt(Cursor cursor){
		CursorHolder data = new CursorHolder();
		data.add(cursor.getInt(0));
		data.add(cursor.getString(1));	
		data.add(cursor.getInt(2));
		data.add(cursor.getInt(3));
		data.add(cursor.getInt(4));
		data.add(cursor.getInt(5));
		data.add(cursor.getInt(6));
		return data;
	}
	
	private CursorHolder cursorToLog(Cursor cursor){
		CursorHolder data = new CursorHolder();
		data.add(cursor.getInt(0));
		data.add(cursor.getString(1));
		data.add(cursor.getString(2));
		return data;
	}
}