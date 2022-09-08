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
