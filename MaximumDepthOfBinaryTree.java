/* 
 @link https://leetcode.com/problems/maximum-depth-of-binary-tree
 @category (binary-tree/dfs)

 Given the root of a binary tree, return its maximum depth.
A binary tree's maximum depth is the number of nodes along the 
longest path from the root node down to the farthest leaf node.

Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: 3

Example 2:
    Input: root = [1,null,2]
    Output: 2
 
Constraints:
    The number of nodes in the tree is in the range [0, 10^4].
    -100 <= Node.val <= 100
*/ 

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

import static java.lang.Math.*; 

class Solution {
    public int maxDepth(TreeNode root) {
        return treeMaxDepth(root, 0);
    }

    private int treeMaxDepth(TreeNode node, int depth) {
        if (node == null) return depth; 
        int l = treeMaxDepth(node.left, depth + 1);
        int r = treeMaxDepth(node.right, depth + 1);

        return max(l, r);
    }
}
