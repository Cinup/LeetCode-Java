package leetcode.list.linkedlist;

import leetcode.entity.ListNode;

/*
 * @description: 24.两两交换链表中的节点
 * @param: head链表头
 * @return:
 * @author: cp
 * @date: 2020/8/25
 */
public class SwapPairs {
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
}
