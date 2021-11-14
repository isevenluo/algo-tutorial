package org.qige.algo.leetcode.day7;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @微信公众号: 七哥聊编程
 * @github: https://github.com/isevenluo
 * @gitee: https://gitee.com/isevenluo
 * @名额不多的个人微信：qige777ya
 * @description: 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * @author: 程序员七哥
 * @create: 2021-11-07 23:11
 **/
public class Leetcode_3 {

    /**
     * 这个解法牛逼，时间复杂度 O(n)
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0) {
            return 0;
        }
        // 存储字符和字符出现的位置
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                // 如果当前字符存在说明重复了，需要从重复字符的位置移动左指针
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            // 存储字符和出现的位置
            map.put(s.charAt(i),i);
            // 当左指针收缩后，说明没有重复元素了，更新结果
            max = Math.max(max,i-left+1);
        }
        return max;
    }

    /**
     * 按照 labuladong 讲的解法
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_1(String s) {
        Map<Character,Integer> window = new HashMap<>();
        int left = 0, right = 0, max = 0;
         if (s == null || s.length() <= 0) {
             return 0;
         }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 移动右指针
            right++;
            // 更新窗口数据
            window.put(c,window.getOrDefault(c,0)+1);
            // 如果当前字符已经大于 1，说明重复了，开始收缩左指针
            while (window.get(c) > 1) {
                char l = s.charAt(left);
                window.put(l,window.getOrDefault(l,0)-1);
                left++;
            }
            // 移动完左指针说明没有重复了
            max = Math.max(max, right-left);
        }
        return max;
    }

    /**
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */
    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring_1("abcabcbb"));
    }
}
