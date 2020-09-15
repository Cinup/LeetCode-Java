package leetcode.list.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    /*
     * @description: 26.删除排序数组中的重复项
     * @param: nums:排序数组
     * @return: int:删除重复元素后的数组长度
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int slow = 0;
        for (int fast = 1, l = nums.length; fast < l; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                if (fast != slow) {
                    nums[slow] = nums[fast];
                }
            }
        }
        //slow是下标,数量应该再加1
        return slow + 1;
    }

    /*
     * @description: 27. 移除元素
     * @param: nums:数组, val:需要移除的元素
     * @return: int: 移除val元素后的数组长度
     */
    public int removeElement(int[] nums, int val) {
        //[0,slow] (slow,fast)
        int slow = 0;
        for (int fast = 0, l = nums.length; fast < l; fast++) {
            if (nums[fast] != val) {
                if (fast != slow) {
                    nums[slow] = nums[fast];
                }
                slow++;
            }
        }
        return slow;
    }

    /*
     * @description: 80.删除排序数组中的重复项II
     * @param: nums:排序数组
     * @return: 删除重复元素后的长度,重复元素最多出现2次
     */
    public int removeDuplicates2(int[] nums) {
        int n = nums.length;
        //当数组内元素数量不超过2时,无论如何都是满足条件的
        if (n <= 2) {
            return nums.length;
        }
        int slow = 1;
        int fast = 2;
        for (; fast < n; fast++) {
            //如果[slow-1]==[fast]
            //那么在[slow-1,fast]区间内都值都是相等的
            //[slow-1,slow]就是2个相同的值,所以需要将不同的值放到slow+1位置上
            if (nums[fast] != nums[slow - 1]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    /*
     * @description: 283.移动零,将所有0移动到数组的末尾,同时保持非零元素的相对顺序
     * @param: nums:数组
     * @return: void
     */
    public void moveZeroes(int[] nums) {
        for (int slow = 0, fast = 0, l = nums.length; fast < l; fast++) {
            if (nums[fast] != 0) {
                if (slow != fast) {
                    int temp = nums[slow];
                    nums[slow] = nums[fast];
                    nums[fast] = temp;
                }
                slow++;
            }
        }
    }

    /*
     * @description: 75.颜色分类
     * @param: nums:仅包含0,1,2的数组
     * @return: 排序
     */
    public void sortColors(int[] nums) {
        int l = 0, r = nums.length - 1;
        for (int i = 0; i <= r; i++) {
            if (nums[i] == 0) {
                int temp = nums[l];
                nums[l] = nums[i];
                nums[i] = temp;
                l++;
            } else if (nums[i] == 2) {
                int temp = nums[r];
                nums[r] = nums[i];
                nums[i] = temp;
                r--;
                i--;
            }
        }
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0, l = nums.length; i < l; i++) {
            if (queue.size() < k) {
                queue.offer(nums[i]);
            } else {
                if (queue.peek() < nums[i]) {
                    queue.poll();
                    queue.offer(nums[i]);
                }
            }
        }
        return queue.poll();
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m, j = 0; j < n; i++, j++) {
            nums1[i] = nums2[j];
        }
        Arrays.sort(nums1);
    }
}
