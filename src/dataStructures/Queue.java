package dataStructures;

import adjacencyList.Node;

public class Queue {
	
	private Node header;
	
	public Queue() {
		header = null;
	}
	
	public void enqueue(int a) {
		Node n = new Node(a);
		
		if(header == null) {
			header = n;
		}
		else {
			Node current = header;
			
			while(current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(n);
		}
	}
	
	public int dequeue() {
		
		if(header == null) {
			return -99;
		}else {
			Node temp = header;

			if (header.getNext() == null) {
				header = null;
				return temp.getInfo();
			}
			else {
				header = header.getNext();
				temp.setNext(null);
				return temp.getInfo();
			}
		}
	}
	
	public boolean isEmpty() {
		return (header == null);
	}

}
