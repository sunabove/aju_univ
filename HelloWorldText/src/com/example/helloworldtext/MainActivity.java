package com.example.helloworldtext;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText firstNumberEt = (EditText) findViewById( R.id.firstNumber );
		
		final EditText secondNumberEt = (EditText) findViewById( R.id.secondNumber );
		
		final TextView resultTv = (TextView) findViewById( R.id.resultTv );
		
		final Button plusBtn  = (Button) findViewById( R.id.plusBtn ) ; 
		
		plusBtn.setOnClickListener( new View.OnClickListener() {  
			@Override
			public void onClick(View v) { 
				String firstNumberStr = "" + firstNumberEt.getText();
				String secondNumberStr = "" + secondNumberEt.getText();
				
				double firstNum  = Double.parseDouble( firstNumberStr.trim() );
				double secondNum = Double.parseDouble( secondNumberStr.trim() );
				
				double result = firstNum + secondNum ;
				
				String resultStr = "" + result ; 
				
				resultTv.setText( resultStr ); 
			} 
		} );
	} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
