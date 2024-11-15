import java.util.*;

public class Test {
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		doAction();
	}

	private static void doAction() {
		int testCase = sc.nextInt();
		for (int i = 0; i < testCase; i++) {
			int nVertices = sc.nextInt();
			int nEdge = sc.nextInt();
			Vertex[] graph = readGraph(nVertices, nEdge);

			int nCount = 0;
			for (int j = 0; j < graph.length; j++) {
				if (!graph[j].visited) {
					dfs(graph[j]);
					nCount++;
				}
			}

			if (nCount == 1 && nVertices == nEdge + 1) {
				sb.append("YES").append('\n');
			} else {
				sb.append("NO").append('\n');
			}

		}
		System.out.println(sb);

	}

	static void dfs(Vertex inputVertex) {
		inputVertex.visited = true;

		for (Vertex each : inputVertex.adjacentVertices) {
			if (!each.visited) {
				dfs(each);
			}
		}
	}

	static Vertex[] readGraph(int nVertices, int nEdge) {
		Vertex[] graph = new Vertex[nVertices];

		for (int i = 0; i < nVertices; i++) {
			graph[i] = new Vertex(i);
		}

		for (int i = 0; i < nEdge; i++) {
			int first = sc.nextInt();
			int second = sc.nextInt();

			graph[first].addVertex(graph[second]);
			graph[second].addVertex(graph[first]);
		}

		for (Vertex each : graph) {
			Collections.sort(each.adjacentVertices, new Comparator<Vertex>() {
				public int compare(Vertex v1, Vertex v2) {
					return Integer.compare(v1.id, v2.id);
				}
			});
		}

		return graph;
	}

	static class Vertex {
		int id;
		List<Vertex> adjacentVertices = new ArrayList<Vertex>();
		boolean visited;

		// Constructor
		public Vertex(int id) {
			this.id = id;
		}

		void addVertex(Vertex ver) {
			adjacentVertices.add(ver);
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public List<Vertex> getAdjacentVertices() {
			return adjacentVertices;
		}

		public void setAdjacentVertices(List<Vertex> adjacentVertices) {
			this.adjacentVertices = adjacentVertices;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}

		//
		int getLevel() {
			return adjacentVertices.size();
		}
	}

}