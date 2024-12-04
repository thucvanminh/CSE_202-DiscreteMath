import java.util.*;

public class LocalAreaNetwork_EILOCAL2 {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

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
		for (int i = 0; i < v.adjecentEdges.size(); i++) {

		}
	}

	static Vertex[] readGraph() {
		int nVertices = sc.nextInt();
		int nEdges = nVertices - 1;
		Vertex[] vertices = new Vertex[nVertices];
		for (int i = 0; i < nVertices; i++) {
			vertices[i] = new Vertex(i);
		}
		int m = sc.nextInt();
		for (int i = 0; i < m; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();

			vertices[from].addAdjacentEdges(vertices[to], weight);
			vertices[to].addAdjacentEdges(vertices[from], weight);

		}

		
		return vertices;
	}
}
