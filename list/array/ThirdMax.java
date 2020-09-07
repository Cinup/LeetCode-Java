package leetcode.list.array;

/*
 * @description: 414.第三大的数
 * @param: nums数组
 * @return: 返回第三大的数,不存在返回最大的数
 * @author: cp
 * @date: 2020/9/6
 */
public class ThirdMax {
    public int thirdMax(int[] nums) {
        long max = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        for (int i = 0, length = nums.length; i < length; i++) {
            int num = nums[i];
            if (num > max) {
                third = second;
                second = max;
                max = num;
                continue;
            }
            if (num > second && num < max) {
                third = second;
                second = num;
                continue;
            }
            if (num > third && num < second) {
                third = num;
                continue;
            }
        }
        return third == Long.MIN_VALUE ? (int) max : (int) third;
    }
}
