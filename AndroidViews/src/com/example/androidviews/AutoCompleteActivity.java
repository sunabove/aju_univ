package com.example.androidviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AutoCompleteActivity extends Activity {
	
	TextView messageTv ; 
	Button okBtn ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_autocomplete);
		
		this.messageTv = (TextView) findViewById( R.id.auto_message );
		this.okBtn = (Button) findViewById( R.id.auto_ok );
		
		this.okBtn.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				finish();
			} 
		});
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
