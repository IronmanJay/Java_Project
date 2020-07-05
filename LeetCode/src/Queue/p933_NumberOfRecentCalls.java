package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class p933_NumberOfRecentCalls {

    // 创建队列
    private static Queue<Integer> queue;

    public static void main(String[] args) {
        RecentCounter();
        int ping1 = ping(1);
        int ping2 = ping(100);
        int ping3 = ping(3001);
        int ping4 = ping(3002);
        System.out.println(ping1 + " " + ping2 + " " + ping3 + " " + ping4);
    }

    public static void RecentCounter() {
        // 初始化队列
        queue = new LinkedList<>();
    }

    public static int ping(int t) {
        // 首先将当前值加入队列
        queue.offer(t);
        // 然后开始循环把不在[t-3000,t]这个范围的值poll出
        while (queue.peek() < t - 3000) {
            queue.poll();
        }
        // 最后返回当前队列的个数
        return queue.size();
    }

}
