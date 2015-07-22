package com.example.androidviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class TestActivity extends Activity {
	
	TextView messageTv ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		this.messageTv = (TextView) findViewById( R.id.test_message );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu); 
		
		/*
		int itemId = 0 ; 
		int orderId = 0 ; 
		menu.add(0, itemId ++, orderId ++, "AutoComplete");
		menu.add(0, itemId ++, orderId ++, "Button");
		menu.add(0, itemId ++, orderId ++, "CheckBox");
		menu.add(1, itemId ++, orderId ++, "EditText");
		menu.add(1, itemId ++, orderId ++, "RadioGroup");
		menu.add(1, itemId ++, orderId ++, "Spinner");
		*/
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();  
		
		String msg = "선택한 메뉴의 아이디는 %d 입니다.";
		
		msg = String.format( msg, id );
		
		this.messageTv.setText( msg );
		
		if (id == R.id.test_AutoComplete ) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
