package Sort;

public class p164_MaximumGap {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 6, 9, 1};
        int res = maximumGap(nums);
        System.out.println("res = " + res);
    }

    public static int maximumGap(int[] nums) {
        // 如果数组元素个数小于2，返回0
        if (nums.length < 2) {
            return 0;
        }
        // 使用基数排序对数组进行排序
        int[] sortNums = radixSort(nums);
        // 找出差值最大的
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] - nums[i] > max) {
                max = nums[i + 1] - nums[i];
            }
        }
        // 返回结果
        return max;
    }

    /**
     * 因为要求线性时间复杂度，所以使用基数排序对数组进行排序（不做注释，具体请参见DataStructures/Soort/RadixSort.java）
     *
     * @param nums 待排序数组
     * @return
     */
    public static int[] radixSort(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        int maxLength = (max + "").length();
        int[][] bucket = new int[10][nums.length];
        int[] bucketElementCounts = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < nums.length; j++) {
                int digitOfElement = nums[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = nums[j];
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        nums[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k] = 0;
            }
        }
        return nums;
    }

}
