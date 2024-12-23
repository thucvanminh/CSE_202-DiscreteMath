import java.util.*;

public class demoTreeHeight {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int n = sc.nextInt();

		TreeNode[] result = new TreeNode[n];
		for (int i = 0; i < n; i++) {
			result[i] = new TreeNode(i);
		}

		for (int i = 0; i < n - 1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			result[a].addNodes(result[b]);
			result[b].addNodes(result[a]);
		}

		int treeHeight = dfs(result[0], -1);
		System.out.println(treeHeight);

	}

	static int dfs(TreeNode node, int parentLevel) {

		node.level = parentLevel + 1;

		int maxHeight = node.level;

		for (TreeNode neighBor : node.arr) {
			if (neighBor.level == -1) {
				maxHeight = Math.max(maxHeight, dfs(neighBor, node.level));
			}
		}

		return maxHeight;
	}

	static class TreeNode {
		int id;
		int level = -1;
		ArrayList<TreeNode> arr = new ArrayList<>();

		public TreeNode(int id) {
			this.id = id;
		}

		void addNodes(TreeNode node) {
			arr.add(node);
		}
	}
}
