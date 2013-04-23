package com.example.asteroids;

public abstract class GameObject {
	int x,y,xsize,ysize,xvelocity,yvelocity;
	boolean exists;
	
	public GameObject(int x, int y, int xsize, int ysize,
			int xvelocity, int yvelocity){
		this.x = x;
		this.y = y; 
		this.xsize = xsize;
		this.ysize = ysize;
		this.xvelocity = xvelocity;
		this.yvelocity = yvelocity;
		this.exists = true;
	}
	
	abstract public void updatePosition();
	
	
		

}
