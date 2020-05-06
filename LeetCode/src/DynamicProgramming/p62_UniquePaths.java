package DynamicProgramming;

public class p62_UniquePaths {

    public static void main(String[] args) {
        p62_UniquePaths p62_uniquePaths = new p62_UniquePaths();
        int res = p62_uniquePaths.uniquePaths(7, 3);
        System.out.println("res = " + res);
    }

    public int uniquePaths(int m, int n) {
        int[][] temp = new int[m][n]; // 生成对应的二维数组
        // 因为第一列不管到哪个位置都只有一条路径，所以置为1
        for (int i = 0; i < m; i++) {
            temp[i][0] = 1;
        }
        // 因为第一行不管到哪个位置都只有一条路径，所以置为1
        for (int i = 0; i < n; i++) {
            temp[0][i] = 1;
        }
        // 然后开始遍历每一个元素，因为最终到达点肯定是从左面或者上面进入的，所以最终点的路径就是左面的之前路径和加上上面的之前路径和
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                temp[i][j] = temp[i - 1][j] + temp[i][j - 1];
            }
        }
        return temp[m - 1][n - 1];
    }

}
