package Sum;

public class p7_IntegerInversion {

    public static void main(String[] args) {
        int x = 123;
        p7_IntegerInversion p7_integerInversion = new p7_IntegerInversion();
        int res = p7_integerInversion.reverse(x);
        System.out.println(res);
    }

    public int reverse(int x) {
        if (x / 10 == 0) { // 取值在-9~9之间的直接返回
            return x;
        }
        int y = 0;
        while (x != 0) {
            if (y > Integer.MAX_VALUE / 10 || y < Integer.MIN_VALUE / 10) { // 溢出
                return 0;
            }
            y *= 10; // 可以理解为向前进
            y += x % 10; // 取出z的最后一位，加上y
            x /= 10; // 继续取x的前面所有位数
        }
        return y; // 返回最终结果
    }

}
