public class p104_MaximumDepthOfBinaryTree {
    p104_MaximumDepthOfBinaryTree left;
    p104_MaximumDepthOfBinaryTree right;
    int val;

    p104_MaximumDepthOfBinaryTree(int val) {
        this.val = val;
    }

    //返回二叉树的深度
    static int getDepth(p104_MaximumDepthOfBinaryTree root) {
        if (root == null) {
            return 0;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }


    public static void main(String[] args) {
        p104_MaximumDepthOfBinaryTree root = new p104_MaximumDepthOfBinaryTree(1);
        p104_MaximumDepthOfBinaryTree left1 = new p104_MaximumDepthOfBinaryTree(2);
        p104_MaximumDepthOfBinaryTree left2 = new p104_MaximumDepthOfBinaryTree(3);
        p104_MaximumDepthOfBinaryTree right1 = new p104_MaximumDepthOfBinaryTree(4);
        //创建一棵树
        root.left = left1;
        left1.right = left2;
        root.right = right1;
        System.out.println("树的深度是：" + getDepth(root));
    }
}
