/** 
 *
 */ 

/*
// Definition for a Node.
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
        
        Map<Integer, Node> visited = new HashMap<>();
        
        return dfs(node, visited);
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




