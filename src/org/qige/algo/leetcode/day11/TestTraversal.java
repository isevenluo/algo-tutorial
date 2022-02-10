package org.qige.algo.leetcode.day11;


import java.util.Stack;

/**
 * 二叉树遍历测试
 */
public class TestTraversal {

    public static void main(String[] args) {
        // 初始化二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(5);
        root.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(6);
        root.left.left.right.left = new TreeNode(7);
        root.left.left.right.right = new TreeNode(8);

        // 递归先序遍历测试
        System.out.println("递归先序遍历结果");
        recursionPreorderTraversal(root);
        System.out.println("\n非递归先序遍历结果");
        // 非递归先序遍历测试
        preorderTraversal(root);

        System.out.println("\n递归中序遍历结果");
        recursionMiddleorderTraversal(root);
        System.out.println("\n非递归中序遍历结果");
        middleOrderTraversal(root);
        System.out.println("\n递归后序遍历结果");
        recursionPostorderTraversal(root);
        System.out.println("\n非递归中序遍历结果");
        postorderTraversal(root);

    }

    // 递归先序遍历
    public static void recursionPreorderTraversal(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            recursionPreorderTraversal(root.left);
            recursionPreorderTraversal(root.right);
        }
    }

    // 非递归先序遍历
    public static void preorderTraversal(TreeNode root) {
        // 找完节点的左子树需要继续找右子树，用栈来暂存节点，栈后进先出的特性符合我们遍历的需求
        Stack<TreeNode> stack = new Stack<>();
        // 新建一本游标节点，从根节点遍历
        TreeNode node = root;
        // 遍历到最后一个节点时，它的左右子树都为空，并且栈也为空
        // 所以只要不同时满足这个条件就继续遍历
        while (node != null || !stack.isEmpty()) {

            // 若当前访问节点非空，则输出该节点的值
            // 由考查顺序得知，需要一直往左走
            while(node != null) {
                // 先输出根节点
                System.out.print(node.val + " ");
                // 入栈，为了回溯找根的右子树
                stack.push(node);
                // 继续找左子树
                node = node.left;
            }

            // 当左子树为空时，则开始取右子树
            // 如果栈为空了，就说明没了
            if (!stack.isEmpty()) {
                // 弹出栈顶元素后取它的右子树
                node = stack.pop().right;
            }
        }

    }

    // 递归中序遍历
    public static void recursionMiddleorderTraversal(TreeNode root) {
        if (root != null) {
            recursionMiddleorderTraversal(root.left);
            System.out.print(root.val + " ");
            recursionMiddleorderTraversal(root.right);
        }
    }

    // 非递归中序遍历
    public static void middleOrderTraversal(TreeNode root) {
        // 逻辑和非递归先序遍历相似，唯一区别是访问到当前节点时，并不直接输出
        // 而是当访问节点为空时，从栈中弹出时候在进行输出，因为优先考虑左子树，左子树没有了说明最左，然后才访问根节点
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.print(node.val + " ");
                node = node.right;
            }
        }
    }
    // 递归后序遍历
    public static void recursionPostorderTraversal(TreeNode root) {
        // 逻辑和先序、中序都是一样的，顺序为:左右根
        if (root != null) {
            recursionPostorderTraversal(root.left);
            recursionPostorderTraversal(root.right);
            System.out.print(root.val + " ");
        }
    }

    // 非递归后续遍历
    public static void postorderTraversal(TreeNode root) {
        /**
         * 1. 后续遍历和先序、中序遍历不太一样,后序遍历在决定是否可以输出当前节点的值的时候，需要考虑其左右子树是否都已经遍历完成。
         * 2. 所以需要设置一个lastVisit游标,表示当上一个输出的节点,若lastVisit等于当前访问节点的右子树，表示该节点的左右子树都已经遍历完成，则可以输出当前节点。
         * 3. 输出当前节点后，把lastVisit节点设置成当前节点，将当前游标节点node设置为空，下一轮就可以访问栈顶元素。
         * 4. 不满足输出条件，需要接着考虑右子树，node = node.right。
         */

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode lastVisit = root;
        while (node != null || !stack.isEmpty()) {
            // 先遍历左子树，直到为空
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            // 查看当前栈顶元素
            node = stack.peek();
            // 如果右子树为空或者右子树等于上一个输出的节点，说明当前节点可以输出了
            if (node.right == null || node.right == lastVisit) {
                System.out.print(node.val + " ");
                stack.pop();
                lastVisit = node;
                // 将node置为空，访问下一个栈顶元素
                node = null;
            } else {
                // 不满足输出条件，就继续遍历右子树
                node = node.right;
            }
        }

    }
    static class TreeNode {
        int val;
        //左子树
        TreeNode left;
        //右子树
        TreeNode right;
        //构造方法
        TreeNode(int x) {
            val = x;
        }
    }
}


