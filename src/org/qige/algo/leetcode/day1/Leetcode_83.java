package org.qige.algo.leetcode.day1;

/**
 * @微信公众号: 七哥聊编程
 * @github: https://github.com/isevenluo
 * @gitee: https://gitee.com/isevenluo
 * @名额不多的个人微信：qige777ya
 * @description: 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次。
 * 返回同样按升序排列的结果链表。
 * <p>
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 * @author: 程序员七哥
 * @create: 2021-11-01 23:03
 **/
public class Leetcode_83 {

    /**
     * 输入：head = [1,1,2,3,3]
     * 输出：[1,2,3]
     */
    public ListNode deleteDuplicates(ListNode head) {
       if (head == null || head.next == null) {
           return head;
       }
       ListNode slow = head, fast = head;
       while (fast.next != null) {
           if (fast.val != fast.next.val) {
               slow.next = fast.next;
               slow = slow.next;
           }
           fast = fast.next;
       }
       slow.next = null;
       return head;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
