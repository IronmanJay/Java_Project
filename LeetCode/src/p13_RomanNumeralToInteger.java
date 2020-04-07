public class p13_RomanNumeralToInteger {

    public static void main(String[] args) {
        String s = "III";
        p13_RomanNumeralToInteger p13_romanNumeralToInteger = new p13_RomanNumeralToInteger();
        int res = p13_romanNumeralToInteger.romanToInt(s);
        System.out.println(res);
    }

    public int romanToInt(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int lastNum = getValue(s.charAt(i));
            if (preNum < lastNum) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = lastNum;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

}
