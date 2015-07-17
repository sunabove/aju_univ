package chat;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ChatServer extends ChatObject implements Runnable {
	private ServerSocket serverSocket;
	private int port = 1000;

	public ChatServer() throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000);
	}

	public void run() {
		try {
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
			Socket socket = serverSocket.accept();
			System.out.println("Just connected to " + socket.getRemoteSocketAddress());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			this.executeReadThread(in);
			this.executeWriteThread(out); 
			
		} catch (SocketTimeoutException s) {
			System.out.println("Socket timed out!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			ChatServer chatServer = new ChatServer();
			;
			Thread t = new Thread(chatServer);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}