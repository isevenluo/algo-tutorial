package org.qige.algo.leetcode.day9;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @微信公众号: 七哥聊编程
 * @github: https://github.com/isevenluo
 * @gitee: https://gitee.com/isevenluo
 * @名额不多的个人微信：qige777ya
 * @description:
 * @author: 程序员七哥
 * @create: 2021-11-14 22:57
 **/
public class LeetCode_225 {

    private Queue<Integer> q;

    int top_elem = 0;

    public LeetCode_225() {
        q = new LinkedList();
    }

    public void push(int x) {
        q.offer(x);
        top_elem = x;
    }

    public int pop() {
       int size = q.size();
       while (size > 2) {
           q.offer(q.poll());
           size--;
       }
       top_elem = q.peek();
       q.offer(q.poll());
       return q.poll();
    }

    public int top() {
        return top_elem;
    }

    public boolean empty() {
       return q.isEmpty();
    }
}
