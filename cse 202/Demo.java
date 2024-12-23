import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Demo {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// Đọc số lượng nút trong cây
		int n = sc.nextInt();

		// Đọc preOrder và inOrder từ đầu vào
		int[] preOrder = new int[n];
		int[] inOrder = new int[n];
		for (int i = 0; i < n; i++) {
			preOrder[i] = sc.nextInt();
		}
		for (int i = 0; i < n; i++) {
			inOrder[i] = sc.nextInt();
		}

		// Xây dựng lại cây
		TreeNode root = buildTreeHelper(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);

		// Duyệt PostOrder
		List<Integer> postOrder = new ArrayList<>();
		postOrderTraversal(root, postOrder);

		// In kết quả
		for (int val : postOrder) {
			System.out.print(val + " ");
		}
	}

	// Xây dựng cây từ preOrder và inOrder

	static TreeNode buildTreeHelper(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {
		if (preStart > preEnd || inStart > inEnd)
			return null;

		// Gốc của cây con hiện tại là phần tử đầu tiên trong preOrder
		int rootVal = preOrder[preStart];
		TreeNode root = new TreeNode(rootVal);

		// Tìm vị trí của gốc trong inOrder để chia thành cây con trái và phải
		int rootIndex = inStart;
		while (inOrder[rootIndex] != rootVal) {
			rootIndex++;
		}

		// Tính số lượng nút trong cây con trái
		int leftTreeSize = rootIndex - inStart;

		// Xây dựng cây con trái
		root.left = buildTreeHelper(preOrder, preStart + 1, preStart + leftTreeSize, inOrder, inStart, rootIndex - 1);

		// Xây dựng cây con phải
		root.right = buildTreeHelper(preOrder, preStart + leftTreeSize + 1, preEnd, inOrder, rootIndex + 1, inEnd);

		return root;
	}

	// Duyệt PostOrder
	static void postOrderTraversal(TreeNode root, List<Integer> postOrder) {
		if (root == null)
			return;

		// Duyệt cây con trái
		postOrderTraversal(root.left, postOrder);

		// Duyệt cây con phải
		postOrderTraversal(root.right, postOrder);

		// Thêm giá trị của nút hiện tại
		postOrder.add(root.val);
	}

	// Lớp đại diện cho một nút trong cây
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}
}
