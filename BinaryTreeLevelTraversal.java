
/** 
 @link https://leetcode.com/problems/binary-tree-level-order-traversal/

 Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).


Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: [[3],[9,20],[15,7]]

Example 2:
    Input: root = [1]
    Output: [[1]]

Example 3:
    Input: root = []
    Output: []
 

Constraints:
    The number of nodes in the tree is in the range [0, 2000].
    -1000 <= Node.val <= 1000
  
**/

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new LinkedList<>();
        
        List<List<Integer>> list = new ArrayList<>(); 
        Deque<List<TreeNode>> q = new LinkedList<>();
        
        // recursiveLevelOrder(root, 0, list);
        iterativeLevelOrder(root, list, q);
        
        return list;
    }
    
    private void iterativeLevelOrder(TreeNode root, 
            List<List<Integer>> list, Deque<List<TreeNode>> q) { 

        q.add(Arrays.asList(new TreeNode[]{root}));
        
        while(q.size() != 0) { 
            List<TreeNode> nodes = new ArrayList<>();
            List<Integer> childs = new ArrayList<>();
            
            for (TreeNode n : q.poll()) { 
                childs.add(n.val); 
                
                if (n.left != null) nodes.add(n.left);
                if (n.right != null) nodes.add(n.right);
            }
            
            if (childs.size() > 0)
                list.add(childs);
            
            if (nodes.size() > 0)
                q.add(nodes);
        }
    }
    
    
    private void recursiveLevelOrder(TreeNode node, 
            int level, List<List<Integer>> levels) { 

        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());
        
        levels.get(level).add(node.val);
        
        if (node.left != null) 
            recursiveLevelOrder(node.left, level + 1, levels);
        if (node.right != null) 
            recursiveLevelOrder(node.right, level + 1, levels);
    }
}

