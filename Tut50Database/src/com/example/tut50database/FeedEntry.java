package com.example.tut50database;

import android.provider.BaseColumns;

public class FeedEntry implements BaseColumns {
	
	public static final String TABLE_NAME = "entry";
	public static final String COLUMN_NAME_ENTRY_ID = "entryid";
	public static final String COLUMN_NAME_TITLE = "title";
	public static final String COLUMN_NAME_SUBTITLE = "subtitle";
	public static final String COLUMN_NAME_NULLABLE = "entryid" ;

	public FeedEntry() { 
	}

}
