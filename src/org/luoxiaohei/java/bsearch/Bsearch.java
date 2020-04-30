package org.luoxiaohei.java.bsearch;

/**
 * @description: 二分查找思想实现：直到找到要查找的元素，或者区间被缩小为 0
 * @outhor: coderluo
 * @create: 2020-03-31 23:39
 */
public class Bsearch {

    public static void main(String[] args) {
        int [] a = {8,11,13,19,19,19,45,55,67,98};
        int value = 19;
        int result;
        result = bsearch(a,a.length,value);
        System.out.println(result);
        result = bsearch_dg(a,a.length,value);
        System.out.println(result);
        result = bsearch_last(a,a.length,value);
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

    /**
     * 变体二分查找：查找最后一个值等于给定值的元素
     * @return
     */
    public static int bsearch_last(int[] a,int n,int value) {
        int low = 0;
        int high = n-1;
        while (low<=high) {
            int mid = low + ((high-low)>>1);
            if (a[mid] < value) {
                low = mid + 1;
            } else if(a[mid] > value) {
                high = mid-1;
            } else {
                if (mid == 0 || a[mid+1] != value) return mid;
                else low = mid+1;
            }

        }
        return -1;
    }

    /**
     * 变体二分查找：查找第一个大于或者等于给定元素的值
     * @return
     */
    public int bsearch_eqOrgt(int[] a, int n, int value) {
        int low = 0;
        int high = n-1;
        while (low <= high) {
            int mid = low + ((high-low) >> 1);
            if (a[mid] >= value) {
                if (mid == 0 || a[mid-1] < value) {
                    return mid;
                } else{
                    high = mid - 1;
                }
            } else {
               low = mid + 1;
            }
        }
        return -1;

    }

    /**
     * 变体二分查找：查找最后一个小于或者等于给定元素的值
     * @return
     */
    public int bsearch_last_eqOrlt(int[] a,int n, int value) {
        int low = 0; int high = n-1;
        while (low<=high) {
            int mid = low + ((high-low)>>1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == n-1) || a[mid + 1] > value) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

}
