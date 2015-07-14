package calculator;

import java.util.Scanner;

public class Console {
	
	public Console() {
		
	}
	
	public int getNumber() {
		int i = 0;
		return i;
	}
	
	public UserInput userEnter() {
		UserInput b ;
		b = new UserInput();
		
		String msg;
		msg = "Enter x value: " ;
		b.x = userEnterInteger( msg );
		
		msg = "Enter y value: " ;
		
		b.y = this.userEnterInteger( msg );
		
		msg = "Enter a operator ( +, -, *, / ): " ;
		
		b.oper = this.userEnterOperator( msg );
		
		return b;
	}
	
	private double userEnterInteger( String msg ) {
		boolean userInputOk = false ; 
		double number = 0 ;
		while( ! userInputOk ) { 
			try { 
				System.out.print( msg ); 
				Scanner scanner = new Scanner( System.in );   
				number = scanner.nextDouble(); 
				userInputOk = true;
			} catch( java.util.InputMismatchException e ) {
				String message = "Invalid integer! Enter a valid integer, please!";
				System.out.println( message ); 
			} 
		} 
		
		return number; 
	}
	
	private String userEnterOperator( String msg ) {
		boolean userInputOk = false ; 
		String oneLine = null  ; 
		while( ! userInputOk ) { 
			try { 
				System.out.print( msg ); 
				Scanner scanner = new Scanner( System.in );   
				oneLine = scanner.nextLine();
				oneLine = oneLine.trim();
				if( oneLine.equals( "+" ) ||  oneLine.equals( "-" ) ||  oneLine.equals( "*" ) ||  oneLine.equals( "/" ) ) {
					userInputOk = true;
				} else {
					String message = "Invalid operator! Enter a valid operator, please!";
					System.out.println( message ); 
				}
			} catch( Exception e ) {
				String message = "Invalid operator! Enter a valid operator, please!";
				System.out.println( message ); 
			} 
		} 
		
		return oneLine; 
	} 
	
}
