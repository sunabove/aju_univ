package tutorial;

public class ReplaceAll {

	public static void main(String[] args) {
		String str = "ABC AB C. \\";
		
		System.out.println( str );
		
		String str2 = str.replaceAll( ".", "XYZ" );
		
		System.out.println( str2 );
		
		String str3 = str.replaceAll( "\\.", "XYZ" ); 
		
		System.out.println( str3 );
		
		String str4 = str.replaceAll( "\\\\", "A" ); 
		
		System.out.println( str4 );

	}

}
