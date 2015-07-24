package com.addressBook;

import java.io.*;
import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*; 
import android.text.format.DateFormat;
import android.view.*;
import android.view.View.*;
import android.widget.*; 

public class PersonEditActivity extends PersonCommonActivity {
	
	Button saveBtn ;
	Button editBtn ; 
	Button cancelBtn ;  
	
	EditText personNameEt ;
	RadioButton personMaleRd ; 
	RadioButton personFemaleRd ; 
	EditText personBirthDateEt ; 
	EditText personPhoneNoEt ;
	EditText personAddrEt ;
	EditText personMemoEt ; 
	
	ImageView personPhotoIv ; 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_edit);
		
		this.saveBtn = (Button) findViewById( R.id.edit_save_btn );
		this.editBtn = (Button) findViewById( R.id.edit_edit_btn );
		this.cancelBtn = (Button) findViewById( R.id.edit_cancel_btn );
		
		this.personNameEt = (EditText) findViewById( R.id.edit_person_name );
		this.personBirthDateEt = (EditText) findViewById( R.id.edit_person_birth_date );
		this.personPhoneNoEt = (EditText) findViewById( R.id.edit_person_phone_number );
		this.personAddrEt = (EditText) findViewById( R.id.edit_person_address );
		
		this.personMaleRd = (RadioButton) findViewById( R.id.edit_person_gender_male );
		this.personFemaleRd = (RadioButton) findViewById( R.id.edit_person_gender_female );
		
		this.personPhotoIv = (ImageView) findViewById(R.id.edit_person_photo );
		this.personMemoEt = (EditText) findViewById( R.id.edit_person_memo );
		
		this.editBtn.setVisibility( View.GONE );
		
		this.saveBtn.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				savePerson();
			} 
		});
		
		this.cancelBtn.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				cancelEdit();
			} 
		});
	} 
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	public void savePerson() {
		String name = this.personNameEt.getText().toString() ;
		if( name == null || name.trim().length() < 1 ) { 	
			
			Activity activity = this; 
			String title = "이름 입력" ;
			String message = "이름을 입력하세요!";
			
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			// Set the dialog title
			builder.setTitle( title ); 
			builder.setMessage( message );

			// Set the action buttons
			builder.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {  
				}
			});

			// set the negative button
			builder.setNegativeButton( "Cancel" , new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
				}
			});

			AlertDialog dialog = builder.create();
			dialog.show();
			
			return ; 
		}
		
		Person.Gender gender = this.personMaleRd.isChecked() ? Person.Gender.MALE : Person.Gender.FEMALE ;
		Date birthDate = null ;
		String birthDateStr = this.personBirthDateEt.getText().toString();
		try {
			SimpleDateFormat dateFormat =new SimpleDateFormat("yyyyMMDD");
			birthDate = dateFormat.parse( birthDateStr );
		} catch (Exception e) { 
		}
		String phoneNumber = this.personPhoneNoEt.getText().toString() ;
		String address =this.personAddrEt.getText().toString();
		String memo = this.personMemoEt.getText().toString();
		
		Person p = new Person();
		p.name = name.trim() ;
		p.gender = gender ; 
		p.birthDate = birthDate ;
		p.phoneNumber = phoneNumber ;
		p.address = address ; 
		p.memo = memo ; 
		
		AddressBook addressBook = AddressBook.getAddressBook();
		
		p.id = addressBook.persons.size() ; 
		addressBook.persons.add( p );
		
		addressBook.personInserted = true ; 
		
		addressBook.saveAllPersonsOnFile();
		
		finish(); 
	}
	
	public void cancelEdit() {
		finish();
	}
	
	public void viewList() { 
	}
	
	public void showMessageDialog( String msg ) {
		Context context = this.getApplicationContext();
		new AlertDialog.Builder(context)
	    .setTitle("Delete entry")
	    .setMessage("Are you sure you want to delete this entry?")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // continue with delete
	        }
	     })
	    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     })
	    .setIcon(android.R.drawable.ic_dialog_alert)
	     .show();
	}  
	
}
