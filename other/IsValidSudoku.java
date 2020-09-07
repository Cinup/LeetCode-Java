package leetcode.other;

import java.util.HashSet;
import java.util.Set;

/*
 * @description: 36.有效的数独
 * @param: board 9*9的表格
 * @return: true为有效,false无效
 * @author: cp
 * @date: 2020/9/5
 */
public class IsValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    String s = "(" + board[i][j] + ")";
                    if (!set.add(s + i) || !set.add(j + s) || !set.add(i / 3 + s + j / 3))
                        return false;
                }
            }
        }
        return true;
    }
}
