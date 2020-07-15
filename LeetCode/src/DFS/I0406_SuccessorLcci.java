package DFS;

public class I0406_SuccessorLcci {

    int val;
    I0406_SuccessorLcci left;
    I0406_SuccessorLcci right;

    I0406_SuccessorLcci(int x) {
        val = x;
    }

    // 保存前一个结点，如果pre和当前值相等，说明node就是结果
    private static I0406_SuccessorLcci pre;
    // 保存返回结果
    private static I0406_SuccessorLcci res;
    // 标志位，如果为true就停止递归，返回结果
    private static boolean found;

    public static void main(String[] args) {
        I0406_SuccessorLcci root = new I0406_SuccessorLcci(2);
        I0406_SuccessorLcci left = new I0406_SuccessorLcci(1);
        I0406_SuccessorLcci right = new I0406_SuccessorLcci(3);
        root.left = left;
        root.right = right;
        I0406_SuccessorLcci res = inorderSuccessor(root, left);
        System.out.println("res = " + res.val);
    }

    public static I0406_SuccessorLcci inorderSuccessor(I0406_SuccessorLcci root, I0406_SuccessorLcci p) {
        // 开始中序遍历
        inorderTraver(root, p);
        // 返回结果
        return res;
    }

    public static void inorderTraver(I0406_SuccessorLcci node, I0406_SuccessorLcci p) {
        // 如果当前节点为空说明遍历结束，或者found为true说明找到，都返回
        if (node == null || found) {
            return;
        }
        // 如果左子结点不空，向左子树递归
        if (node.left != null) {
            inorderTraver(node.left, p);
        }
        // 比较当前结点的前一个结点是否和p相等，如果相等，说明当前结点就是要找的结果，将标志位置为true，返回即可
        if (pre == p) {
            res = node;
            found = true;
        }
        // 更新pre，如果不符合把当前结点赋值给pre，成为下一个节点的前一个结点
        pre = node;
        // 如果右子树不空，向右子树递归
        if (node.right != null) {
            inorderTraver(node.right, p);
        }
    }

}
