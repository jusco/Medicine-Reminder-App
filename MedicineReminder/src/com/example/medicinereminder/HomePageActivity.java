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
		
		TextView fullNameText = (TextView)findViewById(R.id.fullNameText);

		fullNameText.setText(guy.fullName);
		
		ImageView image = (ImageView)findViewById(R.id.avatarImage);
		if(guy.avatar >0 && guy.avatar <9){
			switch (guy.avatar){
			case 1:
				image.setImageResource(R.drawable.man1_64x64);
				break;
			case 2:
				image.setImageResource(R.drawable.woman1_64x64);
				break;
			case 3:
				image.setImageResource(R.drawable.man2_64x64);
				break;
			case 4:
				image.setImageResource(R.drawable.woman2_64x64);
				break;
			case 5:
				image.setImageResource(R.drawable.man3_64x64);
				break;
			case 6:
				image.setImageResource(R.drawable.woman3_64x64);
				break;
			case 7:
				image.setImageResource(R.drawable.man4_64x64);
				break;
			case 8:
				image.setImageResource(R.drawable.woman4_64x64);
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
		super.onResume();
	}
	
	public void onClickPlay(View v){
		Intent intent = new Intent(this, com.example.asteroids.Asteroids.class);
		startActivity(intent);
	}

}
