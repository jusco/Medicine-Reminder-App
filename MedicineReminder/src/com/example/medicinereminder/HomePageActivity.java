package com.example.medicinereminder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class HomePageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		
		MyGuy guy = MyGuy.getUser();
		AlarmTracker tracker = AlarmTracker.getTracker();
		
		TextView fullNameText = (TextView)findViewById(R.id.fullNameText);
		

		fullNameText.setText(guy.fullName);
		
		setAvatar();
		TextView streakText = (TextView)findViewById(R.id.medStreak);
		streakText.setText(Integer.toString(tracker.streak));
		
		
		if (tracker.appointments!=null){
			TextView appointmentText = (TextView)findViewById(R.id.appointmentDate);
			int month = tracker.appointments.get(0).getTime().getMonth();
			int day = tracker.appointments.get(0).getTime().getDate();			
			appointmentText.setText(month + "/" + day);
		}
		
		if (tracker.refills!=null){
			TextView refillText = (TextView)findViewById(R.id.refillDate);
			int month = tracker.refills.get(0).getTime().getMonth();
			int day = tracker.refills.get(0).getTime().getDate();			
			refillText.setText(month + "/" + day);
		}

	}
	
	public void setAvatar(){
		MyGuy guy = MyGuy.getUser();
		ImageView image = (ImageView)findViewById(R.id.avatarImage);
		if(guy.avatar >0 && guy.avatar <9){
			switch (guy.avatar){
			case 1:
				image.setImageResource(R.drawable.man1_128x128);
				break;
			case 2:
				image.setImageResource(R.drawable.woman1_128x128);
				break;
			case 3:
				image.setImageResource(R.drawable.man2_128x128);
				break;
			case 4:
				image.setImageResource(R.drawable.woman2_128x128);
				break;
			case 5:
				image.setImageResource(R.drawable.man3_128x128);
				break;
			case 6:
				image.setImageResource(R.drawable.woman3_128x128);
				break;
			case 7:
				image.setImageResource(R.drawable.man4_128x128);
				break;
			case 8:
				image.setImageResource(R.drawable.woman4_128x128);
				break;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home_page, menu);
		return true;
	}
	
	public void toTakeMedicine(View v){
		//TODO Make sure that going straight to take medicine is the same as activating alarm
		AlarmTracker.getTracker().setSoundAlarm(false);

		Intent intent = new Intent(this,AlarmPage.class);
		startActivity(intent);
	}
	
	public void toEditSettings(View v){
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}	
	
	public void onResume(){
		TextView fullNameText = (TextView)findViewById(R.id.fullNameText);

		fullNameText.setText(MyGuy.getUser().fullName);
		TextView streakText = (TextView)findViewById(R.id.medStreak);
		streakText.setText(Integer.toString(AlarmTracker.getTracker().streak));
		setAvatar();
		super.onResume();
	}
	
	public void onStop(){
		//Send when the app exits
		MyGuy.getUser().sendToDatabase(this);
		AlarmTracker.getTracker().sendToDatabase(this);
		super.onStop();
	}
	
	public void onClickPlay(View v){
		Intent intent = new Intent(this, StreakActivity.class);
		startActivity(intent);
	}

}
