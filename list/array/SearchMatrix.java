package leetcode.list.array;

/*
 * @description: 74.搜索二维矩阵
 * @param: matrix二维矩阵,target目标值
 * @return: true存在,false不存在
 * @author: cp
 * @date: 2020/9/4
 */
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) {
            return false;
        }
        int col = matrix[0].length;
        if (col == 0) {
            return false;
        }
        int l = 0;
        int h = row * col - 1;
        while (l <= h) {
            int mid = l + ((h - l) >> 1);
            int midInt = matrix[mid / col][mid % col];
            if (midInt == target)
                return true;
            else if (midInt < target)
                l = mid + 1;
            else if (midInt > target)
                h = mid - 1;
        }
        return false;
    }
}
