package com.example.medicinereminder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;

public class RegistrationAvatarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration_avatar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration_avatar, menu);
		return true;
	}
	
	public void submitForm(View v){
		Intent intent = new Intent(this, HomePageActivity.class);
		startActivity(intent);
		finish();
	}
	

	public void toPrevPage(View v){
		Intent intent = new Intent(this, RegistrationRefillsActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void selectAvatar(View v){
		v.setBackgroundColor(Color.RED);
		/*TODO Store avatar on click. Also, set other avatars background color to grey, so that
		 *only one is selected at a time.
		 */
}

}
