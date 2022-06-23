/** 
 * @link https://leetcode.com/problems/binary-tree-cameras/
 */

/** The intitive approach */
class Solution {
    int camera = 0;
    public enum Camera { HAS_CAMERA, COVERED, PLEASE_COVER };
    
    public int minCameraCover(TreeNode root) {
        // If root is not covered then we need to place a camera at root node
        return cover(root) == Camera.PLEASE_COVER ? ++camera : camera;
    }
    
    public Camera cover(TreeNode root) {
        
        // Base case - if there is no node then it's already covered
        if (root == null)
            return Camera.COVERED;
        
        // Try to cover left and right children's subtree
        Camera l = cover(root.left);
        Camera r = cover(root.right);
        
        // If Any one of the children is not covered then we must place a camera at current node
        if (l ==  Camera.PLEASE_COVER || r == Camera.PLEASE_COVER) {
            camera++;
            return Camera.HAS_CAMERA;
        }
        
        // If any one of left or right node has Camera then the current node is also covered
        if (l== Camera.HAS_CAMERA || r == Camera.HAS_CAMERA) 
            return Camera.COVERED;
        
        // If None of the children is covering the current node then ask it's parent to cover
        return Camera.PLEASE_COVER;
    }
}


/** Tedius one */

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
    private static final int INF = 99999; 
    
    Map<Pair<TreeNode, Pair<Boolean, Boolean>>, Integer> map; 
    
    public int minCameraCover(TreeNode root) {
        map = new HashMap<>();
        return Math.min(dfs(root, false, false), dfs(root, true, false));
    }
    
    private int dfs(TreeNode node, boolean parentHasCam, boolean parentMonitored) {
        if (node == null) { 
            if (parentHasCam) return INF; // not clear
            return 0;
        }
        
        if (node.left == null && node.right == null) {
            if (parentHasCam) return 1;  // not clear
            if (parentMonitored) return 0;  // not clear
            return INF;
        }
        
        Pair<Boolean, Boolean> cameras = new Pair<>(parentHasCam, parentMonitored);
        Pair<TreeNode, Pair<Boolean, Boolean>> key = new Pair<>(node, cameras);
        
        if (map.get(key) != null) { 
            return map.get(key).intValue();
        }
        
        if (parentHasCam) { 
            int value = 1 + Math.min(dfs(node.left, false, parentHasCam), dfs(node.left, true, parentHasCam)) 
                          + Math.min(dfs(node.right, false, parentHasCam), dfs(node.right, true, parentHasCam));
            
            map.put(key, value);
            return value; 
            
        } else { 
            if (parentMonitored) { 
                int value = Math.min(dfs(node.left, false, parentHasCam), dfs(node.left, true, parentHasCam)) 
                       + Math.min(dfs(node.right, false, parentHasCam), dfs(node.right, true, parentHasCam));
                
                map.put(key, value);
                return value; 
            } else { 
                int leftHasCam = dfs(node.left, true, parentHasCam)
                       + Math.min(dfs(node.right, false, parentHasCam), dfs(node.right, true, parentHasCam));
                
                int rightHasCam = dfs(node.right, true, parentHasCam)
                       + Math.min(dfs(node.left, false, parentHasCam), dfs(node.left, true, parentHasCam));
                
                int value = Math.min(leftHasCam, rightHasCam);
                
                map.put(key, value);
                
                return value; 
            }
        }
    }
}

