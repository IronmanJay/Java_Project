package DAC;

public class Hanoitower {

    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
    }

    // 汉诺塔的移动方法
    // 使用分治算法
    public static void hanoiTower(int num, char a, char b, char c) {
        // 如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            // 如果有n>=2的情况，总看作是两个盘，最下边的一个盘和上边的所有盘
            // 1、先把最上边的盘A->B
            hanoiTower(num - 1, a, c, b);
            // 2、把最下边的盘A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            // 3、把B塔的盘从B->C
            hanoiTower(num - 1, b, a, c);
        }
    }

}
