/*
 *
 * @link https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 *
 *
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) return root; 
        Node curr = root; 
        bfsWithNSpace(curr);
        return root; 
    }
    
    public void bfsWithNSpace(Node curr) { 
        Queue<Node> q = new LinkedList<>();
        
        q.add(curr);
        
        while(!q.isEmpty()) {
            int size = q.size(); 
            Node dummy = new Node(-101);
            
            while((size--)>0) { 
                Node n = q.poll(); 
                
                dummy.next = n; 
                dummy = dummy.next; 
                
                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
            }
        }
    }
}










