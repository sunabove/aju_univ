package nimGameStru;

import java.util.Scanner;

public class Human extends NimObject {

	public Human() {

	}

	public void play( Stage stage ) {
		String msg;

		int [] cakeCounts = stage.cakeCounts ; 
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
	
}
