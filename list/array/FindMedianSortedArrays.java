package leetcode.list.array;

/*
 * @description: 4.寻找两个正序数组的中位数,时间复杂度O(log(m + n))
 * @param: 数组
 * @return: 中位数
 * @author: cp
 * @date: 2020/8/1
 */
public class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int count = length1 + length2;
        //奇数情况,取中间的一个数即可
        if ((count & 1) == 1) {
            return getTopK(nums1, 0, nums2, 0, count / 2 + 1) / 1.0;
        }
        //偶数情况,取中间2位数求平均值
        return (getTopK(nums1, 0, nums2, 0, count / 2) + getTopK(nums1, 0, nums2, 0, count / 2 + 1)) / 2.0;
    }

    /*
     * @description: 获取两个数组中第k大的数
     * num1,num2为两个数组,index1为num1起始下标,index2为num2起始下标,k为第k个
     */
    private int getTopK(int[] nums1, int index1, int[] nums2, int index2, int k) {
        if (index1 >= nums1.length) {
            return nums2[index2 + k - 1];
        }
        if (index2 >= nums2.length) {
            return nums1[index1 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[index1], nums2[index2]);
        } else {
            int tmpK = k / 2 - 1;
            int position1 = (index1 + tmpK) > nums1.length - 1 ? nums1.length - 1 : index1 + tmpK;
            int position2 = (index2 + tmpK) > nums2.length - 1 ? nums2.length - 1 : index2 + tmpK;
            if (nums1[position1] >= nums2[position2]) {
                position2 = position2 + 1;
                k = k - (position2 - index2);
                return getTopK(nums1, index1, nums2, position2, k);
            } else {
                position1 = position1 + 1;
                k = k - (position1 - index1);
                return getTopK(nums1, position1, nums2, index2, k);
            }
        }
    }
}
