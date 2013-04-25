package com.example.medicinereminder.sql;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
//import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
//import android.os.Environment;
import android.util.Log;

/**
* @author Justin Cockburn
* @date February 10, 2013
* @project Medicine Reminder
*
*/
public class MySQLiteHelper extends SQLiteOpenHelper {
	
	//In this iteration there is one user
	//There is a user table, appointment table, medicine table and a schedule table;
	public static final String TABLE_USERINFO = "user_info";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_LAST_NAME = "last_name";
	public static final String COLUMN_AGE = "age";
	public static final String COLUMN_VIRAL = "viral_load";
	public static final String COLUMN_CD4 = "cd4_count";
	public static final String COLUMN_DIAG_DAY = "diag_day";
	public static final String COLUMN_DIAG_MONTH = "diag_month";
	public static final String COLUMN_DIAG_YEAR = "diag_year";
	public static final String COLUMN_DOCTOR = "doctor";
	public static final String COLUMN_DOCTOR_NO = "doctor_no";
	
	public static final String TABLE_MEDICINE = "medicine";
	public static final String COLUMN_MEDICINE_ID = "medicine_id";
	public static final String COLUMN_MEDICINE = "medicine";

	
	public static final String TABLE_SCHEDULE = "schedule";
	public static final String COLUMN_SCHEDULE_ID = "schedule_id";
	public static final String COLUMN_MINUTE = "minute";
	public static final String COLUMN_HOUR = "hour";
	public static final String COLUMN_DAY = "day";
	public static final String COLUMN_MONTH = "month";
	public static final String COLUMN_YEAR = "year";
	//Medicine as foreign key
	
	public static final String TABLE_APPT = "appointment";
	public static final String COLUMN_APPT_ID = "appt_id";
	public static final String COLUMN_CLINIC = "clinic";
	//Use the time parameters above
	
	public static final String TABLE_LOG = "log";
	public static final String COLUMN_MESSAGE_ID = "message_id";
	public static final String COLUMN_MESSAGE = "message";
	public static final String COLUMN_TIME = "time";
	
	public static final String TABLE_REFILL = "refill";
	public static final String COLUMN_REFILL_ID = "refill_id";
	//Medicine as foreign key
	//Use the time parameters above
	
	
	private static final String DATABASE_NAME= "user.db";
	private static final int DATABASE_VERSION = 2;
	
	//Database creation
	private static final String DATABASE_CREATE_USER = "create table "
			+ TABLE_USERINFO + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_NAME
			+ " text not null, " + COLUMN_LAST_NAME 
			+ " text not null, " + COLUMN_AGE 
			+ " integer, " + COLUMN_VIRAL
			+ " integer, " + COLUMN_DIAG_DAY
			+ " integer, " + COLUMN_DIAG_MONTH
			+ " integer, " + COLUMN_DIAG_YEAR
			+ " integer, " + COLUMN_DOCTOR
			+ " text not null, " + COLUMN_DOCTOR_NO
	        + " text not null);";
	
	private static final String DATABASE_CREATE_MEDICINE = "create table "
			+ TABLE_MEDICINE + "(" + COLUMN_MEDICINE_ID
			+ " integer primary key autoincrement, " + COLUMN_MEDICINE 
			+ " text not null);";
	
	private static final String DATABASE_CREATE_SCHEDULE = "create table "
			+ TABLE_SCHEDULE + "(" + COLUMN_SCHEDULE_ID
			+ " integer primary key autoincrement, " + COLUMN_MINUTE
			+ " integer, " + COLUMN_HOUR 
			+ " integer, " + COLUMN_DAY 
			+ " integer, " + COLUMN_MONTH
			+ " integer, " + COLUMN_YEAR
			+ " integer);";
	
	private static final String DATABASE_CREATE_APPT = "create table "
			+ TABLE_APPT + "(" + COLUMN_APPT_ID
			+ " integer primary key autoincrement, " + COLUMN_MINUTE
			+ " integer, " + COLUMN_HOUR 
			+ " integer, " + COLUMN_DAY 
			+ " integer, " + COLUMN_MONTH
			+ " integer, " + COLUMN_YEAR
			+ " integer);";
	
	private static final String DATABASE_CREATE_REFILL = "create table "
			+ TABLE_REFILL + "(" + COLUMN_REFILL_ID
			+ " integer primary key autoincrement, " +  COLUMN_MINUTE
			+ " integer, " + COLUMN_HOUR 
			+ " integer, " + COLUMN_DAY 
			+ " integer, " + COLUMN_MONTH
			+ " integer, " + COLUMN_YEAR
			+ " integer);";
	
	private static final String DATABASE_CREATE_LOG = "create table "
			+ TABLE_LOG + "(" + COLUMN_MESSAGE_ID
			+ " integer primary key autoincrement, " + COLUMN_MESSAGE
			+ " text not null, " + COLUMN_TIME 
			+ " text not null);";
	
	
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE_USER);
		database.execSQL(DATABASE_CREATE_MEDICINE);
		database.execSQL(DATABASE_CREATE_SCHEDULE);
		database.execSQL(DATABASE_CREATE_APPT);
		database.execSQL(DATABASE_CREATE_LOG);
		database.execSQL(DATABASE_CREATE_REFILL);
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERINFO);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOG);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REFILL);
		onCreate(db);
	}
	

}