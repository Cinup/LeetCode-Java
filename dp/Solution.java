package leetcode.dp;

public class Solution {
    /*
     * @description: 5.最长回文子串,回文正反输出一样都字符串,例如aba
     * @param: s:字符串
     * @return: String:最长的回文子串
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        int n = s.length();
        //dp[i][j]即为s(i,j)是否为回文字串
        boolean[][] dp = new boolean[n][n];
        int start = 0;
        int end = 0;
        //最外层为字符串长度
        for (int l = 0; l < n; l++) {
            for (int i = 0; i + l < n; i++) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = l == 1 ? true : dp[i + 1][j - 1];
                }
                if (dp[i][j] && l + 1 > (end - start)) {
                    start = i;
                    end = i + 1 + l;
                }
            }
        }
        return s.substring(start, end);
    }
}
