package chatMulti;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ChatObject {

	String line = "#######################################";
	String msgEnterPrompt = "Enter message: " ; 
	
	public ChatObject() {
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
