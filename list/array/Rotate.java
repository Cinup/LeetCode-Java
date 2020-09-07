package leetcode.list.array;

/*
 * @description: 48.旋转图像,顺时针旋转90
 * @param: matrix n*n的二维数组
 * @return:
 * @author: cp
 * @date: 2020/9/5
 */
public class Rotate {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        //中心对称
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (i != j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
        //左右轴对称
        //行
        for (int k = 0; k < n; k++) {
            //列
            for (int i = 0, j = n - 1; i <= j; i++, j--) {
                int temp = matrix[k][i];
                matrix[k][i] = matrix[k][j];
                matrix[k][j] = temp;
            }
        }
    }
}
