package nimgame;

public class NimGame extends NimObject {
	
	Stage stage ;
	Player monkeyPlayer;
	Player humanPlayer ; 
	
	public NimGame() {  
	}
	
	public void run() { 
		String programInfo = "      NIM GAME Ver 1.0" ;
		
		println();
		println( ONE_LINE );
		println();
		println( programInfo );
		println();
		println( ONE_LINE ); 
		
		this.stage = new Stage();
		
		this.monkeyPlayer = new MonkeyPlayer();
		this.humanPlayer = new HumanPlayer();
		
		for( ; ! stage.stageDone ;  ) { 
			println( stage.toString() );
			
			humanPlayer.play( stage );
			if( ! stage.stageDone ) {
				monkeyPlayer.play( stage );
			}
		}
	}
	
	public static void main(String[] args) {
		NimGame nimGame = new NimGame();
		nimGame.run();
	}

}
