package org.qige.algo.leetcode.day3;

import org.junit.Test;

/**
 * @微信公众号: 七哥聊编程
 * @github: https://github.com/isevenluo
 * @gitee: https://gitee.com/isevenluo
 * @名额不多的个人微信：qige777ya
 * @description: 拼车
 * 
 * @author: 程序员七哥
 * @create: 2021-11-03 23:05
 **/
public class Leetcode_1094 {

    public boolean carPooling(int[][] trips, int capacity) {
        // 获取最大的站点位置，决定差分数组的长度
        int maxLength = 0;
        for (int[]trip : trips) {
            maxLength = maxLength > (trip[1] > trip[2] ? trip[1] : trip[2]) ? maxLength : (trip[1] > trip[2] ? trip[1] : trip[2]);
        }
        // 定义差分数组
        int[] preSum = new int[maxLength+1];
        // 计算每个站点对应位置的差分数组
        for (int i = 0; i < trips.length; i++) {
            preSum[trips[i][1]] += trips[i][0];
            preSum[trips[i][2]] -= trips[i][0];
        }
        // 这里直接定义当前站点需要载客数，无需复原数组了
        int count = 0;
        for (int j = 0; j < preSum.length; j++) {
            count += preSum[j];
            if (count > capacity) {
                return false;
            }

        }
        return true;
    }

    /**
     * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
     * 输出：false
     */
    @Test
    public void test() {
        int[][] trips = new int[][]{{2,1,5},{3,3,7}};
        boolean b = carPooling(trips, 5);
        System.out.println(b);
    }
}
