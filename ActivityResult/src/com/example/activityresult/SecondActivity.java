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

public class SecondActivity extends Activity {
	
	EditText resultEt ;
	Button okBtn ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		this.resultEt = (EditText) this.findViewById( R.id.second_result );
		this.okBtn = (Button) this.findViewById( R.id.second_ok );
		
		this.okBtn.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				onClickOkBtn();
			} 
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		String msg = null ;
		
		Intent intent = this.getIntent();
		
		if( intent == null ) {
			msg = "넘오온 데이터가 없습니다.";
		} else if( intent != null ) {
			Double firstNumber = null ;
			Double secondNumber = null ; 
			try {
				firstNumber = Double.parseDouble( intent.getStringExtra( "firstNumber" ).trim() );
				secondNumber = Double.parseDouble( intent.getStringExtra( "secondNumber" ).trim() ) ;
			} catch (NumberFormatException e) { 
			} 
			
			if( firstNumber == null || secondNumber == null ) {
				msg = "넘어온 데이터 값이 숫자가 아닙니다.";
			} else {
				msg = "" + ( firstNumber + secondNumber );
			}
		}
		
		if( msg != null ) {
			this.resultEt.setText( msg );
		}
	}
	
	public void onClickOkBtn() {
		String result = "" + this.resultEt.getText() ; 
		
		Intent intent = new Intent();
		intent.putExtra( "result", result );
		
		int resultCode = 1; 
		this.setResult(resultCode, intent);
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
