package org.qige.algo.leetcode.day2;

import java.util.HashMap;
import java.util.Map;

/**
 * @微信公众号: 七哥聊编程
 * @github: https://github.com/isevenluo
 * @gitee: https://gitee.com/isevenluo
 * @名额不多的个人微信：qige777ya
 * @description: 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
 * @author: 程序员七哥
 * @create: 2021-11-02 21:33
 **/
public class Leetcode_560 {

    /**
     * 暴力解法，这个思路的精妙在于以 i 结尾，和为k，符合条件的下标 end 的个数， 0<end<i
     * 必须要从i为结尾往前找，因为每一次外循环都是从新增一个新的尾数，前面的都计算过了
     * 同时从头开始计算会导致出错，因为有可能新增了一个尾数可以满足但是从头求和又不满足了
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;

        for (int i = 0; i < nums.length; ++i) {
            int sum = 0;
            for (int end = i; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 前缀和 + 哈希表优化
     * 假设 pre[i]=pre[i−1]+nums[i]
     * pre[i]−pre[j−1]==k （前缀和之差为k，代表这两个前缀和中间的数字相加就是K）
     * 简单来讲, map中的数据都是0~i (i的范围为0到nums.length-1) 的子数组和,
     * 那么pre-k就是 0~j(j<i)的子数组和,如果在map里找到pre-k, j~i的子数组的和就是k了
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum_1(int[] nums, int k) {
        // 最终结果
        int count = 0;
        // 记录当前前面数字相加之和
        int pre = 0;
        // 记录前缀出现的次数
        Map<Integer,Integer> map = new HashMap<>();

        map.put(0,1);

        for (int i = 0; i < nums.length; ++i) {
            pre += nums[i];
            if (map.containsKey(pre-k)) {
                // 获取前缀和+k等于当前前缀和的次数，这些都是区间满足条件的
                count += map.get(pre-k);
            }
            map.put(pre,map.getOrDefault(pre,0)+1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] sum = new int[]{0,0,0};
        Leetcode_560 solution = new Leetcode_560();
        int count = solution.subarraySum_1(sum, 0);
        System.out.println(count);
    }
}
