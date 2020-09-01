package leetcode.other;

/*
 * @description: 10.正则表达式匹配
 * @param: s字符串,p正则表达式,p只包含"."和"*",
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * @return:
 * @author: cp
 * @date: 2020/8/3
 */

public class IsMatch {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean first_match = (!s.isEmpty() &&
                (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));
        //存在*时
        if (p.length() >= 2 && p.charAt(1) == '*') {
            //两种情况
            //1 .*匹配空字符串
            //2 .*匹配一个字符串
            return (isMatch(s, p.substring(2)) ||
                    (first_match && isMatch(s.substring(1), p)));
        } else {
            //不存在*时
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
    }
}

