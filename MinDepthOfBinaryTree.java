/**
 *
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
class MinDepthOfBinaryTree {
    public int minDepth(TreeNode node) {
        if (node == null) return 0;
        
        return helper(node);
    }
    
    public int helper(TreeNode node) { 
        if (node == null)  return Integer.MAX_VALUE; 
        
        int left = helper(node.left);
        int right = helper(node.right);
        
        if (left == Integer.MAX_VALUE && 
            right == Integer.MAX_VALUE) 
            return 1; 
            
        return Math.min(left, right) + 1;
    }
}
