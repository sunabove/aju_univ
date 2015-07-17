package chat;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class GreetingClient {
	
	public GreetingClient() {
		
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
			
			Scanner scanner = new Scanner( System.in );
			boolean goOn = true ;
			
			String userInputMsg ;
			String serverMsg ; 
			while( goOn ) {
				userInputMsg = scanner.nextLine();
				if( userInputMsg.equalsIgnoreCase( "Q" ) ) {
					goOn = false ; 
				} else {
					out.writeUTF( userInputMsg );
					serverMsg = in.readUTF();
					System.out.println( serverMsg );
				}
			} 
			
			socket.close();
			scanner.close();
			
			System.out.println( "Good bye!" );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		GreetingClient client = new GreetingClient();
		client.execute();
	}
}