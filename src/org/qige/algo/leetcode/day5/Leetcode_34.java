package org.qige.algo.leetcode.day5;

import org.junit.Test;

import java.util.Arrays;

public class Leetcode_34 {

    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回[-1, -1]。
     *
     * 进阶：
     * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = left_bound(nums, target);
        result[1] = right_bound(nums, target);
        return result;
    }


    public int left_bound(int nums[], int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length-1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            // 如果相等，不返回继续压缩右侧边界
            if (nums[middle] == target) {
                right = middle - 1;
            // 如果小于，左侧边界右移
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else if(nums[middle] > target) {
                right = middle - 1;
            }
        }
        /**
         * 对于这个数组，算法会返回 1。这个 1 的含义可以这样解读：nums 中小于 2 的元素有 1 个。
         * 比如对于有序数组 nums = [2,3,5,7], target = 1，算法会返回 0，含义是：nums 中小于 1 的元素有 0 个。
         * 再比如说 nums = [2,3,5,7], target = 8，算法会返回 4，含义是：nums 中小于 8 的元素有 4 个。
         * 综上可以看出，函数的返回值（即 left 变量的值）取值区间是闭区间 [0, nums.length]，所以我们简单添加两行代码就能在正确的时候 return -1
         */
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;

    }

    public int right_bound(int nums[], int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length-1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            // 如果相等，不返回继续压缩左侧边界
            if (nums[middle] == target) {
                left = middle + 1;
                // 如果小于，左侧边界右移
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else if(nums[middle] > target) {
                right = middle - 1;
            }
        }
        // 检查right越界的情况
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;

    }


    /**
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     */
    @Test
    public void test() {
        int [] nums = new int[] {5,7,7,8,8,10};
        int[] result = searchRange(nums, 8);
        System.out.println(Arrays.toString(result));
    }





}
