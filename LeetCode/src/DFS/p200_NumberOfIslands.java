package DFS;

public class p200_NumberOfIslands {

    public static void main(String[] args) {
        p200_NumberOfIslands p200_numberOfIslands = new p200_NumberOfIslands();
        char[][] grid = new char[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = '0';
            }
        }
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
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int num = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    num += 1;
                    dfs(grid, i, j);
                }
            }
        }
        return num;
    }

    public void dfs(char[][] grid, int newrow, int newcol) {
        int row = grid.length;
        int col = grid[0].length;
        if (newrow < 0 || newcol < 0 || newrow >= row || newcol >= col || grid[newrow][newcol] == '0') {
            return;
        }
        grid[newrow][newcol] = '0';
        dfs(grid, newrow - 1, newcol);
        dfs(grid, newrow + 1, newcol);
        dfs(grid, newrow, newcol - 1);
        dfs(grid, newrow, newcol + 1);
    }

}
