package DynamicProgramming;

public class p63_UniquePathsII {

    public static void main(String[] args) {
        p63_UniquePathsII p63_uniquePathsII = new p63_UniquePathsII();
        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0},
        };
        int res = p63_uniquePathsII.uniquePathsWithObstacles(obstacleGrid);
        System.out.println("res = " + res);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length <= 0) {
            return 0;
        }
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        // 生成对应的二维数组
        int[][] temp = new int[row][col];
        // 因为第一行不管到哪个位置都只有一条路径，所以置为1，除了有障碍物会有特殊说明
        for (int i = 0; i < row; i++) {
            if (obstacleGrid[i][0] == 1) {
                temp[i][0] = 0;
                break; // 因为遇到障碍物后面的也就无法到达了，所以直接返回，置为0
            } else {
                temp[i][0] = 1; // 如果没有遇到障碍物就置为1
            }
        }
        // 因为第一列不管到哪个位置都只有一条路径，所以置为1，除了有障碍物会有特殊说明
        for (int i = 0; i < col; i++) {
            if (obstacleGrid[0][i] == 1) {
                temp[0][i] = 0; // 因为遇到障碍物后面的也就无法到达了，所以直接返回，置为0
                break;
            } else {
                temp[0][i] = 1; // 如果没有遇到障碍物就置为1
            }
        }
        // 然后开始遍历每一个元素，因为最终到达点肯定是从左面或者上面进入的，所以最终点的路径就是左面的之前路径和加上上面的之前路径和，除了有障碍物会有特殊说明
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    temp[i][j] = 0; // 只要遇到一个障碍物，那么就置为0，表示没有通路
                } else {
                    temp[i][j] = temp[i - 1][j] + temp[i][j - 1]; // 如果没有障碍物，按照动态规划走
                }
            }
        }
        return temp[row - 1][col - 1];
    }

}
