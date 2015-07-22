package com.example.androidviews;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class AutoCompleteActivity extends Activity {

	TextView messageTv;
	Button okBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_autocomplete);

		this.messageTv = (TextView) findViewById(R.id.auto_message);
		this.okBtn = (Button) findViewById(R.id.auto_ok);

		this.okBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		ArrayAdapter<String> monthArray = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Months);
		final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.testAutoComplete);
		textView.setAdapter(monthArray);
		final Button changeButton = (Button) findViewById(R.id.autoCompleteButton);
		changeButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				changeOption(textView);
			}
		});
		final Button changeButton2 = (Button) findViewById(R.id.textColorButton);
		changeButton2.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				changeOption2(textView);
			}
		});

	}

	static final String[] Months = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November",
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
}
