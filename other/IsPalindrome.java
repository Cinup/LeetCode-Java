package leetcode.other;

/*
 * @description: 9.回文数
 * @param: 整数
 * @return: 返回这个数是否为回文数,负数不可能为回文数。
 * @author: cp
 * @date: 2020/8/3
 */
public class IsPalindrome {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        //最后一位不能是0,例如10,100不可能是回文数
        if (x != 0 && (x % 10 == 0)) {
            return false;
        }
        int bit;
        int result = 0;
        while (x > 0) {
            bit = x % 10;
            result = result * 10 + bit;
            if (result > x)
                return false;
            if (result == x)
                return true;
            x = x / 10;
            if (result == x)
                return true;
        }
        return true;
    }
}
