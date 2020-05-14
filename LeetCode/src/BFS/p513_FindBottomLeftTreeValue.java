package BFS;

import java.util.LinkedList;
import java.util.Queue;

public class p513_FindBottomLeftTreeValue {

    int val;
    p513_FindBottomLeftTreeValue left;
    p513_FindBottomLeftTreeValue right;

    p513_FindBottomLeftTreeValue(int x) {
        this.val = x;
    }

    public static void main(String[] args) {
        p513_FindBottomLeftTreeValue root = new p513_FindBottomLeftTreeValue(2);
        p513_FindBottomLeftTreeValue left = new p513_FindBottomLeftTreeValue(1);
        p513_FindBottomLeftTreeValue right = new p513_FindBottomLeftTreeValue(3);
        root.left = left;
        root.right = right;
        int res = findBottomLeftValue(root);
        System.out.println("此树左下角的值 = " + res);
    }

    public static int findBottomLeftValue(p513_FindBottomLeftTreeValue root) {
        // 新建队列
        Queue<p513_FindBottomLeftTreeValue> queue = new LinkedList<>();
        // 加入根节点
        queue.offer(root);
        // 循环跳出的条件时queue不为空，当queue为空的时候，最后一个弹出的元素就是我们想要的，也就是利用了队列的先进先出的特性
        while (!queue.isEmpty()) {
            // 从右到左弹出节点元素，不停的更新root
            root = queue.poll();
            // 这个次序很重要，一定要先加入右节点，因为队列最先进入的，最先出来，最后一个出来的就是最左边的节点元素
            if (root.right != null) {
                queue.offer(root.right);
            }
            if (root.left != null) {
                queue.offer(root.left);
            }
        }
        // 返回最左节点的值
        return root.val;
    }

}
