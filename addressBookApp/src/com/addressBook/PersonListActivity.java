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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class PersonListActivity extends PersonCommonActivity {
	
	Button addPersonBtn ;
	Button editPersonBtn ;
	Button deletePersonBtn ; 
	
	ListView listPersonLv ; 
	
	AddressBook addressBook ;  
	PersonAdapter personAdapter ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_list);
		
		this.addressBook = AddressBook.getAddressBook(); 
		
		this.addPersonBtn = (Button) findViewById( R.id.list_addPerson );
		this.editPersonBtn = (Button) findViewById( R.id.list_editPerson );
		this.deletePersonBtn = (Button) findViewById( R.id.list_deletePerson );
		
		this.listPersonLv = (ListView) findViewById( R.id.list_listPersons ); 
		
		this.personAdapter = new PersonAdapter( this , addressBook , this );
		this.listPersonLv.setAdapter( personAdapter );
		
		this.addPersonBtn.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				addressBook.personSelected = null ; 
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
		
		this.listPersonLv.setOnItemClickListener( new OnItemClickListener() { 
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				onItemClickListPerson( position ); 
			}
			
		}); 
	} 
	
	@Override
	public void onResume() {
		super.onResume();
		
		AddressBook addressBook = this.addressBook ; 
		ListView listPersonLv = this.listPersonLv ; 
		
		if( addressBook.personInserted ) {
			addressBook.personInserted = false ; 
			boolean useDataSetChanged = true ;
			if( useDataSetChanged ) {
				this.personAdapter.notifyDataSetChanged(); 
			} else if( ! useDataSetChanged ) { 
				this.personAdapter = new PersonAdapter( this , addressBook , this );
				listPersonLv.setAdapter( personAdapter );
			}
		}
	}
	
	public void onItemClickListPerson( int position ) {
		AddressBook addressBook = this.addressBook;
		ArrayList<Person> persons = addressBook.persons; 
		Person person = null ; 
		if( position < persons.size() ) {
			person = persons.get( position );
			if( person != null ) { 
				addressBook.personSelected = person ;
				Intent intent = new Intent( this, PersonViewActivity.class );
				this.startActivity( intent );
			}
		}
	}
}
