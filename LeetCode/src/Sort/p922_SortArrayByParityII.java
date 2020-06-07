package Sort;

import java.util.Arrays;

public class p922_SortArrayByParityII {

    public static void main(String[] args) {
        int[] A = {4, 2, 5, 7};
        int[] res = sortArrayByParityII(A);
        System.out.println("res = " + Arrays.toString(res));
    }

    public static int[] sortArrayByParityII(int[] A) {
        // 定义结果数组
        int[] res = new int[A.length];
        // 定义偶数下标和奇数下标
        int oddIndex = 0;
        int evenIndex = 1;
        // 遍历A数组每一个元素，如果是偶数，那么就放到res数组的偶数位置，oddIndex相应+2，如果是奇数，就放到res数组奇数位置，eventIndex相应+2
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                res[oddIndex] = A[i];
                oddIndex += 2;
            } else {
                res[evenIndex] = A[i];
                evenIndex += 2;
            }
        }
        // 返回结果
        return res;
    }

}
