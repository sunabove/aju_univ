package nimgame;

public class MonkeyPlayer extends Player {

	public MonkeyPlayer() {
		
	}

	@Override
	public Pile selectPile(Stage stage) {
		Pile [] piles = stage.piles;
		
		for( Pile pile : piles ) {
			if( pile.cakeCount > 0 	) {
				return pile ; 
			}
		}
		
		return piles[ 0 ];
	}

	@Override
	public int selectCake(Pile pile) {
		int cakeCount = pile.cakeCount ; 
		
		int selCakeCount = (int)( cakeCount*Math.random() );
		return selCakeCount ;
	} 
}
