package Graph;

import java.util.*;

public class p1042_FlowerPlantingWithNoAdjacent {

    public static void main(String[] args) {
        int N = 3;
        int[][] paths = {
                {1, 2},
                {2, 3},
                {3, 1},
        };
        int[] res = gardenNoAdj(N, paths);
        System.out.println("res = " + Arrays.toString(res));
    }

    public static int[] gardenNoAdj(int N, int[][] paths) {
        // 初始化花园，使用map保存花园与其邻接花园的关系
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        // 有几个花园就生成几个HashMao
        for (int i = 0; i < N; i++) {
            graph.put(i, new HashSet<>());
        }
        // 生成每个花园的各边关系
        for (int[] path : paths) {
            int a = path[0] - 1;
            int b = path[1] - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        // 定义结果数组，有几个花园数组就多大
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            // 使用一个boolean的数组，判断当前花园是否使用这种花
            boolean[] used = new boolean[5];
            // 查看当前花园每个邻接花园的状态
            for (int adj : graph.get(i)) {
                used[res[adj]] = true;
            }
            // 给当前花园染色，因为有四种花，所以从1开始，4结束
            for (int j = 1; j < 4; j++) {
                // 说明当前花园可以使用这种花，就把当前花园的花置为当前花
                if (!used[j]) {
                    res[i] = j;
                }
            }
        }
        return res;
    }

}
