package org.qige.algo.leetcode.day2;

public class Leetcode_304 {
    /**
     * Your NumMatrix object will be instantiated and called as such:
     * NumMatrix obj = new NumMatrix(matrix);
     * int param_1 = obj.sumRegion(row1,col1,row2,col2);
     */
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        int i = numMatrix.sumRegion(2, 1, 4, 3);
        int i1 = numMatrix.sumRegion(1, 1, 2, 2);
        int i2 = numMatrix.sumRegion(1, 2, 2, 4);
        System.out.println(i);
        System.out.println(i1);
        System.out.println(i2);

    }
}

/**
 * 解释:
 * NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
 * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
 * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
 *
 */
class NumMatrix {

    int[][] preSum;

    /**
     * 求和公式：preSum[i][j]=preSum[i−1][j]+preSum[i][j−1]−preSum[i−1][j−1]+matrix[i][j]
     * @param matrix
     */
    public NumMatrix(int[][] matrix) {
        if (matrix.length > 0) {
            // 初始化二维数组，用来存放二维前缀和
            preSum = new int[matrix.length+1][matrix[0].length+1];
            // 求前缀和
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    preSum[i+1][j+1] = preSum[i][j+1] + preSum[i+1][j] - preSum[i][j] + matrix[i][j];
                }
            }

        }

    }

    /**
     * 递推公式：

     * 求子矩形面积：
     * preSum[row2][col2]−preSum[row2][col1−1]−preSum[row1−1][col2]+preSum[row1−1][col1−1]
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     * @return
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2+1][col2+1] - preSum[row2+1][col1] -preSum[row1][col2+1] + preSum[row1][col1];

    }
}

