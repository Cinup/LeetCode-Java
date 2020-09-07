package leetcode.other;

import java.util.*;

public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[candidates.length];
        dfs(candidates, target, path, used, result);
        return result;
    }

    private void dfs(int[] nums, int target, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
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
                if (!used[i] && nums[i] <= target) {
                    used[i] = true;
                    path.addLast(nums[i]);
                    dfs(nums, target - nums[i], path, used, res);
                    path.removeLast();
                    used[i] = false;
                }
            }
        }
    }
}
