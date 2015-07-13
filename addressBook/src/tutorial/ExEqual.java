package tutorial;

public class ExEqual {
	public static void main(String args[]) {
		Integer x = 5;
		Integer y = new Integer( 5 );
		Integer z = 5; 
		
		System.out.println( x == y );
		System.out.println( x == z );
		System.out.println( y == z );
		
		System.out.println();
		
		System.out.println(x.equals(y));
		System.out.println(x.equals(z)); 
	}
}
