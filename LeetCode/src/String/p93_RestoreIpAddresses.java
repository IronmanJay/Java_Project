package String;

import java.util.ArrayList;
import java.util.List;

public class p93_RestoreIpAddresses {

    public static void main(String[] args) {
        String s = "25525511135";
        List<String> res = restoreIpAddresses(s);
        System.out.println("res = " + res);
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        backTrack(s, 0, new ArrayList<>(), res);
        return res;
    }

    /**
     * 进行回溯判断
     *
     * @param s   要判断的字符串
     * @param pos 当前遍历到s字符串中的位置
     * @param cur 当前存放已经确定好的ip段的数量
     * @param res 返回结果
     */
    public static void backTrack(String s, int pos, List<String> cur, List<String> res) {
        // 首先进行判断，如果已经分成了四分，那么判断里面的内容是否符合要求
        if (cur.size() == 4) {
            // 继续判断，如果pos已经到了s的最后，说明所有的字符已经遍历完毕
            if (pos == s.length()) {
                // 那么就将这一个符合要求的加入到结果数组
                res.add(String.join(".", cur));
            }
            return;
        }

        // 因为每位地址是三位数，所以需要考虑到每一位的情况，需要遍历3次
        for (int i = 1; i <= 3; i++) {
            // 如果当前位置距离s末尾小于3就不用继续分段了，因为就剩两个了，直接跳出这次循环
            if (pos + i > s.length()) {
                break;
            }
            // 开始分段，一个一个分段，从每位地址的第一位到第三位
            String segment = s.substring(pos, pos + i);
            // 判断分段的数是否符合要求，首先起始位置不能为零，并且分段的长度不能大于1，且int类型不能大于255
            if (segment.startsWith("0") && segment.length() > 1 || (i == 3) && Integer.parseInt(segment) > 255) {
                continue;
            }
            // 将符合要求的加入到cur数组中
            cur.add(segment);
            // 继续遍历下一个位置
            backTrack(s, pos + i, cur, res);
            // 如果进行到这里，说明还没结束整个循环，那么回溯到上一个元素，继续进行判断，删除最后一个元素
            cur.remove(cur.size() - 1);
        }
    }

}
