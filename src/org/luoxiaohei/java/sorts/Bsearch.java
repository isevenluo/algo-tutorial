package org.luoxiaohei.java.sorts;

/**
 * @description: 二分查找思想实现：直到找到要查找的元素，或者区间被缩小为 0
 * @outhor: coderluo
 * @create: 2020-03-31 23:39
 */
public class Bsearch {

    public static void main(String[] args) {
        int [] a = {8,11,19,23,27,33,45,55,67,98};
        int value = 19;
        int result;
        result = bsearch(a,a.length,value);
        System.out.println(result);
        result = bsearch_dg(a,a.length,value);
        System.out.println(result);
    }

    // 循环实现二分查找
    public static int bsearch(int[] a,int n, int value) {
        int low = 0;int high = n-1;
        while (low<=high) {
            // 相比除法运算来说，计算机处理位运算要快得多
            int mid = low+((high-low)>>1);
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                low = mid+1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // 递归实现
    public static int bsearch_dg(int[] a,int n,int value) {
        int result = bsearchInternally(a,0,n-1,value);
        return result;
    }

    public static int bsearchInternally (int[] a,int low, int high, int value) {
        // 终止条件
        if (low > high) {
            return -1;
        }
        int mid = low + ((high-low)>>1);
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return bsearchInternally(a,mid+1,high,value);
        } else {
            return bsearchInternally(a,low,mid-1,value);
        }
    }
}
