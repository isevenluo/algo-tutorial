package org.qige.algo.leetcode.day7;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 滑动窗口算法
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 */
public class Leetcode_76 {

    /**
     * 暴力破解法，在没有掌握滑动窗口算法之前，根据暴力破解法先解题，这也是面试时循序渐进的一个思考过程，显得不是我们背题了
     * @param s
     * @param t
     * @return
     */
    public String forceResolve(String s, String t) {
        // 存储符合条件的字串长度和字串，用来取最短字串
        Map<Integer, String> map = new HashMap<>();
        // 用于统计t中的所有字符出现几次
        Map<String,Integer> tMap = new HashMap<>();
        for (int k = 0; k < t.length(); k++) {
            // 因为有可能t中有相同的字符连续出现，因此不能简单遍历t中的字符判断是否包含，还需要判断出现的次数
            tMap.put(t.charAt(k) + "", tMap.getOrDefault(t.charAt(k)+"",0)+1);
        }
        for (int i = 0; i < s.length(); i++) {
            // 因为java中substring方法是含头不含尾，因此这里等于s.length()，不然无法取到最后一个字符
            for (int j = i + 1; j <= s.length(); j++) {
                String tmpResult = s.substring(i,j);
                // 是否包含所有t中字符的标志
                AtomicBoolean flag = new AtomicBoolean(true);
                tMap.keySet().stream().forEach(p -> {
                    String tmpStr = tmpResult;
                    for (int k = 0; k < tMap.get(p); k++) {
                        // 如果找到了一个就替换这个字符，然后继续匹配，处理一个字符出现多次的情况
                        if (tmpStr.contains(p)) {
                            tmpStr = tmpStr.replaceFirst(p,"");
                        // 如果不包含说明当前子串不包含所有t中的字符，设为false
                        } else {
                            flag.set(false);
                        }
                    }
                    // 不满足说明当前字串不包含所有t中字符，退出当前循环
                    if (flag.get()) {
                        return;
                    }
                });
                // 不为false，说明当前字串包含所有t中字符，将字串长度和字串存起来
                if (flag.get()) {
                    map.put(tmpResult.length(), tmpResult);
                }
            }
        }
        // 获取字串长度最短的key，拿到对应的字串
        Integer integer = map.keySet().stream().min(Integer::compareTo).orElse(0);
        return integer == 0 ? "" : map.get(integer);
    }


    /**
     * 使用滑动窗口算法求解
     * 先移动 right，再移动 left…… 直到 right 指针到达字符串 S 的末端，算法结束。
     * 初始化 window 和 need 两个哈希表，记录窗口中的字符和需要凑齐的字符
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        Map<Character,Integer> window = new HashMap<>();
        Map<Character,Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
             need.put(t.charAt(i),need.getOrDefault(t.charAt(i),0)+1);
        }
        // 存储满足 need 条件中字符的个数
        int valid = 0;
        // 左右指针来标记滑动窗口
        int left = 0, right = 0;
        // 记录最终窗口的起始位置和字符长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            // 判断 valid 是否等于 need 需要的字符条件个数，等于就开始缩小窗口，不等于就继续扩大
            // 初始化是一个左闭右开窗口，因此是空的，需要右移，获取需要加入窗口的字符
            char r = s.charAt(right);
            // 右移
            right++;
            // 如果字符是条件中需要的字符，则处理窗口中字符结果
            if (need.containsKey(r)) {
                window.put(r, window.getOrDefault(r, 0) + 1);
                // 如果窗口中字符个数等于需要的个数，则给已满足条件的字符个数+1
                if (window.get(r).equals(need.get(r))) {
                    valid++;
                }
            }
            // 如果窗口中已经满足所有需要的字符则开始缩小窗口
            while (valid == need.size()) {
                // 更新最小覆盖子串结果，只有比当前结果还短时才更新
                if (right - left < len) {
                    len = right - left;
                    start = left;
                }
                // 开始缩小窗口
                char l = s.charAt(left);
                left++;
                // 如果是条件需要的字符则需要处理结果和窗口字符个数
                if (need.containsKey(l)) {
                    // 如果窗口中字符和需要的一样多，缩小后就不再满足
                    if (window.get(l).equals(need.get(l))) {
                        valid--;
                    }
                    // 窗口中的字符减少
                    window.put(l, window.get(l) - 1);
                }
            }
        }
        // 返回最小子串,如果长度还是 int 最大值说明没有满足条件的子串
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);

    }

    @Test
    public void test() {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(forceResolve(s,t));
        System.out.println(minWindow(s,t));
        // 这个输出应该为空
        String s1 = "aaa", t1 = "aaaa";
        System.out.println(forceResolve(s1,t1));

        System.out.println(minWindow(s1,t1));
    }

}