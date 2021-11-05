package org.qige.algo.leetcode.day5;

import org.junit.Test;

import javax.swing.text.html.HTML;

/**
 * 1. 最基本的二分查找算法：
 * 因为我们初始化 right = nums.length - 1
 * 所以决定了我们的「搜索区间」是 [left, right]
 * 所以决定了 while (left ‹= right)
 * 同时也决定了 left = mid+1 和 right = mid-1
 * 因为我们只需找到一个 target 的索引即可
 * 所以当 nums[mid] == target 时可以立即返回
 *
 * 2. 寻找左侧边界的二分查找：
 * 因为我们初始化 right = nums.length
 * 所以决定了我们的「搜索区间」是 [left, right)
 * 所以决定了 while (left ‹ right)
 * 同时也决定了 left = mid + 1 和 right = mid
 *
 * 因为我们需找到 target 的最左侧索引
 * 所以当 nums[mid] == target 时不要立即返回
 * 而要收紧右侧边界以锁定左侧边界
 *
 * 3. 寻找右侧边界的二分查找：
 * 因为我们初始化 right = nums.length
 * 所以决定了我们的「搜索区间」是 [left, right)
 * 所以决定了 while (left ‹ right)
 * 同时也决定了 left = mid + 1 和 right = mid
 *
 * 因为我们需找到 target 的最右侧索引
 * 所以当 nums[mid] == target 时不要立即返回
 * 而要收紧左侧边界以锁定右侧边界
 *
 * 又因为收紧左侧边界时必须 left = mid + 1
 * 所以最后无论返回 left 还是 right，必须减一
 *
 * 如果将「搜索区间」全都统一成两端都闭，好记，只要稍改 nums[mid] == target 条件处的代码和返回的逻辑即可。
 *
 */
public class Leetcode_704 {

    /**
     * 给定一个 n 个元素有序的（升序）整型数组nums 和一个目标值target ，写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int middle = left + (right-left) / 2;
            if (nums[middle] == target) {
                return middle;
            // 压缩右侧空间
            } else if (nums[middle] > target) {
                right = middle - 1;
            // 压缩左侧空间
            } else if(nums[middle] < target) {
                left = middle + 1;
            }
        }
        return -1;

    }

    /**
     * nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     */
    @Test
    public void test() {
        int [] nums = new int[]{-1,0,3,5,9,12};
        int result = search(nums, 9);
        System.out.println(result);

    }
}
