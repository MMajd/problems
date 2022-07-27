/*

@link https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

 Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 

Example 1:
    Input: root = [1,2,5,3,4,null,6]
    Output: [1,null,2,null,3,null,4,null,5,null,6]

Example 2:
    Input: root = []
    Output: []

Example 3:
    Input: root = [0]
    Output: [0]
 

Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100
 

Follow up: Can you flatten the tree in-place (with O(1) extra space)?
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
    TreeNode prev = null; 
    
    public void flatten(TreeNode root) {
        dfs(root);
    }
    
    /** morris recursive */ 
    /**
    steps: 
        go to deepest right leaf on the right subtree 
        if it has a left tree, go to the deepest right 
        leaf in that tree till reach null node return 
        after return put the null ref to that leaf right child 
        take reference of that leaf in the prev pointer 
        return and repeat 

        This is MORRIS Traversal in recursive 
    */
    private void dfs(TreeNode root) { 
        if (root == null) return; 
        
        dfs(root.right); 
        dfs(root.left); 
        
        root.right = prev; 
        root.left = null; 
        
        prev = root; 
    }
    
    private void morrisIterative(TreeNode root) {
        TreeNode curr = root; 
        TreeNode prev = null; 
        
        while(curr != null) { 
            if (curr.left != null) { 
                prev = curr.left; 
                
                while(prev.right != null) prev = prev.right; 
                prev.right = curr.right; 
                curr.right = curr.left; 
                curr.left = null; 
            }
            curr = curr.right; 
        }
    }
    
    /* T: O(n) | S: O(n) */
    private void iterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>(); 
        TreeNode d = new TreeNode(0);     
        
        stack.add(root);
        
        while (!stack.isEmpty()) { 
            TreeNode node = stack.pop();
            
            if (node == null) continue; 
            
            d.right = node; 
            d.left = null; 
            d = d.right; 
            
            if (node!=null) { 
                stack.add(node.right);
                stack.add(node.left);
            }
        }
        
        root = d.right; 
    }
}
