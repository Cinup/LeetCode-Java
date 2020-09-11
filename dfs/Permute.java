package leetcode.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        permute(nums, used, new ArrayDeque<>(), result);
        return result;
    }

    private void permute(int[] nums, boolean[] used,
                         Deque<Integer> current,
                         List<List<Integer>> result) {
        if (current.size() == nums.length) {
            List<Integer> list = new ArrayList<>(current);
            result.add(list);
            return;
        }
        for (int i = 0, l = nums.length; i < l; i++) {
            if (!used[i]) {
                current.addLast(nums[i]);
                used[i] = true;
                permute(nums, used, current, result);
                used[i] = false;
                current.removeLast();
            }
        }
    }
}
