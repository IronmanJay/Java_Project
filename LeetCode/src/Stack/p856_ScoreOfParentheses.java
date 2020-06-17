package Stack;

import java.util.Stack;

public class p856_ScoreOfParentheses {

    public static void main(String[] args) {
        String S = "(()(()))";
        int res = scoreOfParentheses(S);
        System.out.println("res = " + res);
    }

    public static int scoreOfParentheses(String S) {
        // 新建一个栈，这个栈不是用来存放字符的，而是字符对应的得分
        Stack<Integer> stack = new Stack<Integer>();
        // 初始当前深度为0，得分为0
        stack.push(0);
        // 遍历每一个字符，分别对三种情况说明（注：其实（）和（）（）型判断得分的方式一样，只是（AB）型判断的方式不一样）
        // 如果遇到（就将0入栈，说明还没有与之配对的，如果遇到）说明遇到了一个与之配对的，那么pop出两个元素，对应的分数就为1，再将新的分数push
        // ①、如果在上面的情况遇到了（，则继续将0入栈，还没有与之配对的
        // ②、如果在最上面的初始条件又遇到了），则说明现在肯定有（AB）型的，则pop出两个元素，这两个元素对应就是AB的分数，将其*2，但是还要加上最后一次pop的分数，因为要计算分数和，这里使用max是因为第一次）进入可能正好形成（），但是如果再次进入），说明肯定事（AB）型，所以要取最大值
        for (char c : S.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int c1 = stack.pop();
                int c2 = stack.pop();
                stack.push(c2 + Math.max(2 * c1, 1));
            }
        }
        return stack.pop();
    }

}
