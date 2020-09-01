package leetcode.list.linkedlist;

import leetcode.entity.ListNode;

/*
 * @description: 25.K 个一组翻转链表
 * @param: head链表头结点,k数量
 * @return: 连续k个反转后都头结点
 * @author: cp
 * @date: 2020/8/31
 */
public class ReverseKGroup {
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
}
