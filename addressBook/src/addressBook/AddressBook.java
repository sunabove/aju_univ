package addressBook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class AddressBook { 
	Person [] persons ; 
	
	public AddressBook() {
		persons = new Person[ 10 ] ; 
	}

	public  void run() {  
		String menuCode; 
		int inputNo ;
		do { 
			this.printMenus();
			
			inputNo = -1 ; 
			menuCode = getUserInputMenuCode(); 
			
			try {
				inputNo = Integer.parseInt( menuCode );
			} catch (NumberFormatException e) {  
			}
			
			String error = null;
			if( menuCode.equalsIgnoreCase( "L") ) {
				printPersonList();
			} else if( menuCode.equalsIgnoreCase( "I") ) {
				Person p = insertPerson();
				println( "The person insterted: " + p.toString() );
				if( p != null ) {
					saveAllPersonsOnFile();
				}
				this.enterToContinue();
			} else if( inputNo > -1 ) {
				printPersonInformation( inputNo );
			} else {
				error = "Invalid menu code";
			}
			if( error != null ) {
				printErrorln( error );
				println();
				enterToContinue();
			}
		} while( ! menuCode.equalsIgnoreCase( "Q")  ); 
		
		println(); 
		String msg = "Good bye!"; 
		println( msg );
	}
	
	public void saveAllPersonsOnFile() {
		String msg = null ; 
		FileOutputStream fos = null;
		File file; 
		try { 
			file = new File("c:/tmp/addressBook.txt");
			fos = new FileOutputStream(file);  
			if( ! file.exists() ) {
				file.createNewFile();
			}  
			Person [] persons = this.persons;
			Person p ;
			String newLine = "\n"; 
			for( int i = 0, iLen = persons.length ; i < iLen ; i ++ ) {
				p = persons[i];
				if( p != null ) {
					fos.write( ( "" + p.id + newLine ).getBytes() );
					fos.write( ( p.name + newLine ).getBytes() );
					fos.write( ( p.phoneNumber + newLine ).getBytes() );
					fos.write( ( p.address + newLine ).getBytes() ); 
				}
			} 
			fos.flush();
			fos.close();  
			msg = "Success: saved person information on the file." ;
		} catch (IOException e) {
			e.printStackTrace();
			msg = "Error: cannot save persons information on the file." ; 
		} 
		
		if( msg != null ) {
			println();
			println( msg );
			println();
		}
	}
	
	public void printPersonInformation( int userId ) {
		Person [] persons = this.persons;
		Person foundPerson = null ;
		
		Person p ; 
		for( int i =0, iLen = persons.length ; i< iLen ; i ++ ) {
			p = persons[ i ];
			if( p != null && p.id == userId ) {
				foundPerson = p ;
				i = iLen ;
				break; 
			}
		}
		
		String msg = null ; 
		if( foundPerson == null ) {
			msg = "  Cannot find a person whose id is " + userId ;
		} else if( foundPerson != null ) {
			msg = "  [ Id ] : %d \n  [ Name ] : %s \n  [ Phone number ] : %s \n  [ Address ] : %s" ;
			msg = String.format( msg, foundPerson.id, foundPerson.name, foundPerson.phoneNumber, foundPerson.address );
		}
		
		if( msg != null ) {
			println( msg );
			println();
		}
		
	}
	
	public void enterToContinue() {
		String msg = "Enter to continue: " ;
		print( msg );
		getUserInputMenuCode();
		
		return;
	}
	
	public Person insertPerson() {
		String msg ;
		Scanner scanner = new Scanner( System.in ) ;
		
		Person p = new Person();
		
		msg = "Id : ";
		print( msg );
		p.id = scanner.nextInt();
		
		msg = "Name : ";
		print( msg );
		p.name = getStringFromUser( scanner );
		
		msg = "Phoe number : ";
		print( msg );
		p.phoneNumber = getStringFromUser( scanner );
		
		msg = "Address : ";
		print( msg );
		p.address = getStringFromUser( scanner );
		
		for( int i = 0 , iLen = persons.length ; i < iLen ; i ++ ) {
			if( persons[i] == null ) {
				persons[i] = p ;
				i = iLen ; 
				break;
			}
		}
		
		return p;
		
	}
	
	public String getStringFromUser( Scanner scanner ) {
		String str = null ;
		while( str == null || str.length() < 1 ) {
			str = scanner.nextLine();
			str = str.trim();
		}
		
		return str;
	}
	
	public void printPersonList() {
		Person p;
		
		String msg ; 
		println( );
		msg = "   Persons List" ;
		println( msg );
		println();
		
		boolean anyPersonExist = false ; 
		
		int no = 0 ;
		for( int i = 0, iLen = persons.length ; i < iLen ; i++ ) {
			p = persons[i];
			if( p != null ) { 
				anyPersonExist = true ; 
				no ++ ;  
				msg = " [ %d ] id = %d, name = %s" ;
				msg = String.format( msg, no, p.id, p.name );
				println( msg );
			}
		}
		
		if( ! anyPersonExist ) {
			msg = " There is no person." ;
			println( msg );
			msg = " Enter any person's information at first, please!" ;
			println( msg );
		} 
		
		println();
	}
	
	public void printMenus() {
		String line = "##############################################" ; 
		String programName = "        AddressBook        " ;
		
		println( line ) ;
		println( ) ;
		println( programName );
		println( ) ;
		println( line ) ;
		println(  ) ;
		
		String msg = "";
		msg = "    [ L ] : List person's id and name" ;
		println( msg );
		msg = "    [ I ] : Insert a person information" ;
		println( msg );
		msg = "    [ # ] : Enter a person's id to view his information" ;
		println( msg );
		msg = "    [ Q ] : Quit this program ;";
		println();
		println( msg );
		
	}
	
	public String getUserInputMenuCode() {
		String menuCode ;
		
		Scanner scanner = new Scanner( System.in );
		
		menuCode = scanner.nextLine();
		
		return menuCode;
	}
	
	public void println( String msg ) {
		System.out.println( msg );
		System.out.flush();
	}
	
	public void printErrorln( String msg ) {
		System.err.println( msg );
		System.err.flush();
	}
	
	public void println( ) {
		System.out.println( );
		System.out.flush();
	}
	
	public void print( String msg ) {
		System.out.print( msg );
		System.out.flush();
	} 

}
