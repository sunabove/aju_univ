package com.example.ex100activityresult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends Activity {
	
	Button showSecondActivityBtn ; 
	TextView messageTv ; 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
		this.showSecondActivityBtn = (Button) findViewById( R.id.first_show_second_activity );
		this.messageTv = (TextView) findViewById( R.id.first_message );
		
		showSecondActivityBtn.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				onClickShowSecondActivityBtn();
			} 
		});
	}
	
	public void onClickShowSecondActivityBtn() {
		Activity fromActivity = this ; 
		String sendStr = "Hello....";
		Intent intent = new Intent( fromActivity, SecondActivity.class );
		intent.putExtra( "sendStr", sendStr );
		
		//startActivity( intent ); 
		int requestCode = 1 ; 
		startActivityForResult( intent, requestCode ); 
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent ) {
		String userInputStr = resultIntent.getStringExtra( "userInputStr" );
		
		String msg = "";
		if( userInputStr == null || userInputStr.length() < 1 ) {
			msg = "There is no input data.";
		} else {
			msg = userInputStr; 
		}
		
		this.messageTv.setText( userInputStr );
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
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
