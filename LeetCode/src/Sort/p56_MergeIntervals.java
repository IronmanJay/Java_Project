package Sort;

import java.util.Arrays;

public class p56_MergeIntervals {

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] res = merge(intervals);
        System.out.println("res = " + Arrays.deepToString(res));
    }

    public static int[][] merge(int[][] intervals) {
        // 首先按照自定义排序器给二维数组排序，如果第一个数组的第一个数字大于第二个数组的第一个数字就降序，反之升序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 创建结果数组
        int[][] res = new int[intervals.length][2];
        // 定义指针下标，这里注意要用-1开始，因为第一次要加进去一个数组，无论谁大，用来后面比较，如果不是-1，后面比较就没有值了
        int index = -1;
        for (int[] interval : intervals) {
            // 如果当前是第一个数组或者当前数组的第一个元素大于结果二维数组最后一个数组的最后一个元素，说明没有交集，加入即可
            if (index == -1 || interval[0] > res[index][1]) {
                res[++index] = interval;
            } else {
                // 进入到这里说明当前数组的第一个元素小于结果二维数组最后一个数组的最后一个元素，说明有交集，则更新结果二维数组最后一个数组的最后一个元素为当前数组最后一个元素和结果二维数组的最后一个数组的最后一个元素的最大值
                res[index][1] = Math.max(res[index][1], interval[1]);
            }
        }
        // 返回结果
        return Arrays.copyOf(res, index + 1);
    }

}
