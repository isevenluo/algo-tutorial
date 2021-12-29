package org.qige.algo.leetcode.day10;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
        // 声明一个map用来存储 nums2 每个元素对应的右边第一个比它大的值,然后遍历nums1 的值当做key去获取即可
        Map<Integer,Integer>  map = new HashMap<>();
        // 单调栈，用来计算右边第一个比当前元素大的值,存储的是索引下标
        Stack<Integer> stack = new Stack();
        // 先利用单调栈找到nums2中每一个元素对应下一个比他大的值保存起来
        for (int i = 0; i < nums2.length; i++) {
            // 栈中存放的是还没有找到第一个比他大的值，找到了下一个比他大的值就出栈
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                // 找到了第一个比他大的右边元素就删除
                int j = stack.pop();
                map.put(nums2[j],nums2[i]);
            }
            // 不大于栈顶元素就继续往右找，如果一直就没有找到map中就没有这个元素的key
            stack.push(i);

        }
        // 结果数组
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i],-1);
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
        System.out.println(Arrays.toString(nextGreaterElement(nums1, nums2)));
    }
}
