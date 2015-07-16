package nimGameStru;

public class Monkey {

	public Monkey() {

	}

	public void play(int[] cakeCounts) {
		String msg;

		int cakeCount;
		int eatCount = -1;
		int pileNo = 1;
		for (int i = 0, iLen = cakeCounts.length; i < iLen; i++) {
			cakeCount = cakeCounts[i];
			if (cakeCount > 0) {
				eatCount = 1 + (int) ((cakeCount - 1) * Math.random());
				cakeCounts[i] = cakeCount - eatCount;
				i = iLen;
				break;
			}
			pileNo++;
		}

		msg = "Monkey has eat %d cakes at the pile (%d).";
		msg = String.format(msg, eatCount, pileNo);

		println();
		println(msg);
		println();
	}
	
	public void println() {
		System.out.println();
	}

	public void println(String msg) {
		System.out.println(msg);
	}

	public void print(String msg) {
		System.out.print(msg);
	}
}
