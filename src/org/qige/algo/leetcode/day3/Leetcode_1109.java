package org.qige.algo.leetcode.day3;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @微信公众号: 七哥聊编程
 * @github: https://github.com/isevenluo
 * @gitee: https://gitee.com/isevenluo
 * @名额不多的个人微信：qige777ya
 * @description: 航班预订统计
 * 有一份航班预订表bookings ，表中第i条预订记录bookings[i] = [firsti, lasti, seatsi]
 * 意味着在从 firsti到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi个座位。
 * <p>
 * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
 * @author: 程序员七哥
 * @create: 2021-11-03 23:46
 **/
public class Leetcode_1109 {

    public int[] corpFlightBookings(int[][] bookings, int n) {
        // 定义拆分数组，用来计算每个元素位置增加、减少的最终值
        // 初始数组全为 0，因此不用构造了
        int[] diff = new int[n];
        for (int i = 0; i < bookings.length; i++) {
            diff[bookings[i][0] - 1] += bookings[i][2];
            if (bookings[i][1] < n) {
                diff[bookings[i][1]] -= bookings[i][2];
            }
        }
        int[] res = new int[n];
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
             res[i] = res[i-1] + diff[i];
        }
        return res;
    }

    /**
     * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
     * 输出：[10,55,45,25,25]
     * 解释：
     * 航班编号        1   2   3   4   5
     * 预订记录 1 ：   10  10
     * 预订记录 2 ：       20  20
     * 预订记录 3 ：       25  25  25  25
     * 总座位数：      10  55  45  25  25
     * 因此，answer = [10,55,45,25,25]
     */
    @Test
    public void test() {
        int[][] bookings = new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int[] ints = corpFlightBookings(bookings, 5);
        System.out.println(Arrays.toString(ints));

    }
}
