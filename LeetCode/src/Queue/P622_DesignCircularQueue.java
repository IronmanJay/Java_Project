package Queue;

public class P622_DesignCircularQueue {

    private static int[] queue; // 数组模拟队列
    private static int len; // 需要生成的长度
    private static int front; // 队列头指针
    private static int rear; // 队列尾指针

    public static void main(String[] args) {
        P622_DesignCircularQueue res1 = new P622_DesignCircularQueue(3);
        boolean res2 = enQueue(1);
        boolean res3 = enQueue(2);
        boolean res4 = enQueue(3);
        boolean res5 = enQueue(4);
        int res6 = Rear();
        boolean res7 = isFull();
        boolean res8 = deQueue();
        boolean res9 = enQueue(4);
        int res10 = Rear();
        System.out.println(res1 + " " + res2 + " " + res3 + " " + res4 + " " + res5 + " " + res6 + " " + res7 + " " + res8 + " " + res9 + " " + res10);
    }

    // 初始化队列
    public P622_DesignCircularQueue(int k) {
        len = k + 1; // 这里+1是因为要预留一个元素位置
        queue = new int[len];
    }

    // 向队列插入元素
    public static boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        queue[rear] = value;
        rear = (rear + 1) % len; // 取模就是转了一圈，如果到了队列最后一个，那么下一个就到了队列头
        return true;
    }

    // 弹出队列头元素
    public static boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % len; // 取模就是转了一圈，如果到了队列最后一个，那么下一个就到了队列头
        return true;
    }

    // 得到队列头元素
    public static int Front() {
        if (isEmpty()) {
            return -1;
        }
        return queue[(front + len) % len]; // 因为头指针指哪就是哪，所以直接返回，但是涉及到转圈的问题
    }

    // 得到队列尾元素
    public static int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return queue[(rear - 1 + len) % len]; // 因为尾指针最后一次+1了，所以队尾元素要-1，但是涉及到转圈的问题
    }

    // 判断队列是否空
    public static boolean isEmpty() {
        return rear == front;
    }

    // 判断队列是否满
    public static boolean isFull() {
        return (rear + 1) % len == front; // 这是循环队列的核心，取模就是转了一圈，预留一个元素位置
    }

}
