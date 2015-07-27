package com.example.tut50database;

import java.io.*;
import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.provider.BaseColumns;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.database.Cursor;
import android.database.sqlite.*;

public class DbManager {

	FeedReaderDbHelper mDbHelper;

	private DbManager(Context context) {
		this.mDbHelper = new FeedReaderDbHelper(context);
	}

	public void insert(int id, String title, String subTitle) {
		// Gets the data repository in write mode
		SQLiteDatabase db = mDbHelper.getWritableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_NAME_ENTRY_ID, id);
		values.put(FeedEntry.COLUMN_NAME_TITLE, title);
		values.put(FeedEntry.COLUMN_NAME_SUBTITLE, subTitle);

		// Insert the new row, returning the primary key value of the new row
		long newRowId;
		newRowId = db.insert(FeedEntry.TABLE_NAME, FeedEntry.COLUMN_NAME_NULLABLE, values);
	}

	public void select() {
		SQLiteDatabase db = mDbHelper.getReadableDatabase();

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = { FeedEntry._ID, FeedEntry.COLUMN_NAME_TITLE, FeedEntry.COLUMN_NAME_SUBTITLE, };

		// How you want the results sorted in the resulting Cursor
		String sortOrder = FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

		String selection = "";
		String[] selectionArgs = {};

		Cursor cursor = db.query( FeedEntry.TABLE_NAME, // The table to query
				projection, // The columns to return
				selection, // The columns for the WHERE clause
				selectionArgs, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				sortOrder // The sort order
				);

		cursor.moveToFirst();
		long itemId = cursor.getLong( cursor.getColumnIndexOrThrow(FeedEntry._ID) );
		String itemTitle = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE));
		
	}
	
	public int delete() {
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		
		int rowId = 1;
		String tableName = FeedEntry.TABLE_NAME;
		
		// Define 'where' part of query.
		String selection = FeedEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
		// Specify arguments in placeholder order.
		String[] selectionArgs = { String.valueOf(rowId) };
		// Issue SQL statement.
		int delCount = db.delete(tableName, selection, selectionArgs);
		
		return delCount;
	}
	
	public int update() {
		SQLiteDatabase db = mDbHelper.getReadableDatabase();

		String title = "abc";
		int rowId = 1;
		
		// New value for one column
		ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_NAME_TITLE, title);

		// Which row to update, based on the ID
		String selection = FeedEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
		String[] selectionArgs = { String.valueOf(rowId) };

		int count = db.update(
		    FeedEntry.TABLE_NAME,
		    values,
		    selection,
		    selectionArgs);
		
		return count; 
	}

	private static DbManager DB_MANAGER;

	public static DbManager getDbManager(Context context) {
		if (DB_MANAGER == null) {
			DB_MANAGER = new DbManager(context);
		}

		return DB_MANAGER;
	}

}
