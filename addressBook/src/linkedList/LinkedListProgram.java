package linkedList;

import java.util.Scanner;

public class LinkedListProgram {
	
	LinkedList firstNode ;
	LinkedList selectedNode ; 
	
	String line = "################################################" ;
	
	public LinkedListProgram() {
		this.firstNode = null ;
		this.selectedNode = null ; 
	}
	
	public void run() { 
		boolean programExit = false ;
		
		String programName = "      LinkedList V 1.0" ;
		
		println();
		println( line );
		println();
		println( programName );
		println();
		println( line );
		
		String menuKey ; 
		String msg ; 
		for( ; ! programExit ; ) {
			printMenu();
			
			msg = "Enter menuKey: ";
			menuKey = this.userEnterMenu( msg );
			
			if( menuKey.equalsIgnoreCase( "L") ) {
				this.listLinkedList();
			} else if( menuKey.equalsIgnoreCase( "I") ) {
				this.insertLinkedList();
			} else if( menuKey.equalsIgnoreCase( "S") ) {
				this.selectLinkedList();
			} else if( menuKey.equalsIgnoreCase( "D") ) {
				this.deleteLinkedList();
			} else if( menuKey.equalsIgnoreCase( "Q") ) {
				programExit = true ; 
			} 
		}
		
		msg = "Good bye!";
		println();
		println( msg );
	}
	
	public void listLinkedList() {
		
	}
	
	public void insertLinkedList() {
		
	}
	
	public void selectLinkedList() {
		
	}
	
	public void deleteLinkedList() {
		
	} 
	
	
	public void printMenu() {
		String [] menuStrs = new String[5];
		int index = 0;
		menuStrs[ index ++ ] = "[L]: List" ;
		menuStrs[ index ++ ] = "[I]: Insert" ;
		menuStrs[ index ++ ] = "[S]: Select" ;
		menuStrs[ index ++ ] = "[D]: Delete" ;
		menuStrs[ index ++ ] = "[Q]: Exit" ;
		
		println();
		
		for( String menuStr : menuStrs ) {
			println( menuStr );
		}
		
		println();
	}
	
	public void println() {
		System.out.println();
	}
	public void println( String msg ) {
		System.out.println( msg );
	}
	
	public void print( String msg ) {
		System.out.print( msg );
	}
	
	private int userEnterInteger( String msg ) {
		boolean userInputOk = false ; 
		int number = 0 ;
		while( ! userInputOk ) { 
			try { 
				print( msg ); 
				Scanner scanner = new Scanner( System.in );   
				number = scanner.nextInt(); 
				userInputOk = true;
			} catch( java.util.InputMismatchException e ) {
				String message = "Invalid integer! Enter a valid integer, please!";
				System.out.println( message ); 
			} 
		}  
		return number; 
	}
	
	private String userEnterMenu( String msg ) {
		boolean userInputOk = false ; 
		String oneLine = null  ; 
		while( ! userInputOk ) { 
			try { 
				if( msg != null && msg.length() > 0 ) { 
					print( msg ); 
				}
				Scanner scanner = new Scanner( System.in );   
				oneLine = scanner.nextLine();
				oneLine = oneLine.trim();  
				userInputOk = true ;  
			} catch( Exception e ) {
				String message = "Invalid menu key! Enter a valid menu key, please!";
				System.out.println( message ); 
			} 
		} 
		return oneLine; 
	}

	public static void main(String[] args) {
		LinkedListProgram program = new LinkedListProgram();
		
		program.run(); 
	}

}
