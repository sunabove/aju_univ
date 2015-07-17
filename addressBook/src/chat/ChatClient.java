package chat;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ChatClient extends ChatObject {
	
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
	
	public static void main(String[] args) {
		ChatClient client = new ChatClient();
		client.execute();
	}
}