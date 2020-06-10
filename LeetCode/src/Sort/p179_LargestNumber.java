package Sort;

import java.util.Arrays;
import java.util.Comparator;

public class p179_LargestNumber {

    public static void main(String[] args) {
        int[] nums = {3, 30, 34, 5, 9};
        String res = largestNumber(nums);
        System.out.println("res = " + res);
    }

    public static String largestNumber(int[] nums) {
        // 这个数组就是为了存储原来数字对应的字符串数组
        String[] arrs = new String[nums.length];
        int i = 0;
        // 给这个新的字符串数组赋值
        for (int num : nums) {
            arrs[i++] = String.valueOf(num);
        }
        // 新建比较排序器，如果s2+s1>s1+s2那么就交换两个元素，否则不交换，最后得到一个按照比较器排序的字符串数组
        Arrays.sort(arrs, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s2 + s1).compareTo(s1 + s2);
            }
        });
        // 如果首位为0，直接返回即可
        if (arrs[0] == "0") {
            return "0";
        }
        // 将结果字符串数组转换成字符串返回
        StringBuffer sb = new StringBuffer();
        for (String arr : arrs) {
            sb.append(arr);
        }
        return sb.toString();
    }

}
