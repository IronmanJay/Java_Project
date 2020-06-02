package BinarySearch;

public class p162_FindPeakElement {

    public static void main(String[] args) {
        int[] nums = {3, 4, 3, 2, 1};
        int res = findPeakElement(nums);
        System.out.println("res = " + res);
    }

    public static int findPeakElement(int[] nums) {
        // 有两种情况需要特判，第一种情况，数组长度为1，直接返回0
        if (nums.length == 1) {
            return 0;
        }
        // 第二种情况，数组长度为2，比较两个元素，返回大的那个
        if (nums.length == 2) {
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }
        // 接下来就是正常的二分算法
        // 首先定义左右指针
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // 这里还有两种情况需要特判，和上面的不同，这个判断的是while循环过程中的左右指针
            // 第一种情况，左右指针指向了同一个元素，直接返回
            if (left == right) {
                return right;
            }
            // 第二种情况，左右指针只指向了两个元素，那么返回两个元素中大的下标
            if (right - left == 1) {
                return nums[left] > nums[right] ? left : right;
            }
            int mid = (left + right) / 2;
            // 特判之后就是正常的二分查找
            if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) { // 如果这个元素大于左右两个元素，那么就是我们要找的
                return mid;
            }
            if (nums[mid] < nums[mid + 1]) { // 如果当前元素小于它右边的元素，那么说明要向右边找
                left = mid + 1;
            } else if (nums[mid] < nums[mid - 1]) { // 如果当前元素小于它左边的元素，那么说明要向左边找
                right = mid - 1;
            }
        }
        return -1;
    }

}
