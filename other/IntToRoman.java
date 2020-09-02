package leetcode.other;

/*
 * @description: 12.整数转罗马数字
 * @param: 整数,在 1 到 3999 的范围内
 * @return: 对应的罗马字符
 * 字符          数值
 * 字符          数值
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000
 * @author: cp
 * @date: 2020/8/4
 */
public class IntToRoman {
    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        String[] hundreds = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        //千位
        int thousand = num / 1000;
        for (int i = 0; i < thousand; i++) {
            result.append("M");
        }
        //百位
        int hundred = num % 1000 / 100;
        result.append(hundreds[hundred]);
        int ten = num % 100 / 10;
        result.append(tens[ten]);
        int one = num % 10;
        result.append(ones[one]);
        return result.toString();
    }
}
