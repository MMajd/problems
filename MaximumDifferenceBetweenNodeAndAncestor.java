/*
 @link https://leetcode.com/problems/maximum-difference-between-node-and-ancestor
 @categories (binary-tree/dfs)

 Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.
A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.

Example 1:
    Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
    Output: 7
    Explanation: We have various ancestor-node differences, some of which are given below :
    |8 - 3| = 5
    |3 - 7| = 4
    |8 - 1| = 7
    |10 - 13| = 3
    Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.

Example 2:
    Input: root = [1,null,2,null,0,3]
    Output: 3
     

Constraints:
    The number of nodes in the tree is in the range [2, 5000].
    0 <= Node.val <= 10^5
*/

class Solution {
    public int maxAncestorDiff(TreeNode root) {
        return traverse(root, root.val, root.val, Integer.MIN_VALUE);
    }

    private int traverse(TreeNode root, int max, int min, int ans) {
        if (root == null) return ans; 
        max = Math.max(max, root.val); // find global max, as current root is ancestor to the one that called the method
        min =  Math.min(min, root.val); // find global min

        ans = Math.max(ans, max-min); // find answer till now
        ans = traverse(root.left, max, min, ans); // pass this answer to the left sub-tree
        ans = traverse(root.right, max, min, ans); // pass this answer to the right sub-tree
        return ans; // return curr answer; 
    }

    private int pdfs(TreeNode root, TreeNode p, int max) { 
        if (root == null) return max;
        max = Math.max(pdfs(root.left, p, max), max);
        max = Math.max(pdfs(root.right, p, max), max);
        max = Math.max(Math.abs(p.val - root.val), max);
        return max;
    }

    private int dfs(TreeNode root, int max) { 
        if (root == null) return max; 
        max = Math.max(max, pdfs(root.left, root, max));
        max = Math.max(max, pdfs(root.right, root, max));
        return Math.max(dfs(root.left, max), dfs(root.right, max));
    }
}

