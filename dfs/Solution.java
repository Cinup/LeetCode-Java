package leetcode.dfs;

import java.util.*;

/*
 * @description: 使用深度优先遍历的题目,经常和回溯一起用
 */
public class Solution {
    /*
     * @description: 39.组合总和
     * @param: candidates:无重复元素的数组,target:目标数,所有数字都是正整数
     * @return: candidates中所有可以使数字和为target的组合,每个candidates[i]可以重复选取
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSum(candidates, target, 0, new ArrayDeque<>(), result);
        return result;
    }

    private void combinationSum(int[] nums, int target, int start, Deque<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (target > 0) {
            for (int i = start, length = nums.length; i < length; i++) {
                if (nums[i] <= target) {
                    current.addLast(nums[i]);
                    combinationSum(nums, target - nums[i], i, current, result);
                    current.removeLast();
                }
            }
        }
    }

    /*
     * @description: 40.组合总和II
     * @param: candidates:有重复元素数组,target:目标数,所有数字都是正整数
     * @return: candidates中所有可以使数字和为target的组合,每个candidates[i]只能被选取一次
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        combinationSum2(candidates, target, 0, new ArrayDeque<>(), result);
        return result;
    }

    private void combinationSum2(int[] nums, int target, int start, Deque<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (target > 0) {
            for (int i = start, length = nums.length; i < length; i++) {
                if (nums[i] <= target) {
                    //上一轮已经选过直接跳过
                    if (i > start && nums[i] == nums[i - 1])
                        continue;
                    current.addLast(nums[i]);
                    combinationSum2(nums, target - nums[i], i + 1, current, result);
                    current.removeLast();
                } else {
                    return;
                }
            }
        }
    }

    /*
     * @description: 216.组合总和III
     * @param: k:数字个数, n:和
     * @return: 1-9中所有k个数相加之和为n的组合,每种组合中不存在重复的数字。
     */
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
            }
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (i <= target) {
                current.addLast(i);
                combinationSum3(n, target - i, i + 1, current, result);
                current.removeLast();
            } else {
                return;
            }
        }
    }

    /*
     * @description: 46.全排列
     * @param: nums:无重复元素的数组
     * @return: 所有全排列
     */
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

    /*
     * @description: 47.全排列II
     * @param: nums:可能包含重复元素的数组
     * @return: 所有不重复的全排列
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
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

    /*
     * @description: 77.组合
     * @param: n:数字范围1-n, k:数字个数
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

    /*
     * @description: 78.子集
     * @param: nums:不含重复元素的整数数组
     * @return: 所有可能的子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //问题分解为77题从n个不重复数中取k个,k从0～n遍历即可
        for (int i = 0, l = nums.length; i <= nums.length; i++) {
            List<List<Integer>> currentResult = new ArrayList<>();
            subsets(nums, i, 0, new ArrayDeque(), currentResult);
            result.addAll(currentResult);
        }
        return result;
    }

    /*
     * @description:从nums中选取n个
     */
    private void subsets(int[] nums, int n, int start, Deque current, List<List<Integer>> currentResult) {
        if (current.size() == n) {
            currentResult.add(new ArrayList<>(current));
            return;
        }
        for (int i = start, l = nums.length; i < l; i++) {
            current.addLast(nums[i]);
            subsets(nums, n, i + 1, current, currentResult);
            current.removeLast();
        }
    }

    /*
     * @description: 90.子集II
     * @param: nums:包含重复元素的整数数组
     * @return: 所有可能的子集
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        //类似78题,只需要考虑重复元素
        for (int i = 0, l = nums.length; i <= l; i++) {
            List<List<Integer>> currentResult = new ArrayList<>();
            subsetsWithDup(nums, i, 0, new ArrayDeque(), currentResult);
            result.addAll(currentResult);
        }
        return result;
    }

    private void subsetsWithDup(int[] nums, int n, int start, Deque current, List<List<Integer>> currentResult) {
        if (current.size() == n) {
            currentResult.add(new ArrayList<>(current));
            return;
        }
        for (int i = start, l = nums.length; i < l; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            current.addLast(nums[i]);
            subsetsWithDup(nums, n, i + 1, current, currentResult);
            current.removeLast();
        }
    }

    /*
     * @description: 79.单词搜索
     * @param: board二维字符数组, word:字符串
     * @return: board路径中是否包含word中所有的字符,要求路径连续,且字符顺序一致
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        if (m == 0) {
            return word.isEmpty();
        }
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (exist(board, i, j, word, 0))
                    return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int row, int col, String word, int index) {
        //超出board范围
        if (row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1) {
            return false;
        }
        //访问过
        if (board[row][col] == '*') {
            return false;
        }
        //字符不等
        if (board[row][col] != word.charAt(index)) {
            return false;
        }
        index++;
        //长度相等已经相等
        if (index == word.length()) {
            return true;
        }
        char c = board[row][col];
        board[row][col] = '*';
        if (exist(board, row + 1, col, word, index) ||
                exist(board, row, col + 1, word, index) ||
                exist(board, row - 1, col, word, index) ||
                exist(board, row, col - 1, word, index)) {
            return true;
        }
        board[row][col] = c;
        return false;
    }
}
