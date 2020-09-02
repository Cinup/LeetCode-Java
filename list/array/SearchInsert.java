package leetcode.list.array;

/*
 * @description: 35.搜索插入位置
 * @param: nums有序数组,target插入值
 * @return: target插入位置
 * @author: cp
 * @date: 2020/9/2
 */
public class SearchInsert {
    public int searchInsert(int[] nums, int target) {
        int result = 0;
        if (nums.length == 0)
            return result;
        for (; result < nums.length; result++) {
            if (target <= nums[result])
                break;
        }
        return result;
    }
}
