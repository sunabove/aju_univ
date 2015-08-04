package chatMulti;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatProcess extends ChatObject {

	Socket socket;
	DataInputStream in ;
	DataOutputStream out ; 
	ChatServer chatServer;
	int id ; 
	static int LAST_ID ; 
	
	String userName ;
	
	public ChatProcess(Socket socket, ChatServer chatServer) throws Exception { 
		this.id = LAST_ID ++ ; 
		this.socket = socket;
		this.chatServer = chatServer ; 
		this.in = new DataInputStream( socket.getInputStream() );
		this.out = new DataOutputStream( socket.getOutputStream() );
	}
	
	public void sendMessageToClient( String msg ) throws Exception {
		this.out.writeUTF( msg );
	}
	
	public void executeReadThread() { 
		class MsgReadRunnable implements Runnable {
			public void run() {
				String msg; 
				msg = "Message read thread started.";  
				boolean goOn = true;
				DataInputStream in = ChatProcess.this.in ;  
				ChatServer chatServer = ChatProcess.this.chatServer ; 
				
				// read user name at first
				String nameInfo = null; 
				try {
					nameInfo = in.readUTF();
				} catch (IOException e1) {
					nameInfo = null;
				}
				
				String userName = ""; 
				if( nameInfo != null ) {
					userName = nameInfo.substring(5).trim();
					ChatProcess.this.userName = userName ; 
				}
				
				while (goOn) {
					try {
						msg = in.readUTF(); 
						msg = "[ " + userName + " ] " + msg ; 
						chatServer.sendMessageToAllClients(msg);
					} catch (IOException e) { 
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

}
