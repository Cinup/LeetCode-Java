package leetcode.other;

import java.util.*;

/*
 * @description: 49.字母异位词分组,将由相同字母组成的单词分为一次
 * @param: strs字符串数组
 * @return: 分组结果
 * @author: cp
 * @date: 2020/9/5
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0, length = strs.length; i < length; i++) {
            char[] array = strs[i].toCharArray();
            Arrays.sort(array);
            String key = String.valueOf(array);
            if (map.containsKey(key)) {
                map.get(key).add(strs[i]);
            } else {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(key, list);
            }
        }
        return new ArrayList<>(map.values());
    }
}
