package nimgame;

public class Pile {

	Cake [] cakes ;
	
	public Pile() {
		int cakeCount = (int)( 10*Math.random() );
		cakes = new Cake[ cakeCount ];
		for( int i = 0 , iLen = cakeCount ; i < iLen ; i ++ ) {
			cakes[i] = new Cake();
		}
	}
}
