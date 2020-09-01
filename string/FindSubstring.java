package leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindSubstring {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        //字符串数量
        final int wordsNum = words.length;
        if (wordsNum == 0 || s.isEmpty())
            return result;
        //字符串长度
        final int wordLength = words[0].length();
        Map<String, Integer> wordsMap = new HashMap<>();
        for (int i = 0; i < wordsNum; i++) {
            int num = wordsMap.getOrDefault(words[i], 0);
            num++;
            wordsMap.put(words[i], num);
        }
        //遍历字符串
        int end = s.length() - wordsNum * wordLength + 1;
        for (int i = 0; i < end; i++) {
            Map<String, Integer> existMap = new HashMap<>();
            int num = 0;
            while (num < wordsNum) {
                String word = s.substring(i + num * wordLength, i + (num + 1) * wordLength);
                //如果当前字符串是words中
                if (wordsMap.containsKey(word)) {
                    //已经相等
                    int wordNum = existMap.getOrDefault(word, 0);
                    if (wordNum == wordsMap.get(word)) {
                        break;
                    }
                    wordNum++;
                    existMap.put(word, wordNum);
                } else {
                    break;
                }
                num++;
            }
            if (num == wordsNum) {
                result.add(i);
            }
        }
        return result;
    }
}
