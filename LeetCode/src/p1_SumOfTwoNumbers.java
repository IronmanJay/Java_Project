import java.util.HashMap;
import java.util.Map;

public class p1_SumOfTwoNumbers {

    public static void main(String[] args) {
        int nums[] = new int[]{2, 7, 11, 15};
        int target = 9;
        p1_SumOfTwoNumbers p1_sumOfTwoNumbers = new p1_SumOfTwoNumbers();
        int[] res = p1_sumOfTwoNumbers.twoSum(nums, target);
        System.out.println(res);
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}
