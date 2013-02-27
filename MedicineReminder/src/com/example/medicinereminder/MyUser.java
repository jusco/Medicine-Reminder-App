package com.example.medicinereminder;

public class MyUser  {
    private static final MyUser user = new MyUser();
    
    String firstName;
    String lastName;
    String fullName;
    String phoneNumer;

    // Private constructor prevents instantiation from other classes
    private MyUser() {
    	
    }

    public static MyUser getUser() {
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
    	fullName = firstName + lastName;
    }
  
    public void setPhoneNumer(String num){
    	phoneNumer = num;
    }
}