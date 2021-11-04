package org.qige.algo.leetcode.day4;

import org.junit.Test;

/**
 * 回文串的判断
 * 回文串就是正着读和反着读都一样的字符串。
 */
public class Leetcode_5 {

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            // 解决没有中心轴，偶数长度的回文串，这里就以两个数为中心去查找
            String s1 = palindrome(s, i, i + 1);
            String s2 = palindrome(s, i, i);
            result = result.length() > s1.length() ? result : s1;
            result = result.length() > s2.length() ? result : s2;
        }
        return result;
    }

    public String palindrome(String s, int l, int r) {
        // 防止数组下标越界
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            // 从中心往两边扩展，如果相等就说明正反读都一样
            l--;r++;
        }
        return s.substring(l+1,r);
    }

    @Test
    public void test() {
        String s = "babad";
        String s1 = "ac";
        System.out.println(longestPalindrome(s));
        System.out.println(longestPalindrome(s1));
    }
}

