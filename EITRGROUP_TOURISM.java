import java.io.IOException;
import java.util.*;

public class EITRGROUP_TOURISM {
	static StringBuilder sb = new StringBuilder();
	static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		int nVertices = reader.nextInt();
		int nEdges = reader.nextInt();

		Vertex[] graph = readGraph(nVertices, nEdges);
		for (Vertex v : graph) {
			if (v.isRoot == true) {
				DFS(v, 1);
			}
		}

		System.out.println(sb);
	}

	static void DFS(Vertex inputVertex, int count) {
		inputVertex.visited = true;
		int innerCounter = count;

		for (int i = 0; i < inputVertex.nodeKe.size(); i++) {
			Vertex w = inputVertex.nodeKe.get(i);
			if (w.visited == false) {
				w.visited = true;
				innerCounter++;
				DFS(w, innerCounter);
			}
		}
		if (inputVertex.nodeKe.size() == 0) {
			System.out.print(innerCounter);
		}
	}

	static Vertex[] readGraph(int numNode, int numEdge) {
		Vertex[] result = new Vertex[numNode];
		for (int i = 0; i < numNode; i++) {
			result[i] = new Vertex(i);
		}

		for (int i = 0; i < numEdge; i++) {
			int firsVertex = reader.nextInt();
			int secondVertex = reader.nextInt();

			result[firsVertex].addAdjecentVertex(result[secondVertex]);
		}
		for (Vertex each : result) {

			Collections.sort(each.nodeKe, new Comparator<Vertex>() {
				public int compare(Vertex v1, Vertex v2) {
					return Integer.compare(v1.id, v2.id);
				}
			});
		}

		return result;
	}

	static class Vertex {
		public boolean isRoot;
		public int id;
		public boolean visited;
		public List<Vertex> nodeKe = new ArrayList<>();

		public Vertex(int id) {
			this.id = id;
			isRoot = true;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}

		public List<Vertex> getNodeKe() {
			return nodeKe;
		}

		public void setNodeKe(List<Vertex> nodeKe) {
			this.nodeKe = nodeKe;
		}

		public int getLevel() {
			return nodeKe.size();
		}

		public void addAdjecentVertex(Vertex nodeDuocAdd) {
			nodeKe.add(nodeDuocAdd);
		}

		public boolean isRoot() {
			return isRoot;
		}

		public void setRoot(boolean isRoot) {
			this.isRoot = isRoot;
		}
	}

}
