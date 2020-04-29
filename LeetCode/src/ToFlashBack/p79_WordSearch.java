package ToFlashBack;

public class p79_WordSearch {

    // 用于存放坐标方向，分别运动方向是上，右，下，左
    private static int[][] dires = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    // 定义行数和列数
    private int row, col;
    // 定义是否找到的标志位
    private boolean hasFind;
    // 定义是否访问过的坐标的二维数组
    private boolean[][] visited;

    public static void main(String[] args) {
        p79_WordSearch p79_wordSearch = new p79_WordSearch();
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        boolean res = p79_wordSearch.exist(board, word);
        if (res) {
            System.out.println("找到!");
        } else {
            System.out.println("未找到!");
        }
    }

    /**
     * 判断主方法
     *
     * @param board 初始二维数组
     * @param word  准备查找的字符串
     * @return
     */
    public boolean exist(char[][] board, String word) {
        // 行数
        row = board.length;
        // 列数
        col = board[0].length;
        // 初始标志位为false，没有找到
        hasFind = false;
        // 如果行数和列数乘积小于整个字符串的长度直接返回false
        if (row * col < word.length()) {
            return false;
        }
        // 初始化是否访问的二维数组大小为board的大小
        visited = new boolean[row][col];
        // 把单词字符串转为对应的字符串一维数组
        char[] chars = word.toCharArray();
        // 双层for循环，遍历board的每一个单词
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 如果当前board中的单词等于word中的单词，那么就开始进入下一个方法
                if (board[i][j] == chars[0]) {
                    backTrack(board, chars, 1, i, j);
                    // 如果已经判断出结果，则返回true
                    if (hasFind) {
                        return true;
                    }
                }
            }
        }
        // 如果进入for循环没有任何返回结果，就返回false
        return false;
    }

    /**
     * 查找的核心方法：使用递归+回溯
     *
     * @param board    初始的二维数组
     * @param word     待查找的字符数组
     * @param curIndex 判断的索引值，每次都+1
     * @param x        横坐标，每次都是newX
     * @param y        纵坐标，每次都是newY
     */
    private void backTrack(char[][] board, char[] word, int curIndex, int x, int y) {
        // 如果标志位为true，直接返回，这个也是递归的结束条件
        if (hasFind) {
            return;
        }
        // 如果临时索引值和单词数组长度相等，说明已经找到了最后一个单词，则将标志位置为true，返回
        if (curIndex == word.length) {
            hasFind = true;
            return;
        }
        // 因为传进来的x和y就是当前坐标值，说明已经走过了
        visited[x][y] = true;
        // 使用增强for循环遍历方向数组
        for (int[] dire : dires) {
            // 更新坐标位置，并按照上->右->下->左的方向，依次开始递归
            int newX = x + dire[0];
            int newY = y + dire[1];
            // 如果满足，更新后的坐标在二维数组中，并且没有访问过，并且更新后的坐标值和字符数组的下一个单词相等，则进入递归，如果不满足条件，则依次使用下面的方向继续进行递归
            if (isIn(newX, newY) && !visited[newX][newY] && board[newX][newY] == word[curIndex]) {
                backTrack(board, word, curIndex + 1, newX, newY); // 这里curIndex+1，意味着更新索引值，下面就判断字符数组的下一个单词
            }
        }
        // 将判断是否访问过的二维数组恢复
        visited[x][y] = false;
    }

    /**
     * 判断当前坐标是否在二维数组中
     *
     * @param x 横坐标
     * @param y 纵坐标
     * @return
     */
    private boolean isIn(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

}
