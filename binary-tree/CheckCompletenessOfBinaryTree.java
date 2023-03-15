/*
 @link (https://leetcode.com/problems/check-completeness-of-a-binary-tree) 
 @categories (binary-tree/bfs)

 Given the root of a binary tree, determine if it is a complete binary tree.
**NOTE: In a complete binary tree, every level, except possibly the last, 
is completely filled, and all nodes in the last level are as far left as possible. 
It can have between 1 and 2h nodes inclusive at the last level h.

Example 1:
    Input: root = [1,2,3,4,5,6]
    Output: true
    Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), 
    and all nodes in the last level ({4, 5, 6}) are as far left as possible.

Example 2:
    Input: root = [1,2,3,4,5,null,7]
    Output: false
    Explanation: The node with value 7 isn't as far left as possible.

Constraints:
    The number of nodes in the tree is in the range [1, 100].
    1 <= Node.val <= 1000
*/ 

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 * }
 */
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        Deque<TreeNode> q = new LinkedList<>(); 
        TreeNode prev = new TreeNode(); // dummy start
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll(); 
            if (prev == null && node != null) return false; 
            if (node != null) { 
                q.offer(node.left);
                q.offer(node.right);
            }
            prev = node; 
        }

        return true; 
    }
}
