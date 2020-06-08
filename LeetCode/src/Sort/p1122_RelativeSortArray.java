package Sort;

import java.util.Arrays;

public class p1122_RelativeSortArray {

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        int[] res = relativeSortArray(arr1, arr2);
        System.out.println("res = " + Arrays.toString(res));
    }

    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 因为一共最多1000个数，因为下面不是从0开始，所以定义数组长度位1001
        int[] nums = new int[1001];
        // 定义结果数组，和arr1一样长度
        int[] res = new int[arr1.length];
        // 遍历arr1，统计每个元素的个数
        for (int arr : arr1) {
            nums[arr]++;
        }
        // 遍历arr2，将arr2在arr1中出现的元素按照arr2的顺序存入结果数组
        int index = 0;
        for (int arr : arr2) {
            // 如果对应位置的值>0，说明找到了arr2在arr1中的元素，那么就将这个数存入结果数组，有几个存几个，顺序和arr2一样，然后nums对应位置的值-1，减去了arr2这个元素
            while (nums[arr] > 0) {
                res[index++] = arr;
                nums[arr]--;
            }
        }
        // 遍历nums，将除去arr2中的元素进行排序，注意这里数组从头开始遍历，所以相当于排序了
        for (int i = 0; i < nums.length; i++) {
            // 这个while循环说明这个元素就是arr2中没有的，那么将它加入到结果数组
            while (nums[i] > 0) {
                res[index++] = i;
                nums[i]--;
            }
        }
        // 返回结果数组
        return res;
    }

}
