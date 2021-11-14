package org.qige.algo.leetcode.day7;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @微信公众号: 七哥聊编程
 * @github: https://github.com/isevenluo
 * @gitee: https://gitee.com/isevenluo
 * @名额不多的个人微信：qige777ya
 * @description: 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * @author: 程序员七哥
 * @create: 2021-11-14 12:40
 **/
public class Leetcode_438 {


    /**
     * 采用滑动窗口算法还是比较简单的，注意两点：
     * 1. 求的是子串，所以缩小窗口的条件就是长度大于等于 p 的长度；
     * 2. 当窗口收缩时判断是否满足条件，
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {

        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0, valid = 0;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < p.length(); i++) {
            need.put(p.charAt(i), need.getOrDefault(p.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            char r = s.charAt(i);
            right++;
            if (need.containsKey(r)) {
                window.put(r, window.getOrDefault(r, 0) + 1);
                if (window.get(r).equals(need.get(r))) {
                    valid++;
                }
            }
            // 当子串长度大于目标字符串 p 的长度开始收缩
            while (right - left >= p.length()) {
                // 符合要求将起始下标加入结果列表
                if (valid == need.size()) {
                    result.add(left);
                }
                char l = s.charAt(left);
                left++;
                if (need.containsKey(l)) {
                    if (window.get(l).equals(need.get(l))) {
                        valid--;
                    }
                    window.put(l, window.getOrDefault(l, 0) - 1);
                }
            }
        }
        return result;
    }

    /**
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     */
    @Test
    public void test() {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(findAnagrams(s, p));

    }
}
