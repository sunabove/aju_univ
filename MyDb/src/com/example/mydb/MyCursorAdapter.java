package com.example.mydb;

import com.example.mydb.FeedReaderContract.FeedEntry;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyCursorAdapter extends CursorAdapter {
	
	int rowNo ; 
	
	public MyCursorAdapter(Context context, Cursor cursor ) {
		super(context, cursor, 0);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return LayoutInflater.from(context).inflate(R.layout.item_my_cursor, parent, false);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// Find fields to populate in inflated template
		ImageView iconIv = (ImageView) view.findViewById( R.id.my_cursor_icon );
		TextView idTv = (TextView) view.findViewById(R.id.my_cursor_id);
		TextView titleTv = (TextView) view.findViewById(R.id.my_cursor_title);
		// Extract properties from cursor 

		iconIv.setImageResource( rowNo %2 == 0 ? R.drawable.even : R.drawable.odd );
		String body = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry._ID));
		int priority = cursor.getInt(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE));
		// Populate fields with extracted properties
		idTv.setText(body);
		titleTv.setText(String.valueOf(priority));
		
		rowNo ++ ; 
	}
}
