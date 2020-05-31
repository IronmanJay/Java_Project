package BinarySearch;

public class p367_ValidPerfectSquare {

    public static void main(String[] args) {
        int num = 256;
        boolean res = isPerfectSquare(num);
        System.out.println(num + "是完全平方数");
    }

    public static boolean isPerfectSquare(int num) {
        // 这种情况也就是num=1，特例直接返回
        if (num < 2) {
            return true;
        }
        // 下面就是常规的二分查找方法，只要从中间开始，如果中间值的平方=num，直接返回true，如果中间值的平方<num，left位置变换，如果中间值的平方>num，right位置变换,，要注意一点用long类型，因为后面的用例超出了int的范围，会导致超出时间限制
        long left = 0;
        long right = num;
        long mid;
        while (left <= right) {
            mid = (left + right) / 2;
            long square = mid * mid;
            if (square == num) {
                return true;
            } else if (square < num) {
                left = mid + 1;
            } else if (square > num) {
                right = mid - 1;
            }
        }
        return false;
    }

}
