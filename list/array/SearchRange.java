package leetcode.list.array;

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
        int r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= target)
                r = mid;
            else
                l = mid;
        }
        if (l < nums.length) {
            if (nums[l] == target)
                return l;
        }
        return -1;
    }

    private int searchRight(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > target)
                r = mid;
            else
                l = mid + 1;
        }
        if (l < nums.length) {
            if (nums[l] == target)
                return l;
        }
        return -1;
    }

    //todo
    private int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
