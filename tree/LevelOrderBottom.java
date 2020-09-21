package leetcode.tree;

import leetcode.entity.Entry;
import leetcode.entity.TreeNode;

import java.util.*;

public class LevelOrderBottom {
    /*
     * @description: 94.二叉树的中序遍历,左--根--右
     * @param: root:二叉树根结点
     * @return: List<Integer>:中序遍历结果
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        inorderTraversal(root, result);
        return result;
    }

    private void inorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }

    /*
     * @description: 144.二叉树的前序遍历
     * @param: root:二叉树根结点
     * @return: List<Integer>:前序序遍历结果
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        preorderTraversal(root, result);
        return result;
    }

    private void preorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        list.add(node.val);
        preorderTraversal(node.left, list);
        preorderTraversal(node.right, list);
    }

    /*
     * @description: 145.二叉树的后序遍历
     * @param: root:二叉树根结点
     * @return: List<Integer>:前序序遍历结果
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        postorderTraversal(root, result);
        return result;
    }

    private void postorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        postorderTraversal(node.left, list);
        postorderTraversal(node.right, list);
        list.add(node.val);
    }

    /*
     * @description: 102.二叉树的层序遍历
     * @param: root:二叉树根结点
     * @return: List<List<Integer>>:二叉树层序遍历结果,其中List<Integer>为相同层结点的值
     */
    public List<List<Integer>> levelOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> depthMap = new HashMap<>();
        traversalLevelOrder(depthMap, root, 0);
        for (int i = 0; i < depthMap.size(); i++) {
            result.add(depthMap.get(i));
        }
        return result;
    }

    /*
     * @description: 103.二叉树的锯齿形层次遍历
     * @param: root:二叉树根结点
     * @return: List<List<Integer>>:二叉树层序遍历结果,其中List<Integer>为相同层结点的值,第1层左往右,第2层从右往左以此类推
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> depthMap = new HashMap<>();
        traversalLevelOrder(depthMap, root, 0);
        for (int i = 0; i < depthMap.size(); i++) {
            List<Integer> list = depthMap.get(i);
            if ((i & 1) == 1)
                Collections.reverse(list);
            result.add(depthMap.get(i));
        }
        return result;
    }

    /*
     * @description: 107.二叉树的层次遍历II
     * @param: root:二叉树根结点
     * @return: List<List<Integer>>:二叉树层序自底向上遍历结果,其中List<Integer>为相同层结点的值
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> depthMap = new HashMap<>();
        traversalLevelOrder(depthMap, root, 0);
        for (int i = depthMap.size() - 1; i >= 0; i--) {
            result.add(depthMap.get(i));
        }
        return result;
    }

    private void traversalLevelOrder(Map<Integer, List<Integer>> map, TreeNode node, int depth) {
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
            traversalLevelOrder(map, node.left, depth + 1);
            traversalLevelOrder(map, node.right, depth + 1);
        }
    }

    /*
     * @description: 637.二叉树的层平均值
     * @param: root:二叉树根结点
     * @return: List<Double>:每层节点平均值
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Map<Integer, Entry> depthMap = new HashMap<>();
        averageOfLevels(depthMap, root, 0);
        for (int i = 0; i < depthMap.size(); i++) {
            result.add(depthMap.get(i).getAverage());
        }
        return result;
    }

    private void averageOfLevels(Map<Integer, Entry> map, TreeNode node, int depth) {
        if (node == null)
            return;
        else {
            Entry entry = map.getOrDefault(depth, new Entry(0, 0.0));
            entry.setCount(entry.getCount() + 1);
            entry.setSum(entry.getSum() + node.val);
            map.put(depth, entry);
            averageOfLevels(map, node.left, depth + 1);
            averageOfLevels(map, node.right, depth + 1);
        }
    }
}
