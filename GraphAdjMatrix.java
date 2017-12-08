/**
 * GraphAdjMatrix.java
 * @author Alex Wang
 *
 */
public class GraphAdjMatrix implements Graph {

	private int size;
	private int[][] graph;


	/**
	 * Constructor
	 * @param size
	 * 			given number of vertices in graph
	 */
	public GraphAdjMatrix(int size) {
		this.size = size;
		graph = new int[size][size];
		for (int i=0; i < size; i++){
			for(int j =0; j < size; j++){
				graph[i][j] = -1;
			}
		}
	}

	@Override
	public void addEdge(int v1, int v2) {
		// for PA08
	}

	@Override
	public void topologicalSort() {
		// for PA08
	}

	/**
	 * Adds an undirected edge with weight w between two vertices
	 */
	@Override
	public void addEdge(int v1, int v2, int weight) {
		graph[v1][v2] = weight;
		graph[v2][v1] = weight;
	}


	/**
	 * Returns the weight of the edge between vertices v1 and v2. 
	 * May return 0 or a negative number if such an edge does not exist
	 */
	@Override
	public int getEdge(int v1, int v2) {
		return graph[v1][v2];
	}

	/**
	 * This function performs the following:
	 * Creates the minimum spanning tree from the source graph.
	 * Removes any edges in the graph which are not in the minimum spanning tree.
	 * Returns the weight of the minimum spanning tree.
	 */
	@Override
	public int createSpanningTree() {
		int[] known = new int[size];
		int[] path = new int[size];

		for (int i = 0; i < size; i++) {
			path[i] = -1;
		}
		int[] cost = new int[size];
		for (int j = 0; j < size; j++) {
			cost[j] = Integer.MAX_VALUE;
		}
		/* initialization done */

		cost[0] =0;//TODO hardcoded?
		for (int k = 0; k < size; k++) {

			if (known[k] == 0) { //this vertex not known
				int m;
				known[k] = 1;
				for (m = 0; m < size; m++) {//go through k's row in matrix
					if (graph[k][m] > 0) { // there is edge between k and m
						if (known[m] == 0 && cost[m] > graph[k][m]) {
							cost[m] = graph[k][m];// update smallest cost
							path[m] = k;
						}
					}
				}

				/*Removes any edges in the graph which are 
				 * not in the minimum spanning tree*/
				for(m = 0; m < size; m++){
					if (graph[k][m] != cost[m]){
						graph[k][m] = -1; 
					}
				}
				
			}
		}

		int sum = 0;
		for (int n = 0; n < size; n++) {
			sum = sum + cost[n]; //add up the weight in total
		}
		return sum;
	}

}
