package com.addressBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.addressBook.Person.Gender;

import android.os.Environment;

public class AddressBook { 
	
	public static final SimpleDateFormat BIRTH_DATE_FORMAT =new SimpleDateFormat("yyyy-MM-DD"); 
	private static AddressBook ADDRESS_BOOK = null ;
	
	File addressBookFile ; 
	
	ArrayList<Person>  persons ; 
	
	boolean personInserted ;  
	Person personSelected ;
	
	private AddressBook() {
		this.addressBookFile = this.getAddressBookFile() ; 
		
		this.persons = new ArrayList<Person>();
		
		//add mock up data
		//addMockUpData();
		
		this.readAllPersonsFromFile();
	} 
	
	private File getAddressBookFile() { 
		if( this.addressBookFile == null ) { 
			String fileName = "addressBook.txt";
			File storageDir = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES) ;
			this.addressBookFile = new File(storageDir, fileName); 
		}
		
		return this.addressBookFile  ; 
	}
	
	private void addMockUpData() {
		ArrayList<Person> persons = this.persons;
		
		int id = 1 ; 
		Person p;
		
		p = new Person(); 
		p.id = id ++ ;
		p.name = "Jhon" ;
		p.phoneNumber = "011 2222 4444";
		
		persons.add( p );
		
		p = new Person(); 
		p.id = id ++ ;
		p.name = "Doe" ;
		p.phoneNumber = "010 2222 4444";
		
		persons.add( p );
		
		p = new Person(); 
		p.id = id ++ ;
		p.name = "Jame" ;
		p.phoneNumber = "010 3333 4444";
		
		persons.add( p );
		
		p = new Person(); 
		p.id = id ++ ;
		p.name = "Kang Jung Ho" ;
		p.phoneNumber = "010 2342 7684";
		
		persons.add( p );
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
			File file = this.addressBookFile ;
			if( file == null || ! file.exists() ) {
				return ;
			}
			Scanner scanner = new Scanner( file );
			ArrayList<Person>  persons = this.persons;
			Person p ; 
			boolean endOfFile = false ; 
			int readPersonCount = 0 ; 
			for(   ; ! endOfFile ;  ) {
				p = null ; 
				p = new Person(); 
				try {  
					p.id = Integer.parseInt( scanner.nextLine().trim() ) ;
					p.name = scanner.nextLine().trim();
					int gender = Integer.parseInt( scanner.nextLine().trim() );
					p.gender = gender == 0 ? Person.Gender.MALE : Person.Gender.FEMALE ; 
					Date birthDate = null;
					try {
						String birthDateStr = scanner.nextLine().trim(); 
						if( birthDateStr != null && birthDateStr.length() > 0 ) {
							birthDate = BIRTH_DATE_FORMAT.parse( birthDateStr );
						}
					} catch (Exception ee ) { 
						ee.printStackTrace();
					}
					p.birthDate = birthDate ;
					p.phoneNumber = scanner.nextLine().trim();
					p.address = scanner.nextLine().trim();
					p.imageFileName = scanner.nextLine().trim();
					p.memo = scanner.nextLine().trim();
				} catch ( Exception e ) {
					e.printStackTrace();
					p = null;
					endOfFile = true ; 
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
			file = this.addressBookFile ;
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
					// ID
					fos.write( ( "" + p.id + newLine ).getBytes() );
					// NAME 
					fos.write( ( p.name + newLine ).getBytes() );
					
					// GENDER
					int gender = p.gender == Gender.MALE ? 0 : 1 ; 
					fos.write( ( "" + gender + newLine).getBytes() );
					
					// BIRTH DATE
					String birthDataStr = "";
					if( p.birthDate != null ) { 
						birthDataStr = BIRTH_DATE_FORMAT.format( p.birthDate );
					}
					fos.write( ( birthDataStr + newLine).getBytes() ); 
					
					// PHONE NUMBER
					fos.write( ( p.phoneNumber + newLine ).getBytes() );
					
					// ADDRESS 
					fos.write( ( p.address + newLine ).getBytes() ); 
					
					// IMAGE FILE NAME
					String imageFileName = p.imageFileName ; 
					imageFileName = imageFileName == null ? "" : imageFileName.trim() ; 
					fos.write( (imageFileName + newLine).getBytes() );
					
					// MEMO
					String memo = p.memo ;
					memo = memo == null ? "" : memo.trim();
					fos.write( (memo + newLine).getBytes() ); 
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
