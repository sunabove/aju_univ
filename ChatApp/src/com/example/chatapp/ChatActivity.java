package com.example.chatapp;

import java.io.*;
import java.text.*;
import java.util.*;

import chatMulti.ChatClient;
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
	
	Button connBtn ;
	Button sendBtn ;
	EditText msgOutEt ;
	EditText msgInEt ; 
	EditText serverNameEt ;
	
	ChatClient client ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		
		this.serverNameEt = (EditText) findViewById( R.id.chat_server_ip ) ; 
		
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
		String serverName = "" + this.serverNameEt.getText() ; 
		this.client = new ChatClient( serverName );
		client.execute( this );
	}
	
	public void sendMsg( String msg ) {
		if( this.client != null ) {
			this.client.sendMsg( msg ) ; 
		}
	}
	
	Handler handler = new Handler(); 
	
	public void receiveMsg( final String msg ) {
		
		Runnable runnable = new Runnable() {
			public void run() {
				msgOutEt.append( msg + "\r\n" );
			}
		};
		
		handler.postDelayed( runnable, 0 );
		
	}
	
}
