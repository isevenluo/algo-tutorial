package org.qige.algo.leetcode.day9;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @微信公众号: 七哥聊编程
 * @github: https://github.com/isevenluo
 * @gitee: https://gitee.com/isevenluo
 * @名额不多的个人微信：qige777ya
 * @description:
 * @author: 程序员七哥
 * @create: 2021-11-14 22:53
 **/

public class LeetCode_232 {

    private Deque<Integer> s1,s2;
    public LeetCode_232() {
        s1 = new ArrayDeque<>();
        s2 = new ArrayDeque<>();
    }

    public void push(int x) {
        s1.push(x);
    }

    public int pop() {
        // 保证 s2 非空
        peek();
        return s2.pop();
    }

    public int peek() {
        if(s2.isEmpty()) {
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */