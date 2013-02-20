package com.example.medicinereminder.sql;


import java.util.ArrayList;
import java.util.List;

public class CursorHolder {
	private List<Object> resultList;
	
	public CursorHolder(){
		resultList = new ArrayList<Object>();
	}
	
	public void add(String value){
		addToCollection(value,resultList);
	}
	
	public void add(int value){
		addToCollection((Integer)value,resultList);
	}
	
	public void add(float value){
		addToCollection((Float)value,resultList);
	}
	
	public void add(long value){
		addToCollection((Long)value,resultList);
	}
	
	public String getString(int i){
		return (String) resultList.get(i);
	}
	
	public int getInt(int i){
		return (Integer) resultList.get(i);
	}
	
	public float getFloat(int i){
		return (Float) resultList.get(i);
	}
	
	public long getLong(int i){
		return (Long) resultList.get(i);
	}
	
	private <T> void addToCollection(T input, List<T> reslist){
		reslist.add(input);
	}

}

