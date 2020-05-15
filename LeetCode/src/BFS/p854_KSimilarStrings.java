package BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class p854_KSimilarStrings {

    public static void main(String[] args) {
        String A = "abac";
        String B = "baca";
        int res = kSimilarity(A, B);
        System.out.println("交换了" + res + "次");
    }

    public static int kSimilarity(String A, String B) {
        // 定义存储队列
        Queue<String> q = new LinkedList<>();
        // 定义HashSet，利用其不存放重复元素的性质，通过不断地更新，不断地判断是否到达最后的状态
        Set<String> visit = new HashSet();
        // 将A加入队列，这个就是初始字符串，通过不断地和B判断是否到达最终状态
        q.add(A);
        // 把A也加入HashSet，因为我们要不断地更新这个值，
        visit.add(A);
        // step就是最终的k
        int step = 0;
        while (!q.isEmpty()) {
            // 通过遍历每一个队列中的每个状态，来判断是否到达最终状态
            for (int k = q.size(); k > 0; k--) {
                String cur = q.poll();
                // 如果已经达到最终状态，返回step
                if (cur.equals(B)) {
                    return step;
                }
                int i = 0;
                // 找出A和B不相等的那个字符的index
                while (i < A.length() && cur.charAt(i) == B.charAt(i)) {
                    i++;
                }
                // 从不相等的字符index的下一个开始遍历
                for (int j = i + 1; j < A.length(); j++) {
                    // 假设i，j是A要交换的index，那么当A和B中第i，j个字符相等时，不需要交换，同事要确保B的第j个字符不等于A的第i个字符
                    if (cur.charAt(j) == B.charAt(j) || B.charAt(j) != cur.charAt(i)) {
                        continue;
                    }
                    StringBuilder sb = new StringBuilder(cur);
                    // 交换不相等的字符，更新状态
                    sb.setCharAt(i, cur.charAt(j));
                    sb.setCharAt(j, cur.charAt(i));
                    // 转换成String字符串
                    String next = sb.toString();
                    // 如果状态队列中没有当前这个状态才加入队列
                    if (!visit.contains(next)) {
                        visit.add(next);
                        q.add(next);
                    }
                }
            }
            // 每循环一次说明进行了一次交换
            step++;
        }
        return -1;
    }

}
