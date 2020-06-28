package Tree;

public class o28_SymmetricBinaryTree {

    int val;
    o28_SymmetricBinaryTree left;
    o28_SymmetricBinaryTree right;

    o28_SymmetricBinaryTree(int x) {
        val = x;
    }

    public static boolean isSymmetric(o28_SymmetricBinaryTree root) {
        if (root == null) {
            return true;
        } else {
            return getRes(root.left, root.right);
        }
    }

    public static boolean getRes(o28_SymmetricBinaryTree left, o28_SymmetricBinaryTree right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return getRes(left.left, right.right) && getRes(left.right, right.left);
    }

    public static void main(String[] args) {
        // 先创建一颗二叉树
        o28_SymmetricBinaryTree root = new o28_SymmetricBinaryTree(1);
        o28_SymmetricBinaryTree left1 = new o28_SymmetricBinaryTree(2);
        o28_SymmetricBinaryTree right1 = new o28_SymmetricBinaryTree(2);
        o28_SymmetricBinaryTree left2 = new o28_SymmetricBinaryTree(3);
        o28_SymmetricBinaryTree right2 = new o28_SymmetricBinaryTree(3);
        o28_SymmetricBinaryTree left3 = new o28_SymmetricBinaryTree(4);
        o28_SymmetricBinaryTree right3 = new o28_SymmetricBinaryTree(4);
        root.left = left1;
        root.right = right1;
        left1.left = left2;
        left1.right = left3;
        right1.right = right2;
        right1.left = right3;
        // 调用方法
        boolean symmetric = isSymmetric(root);
        if (symmetric) {
            System.out.println("这棵树是对称二叉树");
        } else {
            System.out.println("这棵树不是对称二叉树");
        }
    }

}
