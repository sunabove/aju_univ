package com.example.androidviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AndroidViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_view);
		
		Button showAutoComplete = (Button) findViewById( R.id.view_showAutoComplete );
		showAutoComplete.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) { 
				showAutoCompleteActivity();
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean useXmlMenu = false ; 
		
		if( useXmlMenu ) { 
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.android_view, menu);
		}else if ( ! useXmlMenu ) {

			super.onCreateOptionsMenu(menu);

			int groupId = 0 ; 
			int itemId = 0 ; 
			int order = 0;

			menu.add(groupId, itemId ++, order ++, "AutoComplete");
			menu.add(groupId, itemId ++, order ++, "Button");
			menu.add(groupId, itemId ++, order ++, "CheckBox");
			menu.add(groupId, itemId ++, order ++, "EditText");
			menu.add(groupId, itemId ++, order ++, "RadioGroup");
			menu.add(groupId, itemId ++, order ++, "Spinner");
		}

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == 0 ) {
			showAutoCompleteActivity();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void showAutoCompleteActivity() {
		Intent autoComplete = new Intent( this, AutoCompleteActivity.class );
		startActivity( autoComplete );
	}
}
