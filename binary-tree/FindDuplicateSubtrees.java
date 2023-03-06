/*
 @link https://leetcode.com/problems/find-duplicate-subtrees/
 @categories (tree/preorder) 
  
 Given the root of a binary tree, return all duplicate subtrees.
For each kind of duplicate subtrees, you only need to return the root node of any one of them.
Two trees are duplicate if they have the same structure with the same node values.

Example 1:
    Input: root = [1,2,3,4,null,2,4,null,null,4]
    Output: [[2,4],[4]]

Example 2:
    Input: root = [2,1,1]
    Output: [[1]]

Example 3:
    Input: root = [2,2,2,3,null,3,null]
    Output: [[2,3],[3]]

Constraints:
    The number of the nodes in the tree will be in the range [1, 10^4]
    -200 <= Node.val <= 200
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
    List<TreeNode> ans = new ArrayList<>();
    Map<String, Integer> map= new HashMap<>();
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        preorder(root);
        return ans; 
    }

    private String preorder(TreeNode node) {
        if (node == null) return "#"; 
        String key = node.val + "|" + preorder(node.left) + preorder(node.right);  

        map.compute(key, (k, v) -> v != null ? ++v : 1); 

        if (map.get(key) == 2) ans.add(node);

        return key; 
    }
}

class Solution {
   int curId = 1;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> serialToId = new HashMap<>();
        Map<Integer, Integer> idToCount = new HashMap<>();
        List<TreeNode> res = new LinkedList<>();
        postorder(root, serialToId, idToCount, res);
        return res;
    }
    
    private int postorder(TreeNode root, Map<String, Integer> serialToId, Map<Integer, Integer> idToCount, List<TreeNode> res) {
        if (root == null) return 0;
        int leftId = postorder(root.left, serialToId, idToCount, res);
        int rightId = postorder(root.right, serialToId, idToCount, res);
        String curSerial = leftId + "," + root.val + "," + rightId;
        int serialId = serialToId.getOrDefault(curSerial, curId);
        if (serialId == curId) curId++;
        serialToId.put(curSerial, serialId);
        idToCount.put(serialId, idToCount.getOrDefault(serialId, 0) + 1);
        if (idToCount.get(serialId) == 2) res.add(root);
        return serialId;
    }
    
}







