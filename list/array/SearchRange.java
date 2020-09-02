package leetcode.list.array;

/*
 * @description: 34.在排序数组中查找元素的第一个和最后一个位置
 * @param: nums目标数组,target目标值
 * @return: 返回最先出现target和最后出现target的数组下标
 * @author: cp
 * @date: 2020/9/2
 */
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums.length == 0)
            return result;
        //二分搜索,先搜最小值,再搜最大值
        int min = searchLeft(nums, target);
        //第一遍搜索发现目标值不存在,不需要第二遍搜索了
        if (min == -1) return result;
        int max = searchRight(nums, target);
        //返回下标
        return new int[]{min, max};
    }

    private int searchLeft(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= target)
                r = mid - 1;
            else
                l = mid + 1;
        }
        if (l >= nums.length || nums[l] != target) {
            return -1;
        }
        return l;
    }

    private int searchRight(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > target)
                r = mid - 1;
            else
                l = mid + 1;
        }
        if (r < 0 || nums[r] != target) {
            return -1;
        }
        return r;
    }
}
