package leetcode.tree;

import leetcode.entity.TreeNode;

/*
 * @description: 101.对称二叉树
 * @param: root,树的根结点
 * @return: 树的左右子树的值是否完全相同
 * @author: cp
 * @date: 2020/8/28
 */
public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        return compare(root, root);
    }

    public boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || left == null)
            return false;
        if (left.val == right.val)
            return compare(left.left, right.right) && compare(left.right, right.left);
        return false;
    }
}
