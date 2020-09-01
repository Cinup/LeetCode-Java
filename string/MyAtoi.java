package leetcode.string;

/*
 * @description: 8.字符串转换整数 (atoi)
 * @param: 字符串,允许开头为空格,可能包含其他字符
 * @return: 字符串转换后的整数,如果超过32有符号整数的最值,就返回最值
 * @author: cp
 * @date: 2020/8/3
 */
public class MyAtoi {
    public int myAtoi(String str) {
        int start = 0;
        int result = 0;
        int tmpResult = 0;
        int invalidResult = 0;
        int positive = 1;
        int l = str.length();
        //首先去除空格
        for (; start < l; start++) {
            if (str.charAt(start) != ' ')
                break;
        }
        if (start == l) {
            return invalidResult;
        }
        //判断首位是不是符号
        int firstChar = str.charAt(start);
        if (firstChar == '+') {
            start++;
            positive = 1;
        } else if (firstChar == '-') {
            start++;
            positive = -1;
        } else if (firstChar < '0' || firstChar > '9') {
            return invalidResult;
        }
        for (; start < l; start++) {
            char c = str.charAt(start);
            //有效字符串
            if (c >= '0' && c <= '9') {
                tmpResult = tmpResult * 10 + (c - 48);//ascii码
                //溢出情况
                if (result != 0 && tmpResult / result < 10) {
                    if (positive == 1) {
                        return Integer.MAX_VALUE;
                    } else {
                        return Integer.MIN_VALUE;
                    }
                }
                result = tmpResult;
            } else {
                //无效字符串
                break;
            }
        }
        return positive * result;
    }
}
