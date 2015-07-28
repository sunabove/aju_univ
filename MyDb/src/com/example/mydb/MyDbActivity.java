package com.example.mydb;

import com.example.mydb.FeedReaderContract.FeedEntry;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MyDbActivity extends Activity {

	FeedReaderDbHelper mDbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_db);

		this.mDbHelper = new FeedReaderDbHelper(getApplicationContext()); 
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
		String sortOrder =
		    FeedEntry.COLUMN_NAME_TITLE + " DESC";
		
		String selection = "" ;
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
}
