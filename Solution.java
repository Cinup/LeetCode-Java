package leetcode;

import leetcode.entity.Entry;
import leetcode.entity.ListNode;
import leetcode.entity.Node;
import leetcode.entity.TreeNode;

import java.util.*;

class Solution {
    /*
     * @description: 1.两数之和,从数组中找出a,b使其和为目标值c
     * @param: nums int数组,target目标值
     * @return: a,b数组下标
     */
    public int[] twoSum(int[] nums, int target) {
        //存储值和下标
        HashMap<Integer, Integer> numberMap = new HashMap();
        int[] result = new int[2];
        for (int i = 0, length = nums.length; i < length; i++) {
            int difference = target - nums[i];
            if (numberMap.containsKey(difference)) {
                result[0] = numberMap.get(difference);
                result[1] = i;
                return result;
            }
            numberMap.put(nums[i], i);
        }
        return result;
    }

    /*
     * @description: 2.两数相加,将两个链表每个结点相加
     * @param: l1,l2非空链表
     * @return: 相加后的结果链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultNode = new ListNode(0);
        //记录初始node的地址,用来return
        ListNode index = resultNode;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            int result = val1 + val2 + carry;
            int val = result % 10;
            carry = result / 10;
            ListNode tmpNode = new ListNode(val);
            resultNode.next = tmpNode;
            resultNode = resultNode.next;
        }
        return index.next;
    }

    /*
     * @description: 3.无重复的最长子串
     * @param: s字符串
     * @return: 无重复字符最长的子串
     */
    public int lengthOfLongestSubstring(String s) {
        int length = 0;
        int i = 0, j = 0;
        int n = s.length();
        Set<Character> characters = new HashSet<>();
        while (i < n && j < n) {
            char c = s.charAt(j);
            if (characters.contains(c)) {
                characters.remove(s.charAt(i));
            } else {
                length = Math.max(length, j - i);
                j++;
            }
        }
        return length;
    }

    /*
     * @description: 4.寻找两个正序数组的中位数,时间复杂度O(log(m + n))
     * @param: nums1,nums2 int数组
     * @return: 中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int count = length1 + length2;
        //奇数情况,取中间的一个数即可
        if ((count & 1) == 1) {
            return getTopK(nums1, 0, nums2, 0, count / 2 + 1) / 1.0;
        }
        //偶数情况,取中间2位数求平均值
        return (getTopK(nums1, 0, nums2, 0, count / 2) + getTopK(nums1, 0, nums2, 0, count / 2 + 1)) / 2.0;
    }

    /*
     * @description: 获取两个数组中第k大的数
     * @param: num1,num2为 int数组,index1为num1起始下标,index2为num2起始下标,k为第k个
     */
    private int getTopK(int[] nums1, int index1, int[] nums2, int index2, int k) {
        if (index1 >= nums1.length) {
            return nums2[index2 + k - 1];
        }
        if (index2 >= nums2.length) {
            return nums1[index1 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[index1], nums2[index2]);
        } else {
            int tmpK = k / 2 - 1;
            int position1 = (index1 + tmpK) > nums1.length - 1 ? nums1.length - 1 : index1 + tmpK;
            int position2 = (index2 + tmpK) > nums2.length - 1 ? nums2.length - 1 : index2 + tmpK;
            if (nums1[position1] >= nums2[position2]) {
                position2 = position2 + 1;
                k = k - (position2 - index2);
                return getTopK(nums1, index1, nums2, position2, k);
            } else {
                position1 = position1 + 1;
                k = k - (position1 - index1);
                return getTopK(nums1, position1, nums2, index2, k);
            }
        }
    }

    /*
     * @description: 5.最长回文子串,回文正反输出一样都字符串,例如aba
     * @param: s字符串
     * @return: 最长的回文子串
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        String ans = "";
        int n = s.length();
        int[][] dp = new int[n][n];
        //最外层为字符串长度
        for (int l = 0; l < n; l++) {
            for (int i = 0; i + l < n; i++) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = 1;
                } else if (l == 1) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = 1;
                    }
                } else {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] > 0 && l + 1 > ans.length()) {
                    ans = s.substring(i, i + 1 + l);
                }
            }
        }
        return ans;
    }

    /*
     * @description: 6.Z字形变换,将一个给定字符串根据给定的行数,以从上往下、从左到右进行 Z 字形排列。
     * @param: s字符串,numRows行数
     * @return: Z变换后的字符串
     */
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

    /*
     * @description: 7.整数反转,123反转321
     * @param: x整数
     * @return: 反转后的整数
     */
    public int reverse(int x) {
        int result = 0;
        int tmpResult = 0;
        int bit;
        while (x != 0) {
            bit = x % 10;
            x = x / 10;
            tmpResult = tmpResult * 10 + bit;
            if (result != 0 && tmpResult / result < 10)
                return 0;
            result = tmpResult;

        }
        return result;
    }

    /*
     * @description: 8.字符串转换整数 (atoi)
     * @param: str字符串,允许开头为空格,可能包含其他字符
     * @return: 字符串转换后的整数,如果超过32有符号整数的最大值,就返回最大值
     */
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

    /*
     * @description: 9.回文数,判断一个数组是否为回文数(字符串形式上)
     * @param: x整数
     * @return: true是回文,false不是
     */
    public boolean isPalindrome(int x) {
        //负数不可能为回文数
        if (x < 0) {
            return false;
        }
        //最后一位不能是0,例如10,100不可能是回文数
        if (x != 0 && (x % 10 == 0)) {
            return false;
        }
        int bit;
        int result = 0;
        while (x > 0) {
            bit = x % 10;
            result = result * 10 + bit;
            if (result > x)
                return false;
            if (result == x)
                return true;
            x = x / 10;
            if (result == x)
                return true;
        }
        return true;
    }

    /*
     * @description: 10.正则表达式匹配
     * @param: s字符串,p正则表达式,
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * @return: true匹配,false不匹配
     */
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean firstCharMatch = (!s.isEmpty() &&
                (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));
        //存在*时
        if (p.length() >= 2 && p.charAt(1) == '*') {
            //两种情况
            //1 x*匹配空字符串
            //2 x*匹配一个字符串
            return (isMatch(s, p.substring(2)) ||
                    (firstCharMatch && isMatch(s.substring(1), p)));
        } else {
            //不存在*时
            return firstCharMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    /*
     * @description: 11.盛最多水的容器
     * @param: height[i]代表高度
     * @return: 最大的面积
     */
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        //初始面积
        int max = Math.min(height[l], height[r]) * (r - l);
        while (l < r) {
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
            int tmpArea = Math.min(height[l], height[r]) * (r - l);
            max = Math.max(max, tmpArea);
        }
        return max;
    }

    /*
     * @description: 12.整数转罗马数字
     * @param: num整数,在 1 到 3999 的范围内
     * @return: 对应的罗马字符
     * 字符          数值
     * 字符          数值
     *  I             1
     *  V             5
     *  X             10
     *  L             50
     *  C             100
     *  D             500
     *  M             1000
     */
    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        //0,100,...,900
        String[] hundreds = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        //0,10,...,90
        String[] tens = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        //0,1,...,9
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

    /*
     * @description: 13.罗马数字转整数
     * @param: s罗马数字,在 1 到 3999 的范围内
     * @return: 对应的数字
     */
    public int romanToInt(String s) {
        Map<Character, Integer> romanMap = initMap();
        int result = 0;
        int pre = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int num = romanMap.get(s.charAt(i));
            if (num >= pre) {
                result += num;
            } else {
                result -= num;
            }
            pre = num;
        }
        return result;
    }

    /*
     * @description: 初始化map
     */
    private Map<Character, Integer> initMap() {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        return romanMap;
    }

    /*
     * @description: 14.最长公共前缀
     * @param: strs字符串数组
     * @return: 返回所有字符串最长公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int index = 0;
        int minLength = Integer.MAX_VALUE;
        //找出字符串最短长度
        for (int i = 0; i < strs.length; i++) {
            minLength = Math.min(minLength, strs[i].length());
        }
        for (int i = 0; i < minLength; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != c)
                    return strs[0].substring(0, index);
            }
            index++;
        }
        return strs[0].substring(0, index);
    }

    /*
     * @description: 15.三数之和
     * @param: nums int数组
     * @return: 所有[a,b,c]使得a+b+c=0且a,b,c不重复
     */
    public List<List<Integer>> threeSum(int[] nums) {
        //三数之和可以转化为两数字之和,例如[-1, 0, 1, 2, -1, -4]只需要依次找到两个和为[1,0,-1,-2,1,4]即可
        List<List<Integer>> result = new ArrayList<>();
        //对数组进行排序
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            //若a已经大于0,由于排序过b,c肯定大于0
            if (nums[i] > 0) {
                break;
            }
            //相同的a直接跳过
            if (i > 0 && (nums[i] == nums[i - 1])) {
                continue;
            }
            int l = i + 1, r = length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                //满足条件
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    result.add(list);
                    while (l < r && (nums[l + 1] == nums[l])) l++;
                    while (l < r && (nums[r - 1] == nums[r])) r--;
                    l++;
                    r--;
                }
                //加大l
                if (sum < 0) {
                    l++;
                }
                //减小r
                if (sum > 0) {
                    r--;
                }
            }
        }
        return result;
    }

    /*
     * @description: 16.最接近的三数之和
     * @param: nums数组,target目标值
     * @return: 最接近的目标值的三数之和
     */
    public int threeSumClosest(int[] nums, int target) {
        //排序
        Arrays.sort(nums);
        int length = nums.length;
        int result = Integer.MAX_VALUE;
        int minGap = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            int l = i + 1, r = length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                //相等时最接近,直接返回
                if (sum == target) {
                    return target;
                }
                if (sum > target) {
                    r--;
                }
                if (sum < target) {
                    l++;
                }
                int gap = Math.abs(sum - target);
                if (gap < minGap) {
                    result = sum;
                    minGap = gap;
                }
            }
        }
        return result;
    }

    /*
     * @description: 17.电话号码的字母组合
     * @param: digits数字字符串
     * @return: 九空格所有字符组合
     */
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

    /*
     * @description: 字符串数组转List数组
     */
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

    /*
     * @description: 笛卡尔积
     */
    private List<String> multiply(List<String> l1, List<String> l2) {
        if (l1.isEmpty() || l2.isEmpty()) {
            return l1.isEmpty() ? l2 : l1;
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < l1.size(); i++)
            for (int j = 0; j < l2.size(); j++) {
                result.add(l1.get(i) + l2.get(j));
            }
        return result;
    }

    /*
     * @description: 18.四数之和
     * @param: nums数组,target目标值
     * @return: 寻找所有不重复的a,b,c,d,使得a+b+c+d=target
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        //对数组进行排序
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            if (nums[i] > target) {
                break;
            }
            if (i > 0 && (nums[i] == nums[i - 1])) {
                continue;
            }
            for (int j = i + 1; j < length; j++) {
                if (nums[i] + nums[j] > target) {
                    break;
                }
                if (j > i + 1 && (nums[j] == nums[j - 1])) {
                    continue;
                }
                int l = j + 1, r = length - 1;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[l]);
                        list.add(nums[r]);
                        result.add(list);
                        while (l < r && (nums[l + 1] == nums[l])) l++;
                        while (l < r && (nums[r - 1] == nums[r])) r--;
                        l++;
                        r--;
                    }
                    //加大l
                    if ((target - sum) > 0) {
                        l++;
                    }
                    //减小r
                    if ((target - sum) < 0) {
                        r--;
                    }
                }
            }
        }
        return result;
    }

    /*
     * @description: 19.删除链表的倒数第N个节点
     * @param: head为链表头结点,n为待删除待倒数第n个,保证n是有效的
     * @return: 返回删除后的头结点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode result = head;
        ListNode tmpNode = head;
        //计算链表长度
        int length = 0;
        while (tmpNode != null) {
            length++;
            tmpNode = tmpNode.next;
        }
        //定位到第n个,就是正数length-n+1
        int index = length - n;
        //如果删除的是第一个,直接删除
        if (index == 0) {
            ListNode next = result.next;
            result.next = null;
            return next;
        }
        tmpNode = head;
        ListNode pre = null;
        while (index > 0) {
            pre = tmpNode;
            tmpNode = tmpNode.next;
            index--;
        }
        pre.next = tmpNode.next;
        return result;
    }

    //todo 20 and 21
    /*
     * @description: 22.括号生成
     * @param: n括号对数
     * @return: 所有有效的可能性组合数
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, 0, 0, "", n);
        return result;
    }

    private void generate(List<String> result, int l, int r, String s, int n) {
        if (l == n && r == n) {
            result.add(s);
            return;
        }
        if (l < n) {
            generate(result, l + 1, r, s + "(", n);
        }
        if (r < l) {
            generate(result, l, r + 1, s + ")", n);
        }
    }

    /*
     * @description: 23.合并K个升序链表
     * @param: lists链表数组
     * @return: 合并后链表头
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        List<Integer> nodeList = new ArrayList<>();
        for (int i = 0; i < lists.length; i++) {
            ListNode head = lists[i];
            while (head != null) {
                nodeList.add(head.val);
                head = head.next;
            }
        }
        Collections.sort(nodeList);
        ListNode head = new ListNode(0);
        ListNode current = head;
        for (int i = 0; i < nodeList.size(); i++) {
            ListNode node = new ListNode(nodeList.get(i));
            current.next = node;
            current = node;
        }
        return head.next;
    }

    /*
     * @description: 24.两两交换链表中的节点
     * @param: head链表头
     * @return: 交换后的链表
     */
    public ListNode swapPairs(ListNode head) {
        //链表只有0个或1个结点时,无法交换
        if (head == null || head.next == null) {
            return head;
        }
        ListNode current = head;
        ListNode next = head.next;
        current.next = swapPairs(next.next);
        next.next = current;
        return next;
    }

    /*
     * @description: 25.K个一组翻转链表
     * @param: head链表头结点,k数量
     * @return: 翻转后的链表
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        ListNode index = head;
        int i = k;
        while (i - 1 > 0) {
            index = index.next;
            if (index == null) {
                return head;
            }
            i--;
        }
        ListNode temp = index.next;
        index.next = null;
        ListNode newHead = reverse(head);
        head.next = reverseKGroup(temp, k);
        return newHead;
    }

    /*
     * @description: 翻转链表
     */
    private ListNode reverse(ListNode head) {
        ListNode newHead = head;
        while (head.next != null) {
            ListNode next = head.next;
            head.next = next.next;
            next.next = newHead;
            newHead = next;
        }
        return newHead;
    }

    //todo 26,28
    /*
     * @description: 27. 移除元素
     * @param: nums:数组, val:需要移除的元素
     * @return: int: 移除val元素后的数组长度
     */
    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                if (i != index) {
                    int temp = nums[index];
                    nums[index] = nums[i];
                    nums[i] = temp;
                }
                index++;
            }
        }
        return index;
    }

    /*
     * @description: 29.两数相除
     * @param: dividend被除数,divisor除数
     * @return: 商(取整)
     */
    public int divide(int dividend, int divisor) {
        int ans = -1;
        int sign = 1;
        if (dividend > 0) {
            sign = opposite(sign);
            dividend = opposite(dividend);
        }
        if (divisor > 0) {
            sign = opposite(sign);
            divisor = opposite(divisor);
        }
        //由于被除数和除数都是负数,如果dividend>divisor
        //说明|dividend|<|divisor|
        //即|dividend|/|divisor| < 1,结果就是0
        if (dividend > divisor) {
            return 0;
        }
        int originDividend = dividend;
        int originDivisor = divisor;
        dividend -= divisor;
        while (divisor >= dividend) {
            ans = ans + ans;
            dividend -= divisor;
            divisor += divisor;
        }

        int a = ans + opposite(divide(originDividend - divisor, originDivisor));
        if (a == Integer.MIN_VALUE) {
            if (sign > 0) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        } else {
            if (sign > 0) {
                return opposite(a);
            } else {
                return a;
            }
        }
    }

    private int opposite(int x) {
        return ~x + 1;
    }

    /*
     * @description: 30.串联所有单词的子串
     * @param: s字符串,words字串,words中所有字符串长度相等
     * @return: 符合条件的字符串起始下标
     */
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

    /*
     * @description: 31.下一个排列
     * @param: nums,int数组
     * @return:
     */
    public void nextPermutation(int[] nums) {
        int end = nums.length - 1;
        int i;
        boolean needSort = true;
        for (i = end; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                i--;
                needSort = false;
                break;
            }
        }
        //如果i==0说明数组是逆序的,
        if (i == 0 && needSort) {
            Arrays.sort(nums);
            return;
        }
        int j;
        //找到第一个比nums[i]大的数字
        for (j = end; j > i; j--) {
            if (nums[j] > nums[i])
                break;
        }
        //交换nums[i],nums[j]
        int temp;
        temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        Arrays.sort(nums, i + 1, nums.length);
    }

    /*
     * @description: 32.最长有效括号
     * @param: s字符串,仅包含'('和')'
     * @return: 输出最长有效括号的长度
     */
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

    /*
     * @description: 33.搜索旋转排序数组,[0,1,2,4,5,6,7]-->[4,5,6,7,0,1,2]
     * @param: nums数组不包含重复元素,target目标值
     * @return: target在数组中的下标,返回-1表示target不存在
     * @date: 2020/9/2
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        if (nums.length == 1)
            return nums[0] == target ? 0 : -1;
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target)
                return mid;
            //[l,mid]有序
            // l        mid      r
            //[4, 5, 6, 7, 1, 2, 3]中找5
            if (nums[l] < nums[mid]) {
                //target范围在[[nums[l],nums[mid])之间,范围缩小到[l,mid-1]
                if (target >= nums[l] && target < nums[mid])
                    r = mid - 1;
                    //否则范围为[mid+1,r]
                else
                    l = mid + 1;
            } else {
                //[mid,r]有序
                // l        mid      r
                //[5, 6, 7, 1, 2, 3, 4]
                //target范围在([nums[mid],nums[n]]之间,范围缩小到(mid+1,r]
                if (target > nums[mid] && target <= nums[r])
                    l = mid + 1;
                    //否则范围为[l,mid-1]
                else
                    r = mid - 1;
            }
        }
        return -1;
    }

    /*
     * @description: 34.在排序数组中查找元素的第一个和最后一个位置
     * @param: nums目标数组,target目标值
     * @return: 返回最先出现target和最后出现target的数组下标,返回[-1,-1]表示数组中不存在目标值
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums.length == 0)
            return result;
        //二分搜索,先搜最小值,再搜最大值
        int min = searchLeft(nums, target);
        //第一遍搜索发现目标值不存在,不需要第二遍搜索了
        if (min == -1) return result;
        int max = searchRight(nums, target);
        //返回下标
        return new int[]{min, max};
    }

    private int searchLeft(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= target)
                r = mid - 1;
            else
                l = mid + 1;
        }
        if (l >= nums.length || nums[l] != target) {
            return -1;
        }
        return l;
    }

    private int searchRight(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (nums[mid] > target)
                r = mid - 1;
            else
                l = mid + 1;
        }
        if (r < 0 || nums[r] != target) {
            return -1;
        }
        return r;
    }

    /*
     * @description: 35.搜索插入位置
     * @param: nums有序数组,target插入值
     * @return: target插入位置
     */
    public int searchInsert(int[] nums, int target) {
        int result = 0;
        if (nums.length == 0)
            return result;
        for (; result < nums.length; result++) {
            if (target <= nums[result])
                break;
        }
        return result;
    }

    /*
     * @description: 36.有效的数独
     * @param: board 9*9的表格
     * @return: true为有效,false无效
     */
    public boolean isValidSudoku(char[][] board) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    String s = "(" + board[i][j] + ")";
                    if (!set.add(s + i) || !set.add(j + s) || !set.add(i / 3 + s + j / 3))
                        return false;
                }
            }
        }
        return true;
    }

    /*
     * @description: 37.解数独
     * @param: board:9*9的表格
     * @return: void
     */
    public void solveSudoku(char[][] board) {
//        solver(board);
        solver(board, 1);
    }

    private boolean solver(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    //从1尝试到9
                    char num = '1';
                    while (num <= '9') {
                        //当前数字是否已经被填过了
                        if (isValid(i, j, board, num)) {
                            board[i][j] = num;
                            if (solver(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                        num++;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * @description: 通过count来记录已经填过的格子数量,减少遍历的次数。
     */
    private boolean solver(char[][] board, int count) {
        for (int i = (count - 1) / 9; i < 9; i++) {
            for (int j = (count - 1) % 9; j < 9; j++) {
                if (board[i][j] == '.') {
                    //从1尝试到9
                    char num = '1';
                    while (num <= '9') {
                        //当前数字是否已经被填过了
                        if (isValid(i, j, board, num)) {
                            board[i][j] = num;
                            if (solver(board, ++count)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                                count--;
                            }
                        }
                        num++;
                    }
                    return false;
                } else {
                    count++;
                }
            }
        }
        return true;
    }

    private boolean isValid(int row, int col, char[][] board, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c || board[i][col] == c) {
                return false;
            }
        }
        row = row / 3 * 3;
        col = col / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[row + i][col + j] == c) {
                    return false;
                }
            }

        }
        return true;
    }

    /*
     * @description: 39.组合总和
     * @param: candidates 无重复元素int数组, target目标值
     * @return: candidates中所有元素和为target的组合,元素可以重复使用
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        combinationSum(candidates, target, 0, path, result);
        return result;
    }

    private void combinationSum(int[] nums, int target, int start, Deque<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            List<Integer> list = new ArrayList<>(path);
            res.add(list);
            return;
        }
        if (target > 0) {
            for (int i = start, length = nums.length; i < length; i++) {
                if (nums[i] <= target) {
                    path.addLast(nums[i]);
                    combinationSum(nums, target - nums[i], i, path, res);
                    path.removeLast();
                }
            }
        }
    }

    /*
     * @description: 41.缺失的第一个正数,算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间
     * @param: nums数组
     * @return: 确实的第一个正数
     */
    public int firstMissingPositive(int[] nums) {
        int length = nums.length;
        for (int i = 1; i <= length; ) {
            int k = nums[i - 1];
            //n个数组最大范围[1,n]超出范围的都设置为-1
            if (k <= 0 || k > nums.length) {
                nums[i - 1] = -1;
                i++;
            } else {
                //nums[i]=k,就把这个值放到数组到数组到第k个位置,即num[k-1]=k
                if (nums[k - 1] != k) {
                    swap(nums, i - 1, k - 1);
                } else {
                    i++;
                }
            }
        }
        int result;
        for (result = 1; result <= length; result++) {
            if (nums[result - 1] != result)
                break;
        }
        return result;
    }

    private void swap(int[] nums, int i, int j) {
        int temp;
        temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /*
     * @description: 43.字符串相乘
     * @param: num1, num2 非负整数
     * @return: num1*num2的字符串结果
     */
    public String multiply(String num1, String num2) {
        String result = "0";
        //num1是0的情况
        if (num1.equals("0")) {
            return result;
        }
        int n = num2.length() - 1;
        //num1乘上num2的每一位
        for (int i = n; i >= 0; i--) {
            result = add(multiply(num1, num2.charAt(i), n - i), result);
            System.out.println(result);
        }
        return result;
    }

    /*
     * @description: 多位数乘一位数,
     * @param: num1多位数,num2一位数,depth num2的位数,即后面有几个零,需要添加到结果后面
     */
    private String multiply(String num1, char num2, int depth) {
        if (num2 == '0') {
            return "0";
        }
        int carry = 0;
        int size = num1.length() + 1 + depth;
        StringBuilder result = new StringBuilder();
        for (int i = num1.length() - 1; i >= 0; i--) {
            int num = multiply(num1.charAt(i), num2) + carry;
            carry = num / 10;
            num = num % 10;
            result.append(num);
        }
        result.append(carry);
        result.reverse();
        char[] zero = new char[depth];
        Arrays.fill(zero, '0');
        result.append(zero);
        if (carry == 0) {
            return result.substring(1, size);
        }
        return result.toString();
    }

    /*
     * @description: 一位数乘一位数
     */
    private int multiply(char num1, char num2) {
        return (num1 - '0') * (num2 - '0');
    }

    /*
     * @description: 多位数加多位数
     */
    private String add(String num1, String num2) {
        if (num1.equals("0")) {
            return num2;
        }
        if (num2.equals("0")) {
            return num1;
        }
        StringBuilder result = new StringBuilder(num1.length() + 1);
        int length = 0;
        int n1 = num1.length() - 1;
        int n2 = num2.length() - 1;
        int carry = 0;
        while (n2 >= 0) {
            int num = add(num1.charAt(n1), num2.charAt(n2)) + carry;
            n1--;
            n2--;
            length++;
            carry = num / 10;
            num = num % 10;
            result.append(num);
        }
        while (n1 >= 0) {
            int num = add(num1.charAt(n1), '0') + carry;
            n1--;
            length++;
            carry = num / 10;
            num = num % 10;
            result.append(num);
        }
        if (carry == 1) {
            result.append(carry);
            length++;
        }
        return result.reverse().substring(0, length);
    }

    /*
     * @description: 一位数加一位数
     */
    private int add(char num1, char num2) {
        return (num1 - '0') + (num2 - '0');
    }

    //todo 42,44
    /*
     * @description: 46.全排列
     * @param: nums数组,数字不重复
     * @return: 数组内数字的所有排列组合
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (len == 0) {
            return result;
        }
        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(nums, len, 0, path, used, result);
        return result;
    }

    private void dfs(int[] nums, int len, int depth,
                     Deque<Integer> path, boolean[] used,
                     List<List<Integer>> res) {
        if (depth == len) {
            List<Integer> list = new ArrayList<>(path);
            if (!res.contains(list))
                res.add(list);
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.addLast(nums[i]);
                used[i] = true;
                dfs(nums, len, depth + 1, path, used, res);
                used[i] = false;
                path.removeLast();
            }
        }
    }

    /*
     * @description: 47.全排列
     * @param: nums数组,数字会重复
     * @return: 数组内数字的所有排列组合
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (len == 0) {
            return result;
        }
        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(nums, len, 0, path, used, result);
        return result;
    }

    /*
     * @description: 50.Pow(x, n)
     * @param: x底数, n指数
     * @return: x的n次方
     */
    public double myPow(double x, int n) {
        //防止Integer.MIN_VALUE溢出
        long N = n;
        return N > 0 ? pow(x, N) : 1 / pow(x, -N);
    }

    private double pow(double x, long n) {
        double result = 1.0;
        while (n > 0) {
            if ((n & 1) == 1) {
                result = result * x;
            }
            x = x * x;
            n = n >> 1;
        }
        return result;
    }

    /*
     * @description: 51.N 皇后
     * @param: n,n*n的棋盘
     * @return: 所有皇后排列可能性
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        solveNQueens(n, new ArrayList<>(), result);
        return result;
    }

    private void solveNQueens(int n, List<Integer> current, List<List<String>> result) {
        if (current.size() == n) {
            List<String> temp = getResult(current);
            result.add(temp);
            return;
        }
        for (int col = 0; col < n; col++) {
            //判断位置是否有效
            if (isValidPosition(current, col)) {
                current.add(col);
                //继续求下一行的位置
                solveNQueens(n, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    /*
     * @description: 将皇后位置信息转换成棋盘格式的字符串,空格用'.'表示,皇后用'Q'表示
     */
    private List<String> getResult(List<Integer> current) {
        List<String> temp = new ArrayList<>();
        int n = current.size();
        for (int i = 0; i < n; i++) {
            char[] t = new char[n];
            Arrays.fill(t, '.');
            t[current.get(i)] = 'Q';
            temp.add(new String(t));
        }
        return temp;
    }

    /*
     * @description: 当前插入位置是否有效,行为current.size,列为col
     */
    private boolean isValidPosition(List<Integer> current, int col) {
        int size = current.size();
        if (size == 0) {
            return true;
        }
        for (int row = 0; row < size; row++) {
            int existCol = current.get(row);
            //当前列是否放置过
            if (existCol == col) {
                return false;
            }
            if (Math.abs(size - row) == Math.abs(col - current.get(row))) {
                return false;
            }
        }
        return true;
    }

    /*
     * @description: 52.N皇后II
     * @param: n,n*n的棋盘
     * @return: 所有皇后排列可能性数量
     */
    public int totalNQueens(int n) {
        return backtrack(new ArrayList<>(), n, 0);
    }

    private int backtrack(List<Integer> current, int n, int count) {
        if (current.size() == n) {
            count++;
        } else {
            for (int col = 0; col < n; col++) {
                //判断位置是否有效
                if (isValidPosition(current, col)) {
                    current.add(col);
                    //继续求下一行的位置
                    count = backtrack(current, n, count);
                    current.remove(current.size() - 1);
                }
            }
        }
        return count;
    }

    /*
     * @description: 53.最大子序和
     * @param: nums int数组
     * @return: 最大子序列的和
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int maxSum = 0;
        //存在nums都是负数的情况,这时候需要返回最大值
        boolean flag = false;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            if (nums[i] > 0) {
                flag = true;
                sum += nums[i];
                maxSum = Math.max(maxSum, sum);
            } else {
                if ((sum + nums[i]) < 0) {
                    sum = 0;
                } else {
                    sum += nums[i];
                }
            }
        }
        return flag ? maxSum : max;
    }

    /*
     * @description: 54.螺旋矩阵
     * @param: matrix二维int数组
     * @return: 螺旋输出
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) {
            return result;
        }
        int n = matrix[0].length;
        int size = m * n;
        int i = 0, j = 0;
        //定义方向 右j++,下i++,左j--,上i--
        int dir = 0;
        int up = 0, down = m - 1, left = 0, right = n - 1;
        while (size > 0) {
            size--;
            result.add(matrix[i][j]);
            switch (dir) {
                case 0:
                    if (j == right) {
                        //上限降低
                        up++;
                        //改变方向
                        dir = (dir + 1) % 4;
                        //向下
                        i++;
                    } else {
                        //继续向右
                        j++;
                    }
                    break;
                case 1:
                    if (i == down) {
                        right--;
                        dir = (dir + 1) % 4;
                        j--;
                    } else {
                        i++;
                    }
                    break;
                case 2:
                    if (j == left) {
                        down--;
                        dir = (dir + 1) % 4;
                        i--;
                    } else {
                        j--;
                    }
                    break;
                case 3:
                    if (i == up) {
                        left++;
                        dir = (dir + 1) % 4;
                        j++;
                    } else {
                        i--;
                    }
                    break;
            }
        }
        return result;
    }

    /*
     * @description: 55.跳跃游戏
     * @param: nums int数组,非负整数,表示当前位置能向后跳的步数
     * @return: true能跳到最后一个位置,false不能跳到最后一个位置
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] arrival = new boolean[n];
        arrival[0] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arrival[j] && (j + nums[j]) >= i) {
                    arrival[i] = true;
                    break;
                }
            }
        }
        return arrival[n - 1];
    }

    /*
     * @description: 56.合并区间
     * @param: intervals n*2数组
     * @return: 合并重叠的区间
     */
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        List<int[]> list = new ArrayList<>();
        if (n == 0) {
            return new int[][]{};
        }
        //排序,起点从小到大排列,起点相同时按终点从小打到大排列
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        int start = intervals[0][0], end = intervals[0][1];
        for (int i = 1; i < n; i++) {
            //当前区间在[start,end]之间
            if (intervals[start][1] >= intervals[i][1]) {
                continue;
            }
            //当前区间起点大于起点大于等于start,但是小于等于end,那么和[start,end]有重合,更新end至较大的值
            if (end >= intervals[i][0]) {
                end = Math.max(end, intervals[i][1]);
            } else {
                //当前区间和[start,end]没有重合时,将{start,end}加入列表,然后更新[start,end]
                list.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        list.add(new int[]{start, end});
        int[][] result = new int[list.size()][2];
        return list.toArray(result);
    }

    /*
     * @description: 59.螺旋矩阵 II
     * @param: n 正整数
     * @return: 生产1-n^2螺旋矩阵
     */
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int size = n * n;
        int i = 0, j = 0, k = 1;
        //定义方向 右j++,下i++,左j--,上i--
        int dir = 0;
        int up = 0, down = n - 1, left = 0, right = n - 1;
        while (size > 0) {
            size--;
            result[i][j] = k;
            k++;
            switch (dir) {
                case 0:
                    if (j == right) {
                        //上限降低
                        up++;
                        //改变方向
                        dir = (dir + 1) % 4;
                        //向下
                        i++;
                    } else {
                        //继续向右
                        j++;
                    }
                    break;
                case 1:
                    if (i == down) {
                        right--;
                        dir = (dir + 1) % 4;
                        j--;
                    } else {
                        i++;
                    }
                    break;
                case 2:
                    if (j == left) {
                        down--;
                        dir = (dir + 1) % 4;
                        i--;
                    } else {
                        j--;
                    }
                    break;
                case 3:
                    if (i == up) {
                        left++;
                        dir = (dir + 1) % 4;
                        j++;
                    } else {
                        i--;
                    }
                    break;
            }
        }
        return result;
    }

    /*
     * @description: 60.第k个排列
     * @param: n集合[1,2,3,…,n],k第k个排列
     * @return: 从小到大的第k个排列
     */
    public String getPermutation(int n, int k) {
        List<String> number = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            number.add(String.valueOf(i));
        }
        return getPermutation(number, n, k);
    }

    private String getPermutation(List<String> number, int n, int k) {
        if (n == 1) {
            return number.get(0);
        }
        int group = factorial(n - 1);
        int index = (k - 1) / group;
        String num = number.get(index);
        number.remove(index);
        k = k % group;
        k = k == 0 ? group : k;
        return num + getPermutation(number, n - 1, k);
    }

    private int factorial(int n) {
        if (n <= 1)
            return 1;
        return n * factorial(n - 1);
    }

    /*
     * @description: 61.旋转链表,将链表向右移动
     * @param: head链表头,k移动步数
     * @return: 旋转后的链表
     */
    public ListNode rotateRight(ListNode head, int k) {
        //无需处理的情况,直接返回
        if (head == null || k == 0) {
            return head;
        }
        int length = 0;
        ListNode tmpHead = head;
        ListNode end = null;
        while (tmpHead != null) {
            length++;
            end = tmpHead;
            tmpHead = tmpHead.next;
        }
        k = k % length;
        //旋转后的链表和原来一模一样
        if (k == 0) {
            return head;
        }
        ListNode newHead = head;
        ListNode newEnd = head;
        for (int i = 0; i < length - k; i++) {
            newEnd = newHead;
            newHead = newHead.next;
        }
        newEnd.next = null;
        end.next = head;
        return newHead;
    }

    /*
     * @description: 62.不同路径
     * @param: m,n m*n的表格
     * @return: 从表格左上角到右下角的所有路径
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        if (m == 1 || n == 1) {
            return 1;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 0;
                } else if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
     * @description: 63.不同路径II
     * @param: obstacleGrid 二维int数组,值为0或者1,其中1表示障碍物
     * @return: 从表格左上角到右下角的所有路径
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) {
            return 0;
        }
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 0;
        //计算第一列
        for (int i = 0; i < m; i++) {
            //当存在一个障碍物时,后续路径就不通了,直接全赋值为0
            if (obstacleGrid[i][0] == 1) {
                for (int j = i; j < m; j++) {
                    dp[j][0] = 0;
                }
                break;
            } else {
                dp[i][0] = 1;
            }
        }
        //计算第一行
        for (int i = 0; i < n; i++) {
            //同上
            if (obstacleGrid[0][i] == 1) {
                Arrays.fill(dp[0], i, n, 0);
                break;
            } else {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
     * @description: 64.最小路径和
     * @param: grid 二维int数组,表示 m x n 网格
     * @return: 从grid[0][0]到grid[m-1][n-1]路径上数字最小总和
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[0][0];
                } else if (i == 0) {
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
     * @description: 71.简化路径
     * @param: path Unix风格文件的绝对路径
     * @return: 规范路径
     */
    public String simplifyPath(String path) {
        String[] dirs = path.split("/");
        Deque<String> wordList = new ArrayDeque<>(dirs.length);
        for (String dirName : dirs) {
            if (dirName.isEmpty() || dirName.equals(".")) {
                continue;
            }
            if (dirName.equals("..")) {
                if (!wordList.isEmpty())
                    wordList.removeLast();
                continue;
            }
            wordList.addLast(dirName);
        }
        return "/" + String.join("/", wordList);
    }

    /*
     * @description: 73.矩阵置零,如果一个元素为0,则将其所在行和列的所有元素都设为0
     * @param: matrix:二维int数组
     * @return:
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return;
        }
        int n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /*
     * @description: 74.搜索二维矩阵
     * @param: matrix二维矩阵,target目标值
     * @return: true存在,false不存在
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) {
            return false;
        }
        int col = matrix[0].length;
        if (col == 0) {
            return false;
        }
        int l = 0;
        int h = row * col - 1;
        while (l <= h) {
            int mid = l + ((h - l) >> 1);
            int midInt = matrix[mid / col][mid % col];
            if (midInt == target)
                return true;
            else if (midInt < target)
                l = mid + 1;
            else if (midInt > target)
                h = mid - 1;
        }
        return false;
    }

    /*
     * @description: 75.颜色分类
     * @param: nums:仅包含0,1,2的数组
     * @return: 排序
     */
    public void sortColors(int[] nums) {
        int l = 0, r = nums.length - 1;
        for (int i = 0; i <= r; i++) {
            if (nums[i] == 0) {
                int temp = nums[l];
                nums[l] = nums[i];
                nums[i] = temp;
                l++;
            } else if (nums[i] == 2) {
                int temp = nums[r];
                nums[r] = nums[i];
                nums[i] = temp;
                r--;
                i--;
            }
        }
    }

    /*
     * @description: 76.最小覆盖子串
     * @param: s,t:字符串
     * @return: s中包含t中所有字符的最小字符串,不存在是返回空字符串""
     */
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        int[] countMap = new int[128];
        int count = t.length();
        for (int i = 0; i < count; i++) {
            countMap[t.charAt(i)]++;
        }
        int indexL = 0, indexR = 0;
        int left = 0, right = -1;
        int minLength = Integer.MAX_VALUE;
        while (indexR < s.length()) {
            char rightChar = s.charAt(indexR);
            //这一步很重要,在下面会用到
            countMap[rightChar]--;
            //如果当前字符在t中
            if (countMap[rightChar] >= 0) {
                count--;
            }
            //
            while (count == 0) {
                int length = indexR - indexL + 1;
                if (length < minLength) {
                    left = indexL;
                    right = indexR;
                    minLength = length;
                }
                char leftChar = s.charAt(indexL);
                indexL++;
                countMap[leftChar]++;
                //t中字符最终都会是0,而其他字符都是小于0,所以可以判断是否大于0来判断左边的字符是不是在t中
                if (countMap[leftChar] > 0) {
                    count++;
                    break;
                }
            }
            indexR++;
        }
        return s.substring(left, right + 1);
    }

    /*
     * @description: 77.组合
     * @param: n 范围1-n, k 数量
     * @return: 1-n中选取k个的所有组合
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combine(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    /*
     * @param: n范围1-n,k选取数量,start起始数字,current当前已经选中数字,result结果集
     */
    private void combine(int n, int k, int start, List<Integer> current, List<List<Integer>> dp) {
        if (current.size() == k) {
            List<Integer> temp = new ArrayList<>(current);
            dp.add(temp);
        } else {
            //i <= n - k + current.size() + 1 进行剪枝
            //即[start,n]元素个数已经小于k-current.size(),不可能再从中凑出到k个元素了
            // n - start + 1 < k - current.size() --> start > n - k+current.size() + 1
            for (int i = start; i <= n - k + current.size() + 1; i++) {
                if (!current.contains(i)) {
                    current.add(i);
                    combine(n, k, i + 1, current, dp);
                    current.remove(current.size() - 1);
                }
            }
        }
    }

    /*
     * @description: 78.子集
     * @param: nums:不含重复元素的整数数组
     * @return: 所有可能的子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //问题分解为77题从n个不重复数中取k个,k从0～n遍历即可
        for (int i = 0, l = nums.length; i <= nums.length; i++) {
            List<List<Integer>> currentResult = new ArrayList<>();
            subsets(nums, i, 0, new ArrayDeque(), currentResult);
            result.addAll(currentResult);
        }
        return result;
    }

    /*
     * @description:从nums中选取n个
     */
    private void subsets(int[] nums, int n, int start, Deque current, List<List<Integer>> currentResult) {
        if (current.size() == n) {
            currentResult.add(new ArrayList<>(current));
            return;
        }
        for (int i = start, l = nums.length; i < l; i++) {
            current.addLast(nums[i]);
            subsets(nums, n, i + 1, current, currentResult);
            current.removeLast();
        }
    }

    /*
     * @description: 79.单词搜索
     * @param: board二维字符数组, word:字符串
     * @return: board中是否包含word中所有的字符
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        if (m == 0) {
            return word.isEmpty();
        }
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (exist(board, i, j, word, 0))
                    return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int row, int col, String word, int index) {
        //超出board范围
        if (row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1) {
            return false;
        }
        //访问过
        if (board[row][col] == '*') {
            return false;
        }
        //字符不等
        if (board[row][col] != word.charAt(index)) {
            return false;
        }
        index++;
        //长度相等已经相等
        if (index == word.length()) {
            return true;
        }
        char c = board[row][col];
        board[row][col] = '*';
        if (exist(board, row + 1, col, word, index) ||
                exist(board, row, col + 1, word, index) ||
                exist(board, row - 1, col, word, index) ||
                exist(board, row, col - 1, word, index)) {
            return true;
        }
        board[row][col] = c;
        return false;
    }

    /*
     * @description: 80.删除排序数组中的重复项II
     * @param: nums:排序数组
     * @return: 删除重复元素后的长度,重复元素最多出现2次
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        //当数组内元素数量不超过2时,无论如何都是满足条件都
        if (n <= 2) {
            return nums.length;
        }
        int slow = 1;
        int fast = 2;
        for (; fast < n; fast++) {
            //如果[slow-1]==[fast]
            //那么在[slow-1,fast]区间内都值都是相等的
            //[slow-1,slow]就是2个相同的值,所以需要将不同的值放到slow+1位置上
            if (nums[fast] != nums[slow - 1]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    /*
     * @description: 81.搜索旋转排序数组II
     * @param: [nums, target]
     * @return: boolean
     */
    public boolean search2(int[] nums, int target) {
        if (nums.length == 0)
            return false;
        if (nums.length == 1)
            return nums[0] == target;
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target)
                return true;
            //[l,mid]有序
            // l        mid      r
            //[4, 5, 6, 7, 1, 2, 3]中找5
            if (nums[l] < nums[mid]) {
                //target范围在[[nums[l],nums[mid])之间,范围缩小到[l,mid-1]
                if (target >= nums[l] && target < nums[mid])
                    r = mid - 1;
                    //否则范围为[mid+1,r]
                else
                    l = mid + 1;
            } else if (nums[l] == nums[mid]) {
                //由于有重复元素,无法判断,最简单的方法就是将l移除,范围缩小至[l+1,r]再搜索一次
                // l        mid      r
                //[1, 2, 3, 1, 0, 0, 1]
                //    l              r
                l++;
            } else {
                //[mid,r]有序
                // l        mid      r
                //[5, 6, 7, 1, 2, 3, 4]
                //target范围在([nums[mid],nums[r]]之间,范围缩小到[mid+1,r]
                if (target > nums[mid] && target <= nums[r])
                    l = mid + 1;
                    //否则范围为[l,mid-1]
                else
                    r = mid - 1;
            }
        }
        return false;
    }

    /*
     * @description: 82.删除排序链表中的重复元素,保留一个重复元素
     * @param: head链表
     * @return: 删除重复元素后的链表
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode(head.val);
        ListNode cursor = newHead;
        int val = head.val;
        head = head.next;
        while (head != null) {
            if (head.val != val) {
                ListNode node = new ListNode(head.val);
                cursor.next = node;
                cursor = node;
            }
            head = head.next;
            val = head.val;
        }
        return newHead;
    }

    /*
     * @description: 83.删除排序链表中的重复元素,不保留重复元素
     * @param: head链表
     * @return: 删除重复元素后的链表
     */
    public ListNode deleteDuplicates83(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode(0);
        ListNode cursor = newHead;
        //上个元素
        int val = head.val;
        //上个元素是否只出现过一次
        boolean isFirst = true;
        head = head.next;
        while (head != null) {
            if (head.val == val) {
                isFirst = false;
            } else {
                //上个元素只过出现一次
                if (isFirst) {
                    ListNode node = new ListNode(val);
                    cursor.next = node;
                    cursor = node;
                }
                //修改上个元素的值为当前结点的值
                val = head.val;
                isFirst = true;
                if (head.next == null) {
                    ListNode node = new ListNode(val);
                    cursor.next = node;
                }
            }
            head = head.next;
        }
        return newHead.next;
    }

    /*
     * @description: 84.柱状图中最大的矩形
     * @param: heights:int数组,表示高度
     * @return: 最大矩形的面积
     */
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        //让栈按高度递增,遇到小于栈顶元素的就让栈顶元素出栈
        Stack<Integer> stack = new Stack<>();
        int p = 0;
        while (p < heights.length) {
            if (stack.isEmpty() || heights[p] >= heights[stack.peek()]) {
                stack.push(p);
                p++;
            } else {
                //计算栈顶
                int height = heights[stack.pop()];
                int left = stack.isEmpty() ? -1 : stack.peek();
                //当前比栈顶小,但是前一个一定不会比栈顶小,否则当前栈顶肯定已经被出栈了
                int right = p;
                int area = (right - left - 1) * height;
                maxArea = Math.max(area, maxArea);

            }
        }
        while (!stack.isEmpty()) {
            //保存栈顶高度
            int height = heights[stack.pop()];
            //左边第一个小于当前柱子的下标
            int left = stack.isEmpty() ? -1 : stack.peek();
            int area = (p - left - 1) * height;
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }

    /*
     * @description: 86.分隔链表 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
     * @param: head链表,x特定值
     * @return: 分割后的链表
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode lessNode = new ListNode(0);
        ListNode lessCursor = lessNode;
        ListNode greaterNode = new ListNode(0);
        ListNode greaterCursor = greaterNode;
        while (head != null) {
            ListNode node = new ListNode(head.val);
            if (head.val < x) {
                lessCursor.next = node;
                lessCursor = node;
            } else {
                greaterCursor.next = node;
                greaterCursor = node;
            }
            head = head.next;
        }
        lessCursor.next = greaterNode.next;
        return lessNode.next;
    }

    /*
     * @description: 87.扰乱字符串
     * @param: s1,s2:字符串
     * @return: boolean
     */
    public boolean isScramble(String s1, String s2) {
        //判断null
        if (s1 == null) {
            return s2 == null;
        }
        if (s2 == null) {
            return s1 == null;
        }
        //判断长度
        if (s1.length() != s2.length()) {
            return false;
        }
        //判断s1和s2是不是相等
        if (s1.equals(s2)) {
            return true;
        }
        //判断s1和s2是否是相同字符组成
        if (!isEqualChar(s1, s2)) {
            return false;
        }
        for (int i = 1, l = s1.length(); i < l; i++) {
            //s1  a - b
            //s2 a - b
            //  0-i i-l
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            //s1 a - b
            //   0-i i-l
            //s2 b - a
            //   0-i i-l
            if (isScramble(s1.substring(0, i), s2.substring(l - i)) && isScramble(s1.substring(i), s2.substring(0, l - i))) {
                return true;
            }
        }
        return false;
    }

    /*
     * @description: 判断字符串是否由相同的字符组成
     */
    private boolean isEqualChar(String s1, String s2) {
        int[] letters = new int[26];
        for (int i = 0, l = s1.length(); i < l; i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /*
     * @description: 89.格雷编码
     * @param: n:位数[0,2^n-1]
     * @return: 格雷编码,两个连续的数值仅有一个bit位数的差异
     */
    //todo 时间复杂度太高了
    public List<Integer> grayCode(int n) {
        int num = 1 << n;
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[num];
        grayCode(num, visited, new ArrayList<>(), result);
        return result;
    }

    private void grayCode(int n, boolean[] visited, List<Integer> current, List<Integer> result) {
        if (current.size() == n) {
            if (validGrayCode(current)) {
                result.addAll(current);
                return;
            }
        }
        for (int i = 0; i < n; i++) {
            if (visited[i])
                continue;
            current.add(i);
            visited[i] = true;
            grayCode(n, visited, current, result);
            visited[i] = false;
            current.remove(current.size() - 1);
            if (!result.isEmpty()) {
                break;
            }
        }
    }

    //O(n)
    private boolean validGrayCode(List<Integer> list) {
        int size = list.size();
        if (size == 0)
            return true;
        for (int i = 0; i < size - 1; i++) {
            if (!validGrayCode(list.get(i), list.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    private boolean validGrayCode(int x, int y) {
        int z = x ^ y;
        int count = 0;
        while (z > 0) {
            count += (z & 1);
            if (count > 1) {
                return false;
            }
            z = z >> 1;
        }
        return true;
    }

    /*
     * @description: 90.子集II
     * @param: nums:包含重复元素的整数数组
     * @return: 所有可能的子集
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        //类似78题,只需要考虑重复元素
        for (int i = 0, l = nums.length; i <= nums.length; i++) {
            List<List<Integer>> currentResult = new ArrayList<>();
            subsetsWithDup(nums, i, 0, new ArrayDeque(), currentResult);
            result.addAll(currentResult);
        }
        return result;
    }

    private void subsetsWithDup(int[] nums, int n, int start, Deque current, List<List<Integer>> currentResult) {
        if (current.size() == n) {
            currentResult.add(new ArrayList<>(current));
            return;
        }
        for (int i = start, l = nums.length; i < l; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            current.addLast(nums[i]);
            subsetsWithDup(nums, n, i + 1, current, currentResult);
            current.removeLast();
        }
    }

    /*
     * @description: 91.解码方法,将数字1-26映射到大写字母A-Z
     * @param: s:字符串仅数组
     * @return: int:将字符串数字映射到字符有多少种方式,例如123可以拆分为1-2-3,1-23或者12-3
     */
    public int numDecodings(String s) {
        HashMap<Integer, Integer> memoization = new HashMap<>();
        return numDecodings(s, 0, memoization);
    }

    private int numDecodings(String s, int start, HashMap<Integer, Integer> memoization) {
        if (start == s.length()) {
            return 1;
        }
        if (s.charAt(start) == '0') {
            return 0;
        }
        //判断之前是否计算过
        int m = memoization.getOrDefault(start, -1);
        if (m != -1) {
            return m;
        }
        int ans1 = numDecodings(s, start + 1, memoization);
        int ans2 = 0;
        //如果字符串后续还有2位
        if (start < s.length() - 1) {
            int num = ((s.charAt(start) - '0') * 10) + s.charAt(start + 1) - '0';
            if (num <= 26) {
                ans2 = numDecodings(s, start + 2, memoization);
            }
        }
        //将结果保存
        memoization.put(start, ans1 + ans2);
        return ans1 + ans2;
    }

    /*
     * @description: 92.反转链表II
     * @param: head链表 m,n反转范围
     * @return: 反转后的链表
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        if (m == n) {
            return head;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        int count = 1;
        ListNode pre = newHead;
        //找到反转的起点
        while (count < m) {
            count++;
            pre = pre.next;
            head = head.next;
        }
        ListNode left = pre;
        ListNode right = head;
        pre = pre.next;
        head = head.next;
        count++;
        //反转链表
        while (count < n) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        //
        right.next = head.next;
        head.next = pre;
        left.next = head;
        return newHead.next;
    }

    /*
     * @description: 93.复原IP地址
     * @param: String:仅包含数字的字符串
     * @return: List<String>:所有有效的ip地址
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        //IP地址的有效长度范围为[4,12]
        if (s.length() < 4 || s.length() > 12) {
            return result;
        }
        restoreIpAddresses(s, 0, 0, new StringBuilder(), result);
        return result;
    }

    public void restoreIpAddresses(String s, int start, int count, StringBuilder current, List<String> result) {
        //当最后剩余的数字都取三位数还有余的时候,说明前面取少了,提前剪枝
        if (s.length() - start > 3 * (4 - count)) {
            return;
        }
        if (count == 4) {
            if (start == s.length()) {
                result.add(new String(current.delete(current.length() - 1, current.length())));//删除最后一个点
            }
            return;
        }
        //count没到4
        if (start == s.length()) {
            return;
        }
        StringBuilder temp = new StringBuilder(current);
        current.append(s, start, start + 1).append(".");
        restoreIpAddresses(s, start + 1, count + 1, current, result);
        //一位数允许0.0.0.0
        //但是多位数不允许0开头
        if (s.charAt(start) == '0')
            return;
        if (start + 1 < s.length()) {
            current = new StringBuilder(temp);
            current.append(s, start, start + 2).append(".");
            restoreIpAddresses(s, start + 2, count + 1, current, result);
        }
        if (start + 2 < s.length()) {
            //三位数有效数字为[100,255]
            if (Integer.valueOf(s.substring(start, start + 3)) <= 255) {
                current = new StringBuilder(temp);
                current.append(s, start, start + 3).append(".");
                restoreIpAddresses(s, start + 3, count + 1, current, result);
            }
        }
    }

    /*
     * @description: 94.二叉树的中序遍历
     * @param: root,二叉树根结点
     * @return: 中序遍历结果
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        inorderTraversal(root, result);
        return result;
    }

    /*
     * @description: 中序遍历
     */
    private void inorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }

    /*
     * @description: 98.验证二叉搜索树
     * @param: 二叉树根结点
     * @return: true有效二叉搜索树,false无效二叉搜索树
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        List<Integer> list = new ArrayList<>();
        //有效二叉树中序遍历应该是递增的
        inorderTraversal(root, list);
        //判断是否递增即可
        for (int i = 0, l = list.size(); i < l - 1; i++) {
            if (list.get(i + 1) <= list.get(i)) {
                return false;
            }
        }
        return true;
    }

    /*
     * @description: 102.二叉树的层序遍历
     * @param: root:二叉树根结点
     * @return: List<List<Integer>>:二叉树层序遍历结果,其中List<Integer>为相同层结点的值
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> depthMap = new HashMap<>();
        levelOrderTraversal(depthMap, root, 0);
        for (int i = 0; i < depthMap.size(); i++) {
            result.add(depthMap.get(i));
        }
        return result;
    }

    /*
     * @description: 层序遍历
     */
    private void levelOrderTraversal(Map<Integer, List<Integer>> map, TreeNode node, int depth) {
        if (node == null)
            return;
        else {
            if (map.containsKey(depth)) {
                map.get(depth).add(node.val);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(node.val);
                map.put(depth, list);
            }
            levelOrderTraversal(map, node.left, depth + 1);
            levelOrderTraversal(map, node.right, depth + 1);
        }
    }

    /*
     * @description: 103.二叉树的锯齿形层次遍历
     * @param: root:二叉树根结点
     * @return: List<List<Integer>>:二叉树层序遍历结果,其中List<Integer>为相同层结点的值,第1层左往右,第2层从右往左以此类推
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> depthMap = new HashMap<>();
        levelOrderTraversal(depthMap, root, 0);
        for (int i = 0; i < depthMap.size(); i++) {
            List<Integer> list = depthMap.get(i);
            if ((i & 1) == 1)
                Collections.reverse(list);
            result.add(depthMap.get(i));
        }
        return result;
    }

    /*
     * @description: 107.二叉树的层次遍历II
     * @param: root:二叉树根结点
     * @return: List<List<Integer>>:二叉树层序遍历自底向上结果,其中List<Integer>为相同层结点的值
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> depthMap = new HashMap<>();
        levelOrderTraversal(depthMap, root, 0);
        for (int i = depthMap.size() - 1; i >= 0; i--) {
            result.add(depthMap.get(i));
        }
        return result;
    }

    /*
     * @description: 110.平衡二叉树
     * @param: root:二叉树根结点
     * @return: boolean:是否为平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        return getTreeDepth(root) != -1;
    }

    private int getTreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getTreeDepth(root.left);
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = getTreeDepth(root.right);
        if (rightDepth == -1) {
            return -1;
        }
        if (Math.abs(leftDepth - rightDepth) != 1) {
            return -1;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /*
     * @description: 111.二叉树的最小深度
     * @param: root:二叉树根结点
     * @return: int:最小深度
     */
    public int minDepth(TreeNode root) {
        return 0;
    }

    /*
     * @description: 113.路径总和II
     * @param: root:二叉树根目录,sum:和
     * @return: List<List<Integer>>:所有从根节点到叶子节点路径总和等于给定目标和的路径
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        Deque current = new ArrayDeque<>();
        pathSum(root, sum, current, result);
        return result;
    }

    private void pathSum(TreeNode root, int sum, Deque<Integer> current, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        //当前是叶子结点
        current.addLast(root.val);
        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                result.add(new ArrayList<>(current));
            }
        }
        pathSum(root.left, sum - root.val, current, result);
        pathSum(root.right, sum - root.val, current, result);
        current.removeLast();
    }

    /*
     * @description: 114.二叉树展开为链表,原地将它展开为一个单链表
     * @param: root:二叉树根结点
     * @return: void
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                TreeNode oldRight = root.right;
                TreeNode newRight = root.left;
                root.right = newRight;
                root.left = null;
                while (newRight.right != null) {
                    newRight = newRight.right;
                }
                newRight.right = oldRight;
                root = root.right;
            }
        }
    }

    /*
     * @description: 116.填充每个节点的下一个右侧节点指针
     * @param: root: 一个完美二叉树根结点
     * @return: Node:
     */
    public Node connect(Node root) {
        connectNode(root);
        return root;
    }

    private void connectNode(Node root) {
        if (root == null || root.left == null) {
            return;
        }
        //连接当前结点左右孩子结点
        root.left.next = root.right;
        root.right.next = (root.next == null) ? null : root.next.left;
        connect(root.left);
        connect(root.right);
    }

    /*
     * @description: 144.二叉树的前序遍历
     * @param: root:二叉树根结点
     * @return: List<Integer>:前序序遍历结果
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        preorderTraversal(root, result);
        return result;
    }

    /*
     * @description: 前序遍历
     */
    private void preorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        list.add(node.val);
        preorderTraversal(node.left, list);
        preorderTraversal(node.right, list);
    }

    /*
     * @description: 145.二叉树的后序遍历
     * @param: root:二叉树根结点
     * @return: List<Integer>:前序序遍历结果
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        postorderTraversal(root, result);
        return result;
    }

    /*
     * @description: 后序遍历
     */
    private void postorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        postorderTraversal(node.left, list);
        postorderTraversal(node.right, list);
        list.add(node.val);
    }

    /*
     * @description: 150.逆波兰表达式求值
     * @param: tokens:表达式
     * @return: int:逆波兰表达式结果
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0, n = tokens.length; i < n; i++) {
            String token = tokens[i];
            if (isOperator(token)) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(calculate(num2, num1, token));
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }

    /*
     * @description: 判断字符是否是+ - * /中的一个
     */
    private boolean isOperator(String token) {
        return "+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token);
    }

    /*
     * @description: 根据操作符计算a和b的结果
     */
    private int calculate(int a, int b, String operator) {
        int result = 0;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
        }
        return result;
    }

    /*
     * @description: 347.前 K 个高频元素
     * @param: nums,非空int数组, k个数
     * @return: 出现次数最多的k个
     */
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        //key-num value-count
        Map<Integer, Integer> countMap = new HashMap<>();
        //统计出现次数 O(n)
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        //O(nlogk)
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        for (int i = 0; i < k; ++i) {
            result[i] = queue.poll()[0];
        }
        return result;
    }

    /*
     * @description: 414.第三大的数
     * @param: nums数组
     * @return: 返回第三大的数,不存在返回最大的数
     */
    public int thirdMax(int[] nums) {
        long max = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        for (int i = 0, length = nums.length; i < length; i++) {
            int num = nums[i];
            if (num > max) {
                third = second;
                second = max;
                max = num;
                continue;
            }
            if (num > second && num < max) {
                third = second;
                second = num;
                continue;
            }
            if (num > third && num < second) {
                third = num;
                continue;
            }
        }
        return third == Long.MIN_VALUE ? (int) max : (int) third;
    }

    /*
     * @description: 448.找到所有数组中消失的数字
     * @param: nums数组范围为1-n
     * @return: 找出1-n中消失的数字
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ) {
            int currentNum = nums[i];
            if (currentNum <= 0 || currentNum > n) {
                nums[i] = -1;
                i++;
                continue;
            }
            if (currentNum != (i + 1)) {
                if (nums[currentNum - 1] == currentNum) {
                    nums[i] = -1;
                    i++;
                } else {
                    int temp = nums[currentNum - 1];
                    nums[currentNum - 1] = nums[i];
                    nums[i] = temp;
                }
            } else {
                i++;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == -1) {
                result.add(i + 1);
            }
        }
        return result;
    }

    /*
     * @description: 485.最大连续1的个数
     * @param: nums数组,仅包含0和1
     * @return: 最大连续1的个数
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int result = 0;
        int count = 0;
        for (int i = 0, length = nums.length; i < length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                result = Math.max(result, count);
                count = 0;
            }
        }
        result = Math.max(result, count);
        return result;
    }

    /*
     * @description: 495.提莫攻击
     * @param: timeSeries数组表示攻击时间,duration中毒持续间隔
     * @return: 中毒时间总和
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int length = timeSeries.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return duration;
        }
        int result = 0;
        for (int i = 1; i < length; i++) {
            int time = timeSeries[i] - timeSeries[i - 1];
            if (time >= duration) {
                result += duration;
            } else {
                result += time;
            }
        }
        //最后一个时间再加上duration
        result += duration;
        return result;
    }

    /*
     * @description: 628.三个数的最大乘积
     * @param: nums数组
     * @return: 三个数最大乘积
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);
    }

    /*
     * @description: 637.二叉树的层平均值
     * @param: root:二叉树根结点
     * @return: List<Double>:每层节点平均值
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Map<Integer, Entry> depthMap = new HashMap<>();
        averageOfLevels(depthMap, root, 0);
        for (int i = 0; i < depthMap.size(); i++) {
            result.add(depthMap.get(i).getAverage());
        }
        return result;
    }

    private void averageOfLevels(Map<Integer, Entry> map, TreeNode node, int depth) {
        if (node == null)
            return;
        else {
            Entry entry = map.getOrDefault(depth, new Entry(0, 0.0));
            entry.setCount(entry.getCount() + 1);
            entry.setSum(entry.getSum() + node.val);
            map.put(depth, entry);
            averageOfLevels(map, node.left, depth + 1);
            averageOfLevels(map, node.right, depth + 1);
        }
    }

    /*
     * @description: 645.错误的集合
     * @param: nums数据从1-n
     * @return: 找出数组中重复和缺失的数字
     */
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        boolean[] exist = new boolean[n + 1];
        int num;
        int errorNum = 0;
        int errorSum = 0;
        for (int i = 0; i < n; i++) {
            num = nums[i];
            errorSum += num;
            if (exist[num]) {
                errorNum = num;
            } else {
                exist[num] = true;
            }
        }
        int sum = n * (n + 1) / 2;
        errorSum = errorSum - errorNum;
        int missNum = sum - errorSum;
        return new int[]{errorNum, missNum};
    }

    /*
     * @description: 剑指Offer 35.复杂链表的复制
     * @param: head:链表头
     * @return: Node:深拷贝的链表
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node copyNode = new Node(head.val);
        Node cursor = copyNode;
        Node headCursor = head;
        Map<Node, Node> nodeMap = new HashMap<>();
        nodeMap.put(copyNode, head);
        //首先赋值next
        while (headCursor.next != null) {
            headCursor = headCursor.next;
            cursor.next = new Node(headCursor.val);
            cursor = cursor.next;
            nodeMap.put(head, cursor);
        }
        headCursor = head;
        cursor = copyNode;
        //再复制random
        while (headCursor != null) {
            if (headCursor.random == null) {
                cursor.random = null;
            } else {
                cursor.random = nodeMap.get(headCursor.random);
            }
            headCursor = headCursor.next;
            cursor = cursor.next;
        }
        return copyNode;
    }

    /*
     * @description: 面试题 01.01.判定字符是否唯一
     * @param: astr:字符串
     * @return: boolean:字符中是否不包含重复的字符
     */
    public boolean isUnique(String astr) {
        int lowercase = 0;
        int uppercase = 0;
        for (int i = 0, l = astr.length(); i < l; i++) {
            char c = astr.charAt(i);
            int bit;
            if (c >= 'a') {
                bit = 1 << (c - 'a' + 1);
                if ((bit & lowercase) == bit) {
                    return false;
                } else {
                    lowercase |= bit;
                }
            } else {
                bit = 1 << (c - 'A' + 1);
                if ((bit & uppercase) == bit) {
                    return false;
                } else {
                    uppercase |= bit;
                }
            }
        }
        return true;
    }

    /*
     * @description: 面试题 01.02.判定是否互为字符重排
     * @param: s1,s2:字符串
     * @return: boolean:字符串s1和s2的字符是否完全相同
     */
    public boolean CheckPermutation(String s1, String s2) {
        if (s1 == null) {
            return s2 == null;
        }
        if (s2 == null) {
            return s1 == null;
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] charNum = new int[128];
        for (int i = 0, l = s1.length(); i < l; i++) {
            charNum[s1.charAt(i)]++;
            charNum[s2.charAt(i)]--;
        }
        for (int i = 0; i < 128; i++) {
            if (charNum[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /*
     * @description: 面试题 01.03.URL化
     * @param: S:字符串,length:字符串真实长度
     * @return: String:将空格替换为"%20"
     */
    public String replaceSpaces(String S, int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            if (c == ' ') {
                result.append("%20");
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /*
     * @description: 面试题 01.04.回文排列
     * @param: s:字符串
     * @return: boolean: 字符串的字符是否可以组成回文字符串
     */
    public boolean canPermutePalindrome(String s) {
        int[] charNum = new int[128];
        for (int i = 0, l = s.length(); i < l; i++) {
            charNum[s.charAt(i)]++;
        }
        int num = 0;
        for (int i = 0; i < 128; i++) {
            num += charNum[i] & 1;
            if (num > 1) {
                return false;
            }
        }
        return true;
    }

    /*
     * @description: 面试题 01.06.字符串压缩
     * @param: S:字符仅包含a-z
     * @return: String:压缩成字符数量的形式,例如aa-->a2,若字符串没有变短,返回原始字符
     */
    public String compressString(String S) {
        if (S.length() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (int i = 0, l = S.length(); i < l - 1; i++) {
            if (S.charAt(i + 1) == S.charAt(i)) {
                count++;
            } else {
                result.append(S.charAt(i)).append(count);
                if (result.length() >= S.length()) {
                    return S;
                }
                count = 1;
            }
        }
        if (S.charAt(S.length() - 1) == S.charAt(S.length() - 2)) {
            result.append(S.charAt(S.length() - 1)).append(count);
        } else {
            result.append(S.charAt(S.length() - 1)).append(count);
        }
        return result.length() < S.length() ? result.toString() : S;
    }
}