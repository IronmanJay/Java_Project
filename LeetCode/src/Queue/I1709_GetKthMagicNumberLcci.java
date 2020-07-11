package Queue;

public class I1709_GetKthMagicNumberLcci {

    public static void main(String[] args) {
        int res = getKthMagicNumber(5);
        System.out.println("res = " + res);
    }

    public static int getKthMagicNumber(int k) {
        // 定义结果数组
        int[] res = new int[k];
        // 初始化第一个位置为1
        res[0] = 1;
        // 定义素因子指针
        int p3 = 0;
        int p5 = 0;
        int p7 = 0;
        for (int i = 1; i < k; i++) {
            // 某一个满足结果的数，一定是之前的某个res[p3]*3，或者是res[p5]*5，或者是res[p7]*7的结果，并且结果一定是这三个乘积的最小值
            int temp = Math.min(Math.min(res[p3] * 3, res[p5] * 5), res[p7] * 7);
            // p3，p5，p7是不断变化的，并且都应该是由小到大，谁被选中，就应该取下一个值
            if (temp % 3 == 0) {
                p3++;
            }
            if (temp % 5 == 0) {
                p5++;
            }
            if (temp % 7 == 0) {
                p7++;
            }
            // 将符合要求的加入结果数组
            res[i] = temp;
        }
        // 最后一个元素就是目标值，返回即可
        return res[k - 1];
    }

}
