/* 
 
 @link https://leetcode.com/problems/average-of-levels-in-binary-tree/

 Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. Answers within 10-5 of the actual answer will be accepted.

Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: [3.00000,14.50000,11.00000]
    Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
    Hence return [3, 14.5, 11].

Example 2:
    Input: root = [3,9,20,15,7]
    Output: [3.00000,14.50000,11.00000]
      
Constraints:
    The number of nodes in the tree is in the range [1, 104].
    -231 <= Node.val <= 231 - 1
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
    public List<Double> averageOfLevels(TreeNode root) {
        return bfsAverageOfLevels(root); 
        // return dfsAverageOfLevels(root); 
    }
        
    public List<Double> bfsAverageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        
        q.add(root);
        
        while(!q.isEmpty()) {
            int size = q.size();
            
            double sum = 0; 
            
            for (int i=0; i<size; i++) { 
                TreeNode n = q.pollFirst(); 
                sum += n.val; 
                
                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
            }
            
            sum /= size; 
            ans.add(sum);
        }
        
        
        return ans; 
    }
    
    
    public List<Double> dfsAverageOfLevels(TreeNode root) {
        List<Integer> count = new ArrayList<>(); 
        List<Double> ans = new ArrayList<>(); 
        
        dfs(root, ans, count, 0);
        
        for (int i=0; i<ans.size(); i++) { 
            ans.set(i, ans.get(i) / count.get(i));
        }
        
        return ans; 
    }
    
    private void dfs(TreeNode node, List<Double> ans, List<Integer> count, int level) { 
        if (node == null) return; 
         
        if (level == ans.size()) {
            ans.add(Double.valueOf(node.val));
            count.add(1);
        }
        else {
            ans.set(level, ans.get(level) + node.val); 
            count.set(level, count.get(level) + 1);
        }
        
        dfs(node.left, ans, count, level+1);
        dfs(node.right, ans, count, level+1);
    }
}
