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

	Button connBtn;
	Button sendBtn;
	EditText msgOutEt;
	EditText msgInEt;
	EditText serverNameEt;
	EditText userNameEt;

	ChatClient chatClient;
	
	Handler handler = new Handler(); 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);

		this.serverNameEt = (EditText) findViewById(R.id.chat_server_ip);

		this.connBtn = (Button) findViewById(R.id.chat_server_conn_btn);
		this.sendBtn = (Button) findViewById(R.id.chat_message_send_btn);

		this.msgOutEt = (EditText) findViewById(R.id.chat_message_output);
		this.msgInEt = (EditText) findViewById(R.id.chat_input_message);
		this.userNameEt = (EditText) findViewById(R.id.chat_user_name);

		this.connBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				connectServer();
			}
		});

		this.sendBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String msg = "" + msgInEt.getText();
				sendMsg(msg);
			}
		});

	}

	public void connectServer() { 

		Runnable runnable = new Runnable() {
			public void run() {
				/*StrictMode.enableDefaults();
				
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
				*/

				String serverName = "" + serverNameEt.getText();
				String userName = "" + userNameEt.getText();
				
				ChatActivity activity = ChatActivity.this;
				
				activity.chatClient = new ChatClient(serverName.trim());
				ChatClient chatClient = activity.chatClient ; 
				chatClient.userName = userName;

				String msg = null;
				try { 
					chatClient.execute(activity);
				} catch (Exception e) {
					msg = "Error while connecting the server :" + e.getLocalizedMessage() ;
				}
				
				if( msg != null ) {
					println( msg );
				}
			}
		};
		
		boolean runUsingThread = true ;
		if( runUsingThread ) { 
			// There is no error when connect the server by using a thread
			Thread thread = new Thread( runnable) ;
			thread.start();
		} else { 		
			// this code may cause an error when connect the server by using a handler
			handler.postDelayed( runnable, 0 ); 
		} 

	}

	public void println(final String msg) {
		Runnable runnable = new Runnable() {
			public void run() {
				msgOutEt.append(msg + "\r\n");
			}
		};
		
		handler.postDelayed(runnable, 0);		
	}

	public void sendMsg(String msg) {
		if (this.chatClient != null) {
			this.chatClient.sendMsg(msg);
		}
	}

	public void receiveMsg(final String msg) {

		Runnable runnable = new Runnable() {
			public void run() {
				msgOutEt.append(msg + "\r\n");
			}
		};

		handler.postDelayed(runnable, 0);

	}

}
