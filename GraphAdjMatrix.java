/**
 * GraphAdjMatrix.java
 * 
 * @author Alex Wang
 *
 */
public class GraphAdjMatrix implements Graph {

	private int size;
	private int[][] graph;

	/**
	 * Constructor
	 * 
	 * @param size
	 *            given number of vertices in graph
	 */
	public GraphAdjMatrix(int size) {
		this.size = size;
		graph = new int[size][size];
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
	 * Returns the weight of the edge between vertices v1 and v2. May return 0
	 * if such an edge does not exist
	 */
	@Override
	public int getEdge(int v1, int v2) {
		return graph[v1][v2];
	}

	/**
	 * This function performs the following: Creates the minimum spanning tree
	 * from the source graph. Removes any edges in the graph which are not in
	 * the minimum spanning tree. Returns the weight of the minimum spanning
	 * tree.
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

		int leastCost = Integer.MAX_VALUE;

		int curr = minUnknown(cost, known);// start with minimum unknown vertex
		cost[curr] = 0;
		path[curr] = curr;
		int bestDest = curr;

		for (int k = 0; k < size; k++) {
			curr = minUnknown(cost, known);

			
			while (known[curr] == 0) { 
				// go through all connected unknown vertices
				leastCost = Integer.MAX_VALUE;
				known[curr] = 1;
				for (int m = 0; m < size; m++) {// go through curr's row in
												// matrix
					if (graph[curr][m] > 0) { // there is edge between curr and
												// m
						if (known[m] == 0 && cost[m] > graph[curr][m]) {
							cost[m] = graph[curr][m];// update smallest cost
							path[m] = curr;
						}
						if (graph[curr][m] <= leastCost) {
							leastCost = graph[curr][m];
							bestDest = m;
						}
					}
				}
				curr = bestDest;// to the vertex with least cost
			}
		}

		// remove all the edges which are not on spanning tree
		int[][] spanningTree = new int[size][size];
		for (int p = 0; p < size; p++) {
			for (int q = 0; q < size; q++) {
				if (q == path[p]) {
					spanningTree[p][q] = graph[p][q];
					spanningTree[q][p] = graph[q][p];
				}
			}
		}
		graph = spanningTree;

		// add up the weight in total
		int sum = 0;
		for (int n = 0; n < size; n++) {
			sum = sum + cost[n];
		}
		return sum;
	}

	/**
	 * Finds the next vertex with minimum cost
	 * 
	 * @param cost
	 *            list of cost of vertices
	 * @param known
	 *            list of known about vertices
	 * @return the next vertex to be visited
	 */
	private int minUnknown(int[] cost, int[] known) {
		int nextVertex = 0;
		int minCost = Integer.MAX_VALUE;
		for (int i = 0; i < known.length; i++) {
			if (known[i] == 0 && minCost > cost[known[i]]) {
				minCost = cost[known[i]];
				nextVertex = known[i];
			}
		}
		return nextVertex;
	}
}
