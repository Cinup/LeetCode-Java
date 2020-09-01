package leetcode.list.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @description: 15.三数之和
 * @param: nums数组
 * @return: 所有[a,b,c]使得a+b+c=0且a,b,c不重复
 * @author: cp
 * @date: 2020/8/15
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        //三数之和可以转化为两数字之和,例如[-1, 0, 1, 2, -1, -4]只需要依次找到两个和为[1,0,-1,-2,1,4]即可
        List<List<Integer>> result = new ArrayList<>();
        //对数组进行排序
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            //若a已经大于0,由于排序过b,c肯定大于0
            if (nums[i] > 0) {
                break;
            }
            //相同的a直接跳过
            if (i > 0 && (nums[i] == nums[i - 1])) {
                continue;
            }
            int l = i + 1, r = length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                //满足条件
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    result.add(list);
                    while (l < r && (nums[l + 1] == nums[l])) l++;
                    while (l < r && (nums[r - 1] == nums[r])) r--;
                    l++;
                    r--;
                }
                //加大l
                if (sum < 0) {
                    l++;
                }
                //减小r
                if (sum > 0) {
                    r--;
                }
            }
        }
        return result;
    }
}
