import java.util.LinkedList;
import java.util.List;

public class p113_PathSumII {

    p113_PathSumII left;
    p113_PathSumII right;
    int val;

    p113_PathSumII() {

    }

    p113_PathSumII(int val) {
        this.val = val;
    }

    public static void main(String[] args) {
        // 初始化各个节点
        p113_PathSumII root = new p113_PathSumII(5);
        p113_PathSumII left1 = new p113_PathSumII(4);
        p113_PathSumII left2 = new p113_PathSumII(11);
        p113_PathSumII left3 = new p113_PathSumII(7);
        p113_PathSumII left4 = new p113_PathSumII(2);
        p113_PathSumII right1 = new p113_PathSumII(8);
        p113_PathSumII right2 = new p113_PathSumII(13);
        p113_PathSumII right3 = new p113_PathSumII(4);
        p113_PathSumII right4 = new p113_PathSumII(5);
        p113_PathSumII right5 = new p113_PathSumII(1);
        // 初始化树
        root.left = left1;
        left1.left = left2;
        left2.left = left3;
        left2.right = left4;
        root.right = right1;
        right1.left = right2;
        right1.right = right3;
        right3.left = right4;
        right3.right = right5;
        p113_PathSumII p113_pathSumII = new p113_PathSumII();
        int sum = 22;
        // 传入参数
        List<List<Integer>> res = p113_pathSumII.pathSum(root, sum);
        // 打印结果
        System.out.println("res = " + res);
    }

    // 用于存放结果，用于存放所有符合要求的路径
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> pathSum(p113_PathSumII root, int sum) {
        // 如果当前结点为空，直接返回
        if (root == null) {
            return res;
        }
        // 临时List，用于存放当前符合要求的路径
        LinkedList<Integer> temp = new LinkedList<>();
        // 先把当前结点的值放进来
        temp.add(root.val);
        getPath(root, root.val, sum, temp);
        return res;
    }


    public void getPath(p113_PathSumII root, int curSum, int sum, LinkedList<Integer> temp) {
        // 如果当前结点是叶子节点，也就是递归遍历到了最后一个结点
        if (root.left == null && root.right == null) {
            // 如果是叶子节点，那么可以判断是否和sum相等
            if (curSum == sum) {
                // 如果相等，则将整条路经放入res中，这里每次都是new，因为要更新一下
                res.add(new LinkedList<>(temp));
            }
            return;
        }
        // 判断左子树
        if (root.left != null) {
            // 如果左子树有值，那么将左子结点的值放入temp中，更新路径
            temp.add(root.left.val);
            // 开始向左递归，更新curSum的值，传入更新后的路径
            getPath(root.left, curSum + root.left.val, sum, temp);
            // 这步非常重要，使用回溯法，删除最后一个加入的结点，也就是当前结点，这样才能回到上一个结点重新遍历新的路径
            temp.removeLast();
        }
        // 判断右子树，具体方法和判断左子树一样
        if (root.right != null) {
            temp.add(root.right.val);
            getPath(root.right, curSum + root.right.val, sum, temp);
            temp.removeLast();
        }
    }

}
