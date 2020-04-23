public class p129_SumRootToLeafNumbers {

    p129_SumRootToLeafNumbers left;
    p129_SumRootToLeafNumbers right;
    int val;

    p129_SumRootToLeafNumbers(int val) {
        this.val = val;
    }

    public p129_SumRootToLeafNumbers() {

    }

    public static void main(String[] args) {
        // 初始化各个节点
        p129_SumRootToLeafNumbers root = new p129_SumRootToLeafNumbers(1);
        p129_SumRootToLeafNumbers left = new p129_SumRootToLeafNumbers(2);
        p129_SumRootToLeafNumbers right = new p129_SumRootToLeafNumbers(3);
        // 生成这棵树
        root.left = left;
        root.right = right;
        p129_SumRootToLeafNumbers p129_sumRootToLeafNumbers = new p129_SumRootToLeafNumbers();
        int res = p129_sumRootToLeafNumbers.sumNumbers(root);
        System.out.println("res = " + res);
    }

    public int sumNumbers(p129_SumRootToLeafNumbers root) {
        return getSum(root, 0);
    }

    public int getSum(p129_SumRootToLeafNumbers root, int sum) {
        if (root == null) {
            return 0;
        }
        // 临时变量
        int temp = sum * 10 + root.val;
        // 叶子节点
        if (root.left == null && root.right == null) {
            return temp;
        }
        return getSum(root.left, temp) + getSum(root.right, temp);
    }

}
