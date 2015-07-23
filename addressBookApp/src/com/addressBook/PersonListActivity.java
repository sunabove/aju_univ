package com.addressBook;

import java.io.*;
import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*; 
import android.view.*;
import android.view.View.*;
import android.widget.*; 

public class PersonListActivity extends PersonCommonActivity {
	
	Button addPersonBtn ;
	Button editPersonBtn ;
	Button deletePersonBtn ; 
	
	ListView listPersonLv ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_list);
		
		this.addPersonBtn = (Button) findViewById( R.id.list_addPerson );
		this.editPersonBtn = (Button) findViewById( R.id.list_editPerson );
		this.deletePersonBtn = (Button) findViewById( R.id.list_deletePerson );
		
		this.listPersonLv = (ListView) findViewById( R.id.list_listPersons ); 
		
		this.addPersonBtn.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				Intent intent = new Intent( PersonListActivity.this, PersonEditActivity.class );
				startActivity( intent );
			} 
		} );
		
		this.editPersonBtn.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				Intent intent = new Intent( PersonListActivity.this, PersonEditActivity.class );
				startActivity( intent );
			} 
		} );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.person_list, menu);
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
