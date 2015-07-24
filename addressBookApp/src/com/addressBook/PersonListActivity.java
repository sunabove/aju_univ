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
	
	AddressBook addressBook ;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_list);
		
		this.addressBook = AddressBook.getAddressBook(); 
		
		this.addPersonBtn = (Button) findViewById( R.id.list_addPerson );
		this.editPersonBtn = (Button) findViewById( R.id.list_editPerson );
		this.deletePersonBtn = (Button) findViewById( R.id.list_deletePerson );
		
		this.listPersonLv = (ListView) findViewById( R.id.list_listPersons ); 
		
		PersonAdapter personAdapter = new PersonAdapter( this , addressBook );
		this.listPersonLv.setAdapter( personAdapter );
		
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
	
	public void onResume() {
		super.onResume();
		
		AddressBook addressBook = this.addressBook ; 
		ListView listPersonLv = this.listPersonLv ; 
		
		if( addressBook.personInserted ) {
			addressBook.personInserted = false ; 
			PersonAdapter personAdapter = new PersonAdapter( this , addressBook );
			listPersonLv.setAdapter( personAdapter );
		}
	}
}
