package chatMulti;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ChatClient extends ChatObject {
	
	boolean showMsgPrompt = false ;  
	boolean goOn ;
	
	public ChatClient() {
		
	}
	
	public void execute() {
		String serverName = "192.168.0.12"; // ip address of computer to connect //
		int port = 1000;
		String msg ; 
		try {
			System.out.println("Connecting to " + serverName + " on port " + port);
			Socket socket = new Socket(serverName, port);
			System.out.println("Just connected to " + socket.getRemoteSocketAddress());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			// read user name and send it to the server at first
			Scanner scanner = new Scanner( System.in );
			String userName = "";
			msg = "Enter your name : ";
			while( userName.length() < 3 ) { 
				userName = scanner.nextLine();
				if( userName.length() < 3 ) {
					msg = "Your name is too short." ;  
				}
			}
			
			String nameInfo = "\\name " + userName ;
			out.writeUTF(  nameInfo );
			// end of read user name and sending it.
			
			this.goOn = true ; 
			this.executeReadThread(in);
			this.executeWriteThread(out);  
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void executeReadThread(final DataInputStream in) {
		class MsgReadRunnable implements Runnable {
			public void run() {
				String msg; 
				msg = "Message read thread started.";  
				
				while (goOn) {
					try {
						msg = in.readUTF();
						// Output -> Activity
						if( showMsgPrompt ) {
							showMsgPrompt = false ;  
							showMsgPrompt = true;
						} else if( ! showMsgPrompt ){ 
						}
					} catch (IOException e) {
						e.printStackTrace();
						goOn = false ; 
					}
				}
				
				msg = "Message read thread ended." ; 
			}
		}
		
		Runnable runnable = new MsgReadRunnable() ; 
		Thread thread = new Thread( runnable );
		thread.start();
	}
	
	public void executeWriteThread(final DataOutputStream out) {
		class MsgWriteRunnable implements Runnable {
			public void run() {
				String msg; 
				msg = "Message write thread started.";  
				
				Scanner scanner = new Scanner( System.in ); 
				
				while (goOn) {
					try {
						// User Input -> Activity  
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