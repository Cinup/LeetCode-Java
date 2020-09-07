package leetcode.list.array;

/*
 * @description: 485.最大连续1的个数
 * @param: nums数组,仅包含0和1
 * @return: 最大连续1的个数
 * @author: cp
 * @date: 2020/9/6
 */
public class FindMaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int result = 0;
        int count = 0;
        for (int i = 0, length = nums.length; i < length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                result = Math.max(result, count);
                count = 0;
            }
        }
        result = Math.max(result, count);
        return result;
    }
}
