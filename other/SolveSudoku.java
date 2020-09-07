package leetcode.other;

/*
 * @description: 37.解数独
 * @param: board 9*9的表格
 * @return:
 * @author: cp
 * @date: 2020/9/5
 */
public class SolveSudoku {
    public void solveSudoku(char[][] board) {
        solver(board);
    }

    private boolean solver(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    //从1尝试到9
                    char num = '1';
                    while (num <= '9') {
                        //当前数字是否已经被填过了
                        if (isValid(i, j, board, num)) {
                            board[i][j] = num;
                            if (solver(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                        num++;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int row, int col, char[][] board, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) {
                return false;
            }
        }
        row = row / 3 * 3;
        col = col / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[row + i][col + j] == c) {
                    return false;
                }
            }

        }
        return true;
    }
}
