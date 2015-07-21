package com.example.androidphonedialer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AndroidPhoneDialerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_phone_dialer); 
		
		/** Create our Intent to call the Dialer */
		/** Pass the Dialer the number 5551212 */
		Intent DialIntent = new
		///Intent(Intent.DIAL_ACTION,Uri.parse("tel:5551212"));
		Intent(Intent.ACTION_DIAL, Uri.parse("tel:010 2248 0000"));
		/** Use NEW_TASK_LAUNCH to launch the Dialer Activity */
		//DialIntent.setLaunchFlags(Intent.NEW_TASK_LAUNCH );
		DialIntent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
		
		/** Finally start the Activity */
		startActivity(DialIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.android_phone_dialer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
