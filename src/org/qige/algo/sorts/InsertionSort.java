package org.qige.algo.sorts;

/**
 * @description: 插入排序
 * @outhor: coderluo
 * @create: 2020-03-28 19:08
 */
public class InsertionSort {

    public static void insertionSort(int [] a, int n) {
        if (n<=1) return;
        // 分为已排序和未排序两部分，初始的已排序只有第一个元素，因此从第二个开始开始遍历
        for (int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
            for (;j >= 0; --j) {
                // 移动
                if (a[j] > value) {
                    a[j+1] = a[j];
                } else {
                    // 找出已排序部分不大于当前value的值，即该value正确的位置，退出循环
                    break;
                }
            }
            // 插入
            a[j+1] = value;
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000;++i) {
            int[] ints = generate(200, 1000);
            BubbleSort.bubbleSort(ints,ints.length);
        }
        System.out.println(System.currentTimeMillis()-start);


    }

    public static int[] generate(int len,int max) {
        int [] a = new int[len];
        for (int i = 0; i<len;++i) {
            a[i] = (int) (Math.random()*max);
        }
        return a;
    }
}
