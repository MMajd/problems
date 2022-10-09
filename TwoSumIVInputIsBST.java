/*
 @link https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 @categories (bst/two-pointers/dfs/bfs/binary-treeBinarySearch)

 Given the root of a Binary Search Tree and a target number k, 
 return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
    Input: root = [5,3,6,2,4,null,7], k = 9
    Output: true

Example 2:
    Input: root = [5,3,6,2,4,null,7], k = 28
    Output: false

Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    -10^4 <= Node.val <= 10^4
    root is guaranteed to be a valid binary treeBinarySearch tree.
    -10^5 <= k <= 10^5
*/

/**
 * Definition for a binary tree root.
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
    Map<Integer, Integer> map = new HashMap<>();
    
    public boolean findTarget(TreeNode root, int k) {
        // return findTargetUsingMap(root, k); 
        // return findTargetInList(root, k); 
        return findTargetBinarySearch(root, root, k);
    }
    
    public boolean findTargetBinarySearch(TreeNode root, TreeNode cur, int k) {
        if(cur == null) return false;
        
        return treeBinarySearch(root, cur, k - cur.val) 
            || findTargetBinarySearch(root, cur.left, k) 
            || findTargetBinarySearch(root, cur.right, k);
    }
    
    public boolean treeBinarySearch(TreeNode root, TreeNode cur, int k) {
        if(root == null) return false;
        
        return (root.val == k) && (root != cur) 
            || (root.val < k) && treeBinarySearch(root.right, cur, k) 
            || (root.val > k) && treeBinarySearch(root.left, cur, k);
    }

    
    public boolean findTargetInList(TreeNode root, int k) { 
        List<Integer> list = new ArrayList<>();
        
        inorderList(root, list);
        
        int ans = 0; 
        int l = 0, r = list.size()-1;
        
        while(l < r) { 
            ans = list.get(l) + list.get(r);
            
            if (ans > k) { 
                r -= 1;
                while (l < r && Integer.compare(list.get(r+1), list.get(r)) == 0) r-=1; 
            }
            else if (ans < k) {
                l += 1; 
                while (l < r && Integer.compare(list.get(l), list.get(l-1)) == 0) l+=1; 
            }
            else return true; 
        }
        
        return false; 
    }
    
    public void inorderList(TreeNode node, List<Integer> list) {
        if (node == null) return; 
        inorderList(node.left, list);
        list.add(node.val);
        inorderList(node.right, list);
    }
     
    public boolean findTargetUsingMap(TreeNode root, int k) {
        if (root == null) return false;
        if (map.containsKey(k-root.val)) return true; 
        map.compute(root.val, (key, v) -> v == null ? 1 : ++v);
        return findTarget(root.left, k) || findTarget(root.right, k); 
    }
}






