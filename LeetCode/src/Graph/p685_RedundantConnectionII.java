package Graph;

import java.util.Arrays;

public class p685_RedundantConnectionII {

    public static void main(String[] args) {
        int[][] edges = {
                {1, 2},
                {1, 3},
                {2, 3},
        };
        int[] res = findRedundantDirectedConnection(edges);
        System.out.println("res = " + Arrays.toString(res));
    }

    public static int[] findRedundantDirectedConnection(int[][] edges) {
        // 定义辅助数组，长度为结点个数 + 1
        int[] degrees = new int[edges.length + 1];
        // 定义标识位，判断入度是1还是2
        int flag = -1;
        // 给数组赋初值，并判断入读个数，如果入度个数为2，则flag置为当前结点
        for (int[] edge : edges) {
            degrees[edge[1]] += 1;
            if (degrees[edge[1]] == 2) {
                flag = edge[1];
                break;
            }
        }
        // 如果flag=-1，说明所有节点的入度都为1，那么就当作无向图做并查集
        if (flag == -1) {
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
        }
        // 如果flag!=-1，则有两个结点指向该结点，这两条边优先删除位于临边数组后边的一条边，若不能连通返回另一条，否则返回该条
        else {
            // pre：前一条边，pre1：后一条边
            int pre = -1, pre1 = -1;
            // 定义辅助数组，长度为结点个数 + 1
            int[] nums = new int[edges.length + 1];
            int i, j, count = 0;
            // 给数组赋初值，开始每个结点的父节点就是自己
            for (i = 0; i < edges.length + 1; i++) {
                nums[i] = i;
            }
            for (i = 0; i < edges.length; i++) {
                if (edges[i][1] == flag) {
                    if (pre == -1) {
                        pre = i;
                    } else {
                        pre1 = i;
                        break;
                    }
                }
            }
            int f1 = 0, f2 = 0;
            for (i = 0; i < edges.length; i++) {
                // 跳过后一条边
                if (i == pre1) {
                    continue;
                }
                f1 = findRoot(edges[i][0], nums);
                f2 = findRoot(edges[i][1], nums);
                // 合并
                nums[f1] = nums[f2];
            }
            // 判断去掉这个边是否还能构成通路
            for (i = 1; i < nums.length; i++) {
                if (nums[i] == i) {
                    count++;
                }
            }
            // 如果可以构成通路，最后都指向根节点，那么返回后一条边，如果count=2，说明不能构成通路，返回前一条边
            if (count == 2) {
                return edges[pre];
            } else {
                return edges[pre1];
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
