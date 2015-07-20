package com.example.helloworldtext;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button myButton  = (Button) findViewById( R.id.myButton) ; 
		
		myButton.setOnClickListener( new View.OnClickListener() { 
			int clickCount ; 
			@Override
			public void onClick(View v) { 
				myButton.setText( "click count" +  clickCount ++ );
			} 
		} );
	}
	
	protected void onCreateSimple(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button myButton  = (Button) findViewById( R.id.myButton) ; 
		
		View.OnClickListener listener = new View.OnClickListener() { 
			int clickCount ; 
			@Override
			public void onClick(View v) { 
				myButton.setText( "click count" +  clickCount ++ );
			} 
		};
		
		myButton.setOnClickListener( listener );
	}
	
	protected void onCreateDetail(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		View view ;
		view = findViewById( R.id.myButton) ; 
		final Button myButton ;
		myButton = (Button) view ;
		
		class MyOnClickListener implements View.OnClickListener { 
			int clickCount ; 
			@Override
			public void onClick(View v) { 
				myButton.setText( "click count" +  clickCount ++ );
			} 
		}
		
		MyOnClickListener listener = new MyOnClickListener();
		
		myButton.setOnClickListener( listener );
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
