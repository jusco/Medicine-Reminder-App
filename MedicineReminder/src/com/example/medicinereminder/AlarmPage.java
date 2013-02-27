package com.example.medicinereminder;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class AlarmPage extends Activity {
	AlarmSet alarmSet;
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.alarm_page);
		 alarmSet = new AlarmSet(this);
		 MediaPlayer mp = MediaPlayer.create(getBaseContext(),R.raw.lickshot);
		 mp.start();
	 }
	 
	 public void onIgnoreButtonClick(View view){
		 EditText times = (EditText)findViewById(R.id.editSleep);
		 alarmSet.setSleep(times.getText().toString());
		 finish();
	 }
	 

}

