package leetcode.other;

import java.util.ArrayList;
import java.util.List;

/*
 * @description: 22.括号生成
 * @param: n括号对数
 * @return: 所有有效的可能性组合
 * @author: cp
 * @date: 2020/8/25
 */
public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, 0, 0, "", n);
        return result;
    }

    public void generate(List<String> result, int l, int r, String s, int n) {
        if (l == n && r == n) {
            result.add(s.toString());
            return;
        }
        if (l < n) {
            generate(result, l + 1, r, s + "(", n);
        }
        if (r < l) {
            generate(result, l, r + 1, s + ")", n);
        }
    }
}
