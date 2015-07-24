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

public class PersonEditActivity extends PersonCommonActivity {
	
	Button saveBtn ;
	Button editBtn ; 
	Button cancelBtn ;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_edit);
		
		this.saveBtn = (Button) findViewById( R.id.edit_save_btn );
		this.editBtn = (Button) findViewById( R.id.edit_edit_btn );
		this.cancelBtn = (Button) findViewById( R.id.edit_cancel_btn );
		
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
		
	}
	
	public void cancelEdit() {
		finish();
	}
	
	public void viewList() {
		
	}
	
	
	
}
