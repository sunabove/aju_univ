package linkedList;

public class LinkedList<T> {
	
	T data;
	LinkedList<T> next;
	//LinkedList<Object> next;
	
	public LinkedList() { 
		this.next = null ; 
	}
	
	T getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return "" + data;
	}
}
