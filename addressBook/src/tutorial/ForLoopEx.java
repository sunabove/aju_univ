package tutorial;

public class ForLoopEx {

	public static void main(String[] args) {
		if (true) {
			for (int i = 1, k = 1, iLen = 10, kLen = 10; i < iLen && k < kLen; i++, k++) {
				System.out.println("" + i + "*" + k + " = " + (i * k));
			}
		}

		if (true) {
			int i = 1, k = 1, iLen = 10, kLen = 10;

			for ( ; i < iLen && k < kLen; i++, k++) {
				System.out.println("" + i + "*" + k + " = " + (i * k));
			}
		}
		
		if (true) {
			int i = 1;
			int k = 1;
			int iLen = 10;
			int kLen = 10;

			for (; i < iLen && k < kLen; i++, k++) {
				System.out.println("" + i + "*" + k + " = " + (i * k));
			}
		}

	}

}
