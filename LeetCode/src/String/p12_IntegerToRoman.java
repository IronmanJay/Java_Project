package String;

public class p12_IntegerToRoman {

    public static void main(String[] args) {
        int num = 1994;
        System.out.println("第一种方法");
        String resI = intToRomanI(num);
        System.out.println("resI = " + resI);
        System.out.println("第二种方法");
        String resII = intToRomanII(num);
        System.out.println("resII = " + resII);
    }

    public static String intToRomanI(int num) {
        // 结果数组
        String res = "";
        // 罗马数字对应的数字值
        int value[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        // 数字对应罗马数字的值
        String str[] = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        // 一共十三个对应关系，循环十三遍
        for (int i = 0; i < 13; i++) {
            // 当while循环继续的时候，说明当前位有值
            while (num >= value[i]) {
                // 当前数字减去这一位
                num -= value[i];
                // 结果数组加上这一位对应的罗马数字
                res += str[i];
            }
        }
        // 返回结果
        return res;
    }

    public static String intToRomanII(int num) {
        // 根据题目，最多四位数，将每一位提取出来，分别匹配
        int a = num % 10;
        num /= 10;
        int b = num % 10;
        num /= 10;
        int c = num % 10;
        num /= 10;
        int d = num % 10;
        return num4(d) + num3(c) + num2(b) + num1(a);
    }

    // 千位
    public static String num4(int d) {
        String res = "";
        switch (d) {
            case 1:
                res = "M";
                break;
            case 2:
                res = "MM";
                break;
            case 3:
                res = "MMM";
                break;
            case 0:
                res = "";
                break;
        }
        return res;
    }

    // 百位
    public static String num3(int c) {
        String res = "";
        switch (c) {
            case 1:
                res = "C";
                break;
            case 2:
                res = "CC";
                break;
            case 3:
                res = "CCC";
                break;
            case 4:
                res = "CD";
                break;
            case 5:
                res = "D";
                break;
            case 6:
                res = "DC";
                break;
            case 7:
                res = "DCC";
                break;
            case 8:
                res = "DCCC";
                break;
            case 9:
                res = "CM";
                break;
            case 0:
                res = "";
                break;
        }
        return res;
    }

    // 十位
    public static String num2(int b) {
        String res = "";
        switch (b) {
            case 1:
                res = "X";
                break;
            case 2:
                res = "XX";
                break;
            case 3:
                res = "XXX";
                break;
            case 4:
                res = "XL";
                break;
            case 5:
                res = "L";
                break;
            case 6:
                res = "LX";
                break;
            case 7:
                res = "LXX";
                break;
            case 8:
                res = "LXXX";
                break;
            case 9:
                res = "XC";
                break;
            case 0:
                res = "";
                break;
        }
        return res;
    }

    // 个位
    public static String num1(int a) {
        String res = "";
        switch (a) {
            case 1:
                res = "I";
                break;
            case 2:
                res = "II";
                break;
            case 3:
                res = "III";
                break;
            case 4:
                res = "IV";
                break;
            case 5:
                res = "V";
                break;
            case 6:
                res = "VI";
                break;
            case 7:
                res = "VII";
                break;
            case 8:
                res = "VIII";
                break;
            case 9:
                res = "IX";
                break;
            case 0:
                res = "";
                break;
        }
        return res;
    }


}
