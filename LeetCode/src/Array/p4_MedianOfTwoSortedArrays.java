package Array;

public class p4_MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        System.out.println("medianSortedArrays = " + medianSortedArrays);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 这一步是找出x两个数组长度较小的数组作为nums1，方便后续步骤
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        // 获得两个数组的长度
        int m = nums1.length;
        int n = nums2.length;
        // 根据公式得出需要的数组左边的个数
        int totalLeft = (m + n + 1) / 2;
        // 定义左指针和右指针，以nums1为准
        int left = 0;
        int right = m;
        // 开始循环，当nums1遍历完毕跳出循环，i是nums1数组的索引，j是nums2数组的索引
        while (left < right) {
            // 从nums1的中间开始
            int i = left + (right - left) / 2;
            // 左边需要的所有元素个数减去i就是nums2左边所有元素的个数
            int j = totalLeft - i;
            // 如果nums2[j-1] > nums1[i]，说明nums1的分界线需要向右，在[i right]这个区间继续寻找
            if (nums2[j - 1] > nums1[i]) {
                // 下一轮[i+1 right]这个区间继续寻找
                left = i + 1;
            } else {
                // 符合要求给right赋值,下一轮在[left i]这个区间继续寻找
                right = i;
            }
        }
        // 最终找到分界线
        int i = left;
        int j = totalLeft - i;
        // 找到分界线两边的值
        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];
        // 最后判断如果数组长度为奇数返回分界线左边最大值，如果是偶数，返回分界线两边值的一半
        if (((m + n) % 2) == 1) {
            return Math.max(nums1LeftMax, nums2LeftMax);
        } else {
            return (double) ((Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin))) / 2;
        }
    }

}
