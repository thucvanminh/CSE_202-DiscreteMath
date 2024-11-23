import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class EIMAXHTR {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int nVertices = sc.nextInt();
        int nEdges = sc.nextInt();

    }

    private Vertex[] readGraph(int nVertices, int nEdges) {
        Vertex[] result = new Vertex[nVertices];
        for (int i = 0; i < nVertices; i++) {
            result[i] = new Vertex(i);
        }
        for (int i = 0; i < nEdges; i++) {
            int first = sc.nextInt();
            int second = sc.nextInt();

            result[first].adjancyList.add(result[second]);
            result[second].adjancyList.add(result[first]);
        }
        for (Vertex each : result) {
            Collections.sort(each.adjancyList, new Comparator<Vertex>() {
                @Override
                public int compare(Vertex v1, Vertex v2) {
                    return Integer.compare(v1.id, v2.id);
                }

            });

        }

        return result;
    }

    private static class Vertex {
        private int id;
        private ArrayList<Vertex> adjancyList = new ArrayList<>();
        private boolean visited;

        public Vertex(int id) {
            this.id = id;
            visited = false;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public ArrayList<Vertex> getAdjancyList() {
            return adjancyList;
        }

        public void setAdjancyList(ArrayList<Vertex> adjancyList) {
            this.adjancyList = adjancyList;
        }
    }

    private int dfs(Vertex ver) {
        if (ver == null) {
            return 0;
        }

        int maxHeight = 0;
        ver.visited = true;

        for (int i = 0; i < ver.adjancyList.size(); i++) {
            if (ver.adjancyList.get(i).visited == false) {
                int childHeight = dfs(ver.adjancyList.get(i));
                maxHeight = Math.max(maxHeight, childHeight);
            }
        }
        return maxHeight + 1;

    }
}