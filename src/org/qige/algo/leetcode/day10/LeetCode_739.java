package org.qige.algo.leetcode.day10;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 */
public class LeetCode_739 {
    /**
     * 这个问题本质上也是找 Next Greater Number，只不过现在不是问你 Next Greater Number 是多少，而是问你当前距离 Next Greater Number 的距离而已。
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        // 先构造一个单调栈
        Stack<Integer> stack = new Stack<>();
        // 存放每一天对应更高温度的下标
        Map<Integer,Integer> map = new HashMap<>();

        int[] result = new int[temperatures.length];

        // 倒着往栈里放，因为要保持栈中放的都是每次新增元素右边的元素。栈中是单调递增顺序
        for (int i = temperatures.length-1; i >=0 ; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                // 比自己小的全都闪开，因为我进来后他们不可能是后面元素的 Next Greater Number 被我挡住了
                stack.pop();
            }
            result[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            //存储元素的索引下标
            stack.push(i);
        }
        return result;
    }

    /**
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     */
    @Test
    public void test() {
        int[] temperatures = new int[] {73,74,75,71,69,72,76,73};
        System.out.println(Arrays.toString(dailyTemperatures(temperatures)));
    }
}
