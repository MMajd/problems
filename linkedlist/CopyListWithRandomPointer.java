/*

@link https://leetcode.com/problems/copy-list-with-random-pointer/

Copy List with Random Pointer

  A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

  Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the t list such that the pointers in the original list and t list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

  For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the t list, x.random --> y.

  Return the head of the t linked list.

  The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

  val: an integer representing Node.val
  random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
  Your code will only be given the head of the original linked list.

   

Example 1:
  Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
  Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
  Example 2:

Example 2:
  Input: head = [[1,1],[2,1]]
  Output: [[1,1],[2,1]]
  Example 3:

Example 3:
  Input: head = [[3,null],[3,0],[3,null]]
  Output: [[3,null],[3,0],[3,null]]
   

Constraints:
  0 <= n <= 1000
  -104 <= Node.val <= 104
  Node.random is null or is pointing to some node in the list.

*/



/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

/* O(N) */
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        Map<Node, Node> map = new HashMap<>();
        Node c = head;

        while (c!=null) {
            map.put(c, new Node(c.val));
            c = c.next;
        }

        c = head; 

        while (c != null) {
            Node t = map.get(c);
            t.next = map.get(c.next);
            t.random = map.get(c.random);
            c = c.next;
        }

        // return copied head; 
        return map.get(head);
    }
}


/* O(N^2) */
class Solution {
    public Node copyRandomList(Node head) {
        if (head==null) return head;
            
        Node c = head; 
        
        Map<Integer,Node> map = new HashMap<>();
        
        int size = 0; 

        while (c != null) { 
            map.put(size++, new Node(c.val));
            c = c.next; 
        }
        
        c = head; 
        
        for (int i=0; i<size; i++) { 
            Node n = map.get(i);
            
            if (c.random!=null) { 
                Node t = c.random; 
                int steps = 0;
                
                while (t!=null && t!=c) { 
                    t = t.next; 
                    steps++; 
                }
                
                if (t==null) steps = size - steps; 
                else steps = i - steps; 
                
                n.random = map.get(steps); 
            } 
            
            c = c.next; 
            n.next = map.getOrDefault(i+1, null);
        }
        
        return map.get(0);
    }

}






