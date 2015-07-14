package tutorial;
public class SineIntegral {
	double firstAngleRadian;
	double secondAngleRadian;
	
	double integration ; 

	public SineIntegral() {
	}
	
	public double getErrorRatio() { 
		double firstAngleRadian = this.firstAngleRadian;
		double secondAngleRadian = this.secondAngleRadian;
		double integration = this.integration;
		
		double integrationAnswer = Math.cos( firstAngleRadian ) - Math.cos( secondAngleRadian );
		
		double errorRatio = Math.abs( ( integrationAnswer - integration )/integrationAnswer*100 );
		
		return errorRatio; 
	}
	
	public double getErrorRatioTwo() { 
		double firstAngleRadian = this.firstAngleRadian;
		double secondAngleRadian = this.secondAngleRadian;
		double integration = this.integration;
		
		double integrationAnswer = Math.cos( firstAngleRadian ) - Math.cos( secondAngleRadian );
		
		double errorRatio = Math.abs( ( integrationAnswer - integration )/integrationAnswer*100 );
		
		return errorRatio; 
	}
	
	public double getErrorRatioAnother() { 
		double integrationAnswer = Math.cos( firstAngleRadian ) - Math.cos( secondAngleRadian );
		
		double errorRatio = Math.abs( ( integrationAnswer - integration )/integrationAnswer*100 );
		
		return errorRatio; 
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
	
	

}
