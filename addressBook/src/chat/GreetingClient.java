package chat;

import java.net.*;
import java.io.*;

public class GreetingClient {
	public static void main(String[] args) {
		String serverName = "192.168.0.12"; // ip address of computer to connect
		int port = 1000 ;
		try {
			System.out.println("Connecting to " + serverName + " on port "
					+ port);
			Socket socket = new Socket(serverName, port);
			System.out.println("Just connected to "
					+ socket.getRemoteSocketAddress()); 
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			out.writeUTF("Hello from " + socket.getLocalSocketAddress()); 
			System.out.println("Server says " + in.readUTF());
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}