/*

 @link: https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/

 Given the root of a binary tree, calculate the vertical order traversal of the binary tree.

For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).

The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.

Return the vertical order traversal of the binary tree.
 
Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: [[9],[3,15],[20],[7]]
    Explanation:
    Column -1: Only node 9 is in this column.
    Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
    Column 1: Only node 20 is in this column.
    Column 2: Only node 7 is in this column.

Example 2:
    Input: root = [1,2,3,4,5,6,7]
    Output: [[4],[2],[1,5,6],[3],[7]]
    Explanation:
    Column -2: Only node 4 is in this column.
    Column -1: Only node 2 is in this column.
    Column 0: Nodes 1, 5, and 6 are in this column.
    1 is at the top, so it comes first.
    5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
    Column 1: Only node 3 is in this column.
    Column 2: Only node 7 is in this column.

Example 3:
    Input: root = [1,2,3,4,6,5,7]
    Output: [[4],[2],[1,5,6],[3],[7]]
    Explanation:
    This case is the exact same as example 2, but with nodes 5 and 6 swapped.
    Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.
                         

Constraints:
    The number of nodes in the tree is in the range [1, 1000].
    0 <= Node.val <= 1000

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

/** Two solution found */

/** 1. DFS, Map, Tree based solution */
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, Map<Integer, List<Integer>>> map = new TreeMap<>(); 
        
        dfs(root, 0, 0, map);
        
        List<List<Integer>> ans = new ArrayList<>(map.size());
        
        for (Map<Integer, List<Integer>> e : map.values()) { 
            List<Integer> arr = new ArrayList<>();
            
            for (List<Integer> l : e.values()) {
                Collections.sort(l);
                arr.addAll(l);
            }
            
            ans.add(arr);
        }
        
        return ans; 
    }
    
    
    private void dfs(TreeNode node, int col, int row, 
                       Map<Integer, Map<Integer, List<Integer>>> map) {
        if (node == null) return;
        
        map.compute(col, (k, v) -> {
            if (v == null) v = new TreeMap<>(); 
            
            v.compute(row, (k1, v1) -> { 
                if (v1 == null) v1 = new ArrayList<>(); 
                v1.add(node.val);
                return v1; 
            });

            return v; 
        }); 
        
        dfs(node.left, col-1, row+1, map);
        dfs(node.right, col+1, row+1, map);
    }
}



/** 2. DFS, List, Comparator based solution */

class Solution {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<int[]> list = new ArrayList<>();
        
        dfs(root, 0, 0, list);

        Collections.sort(list, (a, b) -> {
            if (a[2] != b[2]) return a[2]-b[2];
            if (a[1] != b[1]) return a[1]-b[1]; 
            return a[0]-b[0];
        });
        
        List<List<Integer>> ans = new ArrayList<>();

        int precol = Integer.MIN_VALUE;

        for(int[] node:list){
            int col = node[2];
            int value = node[0];
            if(col != precol){
                ans.add(new ArrayList<>());
                precol = col;
            }
            ans.get(ans.size()-1).add(value);
        }
        return ans;
    }

    public void dfs(TreeNode node, int row, int col, List<int[]> list){
        if(node == null) return; 

        list.add(new int[]{node.val, row, col});

        dfs(node.left, row+1, col-1, list);
        dfs(node.right, row+1, col+1, list);
    }

}
