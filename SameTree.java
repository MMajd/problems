/*  

 @link leetcode.com/problems/same-tree

    Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

 

Example 1:
    Input: p = [1,2,3], q = [1,2,3]
    Output: true

Example 2:
    Input: p = [1,2], q = [1,null,2]
    Output: false

Example 3:
    Input: p = [1,2,1], q = [1,1,2]
    Output: false

Constraints:
    The number of nodes in both trees is in the range [0, 100].
    -10^4 <= Node.val <= 10^4

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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // return same(p, q);
        return sameIterative(p, q);
    }
    
    private boolean checkNodes(TreeNode p, TreeNode q) { 
        if (p==null && q==null) return true; 
        if (p==null || q==null) return false; 
        if (p.val != q.val) return false; 
        
        return true; 
    }
    
    private boolean sameIterative(TreeNode p, TreeNode q) { 
        Stack<TreeNode> ps = new Stack<>(); 
        Stack<TreeNode> qs = new Stack<>(); 
        
        ps.add(p); 
        qs.add(q); 
        
        while(!ps.isEmpty()) {
            TreeNode r1 = ps.pop();
            TreeNode r2 = qs.pop();
            
            if (!checkNodes(r1, r2)) 
                return false; 
            
            if (r1 != null) { 
                ps.push(r1.right);
                ps.push(r1.left);
            }
            
            if (r2 != null) { 
                qs.push(r2.right);
                qs.push(r2.left);
            }
        }
        
        return true; 
    }

    
    private boolean same(TreeNode p, TreeNode q) { 
        if (p== null && q==null) return true; 
        if (p== null || q== null) return false; 
        if (p.val != q.val) return false; 
        
        boolean left = same(p.left, q.left);
        boolean right = same(p.right, q.right);
        
        return left && right; 
    }
}
