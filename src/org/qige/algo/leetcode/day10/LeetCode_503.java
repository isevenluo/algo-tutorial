package org.qige.algo.leetcode.day10;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 */
public class LeetCode_503 {

    public int[] nextGreaterElements(int[] nums) {

        // 循环数组虽然不能简单的遍历到数组最后一个元素，但是如果遍历一圈还是回到当前位置没找到就说明没有
        int[] tmp = new int[nums.length*2];
        // 将数组循环一遍放入到临时数组中
        int len = nums.length;
        int j = 0;
        for (int i = 0; i < nums.length*2; i++) {
            tmp[j++] = nums[i%len];
        }
        System.out.println(Arrays.toString(tmp));
        // 存放结果
        int[] result = new int[nums.length];
        // 存放数组下标
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(result,-1);
        // 接下来就是按照单调栈寻找下一个比自己大的值来做就好了
        for (int i = 0; i < tmp.length; i++) {
            while (!stack.isEmpty() && tmp[stack.peek()] < tmp[i]) {
                // 如果栈中元素小于当前新进入的元素，那么全部出栈，这些值的下一个更大元素即为当前进入的元素
                result[stack.pop()] = tmp[i];
            }
            // 将当前元素位置入栈，因为这个位置是被拉直的数组所以这里要减去一个数组长度
            stack.push(i % nums.length);

        }
        return result;
    }


    /**
     * 输入: [1,2,1]
     * 输出: [2,-1,2]
     * 解释: 第一个 1 的下一个更大的数是 2；
     * 数字 2 找不到下一个更大的数；
     * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
     */
    @Test
    public void test() {
        // 输出 [2,3,4,-1,4]
        int[] nums = new int[] {1,2,3,4,3};
        System.out.println(Arrays.toString(nextGreaterElements(nums)));

    }
}
