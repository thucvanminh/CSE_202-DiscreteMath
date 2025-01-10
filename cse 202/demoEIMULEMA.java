import java.util.*;

public class demoEIMULEMA {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public void dfs(Vertex node) {
		node.visited = true;
		node.commision = node.sale * 0.15;

		for (Vertex each : node.adjList) {
			dfs(each);
			node.commision += Math.floor(each.commision / 2);
		}
	}
	static class Vertex {
		int id;
		int level = 0;
		double sale;
		double commision;
		boolean visited;
		List<Vertex> adjList = new ArrayList<>();

		public Vertex(int id, double sale) {
			this.id = id;
			this.sale = sale;
		}

		void addAdjList(Vertex v) {
			adjList.add(v);
		}
	}
}
