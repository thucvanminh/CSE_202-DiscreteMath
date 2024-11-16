public class BuildPostOrder {
    public static void main(String[] args) {

    }

    static class Node {
        int id;
        int left, right;

        public Node(int id) {
            this.id = id;
        }

    }

    static void buildPostOrder(int[] inOrder, int[] preOrder) {
        Node root = new Node(preOrder[0]);

    }

}