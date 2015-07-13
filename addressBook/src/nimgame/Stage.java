package nimgame;

public class Stage extends NimObject {
	
	boolean stageDone ; 
	
	Pile [] piles ;
	
	public Stage () {
		this.stageDone = false ;
		
		piles = new Pile[ 3 ] ;
		
		piles[ 0 ] = new Pile();
		piles[ 1 ] = new Pile();
		piles[ 2 ] = new Pile();
	}
	
	@Override
	public String toString() {
		String text = "";
		
		Pile [] piles = this.piles;
		Pile pile ; 
		String textFormat = "[%d] %s";
		int pileNo ; 
		for( int i = 0, iLen = piles.length ; i < iLen ; i ++ ) {
			pile = piles[ i ] ; 
			pileNo = i + 1;
			text += String.format( textFormat, pileNo , pile.toString() ); 
			if( i < iLen -1 ) {
				text += NEW_LINE ; 
			}
		}
		
		return text;
	}
}
