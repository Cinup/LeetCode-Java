package leetcode.tree;

import leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @description: 107.二叉树的层次遍历 II
 * @param:
 * @return:
 * @author: cp
 * @date: 2020/8/28
 */
public class LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> depthMap = new HashMap<>();
        traversal(depthMap, root, 0);
        for (int i = depthMap.size()-1; i >= 0; i--) {
            result.add(depthMap.get(i));
        }
        return result;
    }

    public void traversal(Map<Integer, List<Integer>> map, TreeNode node, int depth) {
        if (node == null)
            return;
        else {
            if (map.containsKey(depth)) {
                map.get(depth).add(node.val);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(node.val);
                map.put(depth, list);
            }
            traversal(map, node.left, depth + 1);
            traversal(map, node.right, depth + 1);
        }
    }
}
