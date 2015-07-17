package chatMulti;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatProcess extends ChatObject {

	Socket socket;
	DataInputStream in ;
	DataOutputStream out ; 
	ChatServer chatServer;
	
	String name;
	
	public ChatProcess(Socket socket, ChatServer chatServer) throws Exception { 
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
				println( line );
				msg = "Message read thread started."; 
				println( msg );
				println( line );
				boolean goOn = true;
				DataInputStream in = ChatProcess.this.in ;  
				ChatServer chatServer = ChatProcess.this.chatServer ; 
				while (goOn) {
					try {
						msg = in.readUTF(); 
						chatServer.sendMessageToAllClients(msg);
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

}
