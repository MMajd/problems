/*
 @link https://leetcode.com/problems/minimum-distance-between-bst-nodes
 @link https://leetcode.com/problems/minimum-absolute-difference-in-bst
 @categories (bst/dfs/inorder-traversal) 

 Given the root of a Binary Search Tree (BST), 
return the minimum difference between the values of any two different nodes in the tree.

Example 1:
    Input: root = [4,2,6,1,3]
    Output: 1

Example 2:
    Input: root = [1,0,48,null,null,12,49]
    Output: 1
 
Constraints:
    The number of nodes in the tree is in the range [2, 100].
    0 <= Node.val <= 10^5
*/ 

import static java.lang.Math.*; 

class Solution {
    public int minDiffInBST(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE; 

        int curr = root.val; 
        int lvalue = leftDepth(root.left);
        int rvalue = rightDepth(root.right);

        int ans = min(abs(curr-lvalue), abs(curr-rvalue));

        int ltree = minDiffInBST(root.left);
        int rtree = minDiffInBST(root.right);

        return min(rtree, min(ans, ltree));
    }

    private int leftDepth(TreeNode node) {
        if (node == null) return Integer.MAX_VALUE; 
        while (node.right != null) node = node.right; 
        return node.val; 
    }

    private int rightDepth(TreeNode node) {
        if (node == null) return Integer.MAX_VALUE; 
        while (node.left != null) node = node.left; 
        return node.val; 
    }
}

class Solution {
  int minDistance = Integer.MAX_VALUE;
  TreeNode prevValue;

  void inorderTraversal(TreeNode root) {
    if (root == null) return; 

    inorderTraversal(root.left);

    if (prevValue != null) {
      minDistance = Math.min(minDistance, root.val - prevValue.val);
    }

    prevValue = root;
    inorderTraversal(root.right);
  }

  public int minDiffInBST(TreeNode root) {
    inorderTraversal(root);
    return minDistance;
  }
}
