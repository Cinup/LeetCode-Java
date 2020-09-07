package leetcode.list.array;

import java.util.Arrays;

/*
 * @description: 628.三个数的最大乘积
 * @param: nums数组
 * @return: 三个数最大乘积
 * @author: cp
 * @date: 2020/9/6
 */
public class MaximumProduct {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);
    }
}
