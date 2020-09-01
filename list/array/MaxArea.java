package leetcode.list.array;

/*
 * @description: 11.盛最多水的容器
 * @param: n个非负整数,height[i]代表高度
 * @return: 最大的面积
 * @author: cp
 * @date: 2020/8/4
 */
public class MaxArea {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        //初始面积
        int max = Math.min(height[l], height[r]) * (r - l);
        while (l < r) {
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
            int tmpArea = Math.min(height[l], height[r]) * (r - l);
            max = Math.max(max, tmpArea);
        }
        return max;
    }
}
