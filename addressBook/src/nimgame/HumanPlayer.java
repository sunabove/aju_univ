package nimgame;

import java.util.Scanner;

public class HumanPlayer extends Player {

	public HumanPlayer() {
	}

	@Override
	public Pile selectPile(Stage stage) {
		Scanner scanner = new Scanner(System.in);

		String msg = "Select a Pile: ";
		int pileNo = 0;
		do {
			print(msg);
			pileNo = scanner.nextInt();
			if (pileNo > stage.piles.length) {
				msg = "Invalid pile number!";
				println(msg);
			}
		} while (pileNo < 1);

		return stage.piles[pileNo - 1];
	}

	@Override
	public int selectCake(Pile pile) {
		Scanner scanner = new Scanner(System.in);

		String msg = "Eat cakes: ";
		int cakeCount = 0;
		do {
			print(msg);
			cakeCount = scanner.nextInt();
			if (cakeCount > pile.cakeCount ) {
				msg = "Invalid cake count!";
				println(msg);
			}
		} while ( cakeCount < 1);

		return cakeCount ; 
	}
}
