package DFS;

public class p111_MinimumDepthOfBinaryTree {

    int val;
    p111_MinimumDepthOfBinaryTree left;
    p111_MinimumDepthOfBinaryTree right;

    p111_MinimumDepthOfBinaryTree(int x) {
        val = x;
    }

    public static void main(String[] args) {
        p111_MinimumDepthOfBinaryTree root = new p111_MinimumDepthOfBinaryTree(3);
        p111_MinimumDepthOfBinaryTree left = new p111_MinimumDepthOfBinaryTree(9);
        p111_MinimumDepthOfBinaryTree right1 = new p111_MinimumDepthOfBinaryTree(20);
        p111_MinimumDepthOfBinaryTree right2 = new p111_MinimumDepthOfBinaryTree(15);
        p111_MinimumDepthOfBinaryTree right3 = new p111_MinimumDepthOfBinaryTree(7);
        root.left = left;
        root.right = right1;
        right1.left = right2;
        right1.right = right3;
        int res = minDepth(root);
        System.out.println("res = " + res);
    }

    public static int minDepth(p111_MinimumDepthOfBinaryTree root) {
        // 如果当前结点为空直接返回0
        if (root == null) {
            return 0;
        }
        // 如果左子树为空，右子树不空，向右继续递归，别忘了+1，因为遍历一个节点深度就+1，当遍历到空节点返回0把之前的都加起来就是当前深度
        if (root.left == null && root.right != null) {
            return minDepth(root.right) + 1;
        }
        // 如果右子树为空，左子树不空，向左继续递归，别忘了+1，因为遍历一个节点深度就+1，当遍历到空节点返回0把之前的都加起来就是当前深度
        if (root.left != null && root.right == null) {
            return minDepth(root.left) + 1;
        }
        // 返回每次递归的最小值就是最小深度
        return Math.min((minDepth(root.left) + 1), (minDepth(root.right) + 1));
    }

}
