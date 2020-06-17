package Stack;

import java.util.Stack;

public class p150_EvaluateReversePolishNotation {

    public static void main(String[] args) {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int res = evalRPN(tokens);
        System.out.println("res = " + res);
    }

    public static int evalRPN(String[] tokens) {
        // 创建栈，这个栈只存放数字和数字运算的结果
        Stack<Integer> stack = new Stack<Integer>();
        // 遍历每一个元素
        for (String token : tokens) {
            // 如果是运算符，则从栈中取出两个元素进行运算，因为运算符在tokens里是按顺序存放的，所以无需担心运算律问题，最后将结果保存进入stack。注：减法和除法要注意弹出的顺序
            if (token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (token.equals("-")) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(num1 - num2);
            } else if (token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (token.equals("/")) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(num1 / num2);
            }
            // 如果是数字则压入栈
            else {
                stack.push(Integer.parseInt(token));
            }
        }
        // 返回结果
        return stack.pop();
    }

}
