import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class EIMAXHTR {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int nVertices = sc.nextInt();
        Vertex[] graph = readGraph(nVertices, nVertices - 1);

        int minVertex = -1, maxHeight = -1;
        graph[0].level = 0;
        //Thử tính chiều cao của cây với đỉnh i làm gốc
       

        for (int i = 0; i < nVertices; i++) {
            int height = findHeight(graph, graph[i]);
            if (height > maxHeight) {
                minVertex = i;
                maxHeight = height;
            }
        }

        System.out.println(minVertex + " " + maxHeight);
    }

    private static Vertex[] readGraph(int nVertices, int nEdges) {
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
                    return Integer.compare(v1.getId(), v2.getId());
                }
            });

        }

        return result;
    }

    private static class Vertex {
        private int level;
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

    private static void dfs(Vertex ver) {
        ver.visited = true;
        


        for (int i = 0; i < ver.adjancyList.size(); i++) {
            if (ver.adjancyList.get(i).visited == false) {
                ver.adjancyList.get(i).level = ver.level + 1;
                dfs(ver.adjancyList.get(i));

            }
        }

    }

    private static int findHeight(Vertex[] graph, Vertex root) {
        dfs(root);

        int maxLevel = 0;
        for (int i = 0; i < graph.length; i++) {
            if (graph[i].level > maxLevel) {
                maxLevel = graph[i].level;
            }
            graph[i].visited = false;
            graph[i].level = 0;
        }

        return maxLevel;
    }
}
