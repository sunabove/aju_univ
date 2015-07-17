package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ChatObject {

	String line = "#######################################";
	String msgEnterPrompt = "Enter message: " ; 
	
	boolean showMsgPrompt = false ; 
	
	public ChatObject() {
	}

	public void executeReadThread(DataInputStream in) {
		class MsgReadRunnable implements Runnable {
			public void run() {
				String msg;
				println( line );
				msg = "Message read thread started."; 
				println( msg );
				println( line );
				boolean goOn = true;
				while (goOn) {
					try {
						msg = in.readUTF();
						if( showMsgPrompt ) {
							println();
							println( msg );
							printMsgPrompt();
							showMsgPrompt = true;
						} else if( ! showMsgPrompt ){
							println( msg );
						}
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
	
	public void executeWriteThread(DataOutputStream out) {
		class MsgWriteRunnable implements Runnable {
			public void run() {
				String msg;
				println( line );
				msg = "Message write thread started."; 
				println( msg );
				println( line );
				
				Scanner scanner = new Scanner( System.in );
				
				boolean goOn = true;
				while (goOn) {
					try {
						printMsgPrompt();
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
				println( line );
				println( msg );
				println( line );
				
				scanner.close();
			}
		}
		
		Runnable runnable = new MsgWriteRunnable() ; 
		Thread thread = new Thread( runnable );
		thread.start();
	}
	
	public void printMsgPrompt() {
		print( msgEnterPrompt );
	}

	public void println() {
		System.out.println();
	}

	public void println(String msg) {
		System.out.println(msg);
	}

	public void print(String msg) {
		System.out.print(msg);
	}

}
