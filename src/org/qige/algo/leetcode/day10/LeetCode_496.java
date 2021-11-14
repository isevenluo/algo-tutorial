package org.qige.algo.leetcode.day10;

import org.junit.Test;

import java.util.Stack;

/**
 * @微信公众号: 七哥聊编程
 * @github: https://github.com/isevenluo
 * @gitee: https://gitee.com/isevenluo
 * @名额不多的个人微信：qige777ya
 * @description: 给你两个 没有重复元素 的数组nums1 和nums2，其中nums1是nums2的子集。
 * 请你找出 nums1中每个元素在nums2中的下一个比其大的值。
 * nums1中数字x的下一个更大元素是指x在nums2中对应位置的右边的第一个比x大的元素。如果不存在，对应位置输出 -1 。
 * @author: 程序员七哥
 * @create: 2021-11-15 00:05
 **/
public class LeetCode_496 {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        // 单调栈
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < nums1.length; i++) {
            // 倒着往栈里放
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                // 比当前元素小的都删掉
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums2[i]);
        }
        return res;
    }

    /**
     * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * 输出: [-1,3,-1]
     */
    @Test
    public void test() {
        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        System.out.println(nextGreaterElement(nums1, nums2));
    }
}
