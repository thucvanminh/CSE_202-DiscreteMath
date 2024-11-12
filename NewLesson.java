import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class NewLesson {
	static InputReader reader;

	public static void main(String[] args) throws IOException {
		Vertex[] graph = readGraph();
		findLongestPath(graph);

		

	}

	static void findLongestPath(Vertex[] tree) {
		int max = 0;
		for (Vertex each : tree) {
			if (each.distance > max) {
				max = each.distance;
			}
		}
		System.out.println(max);
	}

	static class Edge {
		private int cost;
		private Vertex endPoint;

		public Edge(int cost, NewLesson.Vertex endPoint) {
			this.cost = cost;
			this.endPoint = endPoint;
		}

		public int getCost() {
			return cost;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}

		public Vertex getEndPoint() {
			return endPoint;
		}

		public void setEndPoint(Vertex endPoint) {
			this.endPoint = endPoint;
		}

	}

	static class Vertex {
		private int id;
		private int distance;
		private List<Edge> listEdges = new ArrayList<Edge>();

		public Vertex(int id) {
			this.id = id;
			this.distance = 0;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getDistance() {
			return distance;
		}

		public void setDistance(int oldDistance, int cost) {
			this.distance = oldDistance + cost;
		}

		public void addEdge(int cost, Vertex vertex) {
			Edge edge = new Edge(cost, vertex);
			listEdges.add(edge);
		}

	}

	static Vertex[] readGraph() {

		int nVertices = reader.nextInt();

		// Tao ra mảng để lưu các đỉnh và khởi tạo các đỉnh
		Vertex[] vertices = new Vertex[nVertices];
		for (int i = 0; i < nVertices; ++i) {
			vertices[i] = new Vertex(i);
		}

		// Doc lần lượt các cạnh
		for (int i = 0; i < nVertices - 1; ++i) {
			int start = reader.nextInt();
			int end = reader.nextInt();
			int cost = reader.nextInt();

			// Đồ thị vô hướng nên cạnh a-b nghĩa là: a kề của b,
			// b kề của a
			vertices[start].addEdge(cost, vertices[end]);
			vertices[end].setDistance(vertices[start].getDistance(), cost);

		}

		return vertices;
	}

	static class InputReader {
		private byte[] inbuf = new byte[2 << 23];
		public int lenbuf = 0, ptrbuf = 0;
		public InputStream is;

		public InputReader(InputStream stream) throws IOException {

			inbuf = new byte[2 << 23];
			lenbuf = 0;
			ptrbuf = 0;
			is = System.in;
			lenbuf = is.read(inbuf);
		}

		public InputReader(FileInputStream stream) throws IOException {
			inbuf = new byte[2 << 23];
			lenbuf = 0;
			ptrbuf = 0;
			is = stream;
			lenbuf = is.read(inbuf);
		}

		public boolean hasNext() throws IOException {
			if (skip() >= 0) {
				ptrbuf--;
				return true;
			}
			return false;
		}

		public String nextLine() throws IOException {
			int b = skip();
			StringBuilder sb = new StringBuilder();
			while (!isSpaceChar(b) && b != ' ') { // when nextLine, ()
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}

		public String next() {
			int b = skip();
			StringBuilder sb = new StringBuilder();
			while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b
										// != ' ')
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}

		private int readByte() {
			if (lenbuf == -1)
				throw new InputMismatchException();
			if (ptrbuf >= lenbuf) {
				ptrbuf = 0;
				try {
					lenbuf = is.read(inbuf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (lenbuf <= 0)
					return -1;
			}
			return inbuf[ptrbuf++];
		}

		private boolean isSpaceChar(int c) {
			return !(c >= 33 && c <= 126);
		}

		private double nextDouble() {
			return Double.parseDouble(next());
		}

		public Character nextChar() {
			return skip() >= 0 ? (char) skip() : null;
		}

		private int skip() {
			int b;
			while ((b = readByte()) != -1 && isSpaceChar(b))
				;
			return b;
		}

		public int nextInt() {
			int num = 0, b;
			boolean minus = false;
			while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
				;
			if (b == '-') {
				minus = true;
				b = readByte();
			}

			while (true) {
				if (b >= '0' && b <= '9') {
					num = num * 10 + (b - '0');
				} else {
					return minus ? -num : num;
				}
				b = readByte();
			}
		}

		public long nextLong() {
			long num = 0;
			int b;
			boolean minus = false;
			while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
				;
			if (b == '-') {
				minus = true;
				b = readByte();
			}

			while (true) {
				if (b >= '0' && b <= '9') {
					num = num * 10 + (b - '0');
				} else {
					return minus ? -num : num;
				}
				b = readByte();
			}
		}
	}

}
