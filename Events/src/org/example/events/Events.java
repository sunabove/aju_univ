package org.example.events;

import static android.provider.BaseColumns._ID;
import static org.example.events.Constants.TABLE_NAME;
import static org.example.events.Constants.TIME;
import static org.example.events.Constants.TITLE;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Events extends Activity {

	private EventsData events;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		events = new EventsData(this);
		try {
			addEvent("Hello, Android!");
			Cursor cursor = getEvents();
			showEvents(cursor);
		} finally {
			events.close();
		}
	}

	private void addEvent(String string) {
		// Insert a new record into the Events data source.
		// You would do something similar for delete and update.
		SQLiteDatabase db = events.getWritableDatabase(); 
		
		ContentValues values = new ContentValues();
		values.put(TIME, 1111);
		values.put(TITLE, "string" );
				
		db.insertOrThrow(TABLE_NAME, null, values);
	}

	private static String[] FROM = { _ID, TIME, TITLE, };
	private static String ORDER_BY = TIME + " DESC";
	
	@SuppressWarnings("deprecation")
	private Cursor getEvents() {
		// Perform a managed query. The Activity will handle closing
		// and re-querying the cursor when needed.
		SQLiteDatabase db = events.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);
		
		startManagingCursor(cursor);
		
		return cursor;
	}

	private static int[] TO = { R.id.rowid, R.id.time, R.id.title, };

	private void showEvents(Cursor cursor) {
		// Set up data binding
		int[] TO = { R.id.rowid, R.id.time, R.id.title, };
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item, cursor, FROM, TO);
		
		ListView listView = (ListView) findViewById( R.id.myListView );
		
		listView.setAdapter( adapter );
	}

	private void showEventsOnTextView(Cursor cursor) {
		// Stuff them all into a big string
		StringBuilder builder = new StringBuilder("Saved events:\n");
		while (cursor.moveToNext()) {
			// Could use getColumnIndexOrThrow() to get indexes
			long id = cursor.getLong(0);
			long time = cursor.getLong(1);
			String title = cursor.getString(2);
			builder.append(id).append(": ");
			builder.append(time).append(": ");
			builder.append(title).append("\n");
		}
		// Display on the screen
		TextView text = (TextView) findViewById(R.id.text);
		text.setText(builder);
	}
}
