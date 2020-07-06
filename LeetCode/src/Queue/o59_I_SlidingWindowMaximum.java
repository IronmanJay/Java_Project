package Queue;

import java.util.Arrays;
import java.util.LinkedList;

public class o59_I_SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] res = maxSlidingWindow(nums, k);
        System.out.println("res = " + Arrays.toString(res));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        // 如果列表为空直接返回
        if (nums.length == 0) {
            return nums;
        }
        // 定义队列
        LinkedList<Integer> queue = new LinkedList<>();
        // 定义结果数组
        int[] res = new int[nums.length - k + 1];
        // 定义结果数组下标
        int index = 0;
        // 开始循环
        for (int i = 0; i < nums.length; i++) {
            // 因为我们要维护这个队列里面的数据是从大到小的，所以如果队列最后一个元素小于等于数组当前值，那么弹出队列最后一个元素，加入新元素
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            // 下面就把数组当前值加入到队列最后面，顺序仍然是从大到小
            queue.addLast(i);
            // 如果窗口已经划过了队列头部的元素，那么这个元素用不到，需要把后面的元素“升级”，就将头元素弹出队列
            if (queue.peekFirst() == i - k) {
                queue.pollFirst();
            }
            // 如果达到一个窗口大小，那么结果数组就加入当前队列最大值，除了第一次需要等三个值为一个窗口，后面进来一个值就能形成一个窗口
            if (i >= (k - 1)) {
                res[index++] = nums[queue.peekFirst()];
            }
        }
        return res;
    }

}
