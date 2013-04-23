package com.example.asteroids;

import java.util.ArrayList;
import java.util.Random;


import android.content.Context;
import android.widget.Toast;

public class AsteroidGame {
	protected boolean inPlay;
	public static ArrayList<GameObject> pieces = new ArrayList<GameObject>();
	public static PlayerObject globalPlayer;
	private Context mContext;
	public static int height,width;
	
	private int asteroidCount;
	public boolean win;
	public int score;
	
	public boolean firing;
	public boolean lefting;
	public boolean righting;
	public boolean forwarding;
	
	public final int standardx;
	public final int standardy;
	
	public static int standardxsize;
	public static int standardysize;
	
	//Manages firing rate
	private int ticks;
	
	public AsteroidGame(Context context, int height, int width){
		this.mContext = context;
		inPlay = true;
		this.height = height;
		this.width = width;
		
		standardxsize = (int)(width*.12);
		standardysize= (int)(height*.08);
		
		this.standardx = (int)(this.width *.008);
		this.standardy = (int)(this.height *.006);
		
		firing = false;
		lefting = false;
		righting = false;
		forwarding = false;
		
		asteroidCount = 8;
		
		ticks = 0;
	}
	
	
	
	
	private int randomInteger(int start, int end, Random random){
		long diff = (long)start - (long)end + 1;
		long frac = (long)(Math.abs(diff*random.nextDouble()));
		int rand = (int)(frac+start);
		return rand;
	}
	
	
	void initObjects(){
		Random rand = new Random();
		 GameObject asteroid1 = new AsteroidObject(randomInteger(0,(int)(width*.4),rand),
				randomInteger(0,(int)(height*.4),rand),standardxsize,standardysize,standardx,standardy);
		 GameObject asteroid2 = new AsteroidObject(randomInteger(0,(int)(width*.4),rand),
				randomInteger(0,(int)(height*.4),rand),standardxsize,standardysize,standardx,0);
		 GameObject asteroid3 = new AsteroidObject(randomInteger(0,(int)(width*.4),rand),
				randomInteger((int)(height*.6),height,rand),standardxsize,standardysize,0,standardy);
		 GameObject asteroid4 = new AsteroidObject(randomInteger(0,(int)(width*.4),rand),
				randomInteger((int)(height*.6),height,rand),standardxsize,standardysize,standardx,-standardy);
		 GameObject asteroid5 = new AsteroidObject(randomInteger((int)(width*.6),width,rand),
				randomInteger(0,(int)(height*.4),rand),standardxsize,standardysize,-standardx,0);
		 GameObject asteroid6 = new AsteroidObject(randomInteger((int)(width*.6),width,rand),
				randomInteger(0,(int)(height*.4),rand),standardxsize,standardysize,standardx,-standardy);
		 GameObject asteroid7 = new AsteroidObject(randomInteger((int)(width*.6),width,rand),
				randomInteger((int)(height*.6),height,rand),standardxsize,standardysize,0,-standardy);
		 GameObject asteroid8 = new AsteroidObject(randomInteger((int)(width*.6),width,rand),
				randomInteger((int)(height*.6),height,rand),standardxsize,standardysize,standardx,standardy);
		 globalPlayer = new PlayerObject(width/2,height/2,(int)(standardxsize*.4),(int)(standardysize*.4),0,0);
		 
		 pieces.add(asteroid1);
		 pieces.add(asteroid2);
		 pieces.add(asteroid3);
		 pieces.add(asteroid4);
		 pieces.add(asteroid5);
		 pieces.add(asteroid6);
		 pieces.add(asteroid7);
		 pieces.add(asteroid8);
	}
	
	private void checkCollision(GameObject object){
		GameObject temp;
		int left,right,top,bottom,left_t,right_t,top_t,bottom_t;
		for(int i =0;i<pieces.size();i++){
			temp = pieces.get(i);
			if(!object.equals(temp)){
				left = object.x - (object.xsize/2);
				right = object.x + (object.xsize/2);
				top = object.y - (object.ysize/2);
				bottom = object.y + (object.ysize/2);
				left_t = temp.x - (temp.xsize/2);
				right_t = temp.x + (temp.xsize/2);
				top_t = temp.y - (temp.ysize/2);
				bottom_t = temp.y + (temp.ysize/2);
				if (((left_t > left && left_t <right) || (right_t> left && right_t <right)) &&
					((top_t > top && top_t < bottom) || (bottom_t > top && bottom_t < bottom))){
					//We have a collision
					handleCollision(object,temp);
					break;
				}
			}
			
		}
		
	}
	
	private void handleCollision(GameObject first, GameObject second){
		if((first instanceof AsteroidObject) && (second instanceof AsteroidObject) ){
			first.xvelocity = 0 - first.xvelocity;
			first.yvelocity = 0 - first.yvelocity;
			second.xvelocity = 0 - second.xvelocity;
			second.yvelocity = 0 - second.yvelocity;
			if (first.x > second.x){
				first.x += 1;
				second.x -=1;
			}
			else{
				first.x -= 1;
				second.x +=1;
				
			}
			
			if (first.y < second.y){
				first.y += 1;
				second.y -=1;
			}
			else{
				first.y -= 1;
				second.y +=1;
			}
			
		}
		else if((first instanceof AsteroidObject) && (second instanceof BulletObject)){
			first.exists = false;
			second.exists = false;
			pieces.remove(first);
			pieces.remove(second);
			asteroidCount--;
			score += 100;
//			//Make four smaller asteroids
//			if(first.xsize>=16){
//			 GameObject asteroid1 = new AsteroidObject(first.x - 4,
//						first.y -4,4,4,-2,-2);
//			 GameObject asteroid2 = new AsteroidObject(first.x - 4,
//					 first.y +4,4,4,-2,2);
//			 GameObject asteroid3 = new AsteroidObject(first.x + 4,
//					 first.y -4,4,4,2,-2);
//			 GameObject asteroid4 = new AsteroidObject(first.x + 4,
//					 first.x + 4,4,4,2,2);
//			 pieces.add(asteroid1);
//			 pieces.add(asteroid2);
//			 pieces.add(asteroid3);
//			 pieces.add(asteroid4);
//			}
//			else{
//				//It's gone for good
//			}
//			
		}
		else if((first instanceof BulletObject) && (second instanceof AsteroidObject)){
			first.exists = false;
			second.exists = false;
			pieces.remove(first);
			pieces.remove(second);
			asteroidCount--;
			score +=100;
//			//Make four smaller asteroids
//			if(second.xsize>=16){
//			 GameObject asteroid1 = new AsteroidObject(second.x - 4,
//					 second.y -4,4,4,-2,-2);
//			 GameObject asteroid2 = new AsteroidObject(second.x - 4,
//					 second.y +4,4,4,-2,2);
//			 GameObject asteroid3 = new AsteroidObject(second.x + 4,
//					 second.y -4,4,4,2,-2);
//			 GameObject asteroid4 = new AsteroidObject(second.x + 4,
//					 second.x + 4,4,4,2,2);
//			 pieces.add(asteroid1);
//			 pieces.add(asteroid2);
//			 pieces.add(asteroid3);
//			 pieces.add(asteroid4);
//			}
//			else{
//				//It's gone for good
//			}
//			
		}
		else if(((first instanceof AsteroidObject) && (second instanceof PlayerObject))||
				((second instanceof AsteroidObject) && (first instanceof PlayerObject))){
			//Game's Over
			inPlay = false;
		}
	}
	
	public void createBullet(){
		double init_x = (8 * Math.cos((Math.PI/180)*((PlayerObject)globalPlayer).top_angle)) + globalPlayer.x;
		double init_y = (-8 * Math.sin((Math.PI/180)*((PlayerObject)globalPlayer).top_angle)) + globalPlayer.y;
		double init_velx = 6* standardx * Math.cos((Math.PI/180)*globalPlayer.top_angle);
		double init_vely = 6 * -standardx * Math.sin((Math.PI/180)*globalPlayer.top_angle);
		GameObject bullet = new BulletObject((int)init_x,(int)init_y,(int)(standardxsize*.2),(int)(standardysize*.2),(int)init_velx*2,(int)init_vely*2);
		pieces.add(bullet);
	}
	
	public void leftClicked(){
		((PlayerObject)globalPlayer).angular_vel = 5 * standardx;
	}
	
	public void rightClicked(){
		((PlayerObject)globalPlayer).angular_vel = -(5* standardy);
	}
	
	public void forwardClicked(){
		globalPlayer.xvelocity = (int)(5*standardx * Math.cos((Math.PI/180)*((PlayerObject)globalPlayer).top_angle));
		globalPlayer.yvelocity = (int)(-5 * standardy * Math.sin((Math.PI/180)*((PlayerObject)globalPlayer).top_angle));
	}
	
	public void updateInputs(){
		if(firing && ticks > 100){
			ticks=0;
			createBullet();
		}
		else if(forwarding)
			forwardClicked();
		else if(righting)
			rightClicked();
		else if(lefting)
			leftClicked();
		ticks++;	
	}
	
	public void clearPieces(){
		pieces.clear();
	}
	
	public void handleLost(){
		if(!inPlay)
			win = false;
	}
	
	public void checkWon(){
		if(asteroidCount == 0 ){
			inPlay = false;
			win = true;
		}
	}
	
	public void checkBullets(){
		GameObject temp;
		for(int i =0;i<pieces.size();i++){
			temp = pieces.get(i);
			if(temp instanceof BulletObject){
				if(((BulletObject) temp).framesExisted > 10){
					temp.exists = false;
					pieces.remove(i);
				}
			}
		}
		
	}
	
	boolean runGame(){
		GameObject temp;
		for(int i =0;i<pieces.size();i++){
			temp = pieces.get(i);
			temp.updatePosition();
			checkCollision(temp);
			updateInputs();
		}
		checkBullets();
		checkCollision(globalPlayer);
		globalPlayer.updatePosition();
		handleLost();
		checkWon();
		
		return inPlay;
	}

}
