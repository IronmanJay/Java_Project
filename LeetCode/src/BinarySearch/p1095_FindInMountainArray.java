package BinarySearch;

public class p1095_FindInMountainArray {

    public static void main(String[] args) {
        int[] mountainArr = {1, 2, 3, 4, 5, 3, 1};
        int res = findInMountainArray(3, mountainArr);
        System.out.println("res = " + res);
    }

    public static int findInMountainArray(int target, int[] mountainArr) {
        // 这是已经找到的峰顶元素下标
        int topIndex = findPeakElement(mountainArr);
        // 如果目标值大于峰值，那么不用继续判断了，直接返回
        if (mountainArr[topIndex] < target) {
            return -1;
        }
        // 定义数组长度
        int len = mountainArr.length;
        // 如果数组第一个值小于目标值，说明左边升序，向左查找
        if (mountainArr[0] <= target) {
            int left = 0;
            int right = topIndex;
            int mid;
            while (left <= right) {
                mid = left + (right - left) / 2;
                int mVal = mountainArr[mid];
                if (mVal == target) {
                    return mid;
                } else if (target < mVal) {
                    right = mid - 1;
                } else if (target > mVal) {
                    left = mid + 1;
                }
            }
        }
        // 如果数组最后一个值小于目标值，说明右边降序，向右查找
        if (mountainArr[len - 1] <= target) {
            int left = topIndex;
            int right = len - 1;
            int mid;
            while (left <= right) {
                mid = left + (right - left) / 2;
                int mVal = mountainArr[mid];
                if (mVal == target) {
                    return mid;
                } else if (target < mVal) {
                    left = mid + 1;
                } else if (target > mVal) {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static int findPeakElement(int[] nums) {
        // 首先定义左右指针
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int mVal = nums[mid];
            // 如果这个元素大于左右两个元素，那么就是我们要找的
            if (mVal > nums[mid + 1] && mVal > nums[mid - 1]) {
                return mid;
            }
            if (mVal < nums[mid + 1]) { // 如果当前元素小于它右边的元素，那么说明要向右边找
                left = mid + 1;
            } else if (mVal < nums[mid - 1]) { // 如果当前元素小于它左边的元素，那么说明要向左边找
                right = mid - 1;
            }
        }
        return -1;
    }
}
