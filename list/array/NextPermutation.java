package leetcode.list.array;

import java.util.Arrays;

/*
 * @description: 31.下一个排列
 * @param:  数组组合
 * @return: 下一个比他大的组合,例如1 2 3 -> 1 3 2
 * @author: cp
 * @date: 2020/8/26
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int end = nums.length - 1;
        int i;
        boolean needSort = true;
        for (i = end; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                i--;
                needSort = false;
                break;
            }
        }
        //如果i==0说明数组是逆序的,
        if (i == 0 && needSort) {
            Arrays.sort(nums);
            return;
        }
        int j;
        //找到第一个比nums[i]大的数字
        for (j = end; j > i; j--) {
            if (nums[j] > nums[i])
                break;
        }
        //交换nums[i],nums[j]
        int temp;
        temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        Arrays.sort(nums, i + 1, nums.length);
//        for (int k = i + 1; k < end; k++) {
//            temp = nums[k];
//            nums[k] = nums[k + 1];
//            nums[k + 1] = temp;
//        }
    }
}
