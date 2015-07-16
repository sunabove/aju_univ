package nimGameStru;

public class Stage {
	
	int[] cakeCounts ;

	public Stage() {  
		this.cakeCounts = new int[3];
		
		int maxCakeCount = 9;
		for (int i = 0, iLen = cakeCounts.length; i < iLen; i++) {
			cakeCounts[i] = (int) (1 + maxCakeCount * Math.random());
		}
	}

}
