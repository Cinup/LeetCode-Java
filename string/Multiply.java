package leetcode.string;

/*
 * @description: 43.字符串相乘
 * @param: num1,num2非负整数
 * @return: num1乘num2的结果
 * @author: cp
 * @date: 2020/8/28
 */
public class Multiply {
    //todo
    public String multiply(String num1, String num2) {
        String zero = "0";
        String one = "1";
        //0和1比较特殊,先处理一下
        if (num1.equals(zero) || num2.equals(zero))
            return zero;
        if (num1.equals(one))
            return num2;
        if (num2.equals(one))
            return num1;
        int length1 = num1.length();
        int length2 = num2.length();
        int length = Math.min(length1, length2);
        int charLength = Math.max(length1, length2) + 1;
        char[] result = new char[charLength];
        result[0] = 0;
        int carry = 0;
        for (int i = 1; i <= length; i++) {
            int tmp = multiplyChar(num1.charAt(length1 - i), num2.charAt(length2 - i));
            result[charLength - i] = (char) (tmp % 10 + carry + '0');
            carry = tmp / 10;
        }
        if (result[0] == 0)
            return new String(result, 1, result.length);
        return new String(result);
    }

    public int multiplyChar(char a, char b) {
        return getAscii(a) * getAscii(b);
    }

    public int getAscii(char c) {
        return c - '0';
    }
}
