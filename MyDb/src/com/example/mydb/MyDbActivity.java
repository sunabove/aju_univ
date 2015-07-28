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

		this.mDbHelper = new FeedReaderDbHelper(getApplicationContext());  
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		ListView listView = this.listView ; 
		
		// insert test records
		String msg ; 
		int maxId = 0 ; 
		int iLen = 10 ; 
		for( int i = 0 ; i < iLen ; i ++ ) {
			int id = (maxId +  i + 1 );
			String title = "myTitle" ; 
			String content = "myContent"; 
			insertRecord( id, title, content );
		}
		
		msg = "Record %d inserted." ;
		msg = String.format( msg, iLen) ;
		this.messageTv.setText( msg );
		// end of inserting test data
		
		Cursor cursor = this.getCursor();
		this.showTableCursor(listView, cursor);
	}

	public long insertRecord(int id, String title, String content) {
		// Gets the data repository in write mode
		SQLiteDatabase db = mDbHelper.getWritableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_NAME_ENTRY_ID, id);
		values.put(FeedEntry.COLUMN_NAME_TITLE, title);
		// values.put(FeedEntry.COLUMN_NAME_CONTENT, content);

		// Insert the new row, returning the primary key value of the new row
		long newRowId;
		newRowId = db.insert(FeedEntry.TABLE_NAME, null, values);
		
		return newRowId; 
	}
	
	public Cursor getCursor() {
		SQLiteDatabase db = mDbHelper.getReadableDatabase();

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = {
		    FeedEntry._ID,
		    FeedEntry.COLUMN_NAME_TITLE,
		    //FeedEntry.COLUMN_NAME_UPDATED,
		    //...
		    };

		// How you want the results sorted in the resulting Cursor
		String sortOrder = FeedEntry.COLUMN_NAME_TITLE + " DESC";
		
		String selection = null ;
		// Specify arguments in placeholder order.
		String[] selectionArgs = {  };

		Cursor c = db.query(
		    FeedEntry.TABLE_NAME,  // The table to query
		    projection,                               // The columns to return
		    selection,                                // The columns for the WHERE clause
		    selectionArgs,                            // The values for the WHERE clause
		    null,                                     // don't group the rows
		    null,                                     // don't filter by row groups
		    sortOrder                                 // The sort order
		    );
		
		return c ; 
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
