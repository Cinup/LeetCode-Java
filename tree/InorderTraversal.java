package leetcode.tree;

import leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
 * @description: 94.二叉树的中序遍历
 * @param: root,二叉树根结点
 * @return: 中序遍历结果
 * @author: cp
 * @date: 2020/9/4
 */
public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        traversal(root, result);
        return result;
    }

    private void traversal(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        traversal(node.left, list);
        list.add(node.val);
        traversal(node.right, list);
    }
}
