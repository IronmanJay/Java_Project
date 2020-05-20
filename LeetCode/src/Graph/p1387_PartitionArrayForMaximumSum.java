package Graph;

public class p1387_PartitionArrayForMaximumSum {

    public static void main(String[] args) {
        int[][] graph = {
                {1, 2, 3},
                {0, 2},
                {0, 1, 3},
                {0, 2},
        };
        boolean res = isBipartite(graph);
        System.out.println("res = " + res);
    }

    public static boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return false;
        }
        // 有几个节点就建立多大的数组
        int v = graph.length;
        // 初始化为0表示未被染色，1表示黑色，2表示白色
        int[] colors = new int[v];
        // 要考虑非连通图，所以要遍历每一个节点
        for (int i = 0; i < v; i++) {
            if (!dfs(graph, i, colors, 0)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 深度优先遍历每一个节点
     *
     * @param graph     原始图
     * @param i         第i个节点
     * @param colors    颜色数组
     * @param lastColor 相邻的节点颜色
     * @return
     */
    public static boolean dfs(int[][] graph, int i, int[] colors, int lastColor) {
        // 被染色的节点不要再次染色，如果继续染色，就会导致死循环
        if (colors[i] != 0) {
            return colors[i] != lastColor;
        }
        // 未被染色，染成与相邻节点不同的颜色，使用三目运算符
        colors[i] = lastColor == 1 ? 2 : 1;
        // 遍历当前这个节点所有的邻接节点
        for (int j = 0; j < graph[i].length; j++) {
            if (!dfs(graph, graph[i][j], colors, colors[i])) {
                return false;
            }
        }
        return true;
    }


}
