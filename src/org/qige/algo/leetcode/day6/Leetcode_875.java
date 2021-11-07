package org.qige.algo.leetcode.day6;

import org.junit.Test;

/**
 * @微信公众号: 七哥聊编程
 * @github: https://github.com/isevenluo
 * @gitee: https://gitee.com/isevenluo
 * @名额不多的个人微信：qige777ya
 * @description: 二分搜索运用
 * 1、确定 x, f(x), target 分别是什么，并写出函数 f 的代码。
 * 2、找到 x 的取值范围作为二分搜索的搜索区间，初始化 left 和 right 变量。
 * 3、根据题目的要求，确定应该使用搜索左侧还是搜索右侧的二分搜索算法，写出解法代码。
 * @author: 程序员七哥
 * @create: 2021-11-07 19:37
 **/
public class Leetcode_875 {

    /**
     * target 就是限定在多少小时内吃完，也就是 h
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        if (piles == null || piles.length == 0) {
               return 0;
        }
        // 定义 x 的左右边界,最小是 1，最大是数组中元素的最大值，因为每小时只能吃一堆
        int left = 1, right = 1000000000 + 1;
        while (left < right) {
            int mid = left + (right - left)/2;
            // 用 f（x） 返回的时间和要求的时间对比，如果相等或者小于就压缩右侧空间
            if (f(piles, mid) <= h ) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 确定 f（x）方法
     * x：求啥 x 就是啥，这道题求的是吃香蕉的最小速度
     * f(x) 返回的就是吃完香蕉所需要的时间，x 越大 f(x) 越小
     *
     * @return
     */
    public int f(int[] piles, int x) {
        int hours = 0;
        // 以每小时 x 根的速度吃完需要几小时
        for (int i = 0; i < piles.length; i++) {
            hours += piles[i] / x;
            if (piles[i] % x > 0) {
                hours++;
            }
        }
        return hours;
    }

    /**
     * 输入: piles = [3,6,7,11], H = 8
     * 输出: 4
     */
    @Test
    public void test() {
        int[] piles = new int[] {3,6,7,11};
        int speed = minEatingSpeed(piles, 8);
        System.out.println(speed);

    }
}
