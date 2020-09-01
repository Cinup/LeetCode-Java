package leetcode.string;

import java.util.ArrayList;
import java.util.List;

/*
 * @description: 17.电话号码的字母组合
 * @param: 数字字符串
 * @return: 九空格所有字符组合
 * @author: cp
 * @date: 2020/8/30
 */
public class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) {
            return result;
        }
        String digitLetter[] = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        for (int i = 0; i < digits.length(); i++) {
            List<String> charList = getList(digitLetter[digits.charAt(i) - '0']);
            result = multiply(result, charList);
        }
        return result;
    }

    //字符串转链表
    public List<String> getList(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            result.add(s.charAt(i) + "");
        }
        return result;
    }

    //链表相乘
    public List<String> multiply(List<String> l1, List<String> l2) {
        if (l1.isEmpty() || l2.isEmpty()) {
            return l1.isEmpty() ? l2 : l1;
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < l1.size(); i++)
            for (int j = 0; j < l2.size(); j++) {
                ans.add(l1.get(i) + l2.get(j));
            }
        return ans;
    }
}
