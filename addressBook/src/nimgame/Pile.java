package nimgame;

public class Pile {

	int cakeCount ; 
	Cake [] cakes ;
	
	public Pile() {
		int cakeCount = (int)( 10*Math.random() );
		this.cakeCount = cakeCount ; 
		cakes = new Cake[ cakeCount ];
		for( int i = 0 , iLen = cakeCount ; i < iLen ; i ++ ) {
			cakes[i] = new Cake();
		}
	}
	
	@Override
	public String toString() {
		String text = "";
		
		Cake [] cakes = this.cakes;
		Cake cake;
		for( int i = 0, iLen = cakes.length ; i < iLen ; i ++ ) {
			cake = cakes[ i ];
			if( cake != null ) { 
				text += cake.toString();
			}
		}
		
		return text; 
	}
}
