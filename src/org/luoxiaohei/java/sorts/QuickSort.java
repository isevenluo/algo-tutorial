package org.luoxiaohei.java.sorts;

import java.util.Arrays;

/**
 * @description: 快速排序，类似于归并排序，采用分治思想，用递归来实现 {@link MergeSort}
 * 递推公式：quick_sort(p…r) = quick_sort(p…q-1) + quick_sort(q+1… r)
 * 终止条件：p >= r
 * 和归并的区别：1. 归并是从下到上，先计算子问题，然后在合并，而快排是自上而下，先分区，在处理子问题
 * @outhor: coderluo
 * @create: 2020-03-29 12:45
 */
public class QuickSort {
    /**
     * quick sort (no stable)
     * worst: O(n2)
     * best: O(nlogn)
     * average: O(nlogn)
     * space: O(1)
     * @param a 要排序的数组
     * @param n 数组大小
     */
    public static void quickSort(int []a,int n) {
        quick_sort_c(a,0,n-1);
    }
    private static void quick_sort_c(int[] a, int p, int r) {
        // 终止条件
        if (p>=r)
            return;
        // 获取分区位置
        int q = partition(a,p,r);
        quick_sort_c(a,p,q-1);
        quick_sort_c(a,q+1,r);
    }
    // 分区函数
    private static int partition(int[] a, int p, int r) {
        // 取分区最后一个元素作为分区点
        int pivot = a[r];
        // 游标i将需要排序的数组分为两部分，左边小于pivot，左右大于pivot
        int i = p;
        // 这里不需要比较最后一个元素，因为其值是pivot，最后放到i的位置，即分区的中心，左边小于于它，右边大于它
        for (int j = p; j <= r-1; ++j) {
            if (a[j] < pivot) {
                int tmp = a[i];
                a[i++] = a[j];
                a[j] = tmp;
            }
        }
        int tmp = a[r];
        a[r] = a[i];
        a[i] = tmp;
        return i;
    }

    public static void main(String[] args) {
        int a [] = generate(10,100);
        System.out.println(Arrays.toString(a));
        quickSort(a,a.length);
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
