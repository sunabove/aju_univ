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
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class AutoCompleteActivity extends Activity {

	TextView messageTv;
	Button okBtn;
	CheckBox myCheckBox ; 
	RadioButton femaleRadio ;
	RadioButton maleRadio ; 
	Spinner mySpinner ; 

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

		ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Months);
		
		final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.testAutoComplete);
		textView.setAdapter(monthAdapter);
		
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
		
		this.myCheckBox = (CheckBox) findViewById( R.id.myCheckBox );
		this.myCheckBox.setOnClickListener( new OnClickListener () { 
			@Override
			public void onClick(View v) { 
				//boolean isSelected = myCheckBox.isSelected();
				boolean isChecked = myCheckBox.isChecked(); 
				String msg = "";
				if( isChecked ) {
					msg = "당신은 내국인입니다.";
				} else {
					msg = "당신은 외국인입니다.";
				} 
				messageTv.setText( msg );
			} 
		});
		
		this.femaleRadio = (RadioButton) findViewById( R.id.radio_female );
		this.maleRadio = (RadioButton) findViewById( R.id.radio_male );
		
		OnClickListener radioListener = new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				boolean isFemale = femaleRadio.isChecked();
				String msg = "";
				if( isFemale ) {
					msg = "당신은 여성 입니다.";
				} else {
					msg = "당신은 남성 입니다.";
				} 
				messageTv.setText( msg );
			} 
		}; 
		
		this.femaleRadio.setOnClickListener( radioListener );
		this.maleRadio.setOnClickListener( radioListener );
		
		this.mySpinner = (Spinner) findViewById( R.id.mySpinner );
		ArrayAdapter<String> daysAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DAYS );
		this.mySpinner.setAdapter( daysAdapter );

	}

	static final String[] Months = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November",
			"December" };
	
	static final String[] DAYS = new String[] { "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일" };

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
