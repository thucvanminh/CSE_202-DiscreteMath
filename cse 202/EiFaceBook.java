import java.io.*;
import java.util.*;
public class EiFaceBook {

        static InputReader reader;
        static StringBuilder sb = new StringBuilder();
    
        public static void main(String[] args) throws IOException {
            reader = new InputReader(System.in);
            int nVertices = reader.nextInt();
            int nEdges = reader.nextInt();

            Vertex[] graph = readGraph(nVertices, nEdges);
            for(int i =1;i<=nVertices; i++){
                int count =0;
                for(Vertex j: graph[i].adjacentVertices){
                    if (!graph[i].gender.equals(j.gender)){
                        count++;
                    }
                }
                
                sb.append(count+" ");
            }
            System.out.println(sb);
        }

        static Vertex[] readGraph(int nVertice, int nEdge){
            int nVertices = nVertice;
            int nEdges = nEdge;
            
            Vertex[] vertices = new Vertex[nVertices+1 ];
            for(int i = 1; i<=nVertices; ++i){
                String gender = reader.next();
                vertices[i] = new Vertex(i, gender);
            }

            for(int i = 0 ; i<nEdges; ++i){
                int a = reader.nextInt();
                int b = reader.nextInt();

                vertices[a].addAdjacentVertices(vertices[b]);
                vertices[b].addAdjacentVertices(vertices[a]);
            }

            return vertices;
        }

        static class Vertex {
            public int id;
            public String gender;
            public Set<Vertex> adjacentVertices = new HashSet<Vertex>();

            public Vertex(int id, String gender) {
                this.id = id; 
                this.gender = gender;
               }

            public void addAdjacentVertices(Vertex vertex){
                adjacentVertices.add(vertex);
            }
            @Override
        public boolean equals(Object obj) {
            if (obj instanceof Vertex) {
                return ((Vertex) obj).id == id;
            }
            return false;
        }

        @Override
        public int hashCode() {
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
