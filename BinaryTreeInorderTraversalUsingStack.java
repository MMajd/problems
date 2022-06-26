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
        Set<TreeNode> visited = new HashSet<>();
        Stack<TreeNode> s = new Stack<>();
        List<Integer> ans = new LinkedList<>();
        
        s.push(root);
        visited.add(root);
        visited.add(null);
        
        while(!s.isEmpty()) { 
            TreeNode curr = s.pop(); 
            
            if (curr == null) continue; 

            if (!visited.contains(curr.left) 
                || !visited.contains(curr.right)) {
                if (curr.right != null) {
                    visited.add(curr.right);
                    s.push(curr.right);
                }
                s.push(curr);

                if (curr.left != null) { 
                    visited.add(curr.left);
                    s.push(curr.left);
                }
            } else { 
                ans.add(curr.val);
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
