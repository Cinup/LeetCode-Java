package leetcode.list.linkedlist;

import leetcode.entity.ListNode;

import java.util.ArrayList;
import java.util.List;

/*
 * @description: 剑指 Offer 06.从尾到头打印链表
 * @param: 链表头结点
 * @return: 反向数组
 * @author: cp
 * @date: 2020/8/27
 */
public class ReversePrint {
    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] result = new int[list.size()];
        for (int i = 0, length = list.size(); i < length; i++) {
            result[i] = list.get(length - i - 1);
        }
        return result;
    }
}
