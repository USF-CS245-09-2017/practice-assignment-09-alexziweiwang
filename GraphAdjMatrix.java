public class GraphAdjMatrix implements Graph {

	private int size;
	private int[][] graph;
	
	public GraphAdjMatrix(int size){
		this.size = size;
		graph = new int[size][size];
	}
	
	@Override
	public void addEdge(int v1, int v2) {
		//undirected 
		graph[v1][v2] = 0;
		graph[v2][v1] = 0;
	}

	@Override
	public void topologicalSort() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addEdge(int v1, int v2, int weight) {
		graph[v1][v2] = weight;
		graph[v2][v1] = weight;
	}

	@Override
	public int getEdge(int v1, int v2) {
		return graph[v1][v2];
	}

	@Override
	public int createSpanningTree() {
		int[] known = new int[size];
		int[] path = new int[size];
		for(int i=0; i <size; i++){
			path[i] = -1;
		}
		int[] cost = new int[size];
		for(int j = 0; j <size; j++){
			cost[j] = 1000000;
		}
		/*initialization done*/
		
		cost[0] =0;
		for(int k=0; k< size; k++){
			
			if(known[k] ==0){
				known[k] =1;
				
				for(int m=0; m<size; m++){
					if(graph[k][m] > 0){ //there is edge between curr and m
			
						if(known[m] ==0 && cost[m] > graph[k][m]){
							cost[m] = graph[k][m];//update smallest cost
							path[m] = k;
						}
					}
				}
			}
		}
		int sum = 0;
		for(int n=0; n < size ; n++){
			sum = sum + cost[n];
		}
		return sum;
	}
	
}
