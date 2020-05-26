package String;

public class p521_LongestUncommonSubsequenceI {

    public static void main(String[] args) {
        String a = "aaa";
        String b = "bbb";
        int res = findLUSlength(a, b);
        System.out.println("res = " + res);
    }

    public static int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }

}
