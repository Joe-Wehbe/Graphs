package dataStructures;

import adjacencyList.Node;

public class Stack {
	
	private Node header;
	
	public Stack() {
		header = null;
	}
	
	public void push(int a) {
		
		Node n = new Node(a);
		
		if(header == null) {
			header = n;
		}else {
			n.setNext(header);
			header = n;
		}
		
	}
	
	public int pop() {
		
		if(header == null) {
			return -99;
		}else {
			Node temp = header;
			if(header.getNext() == null) {
				header = null;
				return temp.getInfo();
			}
			
			header = header.getNext();
			temp.setNext(null);
			return temp.getInfo();
		
		}
	}
	
	public int peek() {
		if(header == null) {
			return -99;
		}else {
			return header.getInfo();
		}
	}
	
	public boolean isEmpty() {
		return (header == null);
	}
	
	public void viewStack() {
		
		if(header == null) {
			System.out.println("Stack empty");
		}else {
			Node current = header;
			
			while(current.getNext() != null) {
				System.out.print(current.getInfo());
				current = current .getNext();
			}
		}
		
	}

}
