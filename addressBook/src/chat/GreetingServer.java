package chat;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class GreetingServer extends Thread {
	private ServerSocket serverSocket;

	public GreetingServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000);
	}

	public void run() {
		while (true) {
			try {
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
				Socket socket = serverSocket.accept();
				System.out.println("Just connected to " + socket.getRemoteSocketAddress());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				
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
			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public static void main(String[] args) {
		int port = 1000;
		try {
			Thread t = new GreetingServer(port);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}