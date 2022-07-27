/*

@link https://leetcode.com/problems/validate-binary-search-tree/

   Validate Binary Search Tree
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:
    Input: root = [2,1,3]
    Output: true

Example 2:
    Input: root = [5,1,4,null,null,3,6]
    Output: false
    Explanation: The root node's value is 5 but its right child's value is 4.
     

Constraints:
    The number of nodes in the tree is in the range [1, 104].
    -231 <= Node.val <= 231 - 1
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

/** D&C, Maintain BST boundary, low<x<high where x is the node under test  */
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true; 
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE); 
    }
    
    private boolean isValidBST(TreeNode root, long low, long high) { 
        if (root == null) return true; 
        
        boolean left = isValidBST(root.left, low, root.val); 
        boolean right = isValidBST(root.right, root.val, high); 
        
        return left && right && root.val > low && root.val < high; 
    }
}



/** When we traverse BST inorder traversal we get monotonically 
 * increasing sequence, so we always check that the previous is 
 * less than current element
 * */

public Solution { 
    private Integer prevVal = null; 

    public boolean isValidBST(TreeNode root) { 
        return inorderRecursive(root); 
    }

    public boolean inorderRecursive(TreeNode root) { 
        if (root == null) return true; 

        if (!inorder(root.left)) return false; 

        if (prevVal!=null && prevVal>=root.val)
            return false; 

        prevVal = root.val; 

        return inorderRecursive(root.right); 
    }

    public boolean inorderIterative(TreeNode root) { 
        if (root == null) return; 

        Stack<TreeNode> stack = new Stack<>(); 
        TreeNode curr = root, prev = null; 

        while(!s.isEmpty()) { 

            // go till there's no nodes in the left subtree 
            while(curr != null) { 
                s.add(curr);
                curr = curr.left; 
            }

            // get last left node in the left subtree
            curr = s.poll(); 

            if (prev != null && prev.val >= curr.val) { 
                return false; 
            }

            prev = curr; 
            curr = curr.right;
        }

        return true; 
    }
}
