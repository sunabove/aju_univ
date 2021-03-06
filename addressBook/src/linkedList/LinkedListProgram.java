package linkedList;

import java.util.Scanner;

public class LinkedListProgram {
	
	LinkedList<Integer> firstNode ;
	LinkedList<Integer> selectedNode ; 
	
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
			
			if( menuKey.equalsIgnoreCase( "L" ) || menuKey.length() < 1 ) {
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
		LinkedList<Integer> node = this.firstNode;
		LinkedList<Integer> selectedNode = this.selectedNode;
		
		String msg = null ;
		
		msg = "List linked lists";
		
		println();
		println( msg );
		println();
		
		if( node == null ) {
			msg = "There is no linked list to print.";
		}
		
		int nodeNo = 0 ;
		String format = "[ %d ] (%s) : %s" ;
		for( ; node != null ; ) {
			nodeNo ++ ; 
			String selectedChar = ( node == selectedNode ) ? "*" : " " ; 
			msg = String.format( format, nodeNo, selectedChar, node.toString() ); 
			println( msg );
			
			node = node.next ; 
		}
		
		if( nodeNo > 0 ) {
			msg = "There are %d linked lists." ;
			msg = String.format( msg, nodeNo );
		}
		
		if( msg != null ) {
			println();
			print( msg );
			println();
		}
	}
	
	public void insertLinkedList() {
		LinkedList<Integer> node = new LinkedList<Integer>();
		
		int data ;
		Scanner scanner = new Scanner( System.in );
		String msg;
		
		msg = "Insert a new linked list" ;
		println();
		println( msg );
		println();
		
		msg = "Enter data: " ;
		print( msg );
		data = scanner.nextInt(); 
		
		node.data = data;
		
		if( this.firstNode == null ) {
			this.firstNode = node ; 
			this.selectedNode = node;
		} else if( this.selectedNode != null ){
			node.next = this.selectedNode.next;
			this.selectedNode.next = node ; 
			this.selectedNode = node;
		}	 
		
	}
	
	public void selectLinkedList() {
		String msg ;
		
		msg = "Select a linked list object";
		
		println();
		println( msg );
		println();
		
		msg = "Enter linked list number to select : ";
		
		final int number = this.userEnterInteger( msg );
		
		LinkedList node = this.firstNode;
		
		boolean isNodeSelected = false ;
		
		int index = 0 ; 
		while( node != null && ! isNodeSelected ) {
			if( index == number - 1 ) {
				this.selectedNode = node ;
				
				isNodeSelected = true ;  
			}  
			node = node.next; 
			index ++ ;  
		}
		
		if( ! isNodeSelected ) {
			msg = "Error: Invalid linked list number! " ;
			
			println();
			print( msg ); 
			this.enterToContinue();
		}
	}
	
	public void deleteLinkedList() {
		
	} 
	
	
	public void printMenu() {
		String msg = null ; 
		
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
		
		LinkedList selectedNode = this.selectedNode;
		if( selectedNode != null ) {
			msg = "Selected node data: " + selectedNode ;
			
			println();
			println( msg );
			println();
		}
		
		println();
	}
	
	public void enterToContinue() {
		String msg = "Enter to continue: " ;
		print( msg );
		Scanner scanner = new Scanner( System.in );   
		scanner.nextLine();
		
		return;
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
