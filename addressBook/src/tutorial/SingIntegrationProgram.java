package tutorial; 
import java.util.Scanner; 
public class SingIntegrationProgram { 
	public static void main(String[] args) { 
		double firstAngleDegree  = 0 ;
		double secondAngleDegree = 0 ; 
		
		SineIntegral sineIntegral = new SineIntegral();
		
		Scanner scanner = new Scanner( System.in);  
		String msg = "Enter the first angle, second angle (degree): ";
		print( msg ); 
		
		String oneLine = scanner.nextLine();
		String [] strArray = oneLine.split( "," );
		firstAngleDegree = Double.parseDouble( strArray[0].trim() );
		secondAngleDegree = Double.parseDouble( strArray[1].trim() ) ;	
		
		sineIntegral.firstAngleRadian = Math.toRadians( firstAngleDegree );
		sineIntegral.secondAngleRadian = Math.toRadians( secondAngleDegree );
		
		double integration = sineIntegral.integrate();
		
		msg = "The sine intgration from angle %f to angle %f is %f" ; 
		msg = String.format( msg, firstAngleDegree, secondAngleDegree, integration ); 
		println( msg ); 
		
		double errorRatio = sineIntegral.getErrorRatio(); 
		msg = "The error ratio is %f %s";
		msg = String.format( msg, errorRatio, "%" );
		println( msg ); 

	}
	
	public static void println( String msg ) {
		System.out.println( msg );
	}
	
	public static void print( String msg ) {
		System.out.print( msg );
	}

}
