package leetcode.dfs;

import java.util.*;

public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        combinationSum2(candidates, target, 0, new ArrayDeque<>(), result);
        return result;
    }

    private void combinationSum2(int[] nums, int target, int start, Deque<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            List<Integer> list = new ArrayList<>(path);
            result.add(list);
            return;
        }
        if (target > 0) {
            for (int i = start, length = nums.length; i < length; i++) {
                if (nums[i] <= target) {
                    //上一轮已经选过直接跳过
                    if (i > start && nums[i] == nums[i - 1])
                        continue;
                    path.addLast(nums[i]);
                    combinationSum2(nums, target - nums[i], i + 1, path, result);
                    path.removeLast();
                }else{
                    return;
                }
            }
        }
    }
}
