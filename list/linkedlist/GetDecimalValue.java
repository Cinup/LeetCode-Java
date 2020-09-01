package leetcode.list.linkedlist;

import leetcode.entity.ListNode;

/*
 * @description: 1290.二进制链表转整数
 * @param: 链表头结点,值只有1或者1,链表结点数小于30
 * @return: 十进制表示
 * @author: cp
 * @date: 2020/8/27
 */
public class GetDecimalValue {
    public int getDecimalValue(ListNode head) {
        int result = 0;
        while (head != null) {
            result = result << 1;
            result += head.val;
            head = head.next;
        }
        return result;
    }
}
