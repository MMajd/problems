/** 
 * @link leetcode.com/problems/construct-tree-from-preorder-and-inorder-traversal
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
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder.length == 0) return null; 
        int len = inorder.length; 
        
        return  treeBuilder(preorder, 0, len-1, inorder, 0, len-1);
    }
    
    private TreeNode treeBuilder(int[] preorder, int prestart, int preend, 
                                 int[] inorder, int instart, int inend) {
        
        if (instart>inend || prestart>preend) return null;
        
        int i=0; 
        for(i=instart; i<= inend; i++) { 
            if (inorder[i] == preorder[prestart]) break;
        }
        
        TreeNode root = new TreeNode(preorder[prestart]);
        
        int leftLen = i - instart; 
        
        root.left  = treeBuilder(preorder, prestart+1, prestart+1+leftLen, inorder, instart, i-1);
        root.right = treeBuilder(preorder, prestart+1+leftLen, preend, inorder, i+1, inend);

        return root; 
    }
}




