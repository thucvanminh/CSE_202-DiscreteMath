import java.util.*;

public class LocalAreaNetwork_EILOCAL2 {
	static Scanner sc = new Scanner(System.in);
	StringBuilder sb = new StringBuilder();


	public static void main(String[] args) {
		Vertex[] graph = readGraph();
		dfs(graph[0]);
		int maxDistance = -1;
		for(Vertex n: graph){
			if(n.distance > maxDistance){
				maxDistance = n.distance;
			}
		}
		System.out.println(maxDistance);

	}

	static class Vertex {
		public boolean visited;
		public int distance;
		public int id;
		public List<Edge> adjecentEdges = new ArrayList<>();

		public Vertex(int id) {
			this.id = id;
			this.distance = 0;
		}

		public void addAdjacentEdges(Vertex v, int weight) {
			
			adjecentEdges.add(new Edge(v, weight));
		}
	}

	static class Edge {
		public int weight;
		public Vertex endpoint;

		public Edge(Vertex endpoint, int weight) {
			this.weight = weight;
			this.endpoint = endpoint;
		}
	}

	static void dfs(Vertex v) {
		v.visited = true;
		for(Edge each : v.adjecentEdges) {
			if(each.endpoint.visited == false) {
				each.endpoint.distance = each.weight + v.distance;
				dfs(each.endpoint);
			}

		}
	}

	static Vertex[] readGraph() {
		int nVertices = sc.nextInt();
		int nEdges = nVertices - 1;
		Vertex[] vertices = new Vertex[nVertices];
		for (int i = 0; i < nVertices; i++) {
			vertices[i] = new Vertex(i);
		}
	
		for (int i = 0; i < nEdges; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();

			vertices[from].addAdjacentEdges(vertices[to], weight);
			vertices[to].addAdjacentEdges(vertices[from], weight);

		}

		
		return vertices;
	}
}
