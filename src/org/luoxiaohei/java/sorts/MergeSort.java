package org.luoxiaohei.java.sorts;

import java.util.Arrays;

/**
 * @description: 归并排序， 将数据分成前后两部分，然后对前后分别排序，在组装在一起，利用分治思想，递归是实现的常用方式
 * @outhor: coderluo
 * @create: 2020-03-29 10:11
 */
public class MergeSort {
    /**
     * merge sort (stable)
     * worst: O(nlogn)
     * best: O(nlogn)
     * average: O(nlogn)
     * space: O(n)
     * @param a array tobe sorted
     * @param n length of array
     */
    public static void mergeSort(int[] a,int n) {
        merge_sort_c(a,0,n-1);
    }
    public static void merge_sort_c (int[] a,int start,int end) {
        // 循环终止条件
        if (start >= end) {
            return;
        }
        // 取start到end的终止位置
        int middle = (start+end)/2;
        // 分治递归
        merge_sort_c(a,start,middle);
        merge_sort_c(a,middle+1,end);
        // 合并分治的两个数组
        merge(a,start,middle,end);
    }
    private static void merge(int[] a, int start, int middle, int end) {
        // 申请一个大小和原数组一样大小的临时数组
        int[] tmp = new int[a.length];
        // 初始化两个index，分别指向分治后两个数组的头位置
        int i = start;
        int j = middle + 1;
        int t = 0;
        while (i<=middle && j <= end) {
            if (a[i] >= a[j]) {
                tmp[t++] = a[j++];
            } else {
                tmp[t++] = a[i++];
            }
        }
        // 判断哪个子数组还有数据
        int p = i; int q = middle;
        if (j<=end) {
            p = j; q = end;
        }
        // 将剩余数据拷贝回tmp数组
        while (p<=q) {
            tmp[t++] = a[p++];
        }
        // 将tmp数组拷贝到原数组
        for (int index = 0; index <= end - start;++index) {
            a[start+index] = tmp[index];
        }
    }

    public static void main(String[] args) {
        int a [] = generate(10,100);
        System.out.println(Arrays.toString(a));
        mergeSort(a,a.length);
        System.out.println(Arrays.toString(a));
    }
    public static int[] generate(int len,int max) {
        int [] a = new int[len];
        for (int i = 0; i<len;++i) {
            a[i] = (int) (Math.random()*max);
        }
        return a;
    }
}
