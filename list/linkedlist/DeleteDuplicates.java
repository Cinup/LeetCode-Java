package leetcode.list.linkedlist;

import leetcode.entity.ListNode;

/*
 * @description: 删除排序链表中的重复元素
 * @param: head链表
 * @return: 删除重复元素后的链表
 * @author: cp
 * @date: 2020/9/4
 */
public class DeleteDuplicates {

    //重复的元素只出现一次
    public ListNode deleteDuplicates82(ListNode head) {
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

    //重复的元素会被删除
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
}
