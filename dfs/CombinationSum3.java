package leetcode.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSum3(k, n, 1, new ArrayDeque<>(), result);
        return result;
    }

    private void combinationSum3(int n, int target, int start, Deque<Integer> current, List<List<Integer>> result) {
        if (current.size() == n) {
            if (target == 0) {
                List<Integer> list = new ArrayList<>(current);
                result.add(list);
                return;
            } else {
                return;
            }
        }
        for (int i = start; i <= 9; i++) {
            if (i <= target) {
                current.addLast(i);
                combinationSum3(n, target - i, i + 1, current, result);
                current.removeLast();
            }else {
                return;
            }
        }
    }
}
