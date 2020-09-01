package leetcode.string;

import java.util.HashSet;
import java.util.Set;

/*
 * @description: 3.无重复的最长子串
 * @param: 字符串
 * @return: 无重复字符最长的子串
 * @author: cp
 * @date: 2020/8/1
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int length = 0;
        int i = 0, j = 0;
        int n = s.length();
        Set<Character> characters = new HashSet<>();
        while (i < n && j < n) {
            char c = s.charAt(j);
            if (characters.contains(c)) {
                characters.remove(s.charAt(i));
            } else {
                length = Math.max(length, j - i);
                j++;
            }
        }
        return length;
    }
}
