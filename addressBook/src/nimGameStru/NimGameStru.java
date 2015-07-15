package nimGameStru;

import java.util.Scanner;

public class NimGameStru {
	
	public NimGameStru() {
		
	}
	
	public void run() {
		
		String line = "################################################" ;
		String programInfo = "      Nim Game V 1.0" ;
		
		println( line );
		println();
		println( programInfo );
		println();
		
		String msg ; 
		
		boolean goOn = true ;
		
		while( goOn ) {
			playOneStage();
			
			goOn = this.doPlayNewStage() ; 
		}
		
		
		msg = "Good bye!";
		println();
		println( msg );
	}
	
	public void playOneStage() {
		int []  cakeCounts = new int [ 3 ];
		
		int maxCakeCount = 9;
		for( int i = 0 , iLen = cakeCounts.length ; i < iLen ; i++ ) {
			cakeCounts[ i ] = (int)( 1 + maxCakeCount*Math.random() );
		}
		
		int winner = 0 ;
		
		while( winner == 0 ) {  
			this.printCakes(cakeCounts); 
			playHuman( cakeCounts );
			
			if( this.isAllZero( cakeCounts ) ) {
				winner = 1; 
			} else { 
				playMonkey( cakeCounts );
				if( this.isAllZero( cakeCounts ) ) {
					winner = 2; 
				}  
			}
		}
		
		String msg = null ;
		
		if( winner == 1 ) {
			msg = "Congratulation: You have won!" ; 
		} else {
			msg = "Failed." ;
		}
		
		if( msg != null ) {
			println();
			println( msg );
			println();
			this.enterToContinue();
		}
	}
	
	public boolean isAllZero( int [] cakeCounts ) {
		boolean isAllZero = true ;
		
		for( int cakeCount : cakeCounts ) {
			if( cakeCount > 0 ) {
				isAllZero = false ; 
				break ; 
			}
		}
		return isAllZero ; 
	}
	
	public void playMonkey( int [] cakeCounts ) {
		String msg;
		
		msg = "Monkey played." ;
		
		println();
		println( msg );
		println();
	}
	
	public void playHuman( int [] cakeCounts ) {
		String msg ;
		msg = "Enter the pile number to select : " ;
		
		println();
		int pileNo = this.userEnterInteger( msg );
		if( pileNo >= 1 && pileNo <= cakeCounts.length ) {
			msg = "Enter the cake count to eat : " ;
			int cakeCount = this.userEnterInteger( msg );
			
			cakeCounts[ pileNo -1 ] = cakeCounts[ pileNo -1 ] - cakeCount ; 
			if( cakeCounts[ pileNo -1 ] < 0 ) {
				cakeCounts[ pileNo -1 ] = 0 ;
			}
		}
	}
	
	public void printCakes( int [] cakeCounts ) {
		int pileNo = 1;
		String msgFormat = "[ %d ]  : %s" ; 
		String msg;
		String cakeStr ; 
		for( int cakeCount : cakeCounts ) {
			cakeStr = "";
			for( int k = 0, kLen = cakeCount; k < kLen ; k ++ ) {
				cakeStr += "*";
			}
			msg = String.format( msgFormat, pileNo, cakeStr );
			println( msg );
			pileNo ++ ; 
		}
	}
	
	public boolean doPlayNewStage() {
		String msg;
		msg = "[A] Again / [E] End";
		println();
		println( msg );
		msg = "Select Menu: ";
		
		String menuKey = this.userEnterMenu( msg );
		
		boolean newStage = true;
		
		if( menuKey.equalsIgnoreCase( "E") ) {
			newStage = false ; 
		}
		return newStage;
	}
	
	public void enterToContinue() {
		String msg = "Enter to continue: " ;
		print( msg );
		Scanner scanner = new Scanner( System.in );   
		scanner.nextLine();
		
		return;
	}
	
	public void println() {
		System.out.println();
	}
	public void println( String msg ) {
		System.out.println( msg );
	}
	
	public void print( String msg ) {
		System.out.print( msg );
	}
	
	private int userEnterInteger( String msg ) {
		boolean userInputOk = false ; 
		int number = 0 ;
		while( ! userInputOk ) { 
			try { 
				print( msg ); 
				Scanner scanner = new Scanner( System.in );   
				number = scanner.nextInt(); 
				userInputOk = true;
			} catch( java.util.InputMismatchException e ) {
				String message = "Invalid integer! Enter a valid integer, please!";
				System.out.println( message ); 
			} 
		}  
		return number; 
	}
	
	private String userEnterMenu( String msg ) {
		boolean userInputOk = false ; 
		String oneLine = null  ; 
		while( ! userInputOk ) { 
			try { 
				if( msg != null && msg.length() > 0 ) { 
					print( msg ); 
				}
				Scanner scanner = new Scanner( System.in );   
				oneLine = scanner.nextLine();
				oneLine = oneLine.trim();  
				userInputOk = true ;  
			} catch( Exception e ) {
				String message = "Invalid menu key! Enter a valid menu key, please!";
				System.out.println( message ); 
			} 
		} 
		return oneLine; 
	}

	public static void main(String[] args) {
		NimGameStru nimGame = new NimGameStru();
		
		nimGame.run();

	}

}
