package nimGameStru;

import java.util.Scanner;

public class NimGameStru extends NimObject {

	public NimGameStru() {

	}

	public void run() {

		String line = "################################################";
		String programInfo = "      Nim Game V 1.0";

		println(line);
		println();
		println(programInfo);
		println();

		String msg;

		boolean goOn = true;

		while (goOn) {
			playOneStage();

			goOn = this.doPlayNewStage();
		}

		msg = "Good bye!";
		println();
		println(msg);
	}

	public void playOneStage() {
		
		Stage stage = new Stage();
		
		Monkey monkey = new Monkey();
		Human human = new Human();

		int winner = 0;

		while (winner == 0) {
			this.printCakes(stage.cakeCounts);
			//playHuman(cakeCounts);
			human.play( stage );

			if (this.isAllZero(stage.cakeCounts)) {
				winner = 1;
			} else {
				//playMonkey(cakeCounts);
				monkey.play( stage );
				if (this.isAllZero( stage.cakeCounts)) {
					winner = 2;
				}
			}
		}

		String msg = null;

		if (winner == 1) {
			msg = "Congratulation: You have won!";
		} else {
			msg = "Failed.";
		}

		if (msg != null) {
			println();
			println(msg);
			println();
			this.enterToContinue();
		}
	}

	public boolean isAllZero(int[] cakeCounts) {
		boolean isAllZero = true;

		for (int cakeCount : cakeCounts) {
			if (cakeCount > 0) {
				isAllZero = false;
				break;
			}
		}
		return isAllZero;
	}

	public void playMonkey(int[] cakeCounts) {
		String msg;
		
		int cakeCount ; 
		int eatCount = - 1 ; 
		int pileNo = 1; 
		for( int i = 0, iLen = cakeCounts.length ; i < iLen ; i ++ ) {
			cakeCount = cakeCounts[ i ];
			if( cakeCount > 0 ) {
				eatCount = 1 + (int)( (cakeCount -1)*Math.random());
				cakeCounts[i] = cakeCount - eatCount ; 
				i = iLen ;
				break; 
			}
			pileNo ++ ; 
		}

		msg = "Monkey has eat %d cakes at the pile (%d).";
		msg = String.format( msg, eatCount, pileNo );

		println();
		println(msg);
		println();
	}

	public void playHuman(int[] cakeCounts) {
		String msg;

		int pileNo = -1;
		while (pileNo < 1) {
			// selec a file which cake count is greater than 0.
			msg = "Enter the pile number to select : ";

			println();
			int min = 1, max = cakeCounts.length;
			pileNo = this.userEnterInteger(msg, min, max);
			if( cakeCounts[ pileNo -1 ] < 1 ) {
				msg = "There is no cake to eat at the pile (%d)." ;
				msg = String.format( msg , pileNo );
				println();
				println( msg );
				pileNo = -1 ; 
			}
		}

		if (pileNo >= 1 && pileNo <= cakeCounts.length) {
			// decrease cake count by the user enter number
			msg = "Enter the cake count to eat : ";
			int min = 1 , max = cakeCounts[ pileNo -1 ] ; 
			int cakeCount = this.userEnterInteger(msg, min, max);

			cakeCounts[pileNo - 1] = cakeCounts[pileNo - 1] - cakeCount;
			if (cakeCounts[pileNo - 1] < 0) {
				cakeCounts[pileNo - 1] = 0;
			}
		}
	}

	public void printCakes(int[] cakeCounts) {
		int pileNo = 1;
		String msgFormat = "[ %d ]  : %s";
		String msg;
		String cakeStr;
		for (int cakeCount : cakeCounts) {
			cakeStr = "";
			for (int k = 0, kLen = cakeCount; k < kLen; k++) {
				cakeStr += "*";
			}
			msg = String.format(msgFormat, pileNo, cakeStr);
			println(msg);
			pileNo++;
		}
	}

	public boolean doPlayNewStage() {
		String msg;
		msg = "[A] Again / [E] End";
		println();
		println(msg);
		msg = "Select Menu: ";

		String menuKey = this.userEnterMenu(msg);

		boolean newStage = true;

		if (menuKey.equalsIgnoreCase("E")) {
			newStage = false;
		}
		return newStage;
	} 

	public static void main(String[] args) {
		NimGameStru nimGame = new NimGameStru();

		nimGame.run();

	}

}
