package BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class p199_BinaryTreeRightSideView {

    int val;
    p199_BinaryTreeRightSideView left;
    p199_BinaryTreeRightSideView right;

    p199_BinaryTreeRightSideView(int x) {
        this.val = x;
    }

    public static void main(String[] args) {
        // 创建这棵树
        p199_BinaryTreeRightSideView root = new p199_BinaryTreeRightSideView(1);
        p199_BinaryTreeRightSideView left1 = new p199_BinaryTreeRightSideView(2);
        p199_BinaryTreeRightSideView left2 = new p199_BinaryTreeRightSideView(5);
        p199_BinaryTreeRightSideView right1 = new p199_BinaryTreeRightSideView(3);
        p199_BinaryTreeRightSideView right2 = new p199_BinaryTreeRightSideView(4);
        root.left = left1;
        left1.right = left2;
        left1.left = null;
        root.right = right1;
        right1.right = right2;
        right1.left = null;
        List<Integer> res = rightSideView(root);
        System.out.println("res = " + res);
    }

    public static List<Integer> rightSideView(p199_BinaryTreeRightSideView root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 只要涉及到BFS基本都会用Queue
        Queue<p199_BinaryTreeRightSideView> queue = new LinkedList<>();
        // 加入头结点
        queue.offer(root);
        // 当queue不为空时循环遍历
        while (!queue.isEmpty()) {
            // 这里的size就是用于标识是否是这个队列的最后一个元素，因为这一批的最后一个元素就是我们需要的
            int size = queue.size();
            // 这个循环非常重要，就是遍历整个现在的队列长度，当遍历到最后一个，肯定就是这批，也就是树的这一行的最后一个元素，也就是我们要找的元素
            for (int i = 0; i < size; i++) {
                p199_BinaryTreeRightSideView node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                // 进入这个if判断，就是到了这一批队列的最后一个，也就是这一层的最后一个，所以将当前层的最后一个节点放入结果列表
                if (i == size - 1) {
                    res.add(node.val);
                }
            }
        }
        return res;
    }

}
