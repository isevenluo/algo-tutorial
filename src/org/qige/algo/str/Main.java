package org.qige.algo.str;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String c = scanner.nextLine();
        int cnt = 0,max = 0,i;
        for (i = 0; i < c.length(); i++) {
            if (c.charAt(i) == '(') {
                cnt++;
            } else {
                cnt--;
            }
            max = Math.max(max,cnt);
        }
        scanner.close();
        System.out.println(max);

        String str="qw23eqr123e4tt";
        System.out.println(str.indexOf("q"));
        System.out.println(ARFA(str));

    }
    //传参：str="qw23eqr123e4tt"
    public static String ARFA(String str) {
        //创建一个空字符串用于接收去重后的字符串
        String arfa="";
        //遍历字符串str
        for (int i = 0; i < str.length(); i++) {
            //获取字符串中下标为i的字符（char类型）
            char ch = str.charAt(i);
            //如果字符串没有重复，即：每个字符的下标都会等价于该字符第一次出现时的下标
            //判断字符串第一次出现ch字符时的下标是否等于i
            if (str.indexOf(ch)==i) {
                //将ch字符转化为String类型，并添加到字符串arfa的末尾
                arfa=arfa.concat(String.valueOf(ch));
            }
        }
        return arfa;
    }

}