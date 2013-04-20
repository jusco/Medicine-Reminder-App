package com.example.medicinereminder;

import java.util.ArrayList;

public class AlarmTracker {
	private static final AlarmTracker tracker = new AlarmTracker();
	
	String alarmMessage;
    ArrayList<int[]> alarmTimes;
    int alarmCount;
    ArrayList<String> medicines;
    
    private AlarmTracker() {
    	
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
}
