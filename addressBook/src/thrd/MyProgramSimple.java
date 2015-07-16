package thrd;

public class MyProgramSimple {  
	public static void main(String[] args) {  
		class MyRunnable extends java.lang.Thread implements java.lang.Runnable {
			int programId = 0;
			
			public MyRunnable() { 
			}
			
			public MyRunnable( int programId ) {
				this.programId = programId ; 
			}
			
			public void run() {
				for( int i = 0, iLen = 100000; i < iLen ; i ++ ) {
					System.out.println( "" + programId + " : " + i );
				}
			}
		}
		
		MyRunnable r = new MyRunnable( 1 ); 		
		MyRunnable r2 = new MyRunnable( 2 ); 
		MyRunnable r3 = new MyRunnable( 3 );  
		
		r.start();
		r2.start();  
		r3.start(); 
	}

}
