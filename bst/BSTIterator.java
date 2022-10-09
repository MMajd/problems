





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

/** Time and Space performant solution based on stack, T: O(n), S ~ O(n) */ 

class BSTIterator {
    private TreeNode root = null; 
    private Stack<TreeNode> stack; 
    
    public BSTIterator(TreeNode root) {
        stack = new Stack<>(); 
        inorderPush(root);
    }
    
    private void inorderPush(TreeNode node) { 
        while(node != null) { 
            stack.push(node);
            node = node.left; 
        }
    }
    
    public boolean hasNext() {
        return stack.isEmpty() == false; 
    }
    
    public int next() {
        TreeNode node = stack.pop();
        inorderPush(node.right);
        return node.val; 
    }
    
}


/** Space conservative solution O(1), and Time of O(nlogn) approx */
class BSTIterator {
    private TreeNode root = null; 
    private int lastKey = -1; 
    private int nextVal = -1; 
    
    public BSTIterator(TreeNode root) {
        this.root = root; 
    }
    
    private TreeNode findMin(TreeNode node)  { 
        while(node.left!=null) node = node.left; 
        return node; 
    }
    
    private TreeNode inorderSuccessor(TreeNode node, TreeNode succ, int key) { 
        if (node == null) return succ; 
        
        if (node.val == key) { 
            if (node.right != null) { 
                return findMin(node.right); 
            }
        }
        
        if (node.val > key) { 
            succ = node; 
            return  inorderSuccessor(node.left, succ, key); 
        }
        
        return inorderSuccessor(node.right, succ, key); 
    }
    
    public boolean hasNext() {
        if (nextVal!=-1) return true;
        
        TreeNode node = inorderSuccessor(root, null, lastKey); 
        
        if (node != null) { 
            nextVal = node.val; 
            return true; 
        }
        
        return false; 
    }
    
    public int next() {
        int val = -1; 
        
        if (nextVal != -1) {
            val = nextVal; 
        } else { 
            TreeNode node = inorderSuccessor(root, null, lastKey); 
            val = node.val;
        }
        
        nextVal = -1; 
        lastKey = val; 
        return val; 
    }
    
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
