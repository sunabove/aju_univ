package nimgame;

public class NimGame {
	
	Stage stage ;
	
	public NimGame() { 
		
	}
	
	public void run() { 
		stage = new Stage();
	}

	public static void main(String[] args) {
		NimGame nimGame = new NimGame();
		nimGame.run();
	}

}
