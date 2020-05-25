package String;

public class p520_DetectCapital {

    public static void main(String[] args) {
        String word = "Leetcode";
        boolean res = detectCapitalUse(word);
        if (res) {
            System.out.println("符合全是大写，或者全是小写，或者首字母大写其余小写的规则");
        } else {
            System.out.println("不符合规则");
        }
    }

    public static boolean detectCapitalUse(String word) {
        // 将单词转成数组，方便后续处理
        char[] wds = word.toCharArray();
        // 大写字母个数
        int upWord = 0;
        // 小写字母个数
        int lowWord = 0;
        for (int i = 0; i < wds.length; i++) {
            if (wds[i] - 'a' < 0) {
                upWord++;
            } else {
                lowWord++;
            }
        }
        // 根据题意，如果全是大写或者全是小写，又或者首字母大写其余小写，返回true
        if (upWord == wds.length || lowWord == wds.length || (upWord == 1 && wds[0] < 'a')) {
            return true;
        } else {
            return false;
        }
    }

}
