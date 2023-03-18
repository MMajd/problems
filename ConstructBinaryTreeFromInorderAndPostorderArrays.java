/* 
 @link https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 @categories (tree/binary-tree/d-and-c/hash-table) 

 Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree 
and postorder is the postorder traversal of the same tree, construct and return the binary tree.

Example 1:
    Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
    Output: [3,9,20,null,null,15,7]

Example 2:
    Input: inorder = [-1], postorder = [-1]
    Output: [-1]

Constraints:
    1 <= inorder.length <= 3000
    postorder.length == inorder.length
    -3000 <= inorder[i], postorder[i] <= 3000
    inorder and postorder consist of unique values.
    Each value of postorder also appears in inorder.
    inorder is guaranteed to be the inorder traversal of the tree.
    postorder is guaranteed to be the postorder traversal of the tree.
*/ 

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int val) { this.val = val; }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length; 
        Map<Integer, Integer> map = new HashMap<>(n); 
        for (int i=0; i<n; i++) { 
            map.put(inorder[i], i);
        }
        return buildTree(postorder, n-1, 0, n-1, map);
    }

    private TreeNode buildTree(int[] porder, int pos, int start, int end, Map<Integer, Integer> map) {
        if (pos < 0 || pos >= porder.length || start > end) return null; 
        TreeNode node = new TreeNode(porder[pos]);
        int inpos = map.get(node.val);
        int right = pos - 1; 
        int rstart = inpos + 1; 
        int left = right - (end-inpos); // left = righpos - right tree size 
        int lend = inpos - 1; 
        node.left = buildTree(porder, left, start, lend, map);
        node.right  = buildTree(porder, right, rstart, end, map);
        return node; 
    }
}

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
