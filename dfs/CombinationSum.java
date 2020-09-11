package leetcode.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSum(candidates, target, 0, new ArrayDeque<>(), result);
        return result;
    }

    private void combinationSum(int[] nums, int target, int start, Deque<Integer> current, List<List<Integer>> res) {
        if (target == 0) {
            List<Integer> list = new ArrayList<>(current);
            res.add(list);
            return;
        }
        if (target > 0) {
            for (int i = start, length = nums.length; i < length; i++) {
                if (nums[i] <= target) {
                    current.addLast(nums[i]);
                    combinationSum(nums, target - nums[i], i, current, res);
                    current.removeLast();
                }
            }
        }
    }
}
