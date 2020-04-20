package org.luoxiaohei.java.queue;

import java.util.Arrays;

/**
 * @description: 队列跟栈非常相似，支持的操作也很有限，最基本的操作也是两个：
 * 入队 enqueue()，放一个数据到队列尾部；出队 dequeue()，从队列头部取一个元素。
 * @outhor: coderluo
 * @create: 2020-03-27 23:20
 */
public class ArrayQueue {
    // 数组item，数组大小n
    private String [] items;
    private int n;
    // 队头
    private int head;
    // 队尾
    private int tail;

    public ArrayQueue(int n) {
        items = new String[n];
        this.n = n;
    }
    // 入队，随着入队出队会造成head和tail一直向后移动，到最后可能数组还有空间，但是已经无法入队了
    public boolean enqueue(String item) {
        // 数组容量满了，返回false
        if (tail == n) {
            return false;
        }
        // 放到队尾
        items[tail++] = item;
        return true;
    }

    public boolean enqueue1(String item) {

        // 表示队列没有空间了
        if (tail == n ) {
            // 队列占满了
            if (head == 0)
                return false;
            for (int i = head;i<tail;i++) {
                items[i-head] = items[i];
            }
            tail -= head;
            head = 0;
        }

        // 放到队尾
        items[tail++] = item;
        return true;
    }

    // 出队
    public String dequeue() {
        // 头尾相等，表示队列为空
        if (head == tail) {
            return null;
        }
        // 返回队头元素，索引+1
        return items[head++];
    }



    @Override
    public String toString() {
        return "QueueImpl{" +
                "items=" + Arrays.toString(items) +
                '}';
    }
}
