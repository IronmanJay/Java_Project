package DFS;

public class p200_NumberOfIslands {

    public static void main(String[] args) {
        p200_NumberOfIslands p200_numberOfIslands = new p200_NumberOfIslands();
        // 创建这个图
        char[][] grid = new char[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = '0';
            }
        }
        // 创建岛屿
        grid[0][0] = '1';
        grid[0][1] = '1';
        grid[1][0] = '1';
        grid[1][1] = '1';
        grid[2][2] = '1';
        grid[3][3] = '1';
        grid[3][4] = '1';
        System.out.println("地图形状为:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("结果为:");
        int res = p200_numberOfIslands.numIslands(grid);
        System.out.println("res = " + res);
    }

    public int numIslands(char[][] grid) {
        // 如果图为空返回0
        if (grid == null || grid.length == 0) {
            return 0;
        }
        // 获取行数和列数
        int row = grid.length;
        int col = grid[0].length;
        // 最后要返回的数量
        int num = 0;
        // 开始对每一个位置进行遍历
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 如果这个位置是岛屿，那么进入递归
                if (grid[i][j] == '1') {
                    // 只要有岛屿就+1
                    num += 1;
                    // 开始递归，传入图和当前位置
                    dfs(grid, i, j);
                }
            }
        }
        // 返回结果
        return num;
    }

    /**
     * 深度优先遍历
     *
     * @param grid   图
     * @param newrow 新的行
     * @param newcol 新的列
     */
    public void dfs(char[][] grid, int newrow, int newcol) {
        // 每次都要获取行数和列数，为了下面的递归条件
        int row = grid.length;
        int col = grid[0].length;
        // 如果新的行和列小于零或者大于图的大小，或者遇到了水(0)，就返回
        if (newrow < 0 || newcol < 0 || newrow >= row || newcol >= col || grid[newrow][newcol] == '0') {
            return;
        }
        // 进行走下一步之前要将当前位置置为水(0)，防止影响到深度优先遍历
        grid[newrow][newcol] = '0';
        // 按照上->下->左->右的方向开始递归
        dfs(grid, newrow - 1, newcol);
        dfs(grid, newrow + 1, newcol);
        dfs(grid, newrow, newcol - 1);
        dfs(grid, newrow, newcol + 1);
    }

}
