/**
 @link https://leetcode.com/problems/delete-node-in-a-bst 
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
    public TreeNode deleteNode(TreeNode curr, int key) {
        if (curr == null) return curr;
        if (curr.val == key) { 
            if (curr.right != null) { 
                TreeNode temp = getDepthestLeftAndAdjustRightSubtree(curr, curr.right); 
                curr.val = temp.val;
                return curr; 
            }
            // curr.left could be a sub-tree or a null and that's acceptable
            return curr.left; 
        }
        
        if (curr.val > key) curr.left = deleteNode(curr.left, key); 
        else curr.right = deleteNode(curr.right, key); 
        
        return curr; 
    }
    

    private TreeNode getDepthestLeftAndAdjustRightSubtree(TreeNode prev, TreeNode root) { 
        while(root.left != null) { 
            prev = root; 
            root = root.left;     
        }
        if (root == prev.right)  prev.right = root.right; // root is the right node of curr 
        else prev.left = root.right; 
        return root; 
    }
}

