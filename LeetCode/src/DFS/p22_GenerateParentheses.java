package DFS;

import java.util.ArrayList;
import java.util.List;

public class p22_GenerateParentheses {

    public static void main(String[] args) {
        p22_GenerateParentheses p22_generateParentheses = new p22_GenerateParentheses();
        List<String> res = p22_generateParentheses.generateParenthesis(3);
        System.out.println("res = " + res);
    }

    public List<String> generateParenthesis(int n) {
        // 生成结果集
        List<String> res = new ArrayList<>();
        // 如果n=0，直接返回
        if (n == 0) {
            return res;
        }
        // 使用深度优先遍历搜索所有的可能结果
        dfs("", n, n, res);
        // 返回结果集
        return res;
    }

    private void dfs(String curStr, int left, int right, List<String> res) {
        // 因为每次都是从左括号开始，所以最后一定以右括号结束，并且左括号搜索中的数量一定大于右括号的数量
        // 每次搜索都使用curStr拼接，都是新的字符串变量，所以无需回溯
        // 如果左括号和右括号剩余的数量都为0，那么作为递归的终止条件，将每一次符合要求的搜索结果加入到res中，并返回
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }
        // 如果左括号剩余的数量大于右括号，那么直接返回，不符合要求
        if (left > right) {
            return;
        }
        // 向左递归
        if (left > 0) {
            // 左括号剩余数量-1
            dfs(curStr + "(", left - 1, right, res);
        }
        // 向右递归
        if (right > 0) {
            // 右括号剩余数量-1
            dfs(curStr + ")", left, right - 1, res);
        }
    }

}
