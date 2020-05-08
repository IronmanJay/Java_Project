package DynamicProgramming;

public class p416_PartitionEqualSubsetSum {

    public static void main(String[] args) {
        p416_PartitionEqualSubsetSum p416_partitionEqualSubsetSum = new p416_PartitionEqualSubsetSum();
        int[] nums = {1,5,11,5};
        boolean res = p416_partitionEqualSubsetSum.canPartition(nums);
        if(res){
            System.out.println("可以");
        }else{
            System.out.println("不可以");
        }
    }

    public boolean canPartition(int[] nums) {
        // 定义数组行数
        int len = nums.length;
        // 准备求和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 只有sum不为奇数才可以继续
        if (sum % 2 != 0) {
            return false;
        }
        // dp[i][j] = x 表示，对于前 i 个物品，当前背包的容量为 j 时，若 x 为 true，则说明可以恰好将背包装满，若 x 为 false，则说明不能恰好将背包装满,当然，最后返回最后一个位置的元素
        boolean[][] dp = new boolean[len + 1][sum / 2 + 1];
        // 因为自己本身这个元素只能填满和自己相等的背包，所以置为true
        for (int i = 0; i <= len; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= sum / 2; j++) {
                // 如果当前元素的大小小于背包的剩余容量，那么说明可以放入背包，延续上一个状态
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    // 反之如果不可以放入，那么依然延续上一个状态
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        // 最后返回最后一个元素的状态
        return dp[len][sum / 2];
    }

}
