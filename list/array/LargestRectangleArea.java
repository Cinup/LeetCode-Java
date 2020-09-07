package leetcode.list.array;

public class LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int height = heights[i];
            int maxLength = 0;
            int length = 0;
            for (int j = 0; j < n; j++) {
                if (heights[j] >= height) {
                    length++;
                } else {
                    length = 0;
                }
                maxLength = Math.max(maxLength, length);
            }
            maxArea = Math.max(maxArea, height * maxLength);
        }
        return maxArea;
    }
}
