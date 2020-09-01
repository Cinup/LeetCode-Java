package leetcode.list.array;

import java.util.HashMap;

/*
 * @description: 1.两数之和
 * @param: 数组,两个数的目标值
 * @return: 相加等于目标值的两个数的数组下标
 * @author: cp
 * @date: 2020/8/1
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        //存储值和下标
        HashMap<Integer, Integer> numberMap = new HashMap();
        int[] result = new int[2];
        for (int i = 0, length = nums.length; i < length; i++) {
            int difference = target - nums[i];
            if (numberMap.containsKey(difference)) {
                result[0] = numberMap.get(difference);
                result[1] = i;
                return result;
            }
            numberMap.put(nums[i], i);
        }
        return result;
    }
}
