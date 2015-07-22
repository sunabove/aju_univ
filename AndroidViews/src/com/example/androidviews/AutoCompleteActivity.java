package com.example.androidviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AutoCompleteActivity extends Activity {
	
	TextView messageTv ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_autocomplete);
		
		this.messageTv = (TextView) findViewById( R.id.test_message );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) { 
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) { 
		return super.onOptionsItemSelected(item);
	}
}
