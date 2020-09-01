package leetcode.list.linkedlist;

import leetcode.entity.ListNode;

/*
 * @description: 19.删除链表的倒数第N个节点
 * @param: head为链表头结点,n为待删除待倒数第n个,保证n是有效的
 * @return: 返回删除后的头结点
 * @author: cp
 * @date: 2020/8/25
 */
public class RemoveNthFromEnd {
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
            return result.next;
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
}
