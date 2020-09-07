package leetcode;

import leetcode.entity.Entry;
import leetcode.entity.ListNode;
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
     * @param: nums1,nums2数组
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
     * @description:  6.Z字形变换,将一个给定字符串根据给定的行数,以从上往下、从左到右进行 Z 字形排列。
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
     * @param: 整数,在 1 到 3999 的范围内
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
     * @return: 反转后的链表
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
     * @param: nums数组,target目标值
     * @return: target在数组中的下标,返回-1表示target不存在
     * @date: 2020/9/2
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        if (nums.length == 1)
            return nums[0] == target ? 0 : -1;
        int l = 0, r = nums.length - 1;
        int n = r;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target)
                return mid;
            //[l,mid]有序
            // l        mid      r
            //[4, 5, 6, 7, 1, 2, 3]中找5
            if (nums[l] <= nums[mid]) {
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
                if (target > nums[mid] && target <= nums[n])
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
     * @author: cp
     * @date: 2020/9/5
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
     * @param: board 9*9的表格
     * @return:
     */
    public void solveSudoku(char[][] board) {
        solver(board);
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
     * @description: 60.第k个排列
     * @param: n集合[1,2,3,…,n],k第k个排列
     * @return: 从小到大的第k个排列
     * @author: cp
     * @date: 2020/9/5
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
                if (i == 0 && j == 0)
                    dp[i][j] = 0;
                else if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
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
     * @description: 94.二叉树的中序遍历
     * @param: root,二叉树根结点
     * @return: 中序遍历结果
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        traversal(root, result);
        return result;
    }

    private void traversal(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        traversal(node.left, list);
        list.add(node.val);
        traversal(node.right, list);
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
     * @description: 645.错误的集合
     * @param: nums数据从1-n
     * @return: 找出数组中重复和缺失的数字
     */
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        boolean[] exist = new boolean[n + 1];
        int num = 0;
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
}