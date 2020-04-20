public class p112_PathSum {
    p112_PathSum left;
    p112_PathSum right;
    int val;

    p112_PathSum(int val) {
        this.val = val;
    }


    public static void main(String[] args) {
        p112_PathSum root = new p112_PathSum(5);
        p112_PathSum left1 = new p112_PathSum(4);
        p112_PathSum left2 = new p112_PathSum(11);
        p112_PathSum left3 = new p112_PathSum(7);
        p112_PathSum left4 = new p112_PathSum(2);
        p112_PathSum right1 = new p112_PathSum(8);
        p112_PathSum right2 = new p112_PathSum(13);
        p112_PathSum right3 = new p112_PathSum(4);
        p112_PathSum right4 = new p112_PathSum(1);
        // 构建这棵树
        root.left = left1;
        left1.left = left2;
        left2.left = left3;
        left2.right = left4;
        root.right = right1;
        right1.left = right2;
        right1.right = right3;
        right3.right = right4;
        // 传入根节点
        int sum = 22;
        boolean res = hasPathSum(root, sum);
        if (res) {
            System.out.printf("这棵树有和为%d的路径", sum);
        } else {
            System.out.printf("这棵树没有和为%d的路径", sum);
        }
    }

    // 路径之和方法
    public static boolean hasPathSum(p112_PathSum root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum - root.val == 0;
        }
        // 开始递归
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }


}
