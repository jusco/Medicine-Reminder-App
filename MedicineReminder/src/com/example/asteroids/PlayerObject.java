package com.example.asteroids;

public class PlayerObject extends GameObject {

	public double top_angle;
	public double left_angle;
	public double right_angle;
	public double angular_vel;
	
	//public Facing face;
	public PlayerObject(int x, int y, int xsize, int ysize, int xvelocity,
			int yvelocity) {
		super(x, y, xsize, ysize, xvelocity, yvelocity);
		top_angle = 90;
		left_angle = 220; //+10
		right_angle = 320; // -10
		angular_vel = 0;
	}
	
	public int[] getTop(){
		double init_x = (xsize * Math.cos((Math.PI/180)*top_angle)) + x;
		double init_y = (-xsize * Math.sin((Math.PI/180)*top_angle)) + y;
		int [] output = {(int)init_x,(int)init_y};
		return output;
	}
	
	public int[] getLeft(){
		double init_x = (ysize * Math.cos((Math.PI/180)*left_angle)) + x;
		double init_y = (-ysize * Math.sin((Math.PI/180)*left_angle)) + y;
		int [] output = {(int)init_x,(int)init_y};
		return output;
	}
	
	public int[] getRight(){
		double init_x = (ysize * Math.cos((Math.PI/180)*right_angle)) + x;
		double init_y = (-ysize * Math.sin((Math.PI/180)*right_angle)) + y;
		int [] output = {(int)init_x,(int)init_y};
		return output;
	}
	
	public void updatePosition(){
		this.x += this.xvelocity;
		this.y += this.yvelocity;
		if (this.x < 0)
			this.x = AsteroidGame.width + this.x;
		else if (this.x > AsteroidGame.width )
			this.x = this.x - AsteroidGame.width ;
		
		if (this.y < 0)
			this.y = AsteroidGame.height  + this.y;
		else if (this.y > AsteroidGame.height)
			this.y = this.y - AsteroidGame.height;
		
		top_angle += angular_vel;
		right_angle += angular_vel;
		left_angle += angular_vel;
		//Player velocity decreases over time (shouldn't go less than zero.)
		if(xvelocity>0)
			xvelocity -= 1;
		else if(xvelocity<0)
			xvelocity +=1;
		if(yvelocity>0)
			yvelocity -=1;
		else if(yvelocity<0)
			yvelocity += 1;
		if(angular_vel>0)
			angular_vel -= 1;
		else if (angular_vel<0)
			angular_vel += 1;
	}

}
