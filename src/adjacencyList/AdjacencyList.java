package adjacencyList;

import dataStructures.Stack;

import java.util.ArrayList;

import dataStructures.Queue;

public class AdjacencyList {
	
	private LinkedList[] graph;
	
	public AdjacencyList(int numVertices) {
		
		graph = new LinkedList[numVertices];
		
		for(int i = 0; i < graph.length; i++) {
			graph[i] = new LinkedList();
		}
	}
	
	public void addEdge(int vertex1, int vertex2) {
		graph[vertex1].add(vertex2);
		graph[vertex2].add(vertex1);
	}
	
	public void deleteEdge(int vertex1, int vertex2) {
		graph[vertex1].delete(vertex2);
		graph[vertex2].delete(vertex1);
	}
	
	public boolean isAdjacent(int vertex1, int vertex2) {
		int exists1 = graph[vertex1].search(vertex2);
		int exists2 = graph[vertex2].search(vertex1);
		
		if(exists1 != -1 && exists2 != -1) {
			return true;
		}
		return false;
	}
	
	
	public void printGraph() {
		
		if(graph.length == 0) {
			System.out.println("--> //");
		}
		else {
			for(int i = 0; i < graph.length; i++) {
				System.out.print(i + " --> ");
				graph[i].print();
			}
		}	
		
	}
	
	public int allVisited(boolean[] array) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == false) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	public int DFSforAP(int sourceVertex) {
		/* Method that traverses the graph, it allows multiple
		 copies of a vertex in the stack. It takes a parameter
		 of type char and the return type is void.
		 */
		
		boolean[] visited = new boolean[graph.length]; // array that stores visited and unvisited vertices
		
		// Initializing the visited[] array 
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false; // all vertices are unvisited at the beginning
		}
	
		Stack s = new Stack(); // creating new stack
		s.push(sourceVertex); // traversal starts with the source vertex, we push it
		
		int components = 1;
		int checking = 0; // used to store what the method checkIfAllVisited() gives
		
		
		while (checking != -1) { // while there are still vertices that are not visited (2 components or more)		
			//System.out.print("C" + components + ": "); // numbering the components
			while (!s.isEmpty()) {					
				int u = s.pop();	
				
				// marking the  current vertex as visited if it is not
				if (!visited[u]) {
					visited[u] = true;
					
					/* checking for adjacent vertices to the current vertex and adding them
					   to the stack if they are not visited.
					 */
					for (int i = 0; i < graph.length; i++) {
						if (isAdjacent(u, i)) {
							if (!visited[i]) {
								s.push(i);
							}
						}
					}			
				}
			}
			// To repeat the above process in case there is 2 or more components
			checking = allVisited(visited); // checking if there is an unvisited vertex
			if (checking != -1) { // in case there exist
				s.push(checking); // we repeat the traversal from the unvisited vertex
				components++;

			}
		}
		return components;
	}
	
	public ArrayList<Integer> articulationPoints(AdjacencyList al) {

		ArrayList<Integer> ap = new ArrayList<>();

		for(int i = 0; i < graph.length; i++) {	
			
			int[] deletedEdges = new int[graph[i].size()];
			int pointer = 0;
			int info = 0;
			
			int listSize = graph[i].size();

			for(int j = 0;  j < listSize; j++) {
								
				info = graph[i].infoAtIndex(0);
	
				if(info != -1) {
					deletedEdges[pointer] = info;
					pointer++;

					deleteEdge(i, info);
					
				}			
			}
			
			int numComponents = DFSforAP(0);	

			if(numComponents >= 3) {
				ap.add(i);
			}
			
			for(int k = 0; k < deletedEdges.length; k++) {
				addEdge(i, deletedEdges[k]);
			}
									
		}
		return ap;
		

	}
	
	public void DFS(int sourceVertex) {
		
		boolean[] visited = new boolean[graph.length];
		int[] visitedOrder = new int[graph.length];
		
		for(int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}	
		
		Stack s = new Stack();
		s.push(sourceVertex);
		
		int count = 0;
		int checking = 0;
		
		while(checking != -1) {
			while(!s.isEmpty()) {
				
				int u = s.pop();

				if(!visited[u]) {
					visited[u] = true;

					visitedOrder[count] = u;
					count++;
					
					for(int i = 0; i < graph.length; i++) {
						if(isAdjacent(u,i)) {
							if(!visited[i]) {
								s.push(i);

							}
							
						}
					}
										
				}

			}
			checking = allVisited(visited);
			if(checking != -1) {
				s.push(checking);
			}
			
		}
		System.out.print("Result of the DFS: ");
		for(int i = 0; i < visitedOrder.length; i++) {
			System.out.print(visitedOrder[i]);
		}
	}
	
	public void BFS(int sourceVertex) {
		
		boolean[] visited = new boolean[graph.length];
		int[] visitedOrder = new int[graph.length];
		
		for(int i = 0; i < visitedOrder.length; i++) {
			visited[i] = false;
		}
		
		Queue q = new Queue();
		q.enqueue(sourceVertex);
		
		int count = 0;
		int checking = 0;
		
		while(checking != -1) {
			while(!q.isEmpty()) {
				
				int u = q.dequeue();
				
				if(!visited[u]) {
					visited[u] = true;
					
					visitedOrder[count] = u;
					count++;
					
					for(int i = 0; i < graph.length; i++) {
						if(isAdjacent(u, i)) {
							if(!visited[i]) {
								q.enqueue(i);
							}
						}
					}
				}
			}
			checking = allVisited(visited);
			if(checking != -1) {
				q.enqueue(checking);
			}
		}
		System.out.print("Result of the BFS: ");
		for(int i = 0; i < visitedOrder.length; i++) {
			System.out.print(visitedOrder[i]);
		}
	}
	
	public int computeDegree(int vertex) {
		return graph[vertex].size();
	}
	
	public int maxDegree(){
		
		int max = 0;
		int index = 0;
		
		for(int i = 0; i < graph.length; i++) {
			if(max <= graph[i].size()) {
				max = graph[i].size();
				index = i;
			}
		}
		return index;
	}
	
	public int selfLoops() {
		int count = 0;
		for(int i = 0; i < graph.length; i++) {
			int searching = graph[i].search(i);
			
			if(searching != -1) {
				count++;
			}
		}
		return count;
	}
	
	public int sumDegrees() {
		
		int sum = 0;
		for(int i = 0; i < graph.length; i++) {
			 sum = sum + graph[i].size();
		}
		return sum;
	}
	
	public int avgDegree() {
		
		int sum = sumDegrees();
		int avg = sum/graph.length;
		return avg;
	}
	
	public static void main(String[] args) {
		
		AdjacencyList al = new AdjacencyList(9);
		
		al.addEdge(0, 1);
		al.addEdge(0, 2);
		al.addEdge(1, 2);
		al.addEdge(2, 3);
		al.addEdge(3, 4);
		al.addEdge(2, 5);
		al.addEdge(5, 6);
		al.addEdge(5, 8);
		al.addEdge(6, 7);
		al.addEdge(7, 8);

		
		al.printGraph();
		System.out.println();
		
		al.DFS(0);
		System.out.println();
		System.out.println();
		
		System.out.println("Articulation points: " + al.articulationPoints(al));

		
//		System.out.println();
//		al.BFS(0);
//		System.out.println();
//		System.out.println(al.computeDegree(4));
//		System.out.println(al.maxDegree());
//		System.out.println(al.selfLoops());
//		System.out.println(al.sumDegrees());
//		System.out.println(al.avgDegree());

	}

}
