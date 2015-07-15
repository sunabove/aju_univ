package tutorial;

import java.io.InputStreamReader;

public class ReadConsole {

	public static void main(String[] args) throws Exception {
		InputStreamReader cin = null;

		cin = new InputStreamReader(System.in);
		System.out.println("Enter characters, 'q' to quit.");
		char c;
		do {
			c = (char) cin.read();
			System.out.print(c);
		} while (c != 'q');
		
		cin.close();
	}

}
