package org.qige.algo.linkedlist;

/**
 * @author: sevenluo
 * @date: 2022/02/28 16:48
 * @description: 翻转链表
 */
public class ReverseList {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        reverse(a);
        while (e != null) {
            System.out.println(e.val);
            e = e.next;
        }

    }

    public static ListNode reverse(ListNode rootNode) {

        ListNode next;
        ListNode pre = null;

        while (rootNode != null) {
            // 先取出要放到头的节点
            next = rootNode.next;
            // 要翻转的这个节点，将 next 指向他前面已经翻转的节点
            rootNode.next = pre;
            // 保存上一个已经翻转到头部的节点
            pre = rootNode;
            // 取链表的下一个节点，往尾走
            rootNode = next;
        }
        return pre;
    }
}

