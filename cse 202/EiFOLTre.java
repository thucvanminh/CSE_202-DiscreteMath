import java.io.*;
import java.util.*;
public class EiFOLTre {

        static InputReader reader;
        static StringBuilder sb = new StringBuilder();
    
        public static void main(String[] args) throws IOException {
            reader = new InputReader(System.in);
            HashMap<String,Vertex> graph = readGraph();
            dfs(graph.get(reader.next()), 1);
            System.out.println(sb);
        }

        static void dfs(Vertex v, int dash){
            v.visited = true;
            for (int i = 0; i < dash; i++) {
                sb.append("-");
            }
            sb.append(v.id+"\n");
            for(Vertex w: v.adjacentVertices){
                if(w.visited == false){
                    dfs(w, dash+3);
                }
            }
        }

        static HashMap<String,Vertex> readGraph(){
            int nVertices = reader.nextInt();
            
            HashMap<String, Vertex> vertices = new HashMap<>();

            for(int i = 0 ; i<nVertices-1; ++i){
                String a = reader.next();
                String b = reader.next();

                Vertex va = vertices.get(a);
                if(va== null){
                    va = new Vertex(a);
                }
                vertices.put(a, va);

                Vertex vb = vertices.get(b);
                if(vb== null){
                    vb = new Vertex(b);
                }
                vertices.put(b, vb);
                vertices.get(a).addAdjacentVertices(vertices.get(b));
                vertices.get(b).addAdjacentVertices(vertices.get(a));
            }

            for(Vertex v: vertices.values()){
                Collections.sort(v.adjacentVertices,(v1,v2)-> v1.id.compareToIgnoreCase(v2.id));
            }
            return vertices;
        }

        static class Vertex {
            public String id;
            public boolean visited;
            public List<Vertex> adjacentVertices = new ArrayList<Vertex>();

            public Vertex(String id) {
                this.id = id;    }

            public void addAdjacentVertices(Vertex vertex){
                adjacentVertices.add(vertex);
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
