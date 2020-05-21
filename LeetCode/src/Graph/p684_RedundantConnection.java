package Graph;

import java.util.Arrays;

public class p684_RedundantConnection {

    public static void main(String[] args) {
        int[][] edges = {
                {1, 2},
                {1, 3},
                {2, 3},
        };
        int[] res = findRedundantConnection(edges);
        System.out.println("res = " + Arrays.toString(res));
    }

    public static int[] findRedundantConnection(int[][] edges) {
        // 定义辅助数组，长度为结点个数 + 1
        int[] nums = new int[edges.length + 1];
        // 给数组赋初值，开始每个结点的父节点就是自己
        for (int k = 0; k < nums.length; k++) {
            nums[k] = k;
        }
        // 开始遍历每一条边
        for (int i = 0; i < edges.length; i++) {
            // 找到每一个边的第一个结点的父节点
            int f1 = findRoot(edges[i][0], nums);
            // 找到每一个边的第二个结点的父节点
            int f2 = findRoot(edges[i][1], nums);
            // 如果两个结点的父节点相等，说明这两个结点构成的边是重复的
            if (f1 == f2) {
                return edges[i];
            }
            // 如果不相等，那么连接这两个边
            else {
                nums[f1] = f2;
            }
        }
        return null;
    }

    public static int findRoot(int n, int[] nums) {
        // 当当前结点的父节点不是自己，将父节点赋值给当前结点，并返回
        while (nums[n] != n) {
            n = nums[n];
        }
        return n;
    }

}
