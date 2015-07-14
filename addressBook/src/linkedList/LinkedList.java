package linkedList;

public class LinkedList {
	
	String data;
	LinkedList next;
	
	public LinkedList() {
		this.data = "";
		this.next = null ; 
	}
	
	@Override
	public String toString() {
		return data;
	}
}
