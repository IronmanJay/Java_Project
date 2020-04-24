package Stack;

import java.util.LinkedList;
import java.util.Queue;

public class p933_RecentRequests {

    private Queue<Integer> q;

    public void RecentCounter() {
        q = new LinkedList<Integer>();
    }

    public int ping(int t) {
        q.offer(t);
        while (q.peek() < t - 3000) {
            q.poll();
        }
        return q.size();
    }

}
