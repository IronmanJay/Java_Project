package BinarySearch;

import java.util.Arrays;

public class p350_IntersectionOfTwoArraysII {

    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        int[] res = intersect(nums1, nums2);
        System.out.println("res = " + Arrays.toString(res));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        // 这步的作用主要是要在小的数组里面找大的
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        // 将小数组排序，为后面的二分查找做准备
        Arrays.sort(nums1);
        // 标识位，判断当前数字是否使用过
        boolean[] used = new boolean[nums1.length];
        // 结果数组
        int[] res = new int[nums1.length];
        // 结果下标
        int index = 0;
        // 遍历大数组中的每一个元素和小数组中的值进行二分查找
        for (int num : nums2) {
            if (binarySearch(num, nums1, used)) {
                res[index++] = num;
            }
        }
        // 返回从0截取到index位置
        return Arrays.copyOfRange(res, 0, index);
    }

    public static boolean binarySearch(int target, int[] nums, boolean[] used) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // 如果元素没有使用过，返回true，并将这个位置置为true
                if (!used[mid]) {
                    used[mid] = true;
                    return true;
                } else {
                    // 如果元素已经使用过，则向两端扩展，查找是否有一样且未使用的元素
                    int j = mid + 1;
                    while (j < nums.length && nums[j] == target) { // 向右找
                        if (!used[j]) {
                            used[j] = true;
                            return true;
                        }
                        j++;
                    }
                    int i = mid - 1;
                    while (i >= 0 && nums[i] == target) { // 向左找
                        if (!used[i]) {
                            used[i] = true;
                            return true;
                        }
                        i--;
                    }
                    return false;
                }
            }
        }
        return false;
    }

}
