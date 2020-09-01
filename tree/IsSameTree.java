package leetcode.tree;

import leetcode.entity.TreeNode;

/*
 * @description: 100.相同的树
 * @param: p,q两个树的根结点
 * @return: 两棵树是否完全相同
 * @author: cp
 * @date: 2020/8/28
 */
public class IsSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }
}
