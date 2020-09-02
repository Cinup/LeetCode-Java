package leetcode.string;

import java.util.Map;
import java.util.Stack;

/*
 * @description: 32.最长有效括号
 * @param: s字符串,仅包含'('和')'
 * @return: 输出有效括号的最大值
 * @author: cp
 * @date: 2020/9/2
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int result = 0;
        if (s == null || s.length() < 2) {
            return result;
        }

        Stack<Entry> stack = new Stack<>();
        boolean[] valid = new boolean[s.length()];
        for (int i = 0, length = s.length(); i < length; i++) {
            char c = s.charAt(i);
            //1.栈为空
            //2.左括号
            if (stack.isEmpty() || c == '(') {
                Entry entry = new Entry(i, c);
                stack.push(entry);
                continue;
            }
            //栈不为空且为右括号
            Entry topEntry = stack.peek();
            if (topEntry.getC() == '(') {
                valid[i] = true;
                valid[topEntry.getIndex()] = true;
                stack.pop();
            } else {
                Entry entry = new Entry(i, c);
                stack.push(entry);
            }
        }
        int tempRusult = 0;
        for (int i = 0, length = valid.length; i < length; i++) {
            if (valid[i]) {
                tempRusult++;
                result = Math.max(result, tempRusult);
            } else {
                tempRusult = 0;
            }
        }
        return result;
    }

    class Entry {
        int index;
        char c;

        public Entry(int index, char c) {
            this.index = index;
            this.c = c;
        }

        public int getIndex() {
            return index;
        }

        public char getC() {
            return c;
        }
    }
}
