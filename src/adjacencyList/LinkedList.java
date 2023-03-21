package adjacencyList;

public class LinkedList {
	
	private Node header;
	
	public LinkedList() {
		header = null;
	}

	public Node getHeader() {
		return header;
	}

	public void setHeader(Node header) {
		this.header = header;
	}
	
	public void add(int i) {
		Node n = new Node(i);
		
		if(header == null) {
			header = n;
			
		}else {
			
			Node current = header;

			while(current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(n);
			
		}
			
	}
	
	public int size() {
		Node current = header;
		
		int count = 0;
		while(current != null) {
			current = current.getNext();
			count++;
		}
		return count;
	}
	
	public void print() {
		
		if(header == null) {
			System.out.println("//");
		}else {
			Node current = header;
			
			while(current.getNext() != null) {
				System.out.print(current.getInfo() + " --> ");
				current = current.getNext();
			}
			System.out.println(current.getInfo() + " --> //");
		}

		
	}
	
	public int search(int value) {
		
		if(header == null) {
			return -1;
		}else {
			Node current = header;
			int count = 0;
			
			while(current != null) {
				if(current.getInfo() == value) {
					return count;
				}
				current = current.getNext();
				count++;
			}
		}
		return -1;
	}
	
	public void delete(int value) {
		
		int index = search(value);
		
		if(index < 0 || index > size()) {
			System.out.println("Invalid index");
		}else {
			if(index == 0) {
				if(header.getNext() == null) {
					header = null;
				}else {
					Node temp = header;
					header = header.getNext();
					temp.setNext(null);
				}
			}else {
				Node current = header;
				
				for(int i = 0; i < index - 1; i++) {
					current = current.getNext();
				}
				
				Node temp = current.getNext();
				current.setNext(current.getNext().getNext());
				temp.setNext(null);

			}
		}
	}
	
	public void deleteList() {
		
		if(header == null) {
			return;
		}
		else {
			while(header != null) {
				Node temp = header;
				header = header.getNext();
				temp.setNext(null);
			}

		}

	}
	
	public int infoAtIndex(int index) {
		
		if(header == null) {
			return -1;
		}
		
		if(index < 0 || index > size()) {
			return -1;
		}
		
		else {
			Node current = header;
			
			for(int i = 0; i < index; i++) {
				current = current.getNext();
			}	
			return current.getInfo();
		}
	}
			
	
	public static void main(String[] args) {
		
		LinkedList ll = new LinkedList();
		
		ll.add(1);
		ll.add(3);
		ll.add(4);
		ll.add(5);
		ll.add(6);
		ll.print();
		//System.out.println(ll.size());
		//System.out.println(ll.search(2));
		ll.delete(1);
		ll.print();
		//ll.deleteList();
		//ll.print();
		System.out.println(ll.infoAtIndex(1));

	}
	

}
