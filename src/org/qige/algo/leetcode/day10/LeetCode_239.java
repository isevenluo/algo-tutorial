package org.qige.algo.leetcode.day10;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 */
public class LeetCode_239 {

    class MonotonicQueue {
        // 使用双向链表支持头尾操作
        private LinkedList<Integer> q = new LinkedList();
        // 单调递减队列，将比插入元素小的元素都删除，因为他们不可能是最大元素了
        void push(int n) {
            while (!q.isEmpty() && q.getLast() < n) {
                q.removeLast();
            }
            q.addLast(n);
        }

        int max() {
            return q.getFirst();
        }
        // 先进先出，所以队头就是最好进入的元素了
        void pop(int n){
            // 有可能因为队头由于自己过小在插入时已经被删除了，因此这里加个判断不存在就不用删了
            if (q.getFirst() == n) {
                q.pollFirst();
            }
        }

    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        // 一个单调递减队列，可以每次从队头直接取出最大值
        MonotonicQueue window = new MonotonicQueue();
        int[] result = new int[nums.length-k+1];
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            // 先将窗口塞满k-1个元素
            if (i < k-1) {
                window.push(nums[i]);
            } else {
                // 此时窗口已经有k-1个元素，先添加元素再取出最大值
                window.push(nums[i]);
                result[j++] = window.max();
                // 往前滑动窗口，删除最左边的元素，此时窗口大小k-1
                window.pop(nums[i-k+1]);
            }
        }
        return result;
    }

    /**
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     */
    @Test
    public void test() {
        int[] nums = new int[] {1,3,-1,-3,5,3,6,7};
        int[] window = maxSlidingWindow(nums, 3);
        System.out.println(Arrays.toString(window));
    }
}
