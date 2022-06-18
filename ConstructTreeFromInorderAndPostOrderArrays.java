/** 
 *
 * @link https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) return null; 
        
        int len = inorder.length; 
        
        return treeBuilder(postorder, len-1, inorder, 0, len-1);
    }
    
    private TreeNode treeBuilder(int[] postorder, int postidx, int[] inorder, int instart, int inend) { 
        if (instart > inend || postidx < 0) return null; 
        
        int i=0; 
        for (i=instart; i <= inend; i++) { 
            if (inorder[i] == postorder[postidx]) break;
        }
        
        TreeNode root = new TreeNode(postorder[postidx]); 
        root.right = treeBuilder(postorder, postidx-1, inorder, i+1, inend);
        /*
        Input:                 i             inend 
                inorder = [9,  3,  15,  20,  7], 
                                            postidx     
              postorder = [9,  15,  7,  20,  3]
              
                to get the position of the left we need to know its root index in the postorder tree 
                we can get the length of the right tree from the inorder array 
                the length = inend - i = how many nodes in the right side tree 
                thus in the post order tree we can exclude the root of the tree by substracting -1
                from the postidx then we substract the length of the right side tree to go the the 
                root of the left side tree 
                ex: root = 3, postidx = 4, rightside tree len = 4-1= 3 
                the index of the root of the leftside tree in the postorder array 
                leftIdx = postidx - 1 - (inend -i)   = 4-1 -(3) = 3 - 3 = 0 
                which is element 9 
        */ 
        root.left = treeBuilder(postorder, postidx-1-(inend-i), inorder, instart, i-1);
        
        return root; 
    }
}
