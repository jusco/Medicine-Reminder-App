package com.example.medicinereminder;

import java.util.ArrayList;


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
    
   
    
}
