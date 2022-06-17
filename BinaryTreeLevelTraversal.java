
/** 
 *
 *
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
class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>(); 
        Deque<List<TreeNode>> q = new LinkedList<>();
        
        if (root == null) return list;
        
        helper(root, 0, list);
        
        return list;
        
 //       q.add(Arrays.asList(new TreeNode[]{root}));
 //       
 //       while(q.size() != 0) { 
 //           List<Integer> sublist = new ArrayList<>();
 //           List<TreeNode> nodes = new ArrayList<>();
 //           
 //           for (TreeNode n : q.poll()) { 
 //               sublist.add(n.val); 
 //               
 //               if (n.left != null) nodes.add(n.left);
 //               if (n.right != null) nodes.add(n.right);
 //           }
 //           
 //           if (sublist.size() > 0)
 //               list.add(sublist);
 //           
 //           if (nodes.size() > 0)
 //               q.add(nodes);
 //       }
 //       
 //       return list; 
        
    }
    
    private void helper(TreeNode node, int level, List<List<Integer>> levels) { 
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());
        
        levels.get(level).add(node.val);
        
        if (node.left != null) helper(node.left, level + 1, levels);
        if (node.right != null) helper(node.right, level + 1, levels);
    }
}


