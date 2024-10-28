import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class EIBUYGIFTS {
	static InputReader reader;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		reader = new InputReader(System.in);
		int numberPeople = reader.nextInt();
		int numberRelationships = reader.nextInt();
		int currentDay = reader.nextInt();
		int targetDay = reader.nextInt();

		Vertex[] persons = readGraph(numberPeople, numberRelationships, currentDay, targetDay);

		for (int i = 0; i < persons.length; i++) {
			int count = 0;
			for (int j = 0; j < persons[i].adjecentVertices.size(); j++) {
				if (persons[i].adjecentVertices.get(j).birthDay - currentDay <= targetDay
						&& persons[i].adjecentVertices.get(j).birthDay - currentDay >= 0) {
					count++;
				}
			}
			sb.append(count);
			sb.append('\n');
		}
		System.out.print(sb);

	}

	static void bfs(Vertex v) {
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.add(v);
		v.visited = true;
		sb.append(v.id + " ");
		while (!q.isEmpty()) {
			Vertex current = q.poll();
			for (Vertex x : current.adjecentVertices) {
				if (!x.visited) {
					x.visited = true;
					sb.append(x.id + " ");
					q.add(x);
				}
			}
		}

	}

	static void dfs(Vertex v) {
		v.visited = true;
		for (int i = 0; i < v.adjecentVertices.size(); i++) {
			Vertex w = v.adjecentVertices.get(i);
			if (!w.visited) {
				dfs(w);
			}
		}
	}

	static Vertex[] readGraph(int nVertices, int nEdges, int currentDay, int targetDay) {
		// int nVertices = reader.nextInt();
		// int nEdges = reader.nextInt();

		// Tạo vòng lặp để đọc các đỉnh vào
		Vertex[] vertices = new Vertex[nVertices];
		for (int i = 0; i < nVertices; i++) {
			Vertex x = new Vertex(i);
			x.birthDay = reader.nextInt();
			vertices[i] = x;
		}

		for (int i = 0; i < nEdges; i++) {
			int idFirst = reader.nextInt();
			int idLast = reader.nextInt();

			vertices[idLast].addAdjecentVertex(vertices[idFirst]);
			vertices[idFirst].addAdjecentVertex(vertices[idLast]);

		}

		// Sap xep cac dinh tu be den lon
		// for (Vertex v : vertices) {
		// Collections.sort(v.adjecentVertices, (v1, v2) -> v1.id - v2.id);
		// }

		return vertices;
	}

	static class Vertex {
		public int id;
		public boolean visited = false;
		public int birthDay;
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
