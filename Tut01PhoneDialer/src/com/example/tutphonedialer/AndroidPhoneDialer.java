package com.example.tutphonedialer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class AndroidPhoneDialer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_phone_dialer); 

		String error = null;
		try {
			Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:3331212"));
			dialIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(dialIntent);
		} catch (Exception e) {
			e.printStackTrace();
			error = "이 기기에서는 전화를 걸수가 없습니다.";
		}
		
		if( error != null ) {
			Toast toast = Toast.makeText(this.getApplicationContext(), error, Toast.LENGTH_LONG);
			toast.show();
		}
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
