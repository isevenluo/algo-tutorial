package org.luoxiaohei.java.sorts;

import java.util.Arrays;

/**
 * @description: 选择排序，类似插入排序，是先从未排序的部分选择最小的插入到已排序部分的最后一个位置 {@link InsertionSort}
 * @outhor: coderluo
 * @create: 2020-03-28 19:48
 */
public class SelectionSort {

    public static void selectionSort (int [] a,int n) {
        if (n<=1) {
            return;
        }
        // i代表有序区末尾的位置
        for (int i = 0;i < n;++i) {
            // 未排序区域最小的元素位置
            int min = i;
            // 找出未排序区最小的元素位置
            for (int j = i+1;j < n;++j) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            // 说明未排序区第一个位置不是最小的元素，否则说明当前元素是未排序区最小的值
            if (min != i) {
                int tmp = a[min];
                a[min] = a[i];
                a[i] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = generate(10,100);
        System.out.println(Arrays.toString(arr));
        selectionSort(arr,arr.length);
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
