package calculator; 
import java.util.Scanner; 
public class Calculator { 
	public static void main(String[] args) {
		Console console = new Console();
		
		UserInput userInput = console.userEnter();
		
		double operationValue = userInput.getOperationValue(); 
		
		System.out.println( "Result = " + operationValue );
	} 

}
