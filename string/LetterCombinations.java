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
        String[] letter = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String>[] digitList = getListArray(letter);
        for (int i = 0; i < digits.length(); i++) {
            List<String> charList = digitList[digits.charAt(i) - '0'];
            result = multiply(result, charList);
        }
        return result;
    }

    //字符串转链表
    private List<String>[] getListArray(String[] letters) {
        List<String>[] letterList = new ArrayList[letters.length];
        for (int i = 0; i < letters.length; i++) {
            String digit = letters[i];
            List<String> result = new ArrayList<>();
            for (int j = 0; j < digit.length(); j++) {
                result.add(digit.charAt(j) + "");
            }
            letterList[i] = result;
        }
        return letterList;
    }

    //链表相乘
    private List<String> multiply(List<String> l1, List<String> l2) {
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
