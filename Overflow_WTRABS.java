import java.util.*;

public class Overflow_WTRABS {
	static Scanner sc = new Scanner(System.in);

	static class Vertex {
		int id;
		double weight;
		ArrayList<Vertex> adjacentList = new ArrayList<>();

		public Vertex(int id, double weight) {
			this.id = id;
			this.weight = weight;
		}

		public Vertex(int id) {
			this.id = id;
		}

		public void addAdjacentEdges(Vertex v) {
		}
	}

	static Vertex[] readGraph() {
		int nVertices = sc.nextInt();
		Vertex[] vertices = new Vertex[nVertices];

		for (int i = 0; i < nVertices; i++) {
			double weight = sc.nextDouble();
			vertices[i] = new Vertex(i, weight);
		}

		for (int i = 0; i < nVertices - 1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			vertices[a].addAdjacentEdges(vertices[b]);
			vertices[b].addAdjacentEdges(vertices[a]);
		}
		for (Vertex each : vertices) {
			Collections.sort(each.adjacentList, (v1, v2) -> v1.id - v2.id);
		}

		return vertices;
	}
}
