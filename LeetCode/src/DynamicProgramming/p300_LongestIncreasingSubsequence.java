package DynamicProgramming;

import java.util.Arrays;

public class p300_LongestIncreasingSubsequence {

    public static void main(String[] args) {
        p300_LongestIncreasingSubsequence p300_longestIncreasingSubsequence = new p300_LongestIncreasingSubsequence();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int res = p300_longestIncreasingSubsequence.lengthOfLIS(nums);
        System.out.println("res = " + res);
    }

    public int lengthOfLIS(int[] nums) {
        // 数组不符合要求直接返回
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 定义对比的数组
        int[] temp = new int[nums.length];
        // 全部以1填充这个数组，因为每一个数字可以单独作为一个结果，所以从1开始
        Arrays.fill(temp, 1);
        // 用于保存结果
        int res = 0;
        // 外层for循环保证遍历到每一个数字
        for (int i = 0; i < nums.length; i++) {
            // 内层循环用于遍历当前数字之前的所有数字
            for (int j = 0; j < i; j++) {
                // 如果有比当前数字小的数字，则temp数组相应位置+1
                if (nums[j] < nums[i]) {
                    // 这里增加的是原数组对应位置的temp数组上的位置+1，方便后续比较
                    // 这里为什么是temp[j]+1呢？因为这样比较的就是具体到那个数字
                    temp[i] = Math.max(temp[i], temp[j] + 1);
                }
            }
            // 这里就是最终的比较，每一次循环都会有一个最大值
            res = Math.max(res, temp[i]);
        }
        // 返回结果
        return res;
    }

}
