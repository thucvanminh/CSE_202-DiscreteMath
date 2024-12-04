import java.util.*;

public class Overflow_WTRABS {
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Vertex[] graph = readGraph();
		dfs(graph[0]);
		for (Vertex each : graph) {
			if (each.weight != 0) {
				sb.append(each.id + " " + each.weight + '\n');
			}
		}
		System.out.println(sb);
	}

	static class Vertex {
		boolean visited;
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

		public void addAdjacentList(Vertex v) {
			this.adjacentList.add(v);
		}
	}

	static void dfs(Vertex v) {
		v.visited = true;

		if (v.adjacentList.size() > 0) {
			double transferedWater = v.weight / v.adjacentList.size();
			for (Vertex w : v.adjacentList) {
				if (!w.visited) {
					w.weight += transferedWater;
					dfs(w);
				}
			}
			v.weight = 0;
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

			vertices[b].addAdjacentList(vertices[a]);
		}
		for (Vertex each : vertices) {
			Collections.sort(each.adjacentList, (v1, v2) -> v1.id - v2.id);
		}

		return vertices;
	}
}
