package Sparsearray;

public class SparseArray {

    public static void main(String[] args) {
        // 创建一个原始的二维数组11 * 11
        // 0：表示没有棋子，1：表示黑子，2：表示蓝子
        int ChessArr1[][] = new int[11][11];
        ChessArr1[1][2] = 1;
        ChessArr1[2][3] = 2;
        ChessArr1[4][5] = 2;
        ChessArr1[6][1] = 1;
        //输入原始的二维数组
        System.out.println("原始的二维数组:");
        for (int[] row : ChessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println("\n");
        }

        // 将二维数组转稀疏数组
        // 1、先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (ChessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum:" + sum);

        // 2、创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        // 3、给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        // 4、遍历二维数组将非零的值存放到稀疏数组中
        int count = 0; // 用于记录是第几个非零数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (ChessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = ChessArr1[i][j];
                }
            }
        }
        // 5、输出稀疏数组的形式
        System.out.println("\n");
        System.out.println("得到的稀疏数组为如下形式:");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
            System.out.println("\n");
        }

        // 将稀疏数组恢复成原始的二维数组
        // 1、先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        // 2、在读取稀疏数组后几行的数据(从第二行开始)，并赋给原始的二维数组即可
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        // 3、输出恢复后的二维数组
        System.out.println("\n");
        System.out.println("恢复后的二维数组:");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

}
