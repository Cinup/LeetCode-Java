package leetcode.other;

import java.util.*;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, target, path, result);
        return result;
    }

    private void dfs(int[] nums, int target, Deque<Integer> path, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            List<Integer> list = new ArrayList<>(path);
            Collections.sort(list);
            if (!res.contains(list))
                res.add(list);
            return;
        }
        if (target > 0) {
            for (int i = 0, length = nums.length; i < length; i++) {
                if (nums[i] <= target) {
                    path.addLast(nums[i]);
                    dfs(nums, target - nums[i], path, res);
                    path.removeLast();
                }
            }
        }
    }
}
