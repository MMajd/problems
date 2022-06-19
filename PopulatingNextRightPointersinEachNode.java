/*
 * @link https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
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
        if(root ==null) return root;
        
        Node curr = root;
        
        // dfs(curr.left, curr.right);
        bfs(curr);
        
        return root;
    }
    
    public void bfs(Node curr) { 
        Node next = curr.left; 
        
        while(curr != null && next != null) {
            curr.left.next = curr.right;
            
            if (curr.next != null) {
                curr.right.next = curr.next.left;
            }
            
            curr = curr.next;
            if (curr == null) { 
                curr = next; 
                next = curr.left; 
            }
        }
    }
   
    
    public void dfs(Node n1, Node n2) {
        if(n1 == null || n2 == null)
            return;
        
        n1.next=n2;
        
        dfs(n1.left, n1.right);
        dfs(n1.right, n2.left);
        dfs(n2.left, n2.right);
    }
}











