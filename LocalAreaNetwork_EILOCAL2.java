import java.util.*;

public class LocalAreaNetwork_EILOCAL2 {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

	}

	static class Vertex {
		public int id;
		public List<Vertex> adjecentVertices = new ArrayList<>();

		public Vertex(int id) {
			this.id = id;
		}
	}

	static Vertex[] readGraph() {
		int nVertices = sc.nextInt();
		Vertex[] vertices = new Vertex[nVertices];
		for (int i = 0; i < nVertices; i++) {
			vertices[i] = new Vertex(i);
		}
		int m = sc.nextInt();
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			vertices[a].adjecentVertices.add(vertices[b]);
			vertices[b].adjecentVertices.add(vertices[a]);
		}

		for (Vertex each : vertices) {
			Collections.sort(each.adjecentVertices, new Comparator<Vertex>() {
				public int compare(Vertex v1, Vertex v2) {
					return Integer.compare(v1.id, v2.id);
				}
			});
		}
		return vertices;
	}
}
