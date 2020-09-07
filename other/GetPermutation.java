package leetcode.other;

import java.util.ArrayList;
import java.util.List;

/*
 * @description: 60.第k个排列
 * @param: n集合[1,2,3,…,n],k第k个排列
 * @return: 从小到大的第k个排列
 * @author: cp
 * @date: 2020/9/5
 */
public class GetPermutation {
    public String getPermutation(int n, int k) {
        List<String> number = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            number.add(String.valueOf(i));
        }
        return getPermutation(number, n, k);
    }

    private String getPermutation(List<String> number, int n, int k) {
        if (n == 1) {
            return number.get(0);
        }
        int group = factorial(n - 1);
        int index = (k - 1) / group;
        String num = number.get(index);
        number.remove(index);
        k = k % group;
        k = k == 0 ? group : k;
        return num + getPermutation(number, n - 1, k);
    }

    private int factorial(int n) {
        if (n <= 1)
            return 1;
        return n * factorial(n - 1);
    }
}
