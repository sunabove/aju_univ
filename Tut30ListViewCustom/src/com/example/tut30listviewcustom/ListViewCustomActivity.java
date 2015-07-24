package com.example.tut30listviewcustom;

import java.io.*;
import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*; 
import android.view.*;
import android.view.View.*;
import android.widget.*; 

public class ListViewCustomActivity extends Activity {
	
	ListView listView ;
	
	static final String[] MONTHS = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November",
	"December" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_custom);
		
		this.listView = (ListView) findViewById( R.id.myListView );
		
		//ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MONTHS); 
		
		MonthAdapter monthAdapter = new MonthAdapter( this, MONTHS );
		
		this.listView.setAdapter( monthAdapter );
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view_custom, menu);
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
