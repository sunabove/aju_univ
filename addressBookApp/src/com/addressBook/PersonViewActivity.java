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

public class PersonViewActivity extends PersonEditActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.messageTv.setText(""); 
		this.saveBtn.setVisibility(View.GONE); 

		this.cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cancelEdit();
			}
		});

		this.personPhotoIv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				takePhoto();
			}
		});
		
		boolean enabled = false ; 
		
		this.personNameEt.setEnabled( enabled );
		this.personMaleRd.setEnabled( enabled );
		this.personFemaleRd.setEnabled(enabled);
		this.personBirthDateEt.setEnabled(enabled);
		this.personPhoneNoEt.setEnabled(enabled);
		this.personAddrEt.setEnabled(enabled);
		this.personMemoEt.setEnabled(enabled); 
		
	}

	@Override
	public void onResume() {
		super.onResume(); 
	}
	
	public void takePhoto() {
		int requestCode = 900 ;

		Intent intent = new Intent(this, ViewPhotoActivity.class);
		intent.putExtra("requestCode", requestCode);
		intent.putExtra( "viewOnly", true );
		

		this.startActivityForResult(intent, requestCode);
	}
}
