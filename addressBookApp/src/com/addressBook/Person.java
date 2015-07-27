package com.addressBook;

import java.util.Date;  
import static com.addressBook.Person.Gender;
import static com.addressBook.Person.Gender.*;

public class Person extends Object {
	
	public static enum Gender { MALE, FEMALE } ;
	
	int id;
	String name;
	Gender gender ; 
	Date birthDate ; 
	String phoneNumber;
	String address ;  
	String imageFileName ; 
	String memo ; 
	
	public Person() {  
		this.gender = MALE ; 
	}
	
	@Override
	public String toString() {
		String msg = "id = %d, name = %s" ; 
		msg = String.format( msg, id, name ); 
		
		return msg;
	}
}
