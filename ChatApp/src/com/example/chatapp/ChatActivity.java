package com.example.chatapp;

import java.io.*;
import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*; 
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.widget.*; 
import android.graphics.*;
import android.graphics.Path.*; 
import android.database.*;
import android.database.sqlite.*; 

public class ChatActivity extends Activity {
	
	String serverIp = "192.168.0.12"; 
	
	Button connBtn ;
	Button sendBtn ;
	EditText msgOutEt ;
	EditText msgInEt ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		
		this.connBtn = (Button) findViewById ( R.id.chat_server_conn_btn );
		this.sendBtn = (Button) findViewById ( R.id.chat_message_send_btn );
		
		this.msgOutEt = (EditText) findViewById( R.id.chat_message_output );
		this.msgInEt = (EditText) findViewById( R.id.chat_input_message ); 
		
		this.connBtn.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				connectServer();
			} 
		});
		
		this.sendBtn.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				String msg = "" +  msgInEt.getText() ; 
				sendMsg( msg ) ; 
			} 
		});  
		
	} 
	
	public void connectServer() {
		
	}
	
	public void sendMsg( String msg ) {
		
	}
	
	public void receiveMsg( String msg ) {
		
	}
	
}
