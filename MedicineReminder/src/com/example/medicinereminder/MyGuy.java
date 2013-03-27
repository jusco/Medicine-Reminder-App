package com.example.medicinereminder;

import java.util.ArrayList;


public class MyGuy  {
    private static final MyGuy user = new MyGuy();
    
    String firstName;
    String lastName;
    String fullName;
    String viralCount;
    String phoneNumer;
    String providerPhoneNumber;
    String alarmMessage;
    ArrayList<int[]> alarmTimes;
    int alarmCount;

    // Private constructor prevents instantiation from other classes
    private MyGuy() {
    	
    }

    public static MyGuy getUser() {
        return user;
    }
    
    public void setFirstName(String firstN){
    	firstName = firstN;
    	setFullName();
    }
    
    public void setLastName(String lastN){
    	lastName = lastN;
    	setFullName();
    }
    
    public void setFullName(){
    	fullName = firstName + " " + lastName;
    }
  
    public void setPhoneNumer(String num){
    	phoneNumer = num;
    }
    
    public void setProviderPhoneNumer(String num){
    	providerPhoneNumber = num;
    }
    
    public void setViralCount(String num){
    	viralCount = num;
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
    
}
