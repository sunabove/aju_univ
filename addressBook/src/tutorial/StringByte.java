package tutorial;

public class StringByte {

	public static void main(String[] args) {
		String abc = "ABC" ;
		System.out.println( abc ); 
		
		byte [] bytes = abc.getBytes() ; 
		for( byte b : bytes ) {
			System.out.println( b );
		}
		
		abc.indexOf( "", 2 );
		
		String ganada = "°¡³ª´Ù";
		System.out.println( ganada ); 
		
		bytes = ganada.getBytes(); 
		for( byte b : bytes ) {
			System.out.println( b );
		}

	}

}
