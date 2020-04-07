package Prim;

import java.util.Arrays;

public class PrimAlgorithm {

    public static void main(String[] args) {
        // 测试图是否创建成功
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        // 邻接矩阵的关系使用二维数组描述,10000表示两个点不联通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};
        // 创建MGraph对象
        MGraph mGraph = new MGraph(verxs);
        // 创MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, verxs, data, weight);
        // 输出
        minTree.showGraph(mGraph);
        // 测试Prim算法
        minTree.prim(mGraph, 1);
    }

}

// 创建最小生成树
class MinTree {
    // 创建图的邻接矩阵
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) { // 顶点
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    // 显示图的邻接矩阵
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    // 编写Prim算法，得到最小生成树
    public void prim(MGraph graph, int v) {
        // visited[]标记结点是否被访问过
        int visited[] = new int[graph.verxs];
        // 默认值为0，表示没有访问过
        for (int i = 0; i < graph.verxs; i++) {
            visited[i] = 0;
        }
        // 把当前结点标记为已访问
        visited[v] = 1;
        // 用h1和h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000; // 将minWeight初始化一个较大数，后面会被替换
        for (int k = 1; k < graph.verxs; k++) { // 因为有graph.verxs个顶点，prim算法结束后有graph.verxs - 1 条边
            // 确定每一次生成的子图，和哪个结点的距离最近
            for (int i = 0; i < graph.verxs; i++) { // i结点表示被访问过的结点
                for (int j = 0; j < graph.verxs; j++) { // j结点表示还没有访问过的结点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        // 替换minWeight(寻找已经访问过的结点和未访问过的结点的权值最小的边)
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            // 找到一条边是最小的
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值：" + minWeight);
            // 将当前这个结点标记为已经访问
            visited[h2] = 1;
            // 重置minWeight
            minWeight = 10000;
        }
    }
}

class MGraph {
    int verxs; // 表示图的结点个数
    char[] data; // 存放结点数据
    int[][] weight; // 存放边，邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }

}