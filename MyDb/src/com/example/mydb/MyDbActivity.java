package com.example.mydb;

import com.example.mydb.FeedReaderContract.FeedEntry;

import java.io.*;
import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*; 
import android.view.*;
import android.view.View.*;
import android.widget.*; 
import android.database.*;
import android.database.sqlite.*; 

public class MyDbActivity extends Activity {

	FeedReaderDbHelper mDbHelper;
	TextView messageTv ; 
	ListView listView ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_db);
		
		this.messageTv = (TextView) findViewById( R.id.my_db_message );
		this.listView = (ListView) findViewById( R.id.my_db_listview );

		this.mDbHelper =  FeedReaderDbHelper.getFeedReaderDbHelper( getApplicationContext());  
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		ListView listView = this.listView ; 
		FeedReaderDbHelper dbHelper = this.mDbHelper ; 
		
		dbHelper.insertTestRecords();
		
		Cursor cursor = dbHelper.getCursor();
		
		this.showTableCursor(listView, cursor);
	} 
	
	public void showTableCursor( ListView listView , Cursor cursor ) {
		String[] from = {
			    FeedEntry._ID,
			    FeedEntry.COLUMN_NAME_TITLE,
			    //FeedEntry.COLUMN_NAME_UPDATED,
			    //...
			    };
		int[] to  = { R.id.rowid, R.id.title, };
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item, cursor, from, to);
		
		listView.setAdapter( adapter );
	}
}
