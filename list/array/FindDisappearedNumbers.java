package leetcode.list.array;

import java.util.ArrayList;
import java.util.List;

/*
 * @description: 448.找到所有数组中消失的数字
 * @param: nums数组范围为1-n
 * @return: 找出1-n中消失的数字
 * @author: cp
 * @date: 2020/9/6
 */
public class FindDisappearedNumbers {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ) {
            int currentNum = nums[i];
            if (currentNum <= 0 || currentNum > n) {
                nums[i] = -1;
                i++;
                continue;
            }
            if (currentNum != (i + 1)) {
                if (nums[currentNum - 1] == currentNum) {
                    nums[i] = -1;
                    i++;
                } else {
                    int temp = nums[currentNum - 1];
                    nums[currentNum - 1] = nums[i];
                    nums[i] = temp;
                }
            } else {
                i++;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == -1) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
