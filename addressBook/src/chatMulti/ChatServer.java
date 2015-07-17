package chatMulti;

import java.net.*;
import java.util.*;
import java.io.*;

public class ChatServer extends ChatObject implements Runnable {
	private int port = 1000;

	private ArrayList<ChatProcess> prcsList;

	public ChatServer() {
		this.prcsList = new ArrayList<ChatProcess>();
	}

	public void run() {
		String msg;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e1) {
			e1.printStackTrace();
			serverSocket = null;
		}

		while (serverSocket != null) {
			try {
				msg = "Waiting for client on port " + serverSocket.getLocalPort() + "...";
				println(msg);

				Socket socket = serverSocket.accept();

				msg = "Just connected to " + socket.getRemoteSocketAddress();
				println(msg);

				ChatProcess prcs = null;
				try {
					prcs = new ChatProcess(socket, this);
					prcs.executeReadThread();
				} catch (Exception e) {
					prcs = null;
				}

				if (prcs != null) {
					prcsList.add(prcs);
				}

			} catch (Exception e) {
				msg = "Error: " + e.getMessage();
				println(msg);
			}
		}
	}

	public void sendMessageToAllClients(String msg) {
		int sendErrCount = 0;
		int sendCount = 0;
		for (ChatProcess prcs : prcsList) {
			try {
				prcs.sendMessageToClient(msg);
				sendCount++;
			} catch (Exception e) {
				sendErrCount++;
			}
		}

		msg = "Send message count: %d";
		msg = String.format(msg, sendCount); 
		println(msg);
		
		if (sendErrCount > 0) {
			msg = "Send error count: %d";
			msg = String.format(msg, sendErrCount);

			println(msg);
		}
	}

	public static void main(String[] args) {
		try {
			ChatServer chatServer = new ChatServer();
			Thread t = new Thread(chatServer);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}