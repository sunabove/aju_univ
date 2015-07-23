package com.addressBook;

import java.io.*;
import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*; 
import android.view.*;
import android.view.View.*;
import android.widget.*; 

public class AboutActivity extends Activity {
	
	EditText programInfo ;
	TextView programName ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		this.programInfo = (EditText) findViewById( R.id.about_programInfo );
		this.programName = (TextView) findViewById( R.id.about_programName );
		
		OnClickListener listener = new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				finish();
			} 
		};
		
		this.programInfo.setOnClickListener( listener );
		this.programName.setOnClickListener( listener );
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
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
