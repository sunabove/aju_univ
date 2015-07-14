package tutorial; 
import java.util.Scanner; 
public class Ex10UserInput { 
	public static void main(String[] args) {
		int x = 3; 
		assert x == 1 : x ;
		
		message( "User Input Program" );  
		int a ;
		a = getUserInput( "Enter the first integer: " ); 
		int b  ; 
		b = getUserInput( "Enter the second integer: " );
		int sum = a + b; 
		String message = "%d + %d = %d" ; 
		message = String.format( message, a, b, sum );
		message( message );  
	}
	
	public static int getUserInput( String msg ) {
		boolean inputOk = false ; 
		int a = 0 ;
		while( ! inputOk ) { 
			try { 
				messageNoLine( msg ); 
				Scanner scanner = new Scanner( System.in );   
				a = scanner.nextInt(); 
				inputOk = true;
			} catch( java.util.InputMismatchException e ) {
				String message = "Invalid integer! Enter a valid integer, please!";
				message( message ); 
			} 
		} 
		
		return a; 
	}
	
	public static void message( String message ) {
		System.out.println( message );
	}
	
	public static void messageNoLine( String message ) {
		System.out.print( message );
	}

}
