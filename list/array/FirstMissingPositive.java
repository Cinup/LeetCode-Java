package leetcode.list.array;

/*
 * @description: 41.缺失的第一个正数,算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间
 * @param: nums数组
 * @return: 确实的第一个正数
 * @author: cp
 * @date: 2020/9/2
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int length = nums.length;
        for (int i = 1; i <= length; ) {
            int k = nums[i - 1];
            //n个数组最大范围[1,n]超出范围的都设置为-1
            if (k <= 0 || k > nums.length) {
                nums[i - 1] = -1;
                i++;
            } else {
                //nums[i]=k,就把这个值放到数组到数组到第k个位置,即num[k-1]=k
                if (nums[k - 1] != k) {
                    swap(nums, i - 1, k - 1);
                } else {
                    i++;
                }
            }
        }
        int result;
        for (result = 1; result <= length; result++) {
            if (nums[result - 1] != result)
                break;
        }
        return result;
    }

    private void swap(int[] nums, int i, int j) {
        int temp;
        temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
