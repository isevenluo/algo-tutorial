package org.qige.algo.leetcode.day6;

import org.junit.Test;

/**
 * @微信公众号: 七哥聊编程
 * @github: https://github.com/isevenluo
 * @gitee: https://gitee.com/isevenluo
 * @名额不多的个人微信：qige777ya
 * @description: 在 D 天内送达包裹的能力,传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 *
 * 传送带上的第 i个包裹的重量为weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。

 * @author: 程序员七哥
 * @create: 2021-11-07 20:15
 **/
public class Leetcode_1011 {


    /**
     * 找到 x 的取值范围作为二分搜索的搜索区间，初始化 left 和 right 变量
     * @param weights
     * @param days
     * @return
     */
    public int shipWithinDays(int[] weights, int days) {
        // x 最小取货物中最重的重量，最大取数组元素总和，一船拉完
        int left = 0, right = 0;
        for (int i : weights) {
            left = Math.max(left, i);
            right += i;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            // 因为要找最小运载能力，因此是左侧搜索，要压缩右侧空间
            if (f(weights, mid) <= days) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;

    }

    /**
     * 1. 先找 x，和 f（x）
     * 求啥啥就是 x，因此这里是船舶的载力，f(x) 返回运送玩所需要的天数
     * 货物必须按照给定的顺序装运，不可拆分
     * x 最小等于货物最大的重量，不然货物就装不上去
     */
    public int f(int[] weights, int x) {
        int days = 0;
        for (int i = 0; i < weights.length; ) {
            // 每一天船都装到装不下为止
            int cap = x;
            while (i < weights.length) {
                if (weights[i] <= cap) {
                    // 当前船还能拉多重的货
                    cap -= weights[i];
                    i++;
                } else {
                    // 如果物品的重要大于当前的运载能力，只能扥下一船，因为只能按照顺序不可拆分的装货
                    break;
                }
            }
            // 当一船拉不下了，就第二天在拉
            days++;
        }
        return days;
    }

    /**
     * 输入：weights = [3,2,2,4,1,4], D = 3
     * 输出：6
     * 解释：
     * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
     * 第 1 天：3, 2
     * 第 2 天：2, 4
     * 第 3 天：1, 4
     * @return
     */
    @Test
    public void test() {
        int[] weights = new int[]{3,2,2,4,1,4};
        System.out.println(shipWithinDays(weights,3));

    }
}
