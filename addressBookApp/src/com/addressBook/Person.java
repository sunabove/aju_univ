package com.addressBook;

public class Person extends Object {
	
	int id;
	String name;
	String phoneNumber;
	String address ;  
	
	public Person() {  
	}
	
	@Override
	public String toString() {
		String msg = "id = %d, name = %s" ; 
		msg = String.format( msg, id, name );
		
		//msg = "id " + id + "name = " + name ; 
		
		return msg;
	}
}
