package com.example.androidviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.graphics.Color;

public class AutoCompleteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auto_complete);

		ArrayAdapter<String> monthArray = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MONTHS);
		final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.testAutoComplete);
		textView.setAdapter(monthArray);
		final Button autoCompleteButton = (Button) findViewById(R.id.autoCompleteButton);
		autoCompleteButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				changeOption(textView);
			}
		});
		final Button textColorButton = (Button) findViewById(R.id.textColorButton);
		textColorButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				changeOption2(textView);
			}
		});
		
		final Spinner spinner = (Spinner) findViewById( R.id.auto_spinner ); 
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, MONTHS);
		spinner.setAdapter( spinnerAdapter ); 
		
	}

	static final String[] MONTHS = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November",
			"December" };

	public void changeOption(AutoCompleteTextView text) {
		if (text.getHeight() == 100) {
			text.setHeight(30);
		} else {
			text.setHeight(100);
		}
	}

	public void changeOption2(AutoCompleteTextView text) {
		text.setTextColor(Color.RED);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.auto_complete, menu);
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
