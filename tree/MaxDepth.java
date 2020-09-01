package leetcode.tree;

import leetcode.entity.TreeNode;

/*
 * @description: 104.二叉树的最大深度
 * @param: root,树的根结点
 * @return: 二叉树的深度
 * @author: cp
 * @date: 2020/8/28
 */
public class MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        else
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;

    }
}
