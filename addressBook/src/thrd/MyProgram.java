package thrd;

public class MyProgram {  
	public static void main(String[] args) {  
		class MyRunnable implements Runnable {
			int programId = 0;
			
			public MyRunnable( int programId ) {
				this.programId = programId ; 
			}
			
			public void run() {
				for( int i = 0, iLen = 100000; i < iLen ; i ++ ) {
					System.out.println( "" + programId + " : " + i );
					
					Thread currThread = Thread.currentThread();
					
					if( i%2 == 1 ) { 
						try {
							currThread.sleep( 100 );
						} catch (InterruptedException e) { 
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		MyRunnable r = new MyRunnable( 1 );  
		Thread t = new Thread( r ); 
		
		MyRunnable r2 = new MyRunnable( 2 );  
		Thread t2 = new Thread( r2 ); 
		
		MyRunnable r3 = new MyRunnable( 3 );  
		Thread t3 = new Thread( r3 );
		
		t.start();
		t2.start();
		t3.start();
	}

}
