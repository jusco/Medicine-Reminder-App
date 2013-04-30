package com.example.medicinereminder;

import java.util.ArrayList;

import com.example.medicinereminder.sql.CursorHolder;
import com.example.medicinereminder.sql.DatabaseAccess;

import android.content.Context;


public class MyGuy  {
    private static final MyGuy user = new MyGuy();
    public static boolean firstTime = true;
    
    String firstName;
    String lastName;
    String fullName;
    String viralCount;
    String phoneNumer;
    String providerPhoneNumber;
    String diagDate;
    int avatar;
    

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
    
    public void setDiagDate(String date){
    	diagDate = date;
    }
    
    public void setAvatar(int avatar){
    	this.avatar = avatar;
    	MyGuy.firstTime = false;
    }
    
    public int evalFirst(){
    	if (firstTime)
    		return 1;
    	else
    		return 0;
    }
    
    public boolean revEvalFirst(int val){
    	if  (val==1)
    		return true;
    	else
    		return false;
    }
    
   
    
    public void sendToDatabase(Context context){
    	DatabaseAccess dbAccess = new DatabaseAccess(context);
    	dbAccess.open();
    	dbAccess.resetUserData();
    	dbAccess.addUserData(firstName, lastName, 0, Integer.parseInt(viralCount), diagDate, phoneNumer, providerPhoneNumber,avatar,evalFirst());
    	dbAccess.close();
    }
    
    public void loadFromDatabase(Context context){
    	DatabaseAccess dbAccess = new DatabaseAccess(context);
    	dbAccess.open();
    	CursorHolder result =dbAccess.getUserData(0);
    	if (result!=null){
    		setFirstName(result.getString(1));
    		setLastName(result.getString(2));
    		viralCount = Integer.toString(result.getInt(4));
    		diagDate = result.getString(5);
    		phoneNumer = result.getString(6);
    		providerPhoneNumber = result.getString(7);
    		avatar =result.getInt(8);
    		firstTime = revEvalFirst(result.getInt(9));
    	}
    	dbAccess.close();
    	
    }
    
   
    
}
