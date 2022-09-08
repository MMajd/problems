/*

  @link https://leetcode.com/problems/binary-tree-inorder-traversal/solution/

  Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:
    Input: root = [1,null,2,3]
    Output: [1,3,2]

Example 2:
    Input: root = []
    Output: []

Example 3:
    Input: root = [1]
    Output: [1]

Constraints:
    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100
 */



/**
 *
 * Definition for a binary tree TreeNode.
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
    public List<Integer> inorderTraversal(TreeNode root) {
        return inorderIterative(root); 
    }

    private List<Integer> inorderIterative() { 
        List<Integer> ans = new ArrayList<>(); 
        Deque<TreeNode> stack = new LinkedList<>();
        
        TreeNode curr = root; 
        
        while(curr != null || !stack.isEmpty()) {
            while (curr != null) { 
                stack.push(curr);
                curr = curr.left; 
            }
            
            curr = stack.pop();
            ans.add(curr.val);
            curr = curr.right; 
        }
        
        return ans;
    }


    private List<Integer> inorderDFS() { 
        Set<TreeNode> visited = new HashSet<>();
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();

        if (root != null) stack.push(root);
        
        while(!stack.isEmpty()) { 
            TreeNode node = stack.pop(); 

            if (visited.contains(node)) {
                ans.add(node.val);
            } 
            else { 
                if (node.right != null) stack.push(node.right);

                stack.push(node); 
                visited.add(node); 

                if (node.left != null) stack.push(node.left);
            }
        }
        
       return ans; 
    }

    
    public void inorder(TreeNode TreeNode, List<Integer> list) {
        if (TreeNode == null) return;
        inorder(TreeNode.left, list);
        list.add(TreeNode.val);
        inorder(TreeNode.right, list);
    }
}
