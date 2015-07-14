package nimgame;

public class NimObject {
	
	static final String ONE_LINE = "#########################################";  
	static final String NEW_LINE = "\r\n" ;
	
	public NimObject() { 
	}
	
	public void println( ) {
		System.out.println( );
		System.out.flush();
	}
	
	public void print( String msg ) {
		System.out.print( msg );
		System.out.flush();
	} 
	
	public void println( String msg ) {
		System.out.println( msg );
		System.out.flush();
	}  

}
