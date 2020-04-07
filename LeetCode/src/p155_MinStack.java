import java.util.Stack;

public class p155_MinStack {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int min = minStack.getMin();
        System.out.println("最小值为:" + min);
        minStack.pop();
        int top = minStack.top();
        System.out.println("顶值为:" + top);
        int min1 = minStack.getMin();
        System.out.println("最小值为:" + min1);
    }

}

class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> helper_stack;

    public MinStack() {
        stack = new Stack<>();
        helper_stack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if(helper_stack.isEmpty() || x <= helper_stack.peek()){
            helper_stack.push(x);
        }
    }

    public void pop() {
        if(stack.pop().equals(helper_stack.peek())){
            helper_stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return helper_stack.peek();
    }
}