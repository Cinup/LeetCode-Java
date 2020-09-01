package leetcode.other;

/*
 * @description: 7.整数反转
 * @param: 整数
 * @return: 反转后的整数
 * @author: cp
 * @date: 2020/8/3
 */
public class Reverse {
    public int reverse(int x) {
        int result = 0;
        int tmpResult = 0;
        int bit;
        while (x != 0) {
            bit = x % 10;
            x = x / 10;
            tmpResult = tmpResult * 10 + bit;
            if (result != 0 && tmpResult / result < 10)
                return 0;
            result = tmpResult;

        }
        return result;
    }
}
