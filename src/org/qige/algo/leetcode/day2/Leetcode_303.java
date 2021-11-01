package org.qige.algo.leetcode.day2;

/**
 * @微信公众号: 七哥聊编程
 * @github: https://github.com/isevenluo
 * @gitee: https://gitee.com/isevenluo
 * @名额不多的个人微信：qige777ya
 * @description: 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
 * @author: 程序员七哥
 * @create: 2021-11-02 07:26
 **/
public class Leetcode_303 {


    // 前缀和数组
    private int[] preSum;

    /**
     * 根据输入的数组构造前缀和
     * @param nums
     */
    public Leetcode_303(int[] nums) {
        // preSum[i] 记录 nums[0..i-1] 的累加和,所以数组长度要+1
        preSum = new int[nums.length+1];
        // 初始化前缀和数组
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
    }
    // 现在计算索引区间的数值之和就简单了，直接用做右边减去左边
    public int sumRange(int left, int right) {
        return preSum[right+1] - preSum[left];
    }


    public static void main(String[] args) {
        Leetcode_303 obj = new Leetcode_303(new int[] {-2, 0, 3, -5, 2, -1});

        int param_1 = obj.sumRange(0, 0);
        System.out.println(param_1);
    }
}
