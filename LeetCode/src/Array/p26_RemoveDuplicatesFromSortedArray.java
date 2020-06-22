package Array;

public class p26_RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int res = removeDuplicates(nums);
        System.out.println("res = " + res);
    }

    public static int removeDuplicates(int[] nums) {
        // 定义快慢指针
        int slowIndex = 0; // 慢指针主要作用是对比元素是否相等和预留不相等的元素位置
        int quickIndex = 1; // 快指针的主要作用是对比元素是否相等和遍历整个数组
        // 循环整个数组
        while (quickIndex < nums.length) {
            // 当两个值相等的时候，我们并不交换，而是使快指针向前走，跳过当前元素，这样的目的是找出所有的重复元素
            if (nums[slowIndex] == nums[quickIndex]) {
                quickIndex++;
            }
            // 如果两个值不相等，那么慢指针就+1，只+1，预留一个位置给不相等的元素，因为数组有序，直接将第一个不相等的元素放到慢指针指定位置，然后快指针继续循环
            else {
                slowIndex++;
                nums[slowIndex] = nums[quickIndex];
                quickIndex++;
            }
        }
        // 打印最终的数组
        for (int i = 0; i < slowIndex + 1; i++) {
            System.out.println(nums[i]);
        }
        return slowIndex + 1;
    }

}
