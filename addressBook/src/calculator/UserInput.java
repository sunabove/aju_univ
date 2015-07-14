package calculator;

public class UserInput {
	double x;
	double y;
	String oper;
	
	public UserInput() { 
		this.x = 0;
		this.y = 0;
		this.oper = "+" ; 
	}
	
	 public double getOperationValue() {
		 String oper = this.oper;
		 double x = this.x;
		 double y = this.y;
		 
		 double value = 0 ;
		 if( oper.equals( "+") ) {
			 value = x + y;
		 } else  if( oper.equals( "-") ) {
			 value = x - y;
		 } else  if( oper.equals( "*") ) {
			 value = x * y;
		 } else  if( oper.equals( "/") ) {
			 value = x / y;
		 }
		 return value ;
	 }
}
