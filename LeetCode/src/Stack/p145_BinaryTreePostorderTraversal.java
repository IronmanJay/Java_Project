package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class p145_BinaryTreePostorderTraversal {

    int val;
    p145_BinaryTreePostorderTraversal left;
    p145_BinaryTreePostorderTraversal right;

    p145_BinaryTreePostorderTraversal(int x) {
        val = x;
    }

    public static void main(String[] args) {

        p145_BinaryTreePostorderTraversal root = new p145_BinaryTreePostorderTraversal(1);
        p145_BinaryTreePostorderTraversal right = new p145_BinaryTreePostorderTraversal(2);
        p145_BinaryTreePostorderTraversal right2 = new p145_BinaryTreePostorderTraversal(3);
        root.left = null;
        root.right = right;
        right.left = right2;
        right.right = null;

        List<Integer> res = postorderTraversal(root);
        System.out.println("res = " + res);

    }

    public static List<Integer> postorderTraversal(p145_BinaryTreePostorderTraversal root) {
        // 创建存放树结点的栈
        Stack<p145_BinaryTreePostorderTraversal> stackTree = new Stack<p145_BinaryTreePostorderTraversal>();
        // 创建存放结点值的栈（这里保存按照后序遍历的顺序的结点值）
        Stack<Integer> stackNum = new Stack<Integer>();
        // 存放结果数组
        List<Integer> res = new ArrayList<Integer>();
        // 先把根节点入栈，以树根为基准开始遍历
        stackTree.push(root);
        // 当存放树结点的栈不空的时候说明还没遍历结束
        while (!stackTree.isEmpty()) {
            // 将第一个元素弹出
            p145_BinaryTreePostorderTraversal temp = stackTree.pop();
            // 这里是重点，每次都把根节点入数栈，所以根节点肯定是最下边，最后打印输入
            // 而每次入树结点栈一定要按照先左后右，因为出树栈入数栈，相当于顺序颠倒了一下
            // 最后的顺序就是左->右->根
            if (temp != null) {
                stackNum.push(temp.val);
                stackTree.push(temp.left);
                stackTree.push(temp.right);
            }
        }
        // 将数栈中元素加入结果数组
        while (!stackNum.isEmpty()) {
            res.add(stackNum.pop());
        }
        // 返回结果
        return res;
    }

}
