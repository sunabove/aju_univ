package chatMulti;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ChatClient extends ChatObject {
	
	boolean showMsgPrompt = false ;  
	
	public ChatClient() {
		
	}
	
	public void execute() {
		String serverName = "192.168.0.12"; // ip address of computer to connect
		int port = 1000;
		try {
			System.out.println("Connecting to " + serverName + " on port " + port);
			Socket socket = new Socket(serverName, port);
			System.out.println("Just connected to " + socket.getRemoteSocketAddress());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			this.executeReadThread(in);
			this.executeWriteThread(out);  
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void executeReadThread(DataInputStream in) {
		class MsgReadRunnable implements Runnable {
			public void run() {
				String msg;
				println( line );
				msg = "Message read thread started."; 
				println( msg );
				println( line );
				boolean goOn = true;
				while (goOn) {
					try {
						msg = in.readUTF();
						if( showMsgPrompt ) {
							println();
							println( msg );
							printMsgPrompt();
							showMsgPrompt = true;
						} else if( ! showMsgPrompt ){
							println( msg );
						}
					} catch (IOException e) {
						e.printStackTrace();
						goOn = false ; 
					}
				}
				
				msg = "Message read thread ended." ;
				println( line );
				println( msg );
				println( line );
			}
		}
		
		Runnable runnable = new MsgReadRunnable() ; 
		Thread thread = new Thread( runnable );
		thread.start();
	}
	
	public void executeWriteThread(DataOutputStream out) {
		class MsgWriteRunnable implements Runnable {
			public void run() {
				String msg;
				println( line );
				msg = "Message write thread started."; 
				println( msg );
				println( line );
				
				Scanner scanner = new Scanner( System.in );
				
				boolean goOn = true;
				while (goOn) {
					try {
						printMsgPrompt();
						showMsgPrompt = true ; 
						msg = scanner.nextLine();
						if( msg.equalsIgnoreCase( "Q")) {
							goOn = false ;
						} else {
							out.writeUTF( msg );
						}
					} catch (IOException e) {
						e.printStackTrace();
						goOn = false ; 
					}
				}
				
				msg = "Message write thread ended." ;
				println( line );
				println( msg );
				println( line );
				
				scanner.close();
			}
		}
		
		Runnable runnable = new MsgWriteRunnable() ; 
		Thread thread = new Thread( runnable );
		thread.start();
	}
	
	public static void main(String[] args) {
		ChatClient client = new ChatClient();
		client.execute();
	}
}