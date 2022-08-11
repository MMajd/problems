/**

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
    -2^31 <= Node.val <= 2^31 - 1
*/


/** D&C, Maintain BST boundary, 
 low<x<high where x is the node under test */

class Solution {

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true; 
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE); 
    }

    private boolean isValidBST1(TreeNode root, long low, long high) { 
        if (root == null) return true; 
        if (low >= root.val || high <= root.val) return false; 
        
        return isValidBST(root.left, low, root.val) && 
            isValidBST(root.right, root.val, high);
    }
    
    private boolean isValidBST2(TreeNode root, long low, long high) { 
        if (root == null) return true; 
        
        boolean left = isValidBST(root.left, low, root.val); 
        boolean right = isValidBST(root.right, root.val, high); 
        
        return left && right && root.val > low && root.val < high; 
    }
}

/**
  Inorder traversal of BST produces monotonically 
  increasing sequence, thus the previous value has to be always strictly less than the current value 
 */

public Solution { 
    long prev = Long.MIN_VALUE; 

    public boolean isValidBST(TreeNode root) { 
        return inorder(root); 
    }
    
    private boolean inorder(TreeNode root) {
        if (root == null) return true; 
        if (!inorder(root.left)) return false; 
        if (prev >= root.val) return false; 
        prev = root.val; 
        return inorder(root.right); 
    }


    private boolean inorderIterative(TreeNode root) { 
        if (root == null) return true; 
        
        long prev = Long.MIN_VALUE; 
        Stack<TreeNode> stack = new Stack<>(); 
        TreeNode trav = root; 
        stack.add(root);

        while(!stack.isEmpty()) {
            while(trav!=null && trav.left!=null) { 
                stack.push(trav.left);
                trav = trav.left; 
            }
            
            trav = stack.pop();
            
            if (trav.val <= prev) return false; 
            if (trav.right != null) stack.push(trav.right);
            
            prev = trav.val; 
            trav = trav.right; 
        }
        
        return true; 
    }
}
