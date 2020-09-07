package leetcode.list.linkedlist;

import leetcode.entity.ListNode;

/*
 * @description: 92.反转链表II
 * @param: head链表 m,n反转范围
 * @return: 反转后的链表
 * @author: cp
 * @date: 2020/9/4
 */
public class ReverseBetween {
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
}
