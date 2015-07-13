package nimgame;

public class Stage {
	
	Pile [] piles ;
	
	public Stage () {
		piles = new Pile[ 3 ] ;
		
		piles[ 0 ] = new Pile();
		piles[ 1 ] = new Pile();
		piles[ 2 ] = new Pile();
	}
}
