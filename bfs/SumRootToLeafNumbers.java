/*
 @link https://leetcode.com/problems/sum-root-to-leaf-numbers
 @categories (binary-tree/dfs/bfs/queue/stack) 

 You are given the root of a binary tree containing digits from 0 to 9 only. 
Each root-to-leaf path in the tree represents a number.
For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. 
Test cases are generated so that the answer will fit in a 32-bit integer.
- A leaf node is a node with no children.

Example 1:
    Input: root = [1,2,3]
    Output: 25
    Explanation:
    The root-to-leaf path 1->2 represents the number 12.
    The root-to-leaf path 1->3 represents the number 13.
    Therefore, sum = 12 + 13 = 25.

Example 2:
    Input: root = [4,9,0,5,1]
    Output: 1026
    Explanation:
    The root-to-leaf path 4->9->5 represents the number 495.
    The root-to-leaf path 4->9->1 represents the number 491.
    The root-to-leaf path 4->0 represents the number 40.
    Therefore, sum = 495 + 491 + 40 = 1026.

Constraints:
    The number of nodes in the tree is in the range [1, 1000].
    0 <= Node.val <= 9
    The depth of the tree will not exceed 10.
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
    /** 
     * Available solutions: 
     * - DFS: pure dfs, stack
     * - BFS: queue
     */
    public int sumNumbers(TreeNode root) {
        // return sum(root, 0);
        // return stack(root); 
        return queue(root);
    }

    private int queue(TreeNode root) {
        Deque<TreeNode> q = new LinkedList<>(); 
        int res = 0; 
        q.offer(root);

        while (!q.isEmpty()) { 
            TreeNode node = q.poll(); 
            if (node.left == null && node.right == null) { 
                res += node.val; 
            }
            if (node.left != null) { 
                node.left.val += node.val * 10; 
                q.offer(node.left);
            }
            if (node.right != null) { 
                node.right.val += node.val * 10; 
                q.offer(node.right);
            }
        }

        return res; 
    }

    private int stack(TreeNode root) {
        int res = 0; 
        Deque<TreeNode> stack = new LinkedList<>(); 
        stack.push(root);

        while (!stack.isEmpty()) { 
            TreeNode node = stack.pop();
            if (node.left == null && node.right == null) { 
                res += node.val; 
            }
            if (node.right != null) { 
                node.right.val += node.val * 10; 
                stack.push(node.right);
            }
            if (node.left != null) { 
                node.left.val += node.val * 10; 
                stack.push(node.left);
            }
        }

        return res; 
    }
    
    private int sum(TreeNode node, int sum) { 
        if (node == null) return 0; 
        if (node.left == null && node.right == null) return sum * 10 + node.val; 
        return sum(node.left, sum * 10 + node.val) + sum(node.right, sum * 10 + node.val);
    }
}
