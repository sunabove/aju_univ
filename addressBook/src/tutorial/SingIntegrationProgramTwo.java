package tutorial; 
import java.util.Scanner; 
public class SingIntegrationProgramTwo { 
	public static void main(String[] args) { 
		SineIntegral a = new SineIntegral();
		a.firstAngleRadian = 0;
		a.secondAngleRadian = Math.PI/6;
		
		SineIntegral b = new SineIntegral();
		b.firstAngleRadian = 0;
		b.secondAngleRadian = Math.PI/4 ;
		
		println( "a = " + a.integrate() );
		println( "b = " + b.integrate() );
		
		println( "ae = " + a.getErrorRatio() );
		println( "be = " + b.getErrorRatio() );

	}
	
	public static void println( String msg ) {
		System.out.println( msg );
	}
	
	public static void print( String msg ) {
		System.out.print( msg );
	}

}
