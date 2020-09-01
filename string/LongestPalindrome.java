package leetcode.string;

/*
 * @description: 5.最长回文子串
 * @param: 字符串
 * @return: 最长的回文子串
 * @author: cp
 * @date: 2020/8/1
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        String ans = "";
        int n = s.length();
        int[][] dp = new int[n][n];
        //最外层为字符串长度
        for (int l = 0; l < n; l++) {
            for (int i = 0; i + l < n; i++) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = 1;
                } else if (l == 1) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = 1;
                    }
                } else {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] > 0 && l + 1 > ans.length()) {
                    ans = s.substring(i, i + 1 + l);
                }
            }
        }
        return ans;
    }
}