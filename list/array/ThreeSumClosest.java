package leetcode.list.array;

import java.util.Arrays;

/*
 * @description: 16.最接近的三数之和
 * @param: 数组,目标值
 * @return: 最接近的目标值的三数之和
 * @author: cp
 * @date: 2020/8/15
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        //排序
        Arrays.sort(nums);
        int length = nums.length;
        int result = Integer.MAX_VALUE;
        int gap = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            int l = i + 1, r = length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                //相等时最接近,直接返回
                if (sum == target) {
                    return target;
                }
                if (sum > target) {
                    r--;
                }
                if (sum < target) {
                    l++;
                }
                int tmp = Math.abs(sum - target);
                if (tmp < gap) {
                    result = sum;
                    gap = tmp;
                }
            }
        }
        return result;
    }
}
