package leetcode.list.linkedlist;

import leetcode.entity.ListNode;

/*
 * @description: 2.两数相加
 * @param: 两个非空链表
 * @return: 相加后的结果链表
 * @author: cp
 * @date: 2020/8/1
 */
public class AddTwoNumbers {
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
}
