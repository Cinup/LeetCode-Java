package leetcode.list.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @description: 18.四数之和
 * @param: nums数组,target目标值
 * @return: 寻找所有不重复的abcd,使得a+b+c+d=target
 * @author: cp
 * @date: 2020/8/31
 */
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        //对数组进行排序
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            if (nums[i] > target) {
                break;
            }
            if (i > 0 && (nums[i] == nums[i - 1])) {
                continue;
            }
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] > target) {
                    break;
                }
                if (j > i + 1 && (nums[j] == nums[j - 1])) {
                    continue;
                }
                int l = j + 1, r = length - 1;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[l]);
                        list.add(nums[r]);
                        result.add(list);
                        while (l < r && (nums[l + 1] == nums[l])) l++;
                        while (l < r && (nums[r - 1] == nums[r])) r--;
                        l++;
                        r--;
                    }
                    //加大l
                    if ((target - sum) > 0) {
                        l++;
                    }
                    //减小r
                    if ((target - sum) < 0) {
                        r--;
                    }
                }
            }
        }
        return result;
    }
}
