package leetcode.list.linkedlist;

import leetcode.entity.ListNode;

/*
 * @description: 61.旋转链表,将链表向右移动
 * @param: head链表头,k移动步数
 * @return: 旋转后的链表
 * @author: cp
 * @date: 2020/9/4
 */
public class RotateRight {
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
}
