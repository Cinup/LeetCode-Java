package leetcode.string;

/*
 * @description:  6.Z字形变换
 * @param: 字符串和行数
 * @return: Z变换后的字符串
 * @author: cp
 * @date: 2020/8/1
 */
public class Convert {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] builders = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            builders[i] = new StringBuilder();
        }
        int count = 0;
        boolean addFlag = true;
        for (int i = 0, length = s.length(); i < length; i++) {
            builders[count].append(s.charAt(i));
            if (addFlag) count++;
            else count--;
            if (count == numRows) {
                addFlag = !addFlag;
                count = count - 2;
            }
            if (count == -1) {
                addFlag = !addFlag;
                count = count + 2;
            }
        }
        for (int i = 1; i < numRows; i++) {
            builders[0].append(builders[i]);
        }
        return builders[0].toString();
    }
}
