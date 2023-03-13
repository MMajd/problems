/* 
 @link https://leetcode.com/problems/symmetric-tree
 @categories (dsf/bfs/stack) 

 Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

Example 1:
    Input: root = [1,2,2,3,4,4,3]
    Output: true

Example 2:
    Input: root = [1,2,2,null,3,null,3]
    Output: false

Constraints:
    The number of nodes in the tree is in the range [1, 1000].
    -100 <= Node.val <= 100

Follow up: 
    Could you solve it both recursively and iteratively?
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 * }
 */
class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return null; 
        return equal(root.left, root.right);
    }
    
    private boolean equal(TreeNode l, TreeNode r)  { 
        if (l == null || r == null) {
            return l == r; 
        }

        boolean a = l.val == r.val; 
        boolean b = equal(r.right, l.left);
        boolean c = equal(r.left, l.right);
        
        return a && b && c; 
    }
}
