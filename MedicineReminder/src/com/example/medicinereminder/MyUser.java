package com.example.medicinereminder;

public class MyUser  {
    private static final MyUser user = new MyUser();
    
    String firstName;

    // Private constructor prevents instantiation from other classes
    private MyUser() {
    	
    }

    public static MyUser getUser() {
        return user;
    }
    
    public void setFirstName(String firstN){
    	firstName = firstN;
    }

}