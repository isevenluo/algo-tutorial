package org.qige.algo.stack;

import java.util.Arrays;

/**
 * @description: 利用数组实现一个顺序栈，也可以用链表实现链式栈
 * 栈结构只允许操作栈顶元素，所以使用栈中元素count的加减来实现push和pop操作
 * 时间空间复杂度均为O（1）
 * @outhor: coderluo
 * @create: 2020-03-27 0:41
 */
public class StackBaseOnArray {
    // 数组
    private String[] items;

    // 数组中元素的个数
    private int count;
    // 数组的大小
    private int n;

    public StackBaseOnArray(int n) {
        this.items = new String[n];
        this.count = 0;
        this.n = n;
    }

    public boolean push(String item) {
        // 数组满了，入栈失败
        if (count == n) {
            return false;
        }
        // 将入栈元素放到count位置
        items[count++] = item;
        return true;
    }

    public String pop() {
        // 数组为空，返回null
        if (count == 0) {
            return null;
        }
        // 返回count-1位置的元素
        return items[--count];
    }

    public static void main(String[] args) {
        StackBaseOnArray stack = new StackBaseOnArray(3);
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("c");
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());


    }

    @Override
    public String toString() {
        return "StackImpl{" +
                "items=" + Arrays.toString(items) +
                '}';
    }
}
