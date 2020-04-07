public class p9_PalindromeNumber {

    public static void main(String[] args) {
        int x = 121;
        p9_PalindromeNumber p9_palindromeNumber = new p9_PalindromeNumber();
        boolean res = p9_palindromeNumber.isPalindrome(x);
        System.out.println(res);
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) {
                return false;
            }
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

}
