package Stack;

import java.util.Stack;

public class I9_UsingTwoStacksToImplementQueues {

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        int res1 = cQueue.deleteHead();
        System.out.println(res1);
        cQueue.appendTail(5);
        cQueue.appendTail(2);
        int res2 = cQueue.deleteHead();
        System.out.println(res2);
        int res3 = cQueue.deleteHead();
        System.out.println(res3);
    }

}

class CQueue {

    // 新建一个进栈的栈和出栈的栈
    Stack<Integer> stack_in;
    Stack<Integer> stack_out;

    public CQueue() {
        stack_in = new Stack<Integer>();
        stack_out = new Stack<Integer>();
    }

    /**
     * 向stack_in添加元素，先进后出
     *
     * @param value 要添加的元素
     */
    public void appendTail(int value) {
        stack_in.add(value);
    }

    /**
     * stack_out出栈，因为队列是先进先出，所以用两个栈模拟数组
     * 本质就是stack_in进入的最后一个元素（也就是先进的元素）
     * 作为stack_out的第一个元素（也就是先出的元素）
     * 也就实现了，元素的先进先出，从而模拟队列
     *
     * @return
     */
    public int deleteHead() {
        if (stack_out.isEmpty()) {
            if (stack_in.isEmpty()) {
                return -1;
            }
            while (!stack_in.isEmpty()) {
                stack_out.push(stack_in.pop());
            }
            return stack_out.pop();
        } else {
            return stack_out.pop();
        }
    }

}
