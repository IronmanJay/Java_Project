package DFS;

public class p100_SameTree {

    int val;
    p100_SameTree left;
    p100_SameTree right;

    p100_SameTree(int x) {
        val = x;
    }

    public static void main(String[] args) {
        p100_SameTree tree1Root = new p100_SameTree(1);
        p100_SameTree tree1Left = new p100_SameTree(2);
        p100_SameTree tree1Right = new p100_SameTree(3);
        p100_SameTree tree2Root = new p100_SameTree(1);
        p100_SameTree tree2Left = new p100_SameTree(2);
        p100_SameTree tree2Right = new p100_SameTree(3);
        boolean res = isSameTree(tree1Root, tree2Root);
        System.out.println("res = " + res);
    }

    public static boolean isSameTree(p100_SameTree p, p100_SameTree q) {
        // 如果两棵树节点都为空说明，说明当前递归到底了，这次递归返回true
        if (p == null && q == null) {
            return true;
        }
        // 如果两棵树节点其中有一棵为空，说明两棵树当前节点不相等，这次递归返回false
        if (p == null || q == null) {
            return false;
        }
        // 如果两棵树节点值不相同，说明两棵树当前结点不相等，这次递归返回false
        if (p.val != q.val) {
            return false;
        }
        // 分别向左和向右递归，如果都返回true，说明两棵树相同
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
