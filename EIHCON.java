import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EIHCON {
	static InputReader reader;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		reader = new InputReader(System.in);
		int nVertices = reader.nextInt();
		int nEdges = reader.nextInt();
		int nAsk = reader.nextInt();

		Vertex[] graph = readGraph(nVertices, nEdges);

		for (int i = 0; i < nAsk; i++) {
			int idFirst = reader.nextInt();
			int idLast = reader.nextInt();

			sb.append(findIfAdjency(graph, idFirst, idLast));
			if (i < nAsk - 1) {
				sb.append('\n');
			}

		}
		System.out.println(sb);

	}

	static String findIfAdjency(Vertex[] graph, int idFirst, int idLast) {
		for (int j = 0; j < graph[idFirst].getDegree(); j++) {
			int currentID = graph[idFirst].getAdjecentVertices().get(j).getId();
			if (currentID  == idLast) {
				return "Y";
			} else {
				if(checkAgain(graph,currentID, idLast)){
					return "Y";
				}
			}
		}
		return "N";
	}
	static boolean checkAgain(Vertex[] graph, int idFirst, int idLast) {
		for (int j = 0; j < graph[idFirst].getDegree(); j++) {
			if (graph[idFirst].getAdjecentVertices().get(j).getId() == idLast) {
				return true;
			} else {
			}
		}
		return false;
	}

	static Vertex[] readGraph(int nVertices, int nEdges) {
		// int nVertices = reader.nextInt();
		// int nEdges = reader.nextInt();

		// Tạo vòng lặp để đọc các đỉnh vào
		Vertex[] vertices = new Vertex[nVertices + 1];
		for (int i = 1; i <= nVertices; i++) {
			vertices[i] = new Vertex(i);
		}

		for (int i = 0; i < nEdges; i++) {
			int idLast = reader.nextInt();
			int idFirst = reader.nextInt();

			vertices[idFirst].addAdjecentVertex(vertices[idLast]);
			// vertices[idLast].addAdjecentVertex(vertices[idFirst]);
		}
		return vertices;
	}

	static class Vertex {
		public int id;
		public List<Vertex> adjecentVertices = new ArrayList<>();

		public Vertex(int id) {
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

		public int getId() {
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
