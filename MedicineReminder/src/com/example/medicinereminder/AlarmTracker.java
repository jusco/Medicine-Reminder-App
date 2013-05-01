package com.example.medicinereminder;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import com.example.medicinereminder.sql.CursorHolder;
import com.example.medicinereminder.sql.DatabaseAccess;

import android.content.Context;
import android.text.format.Time;

public class AlarmTracker {
	private static final AlarmTracker tracker = new AlarmTracker();
	
	String alarmMessage;
    ArrayList<int[]> alarmTimes;
    int alarmCount;
    ArrayList<String> medicines;
    int reminder;
    ArrayList<GregorianCalendar> appointments;
    ArrayList<GregorianCalendar> refills;
    int missedAlarms;
    HashMap<Time,String> pillRecord;
    int minutesSlept;
    public int streak;
    public int highscore = 0;
    public boolean soundAlarm;
    
    private AlarmTracker() {
    	pillRecord = new HashMap<Time,String>();
    }
    
    public static AlarmTracker getTracker() {
        return tracker;
    }
    
    public void setAlarmMessage(String message){
    	alarmMessage = message;
    }
    
    public void setAlarmTime(ArrayList<int[]> alarmTimes){
    	this.alarmTimes = alarmTimes;
    }
    
    public void setAlarmCount(int alarmCount){
    	this.alarmCount = alarmCount;
    }
    
    public void setMedicines(ArrayList<String> medicines){
    	this.medicines = medicines;
    }
    
    public void setReminder(int reminder){
    	this.reminder = reminder;
    }
    
    public void setAppt(ArrayList<GregorianCalendar> appts){
    	this.appointments = appts;
    }
    
    public void setRefill(ArrayList<GregorianCalendar> refill){
    	this.refills = refill;
    }
    
    public void setMissed(int missed){
    	this.missedAlarms = missed;
    }
    
    public void setStreak(int streak){
    	this.streak = streak;
    }
    
    public void setHighScore(int score){
    	this.highscore = score;
    }
    
    public void addRecord(Time date, String taken){
    	pillRecord.put(date, taken);
    }
    
    public HashMap<Time,String> getRecord(){
    	return pillRecord;
    }
    
    public void setSoundAlarm(boolean soundAlarm){
    	this.soundAlarm = soundAlarm;
    }
    
    public void sendToDatabase(Context context){
    	DatabaseAccess dbAccess = new DatabaseAccess(context);
    	dbAccess.open();
    	dbAccess.resetAppData();
    	System.out.println("Data Added");
    	dbAccess.addAppData(alarmMessage, alarmCount, reminder, missedAlarms, streak, highscore);
    	dbAccess.close();
    }
    
    public void loadFromDatabase(Context context){
    	DatabaseAccess dbAccess = new DatabaseAccess(context);
    	dbAccess.open();
    	ArrayList<CursorHolder> result_list = (ArrayList)dbAccess.getAllAppData();
    	CursorHolder result = null;
    	if(result_list.size()> 0)
    		result = result_list.get(0);
    	System.out.println("tries app data");
    	if (result!=null){
    		alarmMessage = result.getString(1);
    		System.out.println(alarmMessage);
    		System.out.println("gets app data");
    		alarmCount = result.getInt(2);
    		reminder = result.getInt(3);
    		missedAlarms = result.getInt(4);
    		streak = result.getInt(5);
    		highscore = result.getInt(6);
    	}
    	dbAccess.close();
    }
    
}
