package leetcode.list.array;

/*
 * @description: 33.搜索旋转排序数组,[0,1,2,4,5,6,7]-->[4,5,6,7,0,1,2]
 * @param: nums数组,target目标值
 * @return: target在数组中的下标,不存在返回-1
 * @author: cp
 * @date: 2020/9/2
 */
public class Search {
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        if (nums.length == 1)
            return nums[0] == target ? 0 : -1;
        int l = 0, r = nums.length - 1;
        int n = r;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target)
                return mid;
            //[l,mid]有序
            // l        mid      r
            //[4, 5, 6, 7, 1, 2, 3]中找5
            if (nums[l] <= nums[mid]) {
                //target范围在[[nums[l],nums[mid])之间,范围缩小到[l,mid-1]
                if (target >= nums[l] && target < nums[mid])
                    r = mid - 1;
                //否则范围为[mid+1,r]
                else
                    l = mid + 1;
            } else {
                //[mid,r]有序
                // l        mid      r
                //[5, 6, 7, 1, 2, 3, 4]
                //target范围在([nums[mid],nums[n]]之间,范围缩小到(mid+1,r]
                if (target > nums[mid] && target <= nums[n])
                    l = mid + 1;
                //否则范围为[l,mid-1]
                else
                    r = mid - 1;
            }
        }
        return -1;
    }
}
