package leetcode.string;

/*
 * @description: 14.最长公共前缀
 * @param: 字符串数组
 * @return: 返回最长公共前缀
 * @author: cp
 * @date: 2020/8/30
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int index = 0;
        int minLength = Integer.MAX_VALUE;
        //找出字符串最短长度
        for (int i = 0; i < strs.length; i++) {
            minLength = Math.min(minLength, strs[i].length());
        }
        for (int i = 0; i < minLength; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != c)
                    return strs[0].substring(0, index);
            }
            index++;
        }
        return strs[0].substring(0, index);
    }
}
