package calculator;

import java.util.Scanner;

public class Calculator {

	public Calculator() {

	}

	public void run() {
		Console console = new Console();

		UserInput a;
		a = console.userEnter();

		double operationValue = a.getOperationValue();

		System.out.println("Result = " + operationValue);
	}

	public static void main(String[] args) {
		
		Calculator calculator = new Calculator();
		
		calculator.run();

	}

}
