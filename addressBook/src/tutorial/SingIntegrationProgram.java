package tutorial;

public class SingIntegrationProgram {

	public static void main(String[] args) {
		
		double firstAngleDegree  = 0 ;
		double secondAngleDegree = 0 ; 
		
		SineIntegral sineIntegral = new SineIntegral();
		
		sineIntegral.firstAngleRadian = Math.toRadians( firstAngleDegree );
		sineIntegral.secondAngleRadian = Math.toRadians( secondAngleDegree );
		
		double integration = sineIntegral.integrate();

	}

}
