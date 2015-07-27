package com.example.tut50database;

import java.io.*;
import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*; 
import android.view.*;
import android.view.View.*;
import android.widget.*; 

public class DatabaseActivity extends Activity {
	
	EditText messageEt ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);
		
		this.messageEt = (EditText) findViewById( R.id.db_message );
		this.messageEt.setText( "" );
	}

	public void showMessage( String msg ) {
		
		EditText messageEt = this.messageEt;
		
		this.messageEt.append( msg );
		this.messageEt.append( "\r\n" ); 
		this.messageEt.setSelection( messageEt.getText().length() );
	}
}
