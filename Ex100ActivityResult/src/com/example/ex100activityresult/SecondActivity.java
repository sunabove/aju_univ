package com.example.ex100activityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends Activity {
	
	EditText userInputEt ; 
	Button  okBtn ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second); 
		
		this.userInputEt = (EditText) findViewById( R.id.second_user_input );
		this.okBtn = (Button) findViewById( R.id.second_ok );
		
		this.okBtn.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				onClickSecondButton( v );
			} 
		});
	}
	
	public void onClickSecondButton( View v ) {
		// Create intent to deliver some kind of result data
		String userInputStr = "" + this.userInputEt.getText();
		
		Intent resultIntent = new Intent(); 
		resultIntent.putExtra( "userInputStr", userInputStr );
		
		int resultCode = 1 ; 
		
		setResult( resultCode, resultIntent );

		this.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
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
