package nimgame;

public abstract class Player extends NimObject {

	public Player() { 
	}
	
	public void play( Stage stage )  {
		Pile selPile = selectPile( stage );
		int cakeCount = selectCake( selPile );
		
		// decrease cake count
	}
	
	public abstract Pile selectPile( Stage stage );
	
	public abstract int selectCake( Pile pile );
}
