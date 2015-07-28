package com.example.mydb;

import com.example.mydb.FeedReaderContract.FeedEntry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
	// If you change the database schema, you must increment the database
	// version.
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "FeedReader.db";

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" + FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ FeedEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP + FeedEntry.COLUMN_NAME_TITLE + TEXT_TYPE +
			// Any other options for the CREATE command
			" )";

	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

	private FeedReaderDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);

	}

	public void insertTestRecords() {
		// insert test data
		int maxId = 0;
		int iLen = 10;
		for (int i = 0; i < iLen; i++) {
			int id = (maxId + i + 1);
			String title = "myTitle";
			String content = "myContent";
			this.insertRecord(id, title, content);
		}
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// This database is only a cache for online data, so its upgrade policy
		// is
		// to simply to discard the data and start over
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);
	}

	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}

	public long insertRecord(int id, String title, String content) {
		// Gets the data repository in write mode
		SQLiteDatabase db = this.getWritableDatabase();

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

	public int deleteRecord(int rowId) {
		// Gets the data repository in write mode
		SQLiteDatabase db = this.getWritableDatabase();
		// Define 'where' part of query.
		String selection = FeedEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
		// Specify arguments in placeholder order.
		String[] selectionArgs = { String.valueOf(rowId) };
		// Issue SQL statement.
		int rowCount = db.delete(FeedEntry.TABLE_NAME, selection, selectionArgs);

		return rowCount;
	}

	public Cursor getCursor() {
		SQLiteDatabase db = this.getReadableDatabase();

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = { FeedEntry._ID, FeedEntry.COLUMN_NAME_TITLE,
		// FeedEntry.COLUMN_NAME_UPDATED,
		// ...
		};

		// How you want the results sorted in the resulting Cursor
		String sortOrder = FeedEntry.COLUMN_NAME_TITLE + " DESC";

		String selection = null;
		// Specify arguments in placeholder order.
		String[] selectionArgs = {};

		Cursor c = db.query(FeedEntry.TABLE_NAME, // The table to query
				projection, // The columns to return
				selection, // The columns for the WHERE clause
				selectionArgs, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				sortOrder // The sort order
				);

		return c;
	}

	// singleton static function
	static FeedReaderDbHelper DB_HELPER = null;

	public static FeedReaderDbHelper getFeedReaderDbHelper(Context context) {
		if (DB_HELPER == null) {
			DB_HELPER = new FeedReaderDbHelper(context);
		}

		return DB_HELPER;
	}

}