import java.util.*;

public class treeHeight {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

	}

	static void dfs(Vertex v) {
		v.visited = true;
		for (Vertex w : v.neighbors) {
			if (!w.visited) {
				dfs(w);
			}
		}
	}

	static void bfs(Vertex v) {
		Queue<Vertex> result = new LinkedList<Vertex>();

		
		v.visited = true;
		result.add(v);
		while (!result.isEmpty()) {
			Vertex w = result.poll();
			for (Vertex x : w.neighbors) {
				result.add(x);
				x.visited = true;
			}
		}
	}

	static class Vertex {
		int id;
		ArrayList<Vertex> neighbors = new ArrayList<>();
		boolean visited;

		public Vertex(int id) {
			this.id = id;
			this.visited = false;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public ArrayList<Vertex> getNeighbors() {
			return neighbors;
		}

		public void setNeighbors(ArrayList<Vertex> neighbors) {
			this.neighbors = neighbors;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}

	}

	static Vertex[] readGraph(int nVertices, int nEdges) {
		Vertex[] result = new Vertex[nVertices];
		for (int i = 0; i < nVertices; i++) {
			result[i] = new Vertex(i);
		}

		for (int i = 0; i < nEdges; i++) {
			int first = sc.nextInt();
			int second = sc.nextInt();

			result[first].neighbors.add(result[second]);
			result[second].neighbors.add(result[first]);

		}

		for (Vertex each : result) {
			Collections.sort(each.neighbors, new Comparator<Vertex>() {
				public int compare(Vertex v1, Vertex v2) {
					return Integer.compare(v1.id, v2.id);
				}
			});
		}

		return result;
	}
}