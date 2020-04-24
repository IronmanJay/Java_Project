package String;

public class p14_LongestCommonPrefix {

    public static void main(String[] args) {
        String strs[] = new String[]{"flower", "flow", "flight"};
        p14_LongestCommonPrefix p14_longestCommonPrefix = new p14_LongestCommonPrefix();
        String res = p14_longestCommonPrefix.longestCommonPrefix(strs);
        System.out.println(res);
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

}
