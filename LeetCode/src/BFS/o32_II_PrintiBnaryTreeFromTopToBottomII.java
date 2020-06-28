package BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class o32_II_PrintiBnaryTreeFromTopToBottomII {

    int val;
    o32_II_PrintiBnaryTreeFromTopToBottomII left;
    o32_II_PrintiBnaryTreeFromTopToBottomII right;

    o32_II_PrintiBnaryTreeFromTopToBottomII(int x) {
        val = x;
    }

    public static void main(String[] args) {
        // 创建这棵树
        o32_II_PrintiBnaryTreeFromTopToBottomII root = new o32_II_PrintiBnaryTreeFromTopToBottomII(3);
        o32_II_PrintiBnaryTreeFromTopToBottomII left = new o32_II_PrintiBnaryTreeFromTopToBottomII(9);
        o32_II_PrintiBnaryTreeFromTopToBottomII right1 = new o32_II_PrintiBnaryTreeFromTopToBottomII(20);
        o32_II_PrintiBnaryTreeFromTopToBottomII right2 = new o32_II_PrintiBnaryTreeFromTopToBottomII(15);
        o32_II_PrintiBnaryTreeFromTopToBottomII right3 = new o32_II_PrintiBnaryTreeFromTopToBottomII(7);
        root.left = left;
        root.right = right1;
        right1.left = right2;
        right1.right = right3;
        List<List<Integer>> res = levelOrder(root);
        System.out.println("res = " + res);
    }

    public static List<List<Integer>> levelOrder(o32_II_PrintiBnaryTreeFromTopToBottomII root) {
        // 存放节点的队列，利用先进先出的特性
        Queue<o32_II_PrintiBnaryTreeFromTopToBottomII> queue = new LinkedList<>();
        // 存放结果的二维数组
        List<List<Integer>> result = new ArrayList();
        // 将头结点加入队列
        if (root != null) {
            queue.add(root);
        }
        // 当队列不为空时开始遍历
        while (!queue.isEmpty()) {
            // 新建临时数组
            List<Integer> temp = new ArrayList<>();
            // 每层有几个节点就循环几次
            for (int i = queue.size(); i > 0; i--) {
                // 利用队列先进先出的特性，取出第一个节点，也就是这一层的第一个节点
                o32_II_PrintiBnaryTreeFromTopToBottomII node = queue.poll();
                // 将这个节点的值加入temp中
                temp.add(node.val);
                // 分别把左右节点加入temp中，也就是分别加入每一层的节点
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            // 把每一层的结果加入结果集中
            result.add(temp);
        }
        // 返回结果
        return result;
    }

}
