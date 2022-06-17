/**
 *
 * @link leetcode.com/problems/path-sum
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
class TreePathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false; 
        
        return solve(root, targetSum);
    }
    
    public boolean solve(TreeNode root, int targetSum) { 
        if (root == null) return false;
        if (root != null && root.left == null && root.right == null) { 
            return (targetSum-root.val) == 0; 
        }
        
        boolean a = solve(root.left, targetSum-root.val);
        boolean b = solve(root.right, targetSum-root.val);
        
        return a || b; 
    }
}
