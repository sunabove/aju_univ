package nimGameStru;

import java.util.Scanner;

public class NimObject {

	public NimObject() { 
	}
	
	public int userEnterInteger(String msg, int min, int max) {
		boolean userInputOk = false;
		int number = 0;
		while (!userInputOk) {
			try {
				print(msg);
				Scanner scanner = new Scanner(System.in);
				number = scanner.nextInt();

				String error = null;
				if (number < min) {
					error = "Input number is less than %d";
					error = String.format(error, min);
				} else if (number > max) {
					error = "Input number is greater than %d";
					error = String.format(error, max);
				} else if (min <= number && number <= max) {
					userInputOk = true;
				}

				if (error != null) {
					println();
					print(error);
					println();
				}
			} catch (Exception e) {
				String message = "Invalid integer! Enter a valid integer, please!";
				System.out.println(message);
			}
		}
		return number;
	}
	
	public void enterToContinue() {
		String msg = "Enter to continue: ";
		print(msg);
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();

		return;
	} 

	public String userEnterMenu(String msg) {
		boolean userInputOk = false;
		String oneLine = null;
		while (!userInputOk) {
			try {
				if (msg != null && msg.length() > 0) {
					print(msg);
				}
				Scanner scanner = new Scanner(System.in);
				oneLine = scanner.nextLine();
				oneLine = oneLine.trim();
				userInputOk = true;
			} catch (Exception e) {
				String message = "Invalid menu key! Enter a valid menu key, please!";
				System.out.println(message);
			}
		}
		return oneLine;
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
