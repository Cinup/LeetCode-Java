package leetcode.list.array;

/*
 * @description: 31.下一个排列
 * @param:  数组组合
 * @return: 下一个比他大的组合,例如1 2 3 -> 1 3 2
 * @author: cp
 * @date: 2020/8/26
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i >= 1) {
            if (nums[i - 1] >= nums[i])
                i--;
            else
                break;
        }
        //todo

    }
}
