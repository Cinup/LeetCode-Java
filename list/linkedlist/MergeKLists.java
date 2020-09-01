package leetcode.list.linkedlist;

import leetcode.entity.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * @description: 23.合并K个升序链表
 * @param: 链表数组
 * @return: 合并后链表头
 * @author: cp
 * @date: 2020/8/25
 */
public class MergeKLists {
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
}
