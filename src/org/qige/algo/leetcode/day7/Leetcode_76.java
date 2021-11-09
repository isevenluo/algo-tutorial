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
    public String minWindow(String s, String t) {
        // 存储符合条件的字串长度和字串，用来取最短字串
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            // 因为java中substring方法是含头不含尾，因此这里等于s.length()，不然无法取到最后一个字符
            for (int j = i + 1; j <= s.length(); j++) {
                // 用于统计t中的所有字符出现几次
                Map<String,Integer> tMap = new HashMap<>();
                    String tmpResult = s.substring(i,j);
                for (int k = 0; k < t.length(); k++) {
                    // 因为有可能t中有相同的字符连续出现，因此不能简单遍历t中的字符判断是否包含，还需要判断出现的次数
//                    if (!tmpResult.contains(t.charAt(k) + "")) {
//                        // 中断本层循环，因为不包含t中的字符
//                        break;
//                    }
                    tMap.put(t.charAt(k) + "", tMap.getOrDefault(t.charAt(k)+"",0)+1);
                }
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

    @Test
    public void test() {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s,t));
        // 这个输出应该为空
        String s1 = "aaa", t1 = "aaaa";
        System.out.println(minWindow(s1,t1));
    }

}