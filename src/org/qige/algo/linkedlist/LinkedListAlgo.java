package org.qige.algo.linkedlist;

import java.util.List;

/**
 * @description:
 * - 单链表反转
 * - 链表中环的检测
 * - 两个有序的链表合并
 * - 删除链表倒数第 n 个结点
 * - 求链表的中间结点
 * @outhor: luoxiaohei
 * @create: 2020-04-18 18:25
 */
public class LinkedListAlgo {
    /**
     * 单链表反转
     * @return
     */
    public static Node reverse(Node list) {
        Node current = list, pre = null;
        while (current != null) {
            Node next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;

    }

    /**
     * 检测链表是否有环
     * @param list
     * @return
     */
    public static boolean checkCycle(Node list) {
        if (list == null) return false;
        Node fast = list.next,slow = list;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow  = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 有序链表合并 Leetcode 21
     * @param la
     * @param lb
     * @return
     */
    public static Node mergeTwoLists(Node la, Node lb) {
        // 利用哨兵简化实现难度，不用再去判断谁是头节点
        Node soldier  = createNode(0);
        Node p = soldier;
        while (la != null && lb != null) {
            if (la.getData() < lb.getData()) {
                p.next = la;
                la = la.next;
            } else {
                p.next = lb;
                lb = lb.next;
            }
            p = p.next;
        }

        if (la != null) {
            p.next = la;
        }
        if (lb != null) {
            p.next = lb;
        }
        return soldier.next;

    }

    /**
     * 删除倒数第n个节点
     * @param list
     * @param n
     * @return
     */
    public static Node deleteLastNth(Node list, int n) {
        int i = 1;
        Node fast = list;
        while (fast != null && i < n) {
            fast = fast.next;
            ++i;
        }
        // 说明list为null或者list的大小没有n个
        if (fast == null) {
            return list;
        }
        // 落后于fast节点n个位置
        Node slow = list;
        // 要删除节点的前一个节点
        Node prev = null;
        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if (prev == null) {
            list = list.next;
        } else {
            prev.next = prev.next.next;
        }
        return list;
    }

    /**
     * 求链表的中间结点
     * @param list
     * @return
     */
    public static Node findMiddleNode(Node list) {

        Node fast = list;
        Node slow = list;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void printAll(Node list) {
        Node p = list;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static Node createNode(int value) {
        return new Node(value, null);
    }

    public static void main(String[] args) {
        Node soldier = createNode(5);
        Node list1 = new Node(4,soldier);
        Node list2 = new Node(3,list1);
        Node list5 = new Node(2,list2);

        Node list3 = new Node(1,list5);

        Node list4 = new Node(7,soldier);
        Node list6 = new Node(4,list4);
        Node list7 = new Node(2, list6);

        printAll(list7);
//        reverse(list7);
        System.out.println(findMiddleNode(list7).getData());
        printAll(deleteLastNth(list7,2));
        printAll(list3);
        System.out.println(checkCycle(list3));
        Node cycleNode = mergeTwoLists(list3, list7);
        System.out.println(checkCycle(cycleNode));


    }


    public static class Node{
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
}
