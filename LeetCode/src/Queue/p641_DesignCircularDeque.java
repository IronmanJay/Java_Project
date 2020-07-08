package Queue;

public class p641_DesignCircularDeque {

    private static int[] queue; // 数组模拟队列
    private static int front; // 队列头指针
    private static int rear; // 队列尾指针
    private static int len; // 需要生成的长度

    public static void main(String[] args) {
        p641_DesignCircularDeque res1 = new p641_DesignCircularDeque(4);
        boolean res2 = insertFront(9);
        boolean res3 = deleteFront();
        int res4 = getRear();
        int res5 = getFront();
        int res6 = getFront();
        boolean res7 = deleteFront();
        boolean res8 = insertFront(6);
        boolean res9 = insertLast(5);
        boolean res10 = insertFront(9);
        int res11 = getFront();
        boolean res12 = insertFront(6);
        System.out.println(res1 + " " + res2 + " " + res3 + " " + res4 + " " + res5 + " " + res6 + " " + res7 + " " + res8 + " " + res9 + " " + res10 + " " + res11 + " " + res12);
    }

    // 初始化双端队列
    public p641_DesignCircularDeque(int k) {
        len = k + 1; // 这里+1是因为要预留一个元素位置
        queue = new int[len];
        front = 0;
        rear = 0;
    }

    // 在双端队列头插入元素
    public static boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        front = (front - 1 + len) % len; // 这里是和622不同的地方，因为有可能是-1，所有要+len之后再取模，这样就能形成“环”
        queue[front] = value;
        return true;
    }

    // 在双端队列尾插入元素
    public static boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        queue[rear] = value;
        rear = (rear + 1) % len; // 取模就是转了一圈，如果到了队列最后一个，那么下一个就到了队列头
        return true;
    }

    // 删除双端队列头元素
    public static boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % len; // 这里也是和622不同的地方，因为front就指向当前元素，所以只需让他转圈即可
        return true;
    }

    // 删除双端队列尾元素
    public static boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        rear = (rear - 1 + len) % len; // 因为尾指针最后一次+1了，所以队尾元素要-1，但是涉及到转圈的问题
        return true;
    }

    // 得到双端队列头元素
    public static int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return queue[(front + len) % len]; // 因为头指针指哪就是哪，所以直接返回，但是涉及到转圈的问题
    }

    // 得到双端队列尾元素
    public static int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return queue[(rear - 1 + len) % len]; // 因为尾指针最后一次+1了，所以队尾元素要-1，但是涉及到转圈的问题
    }

    // 判断双端队列是否为空
    public static boolean isEmpty() {
        return rear == front;
    }

    // 判断双端队列是否满
    public static boolean isFull() {
        return (rear + 1) % len == front;
    }

}
