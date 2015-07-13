package tutorial; 
import java.util.Scanner; 
public class SineIntegralExample { 
	public static void main(String[] args) {
		double firstAngleDegree ; 
		double secondAngleDegree ;
		
		Scanner scanner = new Scanner( System.in);  
		String msg = "Enter the first angle, second angle (degree): ";
		print( msg ); 
		
		String oneLine = scanner.nextLine();
		String [] strArray = oneLine.split( "," );
		firstAngleDegree = Double.parseDouble( strArray[0].trim() );
		secondAngleDegree = Double.parseDouble( strArray[1].trim() ) ;		
		
		double firstAngleRadian = Math.toRadians( firstAngleDegree);
		double secondAngleRadian = Math.toRadians( secondAngleDegree );
		
		double x = firstAngleRadian;
		
		double integration = 0;
		double dx = 0.0000001; 
		
		for( ; x < secondAngleRadian;  ) {
			integration += Math.sin( x ) ; 
			//integration += Math.sin( x )*dx ; 
			x += dx;
		}
		integration = dx*integration;
		
		msg = "The sine intgration from angle %f to angle %f is %f" ; 
		msg = String.format( msg, firstAngleDegree, secondAngleDegree, integration ); 
		println( msg );
		
		double integrationAnswer = Math.cos( firstAngleRadian ) - Math.cos( secondAngleRadian );
		double errorRatio = Math.abs( ( integrationAnswer - integration )/integrationAnswer*100 );
		
		msg = "The error ratio is %f";
		msg = String.format( msg, errorRatio );
		println( msg ); 
		
	}
	
	public static void println( String msg ) {
		System.out.println( msg );
	}
	
	public static void print( String msg ) {
		System.out.print( msg );
	}

}
