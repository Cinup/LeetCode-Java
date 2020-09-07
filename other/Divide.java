package leetcode.other;

/*
 * @description: 29.两数相除
 * @param: dividend被除数,divisor除数
 * @return: 商(取整)
 * @author: cp
 * @date: 2020/8/31
 */
public class Divide {
    public int divide(int dividend, int divisor) {
        int ans = -1;
        int sign = 1;
        if (dividend > 0) {
            sign = opposite(sign);
            dividend = opposite(dividend);
        }
        if (divisor > 0) {
            sign = opposite(sign);
            divisor = opposite(divisor);
        }
        //由于被除数和除数都是负数,如果dividend>divisor
        //说明|dividend|<|divisor|
        //即|dividend|/|divisor| < 1,结果就是0
        if (dividend > divisor) {
            return 0;
        }
        int originDividend = dividend;
        int originDivisor = divisor;
        dividend -= divisor;
        while (divisor >= dividend) {
            ans = ans + ans;
            dividend -= divisor;
            divisor += divisor;
        }

        int a = ans + opposite(divide(originDividend - divisor, originDivisor));
        if (a == Integer.MIN_VALUE) {
            if (sign > 0) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        } else {
            if (sign > 0) {
                return opposite(a);
            } else {
                return a;
            }
        }
    }

    /*
     * @description: 取相反数
     */
    private int opposite(int x) {
        return ~x + 1;
    }
}
