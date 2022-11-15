/*
 @link https://leetcode.com/problems/count-complete-tree-nodes
 @categories (tree/binary-tree/dfs/binary-search) 

 Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

**IMPORTANT**
 - Design an algorithm that runs in less than O(n) time complexity.

**OBSERVATION** 
 - Full Tree: a binary tree that every internal node of it has to childs, and its leaves count equal to 2^h, and its nodes count are (2^depth)-1 
 - Complete Tree: is a binary tree that its final level is filled from left to right and its leaves count L is  1<= L <= 2^h 
 - Every sub-tree of a complete/full tree is a itself from the samle type (complete/full) 


Example 1:
    Input: root = [1,2,3,4,5,6]
    Output: 6

Example 2:
    Input: root = []
    Output: 0

Example 3:
    Input: root = [1]
    Output: 1

Constraints:
    The number of nodes in the tree is in the range [0, 5 * 10^4].
    0 <= Node.val <= 5 * 10^4
    The tree is guaranteed to be complete.
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
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0; 
        TreeNode left = root.left, right = root.right; 
        int l = 1, r = 1; 

        while (left != null) { 
            left = left.left; 
            l += 1; 
        }

        while (right != null) { 
            right = right.right; 
            r += 1; 
        }

        if (l == r) return (1<<r)-1; 

        return 1 + countNodes(root.left) + countNodes(root.right); 
    }
}
