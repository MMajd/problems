/** 
 *
 * @link https://leetcode.com/problems/symmetric-tree
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
class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root==null 
            && root.right == null 
            && root.left == null) return true;
        
        return equal(root.left, root.right);
    }
    
    private boolean equal(TreeNode l, TreeNode r)  { 
        if (r == null && l == null)  { 
            return true; 
        }
        else if (r != null && l != null) { 
            boolean a = l.val == r.val; 
            boolean b = equal(r.right, l.left);
            boolean c = equal(r.left, l.right);
            
            return a && b && c; 
        } 
        else { 
            return false; 
        }
    }
}
