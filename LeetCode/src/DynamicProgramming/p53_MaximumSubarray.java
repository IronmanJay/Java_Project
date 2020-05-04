package DynamicProgramming;

public class p53_MaximumSubarray {

    public static void main(String[] args) {
        p53_MaximumSubarray p53_maximumSubarray = new p53_MaximumSubarray();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res = p53_maximumSubarray.maxSubArray(nums);
        System.out.println("res = " + res);
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int tempNum = 0; // 临时变量，保存走过每一个位置之前的数字和，并依次比较
        int maxNum = nums[0]; // 假定数组中第一个数就是最大的，这样才能继续向后比较
        // 使用增强for循环，只从头到尾遍历依次即可，所以时间复杂度为O(N)
        for (int num : nums) {
            tempNum = Math.max(tempNum + num, num); // 这里比较的是遍历的当前数字和之前的所有数字之和谁大，并更新临时变量值
            maxNum = Math.max(tempNum, maxNum); // 这里比较之前最大值和现在的值，并更新最大值
        }
        return maxNum;
    }

}
