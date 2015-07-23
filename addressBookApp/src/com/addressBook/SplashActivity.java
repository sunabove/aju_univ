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

public class SplashActivity extends Activity {
	
	ImageView logoIv ;
	TextView appNameTv ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		this.logoIv = (ImageView) findViewById( R.id.splash_logo );
		this.appNameTv = (TextView) findViewById( R.id.splash_appName );
		
		this.logoIv.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				Intent intent = new Intent( SplashActivity.this, PersonListActivity.class );
				startActivity( intent );
			} 
		});
		
		this.appNameTv.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				Intent intent = new Intent( SplashActivity.this, AboutActivity.class );
				startActivity( intent );
			} 
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
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
