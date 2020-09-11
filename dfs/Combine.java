package leetcode.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Combine {
    /*
     * @description: 77.组合
     * @param: n 范围1-n, k 数量
     * @return: 1-n中选取k个的所有组合
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combine(n, k, 1, new ArrayDeque<>(), result);
        return result;
    }

    /*
     * @param: n范围1-n,k选取数量,start起始数字,current当前已经选中数字,result结果集
     */
    private void combine(int n, int k, int start, Deque<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            List<Integer> temp = new ArrayList<>(current);
            result.add(temp);
        } else {
            //i <= n - k + current.size() + 1 进行剪枝
            //即[start,n]元素个数已经小于k-current.size(),不可能再从中凑出到k个元素了
            // n - start + 1 < k - current.size() --> start > n - k+current.size() + 1
            for (int i = start; i <= n - k + current.size() + 1; i++) {
                if (!current.contains(i)) {
                    current.addLast(i);
                    combine(n, k, i + 1, current, result);
                    current.removeLast();
                }
            }
        }
    }
}
