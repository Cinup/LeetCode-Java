package leetcode.dfs;

import java.util.*;

public class PermuteUnique {
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (len == 0) {
            return result;
        }
        Arrays.sort(nums);
        permuteUnique(nums, new ArrayDeque<>(len), new ArrayDeque<>(len), result);
        return result;
    }

    private void permuteUnique(int[] nums,
                               Deque<Integer> current, Deque<Integer> visited,
                               List<List<Integer>> result) {
        if (current.size() == nums.length) {
            List<Integer> list = new ArrayList<>(current);
            result.add(list);
            return;
        }
        for (int i = 0, l = nums.length; i < l; i++) {
            if (!visited.contains(i)) {
                if (i > 0 && !visited.contains(i - 1) && nums[i] == nums[i - 1])
                    continue;
                visited.addLast(i);
                current.addLast(nums[i]);
                permuteUnique(nums, current, visited, result);
                current.removeLast();
                visited.removeLast();
            }
        }
    }
}
