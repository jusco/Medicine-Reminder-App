package com.example.asteroids;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

public class BulletObject extends GameObject {
	public ShapeDrawable shape;
	public int framesExisted;

	public BulletObject(int x, int y, int xsize, int ysize, int xvelocity,
			int yvelocity) {
		super(x, y, xsize, ysize, xvelocity, yvelocity);
		shape = new ShapeDrawable(new RectShape());
		shape.getPaint().setColor(Color.YELLOW);
		shape.setBounds(x-(xsize/2),y-(ysize/2),x+(xsize/2), y+(ysize/2));
		framesExisted = 0;
		// TODO Auto-generated constructor stub
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
		shape.setBounds(x-(xsize/2),y-(ysize/2),x+(xsize/2), y+(ysize/2));
		framesExisted++;
	}

}
