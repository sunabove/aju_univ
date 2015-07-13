package tutorial;

public class SineIntegral {

	double firstAngleRadian;
	double secondAngleRadian;
	
	double integration ; 

	public SineIntegral() {
	}

	public double integrate() {
		double integration = 0;
		double dx = 0.0000001;
		
		double x = firstAngleRadian; 

		for (; x < secondAngleRadian;) {
			integration += Math.sin(x);
			// integration += Math.sin( x )*dx ;
			x += dx;
		}
		integration = dx * integration;
		
		this.integration = integration ; 

		return integration;
	}
	
	public double getErrorRatio() { 
		double integrationAnswer = Math.cos( firstAngleRadian ) - Math.cos( secondAngleRadian );
		
		double errorRatio = Math.abs( ( integrationAnswer - integration )/integrationAnswer*100 );
		
		return errorRatio; 
	}

}
