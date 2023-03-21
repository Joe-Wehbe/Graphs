package adjacencyList;

public class Node {
	
	private int info;
	private Node next;
	
	public Node(int info) {
		this.info = info;
		next = null;
	}

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
	

}
