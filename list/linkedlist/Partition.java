package leetcode.list.linkedlist;

import leetcode.entity.ListNode;

/*
 * @description: 86.分隔链表 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * @param: head链表,x特定值
 * @return: 分割后的链表
 * @author: cp
 * @date: 2020/9/4
 */
public class Partition {
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
}
