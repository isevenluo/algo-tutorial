package org.qige.algo.sorts;

import java.util.Arrays;

/**
 * @description: 冒泡排序
 * @link
 * @outhor: coderluo
 * @create: 2020-03-28 18:43
 */
public class BubbleSort {

    public static void bubbleSort (int[] a,int n) {
        if (n<=1) {
            return;
        }
        for (int i = 0; i < n; ++i) {
            boolean flag = false;
            // 每一轮冒泡，将一个数放到正确的位置
            for (int j = 0; j<n-i-1; ++j) {
                if (a[j] > a[j+1]) {
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;
                }
            }
            // 如果没有数据交换，说明已经有序，直接返回
            if (!flag) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = generate(10,100);
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr,arr.length);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] generate(int len,int max) {
        int [] a = new int[len];
        for (int i = 0; i<len;++i) {
            a[i] = (int) (Math.random()*max);
        }
        return a;
    }
}
