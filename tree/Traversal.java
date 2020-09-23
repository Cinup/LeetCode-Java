package leetcode.tree;

import leetcode.entity.TreeNode;

import java.util.Stack;

/*
 * @description: 二叉树遍历模版
 */
public class Traversal {
    Stack<TreeNode> stack = new Stack<>();

    /*
     * @description: 递归方式
     */
    /*
     * @description: 前序遍历,当前--左--右 or 当前--右--左
     */
    void preorderTraversal(TreeNode root) {
        if (root == null)
            return;
        //do
        {
            //...
        }
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        //or
        //do
        {
            //...
        }
        preorderTraversal(root.right);
        preorderTraversal(root.left);
    }

    /*
     * @description: 中序遍历,左--当前--右 or 右--当前--左
     */
    void inorderTraversal(TreeNode root) {
        if (root == null)
            return;
        preorderTraversal(root.left);
        //do
        {
            //...
        }
        preorderTraversal(root.right);
        //or
        preorderTraversal(root.right);
        //do
        {
            //...
        }
        preorderTraversal(root.left);
    }

    /*
     * @description: 后序遍历,左--右--当前 or 右--左--当前
     */
    void postorderTraversal(TreeNode root) {
        if (root == null)
            return;
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        //do
        {
            //...
        }
        //or
        preorderTraversal(root.right);
        preorderTraversal(root.left);
        //do
        {
            //...
        }
    }

    /*
     * @description: 非递归方式
     */
    void preorderTraversal2(TreeNode root) {
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                //do
                {
                    //...
                }
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                root = root.right;
            }
        }
    }

    void inorderTraversal2(TreeNode root) {
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                //do
                {
                    //...
                }
                root = root.right;
            }
        }
    }

    void postorderTraversal2(TreeNode root) {
        //todo
    }
}
