// add edge
// remove edge
// isAdjacent
// DFS
// BFS
// compute degree of a vertex
// vertex with max degree
// number of self loops
// Sum of all degrees
// average of all degrees


package adjacencyMatrix;

import java.util.Arrays;

import dataStructures.Queue;
import dataStructures.Stack;

public class AdjacencyMatrix {
	
	private int[][] graph;
	
	public AdjacencyMatrix(int vertexNum) {
		graph = new int[vertexNum][vertexNum];
	}
	
	public void addEdge(int vertex1, int vertex2) {
		graph[vertex1][vertex2] = 1;
		graph[vertex2][vertex1] = 1;
	}
	
	public void deleteEdge(int vertex1, int vertex2) {
		graph[vertex1][vertex2] = 0;
		graph[vertex2][vertex1] = 0;
	}
	
	public boolean isAdjacent(int vertex1, int vertex2) {
		
		if(graph[vertex1][vertex2] == 1 && graph[vertex2][vertex1] == 1) {
			return true;
		}
		return false;
	}
	
	public int allVisited(boolean[] array) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == false) {
				return i;
			}
		}
		return -1;
	}
	
	public void print() {
		System.out.print("  ");
		for(int i = 0; i < graph.length; i++) {
			System.out.print(" " + i);
			System.out.print(" ");
		}
		System.out.println();
		for(int i = 0; i < graph.length; i++) {
			System.out.print(i + " ");
			System.out.println(Arrays.toString(graph[i]));
		}
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
						if(isAdjacent(u, i)) {
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
		
		for(int i = 0; i < graph.length; i++) {
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
		
		int count = 0;
		for(int i = 0; i < graph[vertex].length; i++) {
			if(graph[vertex][i] == 1) {
				count++;
			}
		}
		return count;
	}
	
	public int maxDegree() {
		int max = 0;
		int count = 0;
		int index = 0;
		for(int i = 0; i < graph.length; i++) {
			count = 0;
			for(int j = 0; j < graph[i].length; j++) {
				if(graph[i][j] == 1) {
					count++;
				}
			}
			if(max < count) {
				max = count;
				index = i;
			}
		}
		return index;
	}
	
	public int selfLoops() {
		int count = 0;
		for(int i = 0; i < graph.length; i++) {
			if(graph[i][i] == 1) {
				count++;
			}
			
		}
		return count;
	}
	
	public int sumDegrees() {
		int sum = 0;
		for(int i = 0; i < graph.length; i++) {
			for(int j = 0; j < graph.length; j++) {
				if(graph[i][j] == 1) {
					sum++;
				}
			}
		}
		return sum;
	}
	
	public int avgDegrees() {
		int sum = sumDegrees();
		return sum/graph.length;
	}
	
	public static void main(String[] args) {
		
		AdjacencyMatrix am = new AdjacencyMatrix(5);		
		am.addEdge(0, 1);
		am.addEdge(0, 2);
		am.addEdge(0, 3);
		am.addEdge(2, 3);
		am.addEdge(2, 4);
		am.addEdge(3, 4);
		am.print();	
		System.out.println();
		am.DFS(0);
		System.out.println();
		am.BFS(0);
		System.out.println();
		System.out.println(am.computeDegree(4));
		System.out.println(am.maxDegree());
		System.out.println(am.selfLoops());
		System.out.println(am.sumDegrees());
		System.out.println(am.avgDegrees());
	}

}
