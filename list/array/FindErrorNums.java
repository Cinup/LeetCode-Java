package leetcode.list.array;

/*
 * @description: 645.错误的集合
 * @param: nums数据从1-n
 * @return: 找出数组中重复和缺失的数字
 * @author: cp
 * @date: 2020/9/6
 */
public class FindErrorNums {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        boolean[] exist = new boolean[n + 1];
        int num = 0;
        int errorNum = 0;
        int errorSum = 0;
        for (int i = 0; i < n; i++) {
            num = nums[i];
            errorSum += num;
            if (exist[num]) {
                errorNum = num;
            } else {
                exist[num] = true;
            }
        }
        int sum = n * (n + 1) / 2;
        errorSum = errorSum - errorNum;
        int missNum = sum - errorSum;
        return new int[]{errorNum, missNum};
    }
}
