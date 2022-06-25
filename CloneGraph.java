/** 
 *
 * @link https://leetcode.com/problems/clone-graph
 *
 *
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null)  return null; 
        if (node.neighbors.size() == 0) 
            return new Node(node.val);
        
        // Map<Integer, Node> visited = new HashMap<>();
        // return dfs(node, visited);
        
        return bfs(node);
    }
    
    public Node bfs(Node node) { 
        Map<Integer, Node> visited = new HashMap<>();
        Queue<Node> q = new LinkedList<>(); 
        
        q.add(node);
        
        while(!q.isEmpty()) { 
            int size = q.size(); 
            for (int i=0; i<size; i++) { 
                Node head = q.poll();
                Node clone = visited
                    .computeIfAbsent(head.val, x -> new Node(head.val));
                
                for (Node n : head.neighbors) {
                    
                    if (!visited.containsKey(n.val))  { 
                        q.add(n);
                    }
                    
                    
                    clone.neighbors.add(
                        visited
                        .computeIfAbsent(n.val, x -> new Node(n.val))
                    );
                    
                }
            }
        }
        
        return visited.get(node.val); 
    }

    
    public Node dfs(Node curr, Map<Integer, Node> visited) { 
        if (visited.containsKey(curr.val)) { 
            return visited.get(curr.val);
        }
        
        Node node = visited
            .computeIfAbsent(curr.val, unused -> new Node(curr.val)); 
        
        for (Node n : curr.neighbors) { 
            node.neighbors.add(dfs(n, visited));
        }
        
        return node; 
    }
    
}





