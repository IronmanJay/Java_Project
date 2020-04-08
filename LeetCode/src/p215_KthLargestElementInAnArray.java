public class p215_KthLargestElementInAnArray {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        p215_KthLargestElementInAnArray p215_kthLargestElementInAnArray = new p215_KthLargestElementInAnArray();
        int kthLargest = p215_kthLargestElementInAnArray.findKthLargest(nums, 4);
        System.out.println(kthLargest);
    }

    public int findKthLargest(int[] nums, int k) {
        int[] sortRes = bubbleSort(nums);
        int[] reverseRes = reverse(sortRes);
        return reverseRes[k - 1];
    }

    public int[] reverse(int[] sortRes) {
        for (int i = 0; i < sortRes.length / 2; i++) {
            int temp = sortRes[i];
            sortRes[i] = sortRes[sortRes.length - 1 - i];
            sortRes[sortRes.length - 1 - i] = temp;
        }
        return sortRes;
    }

    public int[] bubbleSort(int[] nums) {
        int temp = 0; //临时变量
        boolean flag = false; // 标识变量，表示是否进行过交换
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    flag = true;
                    temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
            if (!flag) { // 在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false; // 重置flag，进行下次判断
            }
        }
        return nums;
    }

}
