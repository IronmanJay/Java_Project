package Recursion;

public class MiGong {

    public static void main(String[] args) {
        // 先创建一个二维数组，模拟迷宫
        // 地图
        int[][] map = new int[8][7];
        // 使用1表示墙
        // 上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置挡板，1表示
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;
        // 输出地图
        System.out.println("地图的原形状:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        // 使用递归回溯给小球找路
        setWay1(map,1,1);
        // 输出新的地图，小球走过，并标识过的地图
        System.out.println("小球走过，并标识过的地图形状:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    // 使用递归回溯来给小球找路
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // 说明通路已找到
            return true;
        } else {
            if (map[i][j] == 0) { // 如果当前这个点还没有走过
                // 按照策略：下->右->上->左
                map[i][j] = 2; // 假定该点是可以走通
                if (setWay(map, i + 1, j)) { // 向下走
                    return true;
                } else if (setWay(map, i, j + 1)) { // 向右走
                    return true;
                } else if (setWay(map, i - 1, j)) { // 向上走
                    return true;
                } else if (setWay(map, i, j - 1)) { // 向左走
                    return true;
                } else {
                    // 说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果map[i][j] != 0,可能是1，2，3
                return false;
            }
        }
    }

    // 修改找路的策略：上->右->下->左
    public static boolean setWay1(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // 说明通路已找到
            return true;
        } else {
            if (map[i][j] == 0) { // 如果当前这个点还没有走过
                // 按照策略：上->右->下->左
                map[i][j] = 2; // 假定该点是可以走通
                if (setWay1(map, i - 1, j)) { // 向上走
                    return true;
                } else if (setWay1(map, i, j + 1)) { // 向右走
                    return true;
                } else if (setWay1(map, i + 1, j)) { // 向下走
                    return true;
                } else if (setWay1(map, i, j - 1)) { // 向左走
                    return true;
                } else {
                    // 说明该点走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果map[i][j] != 0,可能是1，2，3
                return false;
            }
        }
    }

}
