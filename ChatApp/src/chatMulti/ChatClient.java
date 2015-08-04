package chatMulti;

import java.net.*;
import java.util.Scanner;
import java.io.*;

import com.example.chatapp.ChatActivity;

public class ChatClient extends ChatObject {
	boolean goOn;

	String serverName = "192.168.0.12"; // ip address of computer to connect //
	int port = 1000;

	public String userName = "";

	DataOutputStream out;

	ChatActivity chatActivity;

	public ChatClient(String serverName) {
		this.serverName = serverName;
	}

	public void execute(ChatActivity chatActivity) throws Exception {

		this.chatActivity = chatActivity;

		String msg;
		
		chatActivity.println("Connecting to " + serverName + " on port " + port);
		Socket socket = new Socket(serverName, port);
		chatActivity.println("Connected to " + socket.getRemoteSocketAddress());
		this.out = new DataOutputStream(socket.getOutputStream());

		// read user name and send it to the server at first
		String nameInfo = "\\name " + userName;
		out.writeUTF(nameInfo);
		// end of read user name and sending it.

		DataInputStream in = new DataInputStream(socket.getInputStream());

		this.goOn = true;
		this.executeReadThread(in);

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
						if (chatActivity != null) {
							chatActivity.receiveMsg(msg);
						}
					} catch (IOException e) {
						e.printStackTrace();
						goOn = false;
					}
				}

				msg = "Message read thread ended.";
			}
		}

		Runnable runnable = new MsgReadRunnable();
		Thread thread = new Thread(runnable);
		thread.start();
	}

	public boolean sendMsg(String msg) {
		try {
			this.out.writeUTF(msg);
		} catch (IOException e) {
			return false;
		}

		return true;
	}

}