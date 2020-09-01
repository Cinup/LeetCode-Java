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
        //千位
        int thousand = num / 1000;
        for (int i = 0; i < thousand; i++) {
            result.append("M");
        }
        //百位
        int hundred = num % 1000 / 100;
        switch (hundred) {
            case 1:
                result.append("C");
                break;
            case 2:
                result.append("CC");
                break;
            case 3:
                result.append("CCC");
                break;
            case 4:
                result.append("CD");
                break;
            case 5:
                result.append("D");
                break;
            case 6:
                result.append("DC");
                break;
            case 7:
                result.append("DCC");
                break;
            case 8:
                result.append("DCCC");
                break;
            case 9:
                result.append("CM");
                break;
            case 0:
                break;
        }
        int ten = num % 100 / 10;
        switch (ten) {
            case 1:
                result.append("X");
                break;
            case 2:
                result.append("XX");
                break;
            case 3:
                result.append("XXX");
                break;
            case 4:
                result.append("XL");
                break;
            case 5:
                result.append("L");
                break;
            case 6:
                result.append("LX");
                break;
            case 7:
                result.append("LXX");
                break;
            case 8:
                result.append("LXXX");
                break;
            case 9:
                result.append("XC");
                break;
            case 0:
                break;
        }
        int one = num % 10;
        switch (one) {
            case 1:
                result.append("I");
                break;
            case 2:
                result.append("II");
                break;
            case 3:
                result.append("III");
                break;
            case 4:
                result.append("IV");
                break;
            case 5:
                result.append("V");
                break;
            case 6:
                result.append("VI");
                break;
            case 7:
                result.append("VII");
                break;
            case 8:
                result.append("VIII");
                break;
            case 9:
                result.append("IX");
                break;
            case 0:
                break;
        }
        return result.toString();
    }
}
