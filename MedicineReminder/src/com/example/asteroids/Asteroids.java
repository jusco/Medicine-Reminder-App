package com.example.asteroids;

import com.example.medicinereminder.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Asteroids extends Activity {
	private AsteroidGame game;
	private GameView gv;
	private boolean stopThread;
	private boolean createCalled;
	private Context mContext;
	private TextView score;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asteroids);
		gv = (GameView)findViewById(R.id.gameview);
		score = (TextView)findViewById(R.id.score);
		stopThread = false;
		mContext = this;
		
		Button fire = (Button) findViewById(R.id.fireButton);
		Button left= (Button) findViewById(R.id.leftButton);
		Button right= (Button) findViewById(R.id.rightButton);
		Button forward= (Button) findViewById(R.id.forwardButton);
		fire.setOnTouchListener(new OnTouchListener() {
	        public boolean onTouch(View v, MotionEvent event) {
	               switch ( event.getAction() ) {
	                case MotionEvent.ACTION_DOWN:
	                	game.firing = true;
	                	break;
	                case MotionEvent.ACTION_UP: 
	                	game.firing=false;
	                	break;
	                }
	                return true;
	        }
	    });
		
		left.setOnTouchListener(new OnTouchListener() {
	        public boolean onTouch(View v, MotionEvent event) {
	               switch ( event.getAction() ) {
	                case MotionEvent.ACTION_DOWN:
	                	game.lefting = true;
	                	break;
	                case MotionEvent.ACTION_UP: 
	                	game.lefting=false;
	                	break;
	                }
	                return true;
	        }
	    });
		
		right.setOnTouchListener(new OnTouchListener() {
	        public boolean onTouch(View v, MotionEvent event) {
	               switch ( event.getAction() ) {
	                case MotionEvent.ACTION_DOWN:
	                	game.righting = true;
	                	break;
	                case MotionEvent.ACTION_UP: 
	                	game.righting=false;
	                	break;
	                }
	                return true;
	        }
	    });
		
		forward.setOnTouchListener(new OnTouchListener() {
	        public boolean onTouch(View v, MotionEvent event) {
	               switch ( event.getAction() ) {
	                case MotionEvent.ACTION_DOWN:
	                	game.forwarding = true;
	                	break;
	                case MotionEvent.ACTION_UP: 
	                	game.forwarding=false;
	                	break;
	                }
	                return true;
	        }
	    });
		createCalled=true;
	}
	
	@Override
	public void onWindowFocusChanged (boolean hasFocus) {
	        // the height will be set at this point
			if (createCalled){
				createCalled= false;
				int height = gv.getMeasuredHeight(); 
	        	int width = gv.getMeasuredWidth();
	        	game= new AsteroidGame(this,height,width);
	        	game.initObjects();
				int total = 1;
			
				new BackgroundTask().execute((Integer)total);
			}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.asteroids, menu);
		return true;
	}
	
//	public void onLeftClicked(View v){
//		game.leftClicked();
//	}
//	
//	public void onRightClicked(View v ){
//		game.rightClicked();
//	}
//	
//	public void onForwardClicked(View v){
//		game.forwardClicked();
//	}
//	
//	public void onFireClicked(View v){
//		game.createBullet();
//	}
	
	public void onBackClicked(View v){
		stopThread=true;
		finish();
		//Will go back to app
	}
	
	public void onBackPressed() {
		stopThread=true;
        finish();
        //start activity A here

    }
	
class BackgroundTask extends AsyncTask<Integer,Void,Integer>{
		
		protected Integer doInBackground(Integer ... total){
			try{
				Thread.sleep(80);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			boolean result = game.runGame();
			if(!result)
				stopThread = true;
			return null;
		}
		
		protected void onPostExecute(Integer result){
			gv.invalidate();
			score.setText("Score " + game.score);
			if(!stopThread)
				new BackgroundTask().execute(result);
			else{
				game.clearPieces();
				if(game.win)
					Toast.makeText(mContext, R.string.won,
			                Toast.LENGTH_LONG).show();
				else
					Toast.makeText(mContext, R.string.lost,
			                Toast.LENGTH_LONG).show();
			}
		}

	}





}
