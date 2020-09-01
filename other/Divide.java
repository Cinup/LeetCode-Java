package leetcode.other;

/*
 * @description: 29.两数相除
 * @param: dividend被除数,divisor除数
 * @return:
 * @author: cp
 * @date: 2020/8/31
 */
public class Divide {
    public int divide(int dividend, int divisor) {
        //负数溢出
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1)
                return Integer.MAX_VALUE;
            if (divisor == 1)
                return Integer.MIN_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }
        boolean negative = false;
        //被除数和除数中有负数
        if (((dividend >>> 31) | (divisor >>> 31)) == 1) {
            //结果是负数
            if (((dividend >>> 31) & (divisor >>> 31)) != 1) {
                negative = true;
            }
            dividend = dividend < 0 ? dividend : -dividend;
            divisor = divisor < 0 ? divisor : -divisor;
        }
        int ans = 0;
        while (dividend > 0) {
            dividend -= divisor;
            if (dividend >= 0)
                ans++;
        }
        return negative ? -ans : ans;
    }
}
