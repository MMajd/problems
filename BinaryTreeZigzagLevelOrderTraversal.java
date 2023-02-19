/*
 @link https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal
 @categories (binary-tree/bfs)

 Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. 
(i.e., from left to right, then right to left for the next level and alternate between).

Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: [[3],[20,9],[15,7]]

Example 2:
    Input: root = [1]
    Output: [[1]]

Example 3:
    Input: root = []
    Output: []

Constraints:
    The number of nodes in the tree is in the range [0, 2000].
    -100 <= Node.val <= 100
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        boolean left = true; 
        Queue<TreeNode> q = new LinkedList<>(); 
        List<List<Integer>> ans = new LinkedList<>(); 

        if (root != null) q.add(root);

        while (!q.isEmpty()) { 
            int size = q.size(); 
            List<Integer> level = new ArrayList<>(size); 

            while (size-- > 0) { 
                TreeNode node = q.poll(); 
                
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);

                level.add(node.val);
            }

            if (left) { 
                ans.add(level);
            }
            else { 
                Collections.reverse(level);
                ans.add(level);
            }
            
            left = !left; 
        }

        return ans; 
    }
}
