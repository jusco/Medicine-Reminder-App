package com.example.medicinereminder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class StreakActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_streak);
		
		AlarmTracker tracker = AlarmTracker.getTracker();
		
		TextView tempText = (TextView) findViewById(R.id.medStreak2);
		tempText.setText(Integer.toString(tracker.streak));
		
		tempText = (TextView) findViewById(R.id.highScore);
		tempText.setText(Integer.toString(tracker.highscore));
		
		tempText = (TextView) findViewById(R.id.powerUp);
		tempText.setText(getPowerUp());
		
	}

	private String getPowerUp(){
		int streak = AlarmTracker.getTracker().streak;
		switch(streak){
		case 1:
			return "Bullet Rate x2";
		case 3:
			return "Bullet Rate x3";
		case 6:
			return "Bullet Rate x4";
		case 9:
			return "Bullet Rate x5";
		case 15:
			return "Bullet Rate x6!!";
		default:
			return "No Powerups yet :(";
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.streak, menu);
		return true;
	}
	
	public void onPlayClicked(View v){
		Intent intent = new Intent(this, com.example.asteroids.Asteroids.class);
		startActivity(intent);
		finish();
	}

}
