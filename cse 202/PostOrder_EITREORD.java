import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostOrder_EITREORD {
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Vertex> result = new ArrayList<>();

	public static void main(String[] args) {
		int n = sc.nextInt();

		int[] preOrder = readGraph(n);
		int[] inOrder = readGraph(n);

		Vertex root = buildTree(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
		printPostOrder(root, result);

		for (Vertex each : result) {
			System.out.print(each.id + " ");
		}

	}

	static Vertex buildTree(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {
		if (preStart > preEnd || inStart > inEnd) {
			return null;
		}

		Vertex root = new Vertex(preOrder[preStart]);

		int rootIndex = inStart;
		while (inOrder[rootIndex] != root.id) {
			rootIndex++;
		}

		int leftTreeSize = rootIndex - inStart;

		root.left = buildTree(preOrder, preStart + 1, preStart + leftTreeSize, inOrder, inStart, rootIndex - 1);
		root.right = buildTree(preOrder, preStart + leftTreeSize + 1, preEnd, inOrder, rootIndex + 1, inEnd);

		return root;
	}

	static void printPostOrder(Vertex root, List<Vertex> list) {
		if (root == null) {
			return;
		}
		if (root.left != null) {
			printPostOrder(root.left, list);
		}
		if (root.right != null) {
			printPostOrder(root.right, list);
		}
		list.add(root);
	}

	static int[] readGraph(int val) {
		int[] result = new int[val];

		for (int i = 0; i < val; i++) {
			result[i] = sc.nextInt();
		}

		return result;
	}

	static class Vertex {
		int id;
		boolean visited;
		Vertex left;
		Vertex right;

		public Vertex(int id) {
			this.id = id;
		}
	}
}
