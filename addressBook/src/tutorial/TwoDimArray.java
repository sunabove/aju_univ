package tutorial;

public class TwoDimArray {

	public TwoDimArray() { 
	}

	public static void main(String[] args) { 
		String [][] company ;
		company = new String[7][];
		int index = 0 ;
		  
		company[ index++ ] = "1 Paul 32 California 20000.0".split( " ") ; 
		company[ index++ ] = "2 Allen 25 Texas 15000.0".split( " ") ; 
		company[ index++ ] = "3 Teddy 23 Norway 20000.0".split( " ") ; 
		company[ index++ ] = "4 Mark 25 Rich-Mond 65000.0".split( " ") ; 
		company[ index++ ] = "5 David 27 Texas 85000.0".split( " ") ; 
		company[ index++ ] = "6 Kim 22 South-Hall 45000.0".split( " ") ; 
		company[ index++ ] = "7 James 24 Houston 10000.0".split( " ") ; 
		
		for( int r = 0, rLen = company.length ; r < rLen  ; r ++ ) {
			String msg  = "" ; 
			for( int c = 0 , cLen = company[r].length ; c < cLen ; c ++ ) {
				msg += company[r][c] + "\t" ; 
			}
			System.out.println( msg );
		} 		
		
	}

}
