// Bruteforce : rút cạn
// Thử từng đỉnh từ 1-->n
//  	Nếu đỉnh đó có khác có khác 2 nhánh (skip)
//  	Nếu đỉnh đó có đúng 2 nhánh:
//  		-Tính chênh lệch hai nhánh
//  		- Tính tổng trọng số từng nhánh
//  			-> đánh số đỉnh i là visited
//  			-> lấy đỉnh kề tại 0 là dfs
//  			-> lấy đỉnh kề tại 1 là dfs
//  		-So sanhs chênh lệch của hai nhánh với chênh lệch min


// Cách 2:

// 	b1: tính tổng trọng số các đỉnh của đồ thị T
// 	b2: dfs từ một đỉnh bất kì
// 	b3: Nếu đỉnh được duyệt có 2 con
// 			-> dfs 1 nhánh để tìm đc tổng trọng số 1 nhánh T1, 
// 			sau đó lấy tổng trọng số của cả đồ thị T - T1, 
// 			sau đó tính tổng trọng số nhánh còn lại 
//			bằng cách lấy T - T1 - trọng số của đỉnh đang duyệt

import java.util.*;

public class CayCanBangYeu_EIUWBT {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		long minDiff = Long.MAX_VALUE;
		int nVertices = sc.nextInt();
		int nEdges = sc.nextInt();
		Vertex [] graph = readGraph(nVertices, 4);

	}

	private static class Vertex {
        private int cost;
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

	private static Vertex[] readGraph(int nVertices, int nEdges) {
        Vertex[] result = new Vertex[nVertices];
        for (int i = 0; i < nVertices; i++) {
            result[i] = new Vertex(i);
        }

		for(int i = 0; i < nVertices; i++) {
			result[i].cost = sc.nextInt();
		}

        for (int i = 0; i < nEdges; i++) {
            int first = sc.nextInt()-1;
            int second = sc.nextInt()-1;

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

	private static void dfs(Vertex ver) {
        ver.visited = true;

        for (int i = 0; i < ver.adjancyList.size(); i++) {
            if (ver.adjancyList.get(i).visited == false) {

                dfs(ver.adjancyList.get(i));
            }
        }

    }
}
