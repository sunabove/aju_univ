package com.example.activityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FirstActivity extends Activity {
	
	EditText firstNumberEt ;
	EditText secondNumberEt ;
	Button	plusBtn ;
	TextView messageTv ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
		this.firstNumberEt = (EditText) findViewById( R.id.first_firstNumer );
		this.secondNumberEt = (EditText) findViewById( R.id.first_secondNumber );
		this.plusBtn = (Button) findViewById( R.id.first_plus );
		this.messageTv = (TextView) findViewById( R.id.first_messae );
		
		this.messageTv.setText( "" );
		
		this.plusBtn.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				onClickPlusBtn();
			}
			
		});
	}
	
	public void onClickPlusBtn() {
		String firstNumber = "" + this.firstNumberEt.getText() ; 
		String secondNumber = "" + this.secondNumberEt.getText();
		
		Activity fromActivity = this;
		Class toActivityClass = SecondActivity.class ; 
		Intent intent = new Intent( fromActivity, toActivityClass );
		intent.putExtra( "firstNumber", firstNumber );
		intent.putExtra( "secondNumber", secondNumber );
		
		int requestCode = 1; 
		
		this.startActivityForResult(intent, requestCode);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		String msg = null ; 
		if( data == null ) {
			msg = "넘어온 인테트가 없습니다.";
		} else if( data != null ) {
			String result = data.getStringExtra( "result" );
			msg = result ; 
		}
		
		if( msg != null ) {
			this.messageTv.setText( msg );
		}
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
