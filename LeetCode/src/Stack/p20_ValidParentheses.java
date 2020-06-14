package Stack;

import java.util.Stack;

public class p20_ValidParentheses {

    public static void main(String[] args) {
        String s = "([)]";
        boolean res = isValid(s);
        System.out.println("res = " + res);
    }

    public static boolean isValid(String s) {
        // 新建一个栈
        Stack<Character> stack = new Stack<Character>();
        // 利用栈先进先出的特性，传入一个字符，他反向对应的字符压入栈，当所有的左边字符都遍历完之后，每个字符反向对应的字符都已入栈
        // 这个时候还在继续遍历字符串，也开始从栈中弹出元素，如果这个时候弹出的元素正好和遍历到的字符串相等，则说明这个括号有效
        // 因为当前这个字符最后进来的，和它反向对应的字符在栈顶，依次遍历下去，如果最后所有元素都弹出，栈空，说明所有括号都有效
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }

}
