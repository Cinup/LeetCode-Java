package leetcode.other;

/*
 * @description: 441.排列硬币
 * @param: n个硬币
 * @return: 第k行有k个硬币,最多可以排多少行
 * @author: cp
 * @date: 2020/8/31
 */
public class ArrangeCoins {
    public int arrangeCoins(int n) {
        int result = 0;
        for (int i = 1; ; i++) {
            if ((n = (n - i)) >= 0) {
                result++;
            } else break;
        }
        return result;
    }
}
