import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EIFOLTRE {
	static InputReader reader;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		reader = new InputReader(System.in);
		int nVertices = reader.nextInt();
		HashMap<String, Vertex> graphs = readGraph(nVertices);
		dfs(graphs.get(reader.next()), 1);
		System.out.println(sb);
	}

	static void dfs(Vertex v, int nDashes) {
		v.visited = true;
		for (int i = 0; i < nDashes; ++i) {
			sb.append("-");
		}
		sb.append(v.id + '\n');

		for (int i = 0; i < v.adjecentVertices.size(); i++) {
			Vertex w = v.adjecentVertices.get(i);
			if (!w.visited) {
				dfs(w, nDashes + 3);
			}
		}
	}

	static HashMap<String, Vertex> readGraph(int nVertices) {
		// int nVertices = reader.nextInt();
		// int nEdges = reader.nextInt();

		// Tạo vòng lặp để đọc các đỉnh vào
		HashMap<String, Vertex> mapVertices = new HashMap<String, Vertex>();

		for (int i = 0; i < nVertices - 1; i++) {
			String u = reader.next();
			String v = reader.next();

			if (mapVertices.get(u) == null) {
				mapVertices.put(u, new Vertex(u));
			}

			if (mapVertices.get(v) == null) {
				mapVertices.put(v, new Vertex(v));
			}

			mapVertices.get(u).addAdjecentVertex(mapVertices.get(v));
			mapVertices.get(v).addAdjecentVertex(mapVertices.get(u));

		}

		// vertices[idFirst].addAdjecentVertex(vertices[idLast]);
		// }

		// Sap xep cac dinh tu be den lon
		for (Vertex v : mapVertices.values()) {
			Collections.sort(v.adjecentVertices, (v1, v2) -> v1.id.compareToIgnoreCase(v2.id));
		}

		return mapVertices;
	}

	static class Vertex {
		public String id;
		public boolean visited = false;
		public List<Vertex> adjecentVertices = new ArrayList<>();

		public Vertex(String id) {
			this.id = id;
		}

		public List<Vertex> getAdjecentVertices() {
			return adjecentVertices;
		}

		public void addAdjecentVertex(Vertex vertice) {
			adjecentVertices.add(vertice);
		}

		public int getDegree() {
			return adjecentVertices.size();
		}

		@Override
		public String toString() {
			return id + "";
		}

		public String getId() {
			return id;
		}

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
