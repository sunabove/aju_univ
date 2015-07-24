package com.addressBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook { 
	
	static final String ADDRESS_BOOK_FILE_NAME = "c:/tmp/addressBook.txt" ;
	ArrayList<Person>  persons ; 
	
	static AddressBook ADDRESS_BOOK = null ;
	
	private AddressBook() {
		this.persons = new ArrayList<Person>();
	} 
	
	public static AddressBook getAddressBook() {
		if( ADDRESS_BOOK == null ) {
			ADDRESS_BOOK = new AddressBook();
		}
		
		return ADDRESS_BOOK ; 
	}
	
	public void readAllPersonsFromFile() {
		String msg = null ; 
		try {
			Scanner scanner = new Scanner( new File( ADDRESS_BOOK_FILE_NAME ) );
			ArrayList<Person>  persons = this.persons;
			Person p ; 
			boolean endOfFile = false ; 
			int readPersonCount = 0 ; 
			for(   ; ! endOfFile ;  ) {
				p = null ; 
				if( scanner.hasNext() ) {
					p = new Person();
					p.id = Integer.parseInt( scanner.nextLine() );
					if( scanner.hasNext() ) {
						p.name = scanner.nextLine();
						if( scanner.hasNext() ) {
							p.phoneNumber = scanner.nextLine();
							if( scanner.hasNext() ) {
								p.address = scanner.nextLine();
							} else {
								endOfFile = true;
							}
						} else {
							endOfFile = true;
						}
					} else {
						endOfFile = true;
					}
				} else {
					endOfFile = true;
				}
				if( p != null ) {
					persons.add( p );
					readPersonCount ++  ; 
				} 
			} 
			scanner.close();
			
			if( readPersonCount == 0 ) {
				msg = "There is no person information in the addressbook file.";
			} else {
				msg = "The %d persons information has read from the addressbook file.";
				msg = String.format( msg, readPersonCount );
			}
		} catch ( FileNotFoundException e) { 
			e.printStackTrace(); 
			msg = "Error: cannot found the address book file.";
		}
		
		if( msg != null ) { 
		}
	}
	
	public void saveAllPersonsOnFile() {
		String msg = null ; 
		FileOutputStream fos = null;
		File file; 
		try { 
			file = new File( ADDRESS_BOOK_FILE_NAME );
			fos = new FileOutputStream(file);  
			if( ! file.exists() ) {
				file.createNewFile();
			}  
			ArrayList<Person> persons = this.persons;
			Person p ;
			String newLine = "\r\n"; 
			for( int i = 0, iLen = persons.size() ; i < iLen ; i ++ ) {
				p = persons.get(i);
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
		}
	}  

}
