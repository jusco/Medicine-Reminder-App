package com.example.asteroids;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {
	private Paint p;
	private ArrayList<GameObject> items;
	private GameObject item;
	private ShapeDrawable shape;
	
	public GameView(Context context) {
		super(context);
		init();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	protected void init(){
		p = new Paint();
		p.setColor(Color.BLACK);
		p.setStrokeWidth(4);
	}
	
	
	protected void onDraw(Canvas canvas){
		items = AsteroidGame.pieces;
		if(items!=null){
			for(int i=0;i<items.size();i++){
				item = items.get(i);
				if(item instanceof AsteroidObject){
					shape = ((AsteroidObject)item).shape;
					shape.draw(canvas);
				}
				else if(item instanceof BulletObject){
					shape = ((BulletObject)item).shape;
					shape.draw(canvas);
				}
				else if(item instanceof PlayerObject){
					
				}
			}
		}
		if(AsteroidGame.globalPlayer!=null){
			int [] top =AsteroidGame.globalPlayer.getTop();
			int [] left = AsteroidGame.globalPlayer.getLeft();
			int [] right = AsteroidGame.globalPlayer.getRight();
			canvas.drawLine(top[0],top[1],left[0],left[1],p);
			canvas.drawLine(left[0],left[1],right[0],right[1],p);
			canvas.drawLine(right[0],right[1],top[0],top[1],p);
		}
	}
}
