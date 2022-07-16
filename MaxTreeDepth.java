/* 

Maximum Depth of Binary Tree

Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: 3

Example 2:
    Input: root = [1,null,2]
    Output: 2
 

Constraints:
    The number of nodes in the tree is in the range [0, 104].
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
class MaxTreeDepth {

    // this is not optimized as its non-tail-recusion 
    // because of the one added at each call of the recusive func
    public int maxDepth(TreeNode root) {
        if (root == null) return 0; 
        
        int left = maxDepth(root.left) + 1; 
        int right = maxDepth(root.right) + 1; 
        
        return Math.max(right, left); 
    }

    public int maxDepth2(TreeNode root) { 
    }

    private int findMaxDepthTailRecusion(TreeNode root, int d) { 
        if (root == null) return d; 
        return Math.max(
                findMaxDepthTailRecusion(root.right, d+1), 
                findMaxDepthTailRecusion(root.left, d+1)); 

    }


}
