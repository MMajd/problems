/* 
  @link https://leetcode.com/problems/is-graph-bipartite/
  @categories (bfs/union-find/dfs/graph[graph-coloring])

  There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:

There are no self-edges (graph[u] does not contain u).
There are no parallel edges (graph[u] does not contain duplicate values).
If v is in graph[u], then u is in graph[v] (the graph is undirected).
The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.

Return true if and only if it is bipartite.

Example 1:
    Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
    Output: false
    Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.

Example 2:
    Input: graph = [[1,3],[0,2],[1,3],[0,2]]
    Output: true
    Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.
 

Constraints:
    graph.length == n
    1 <= n <= 100
    0 <= graph[u].length < n
    0 <= graph[u][i] <= n - 1
    graph[u] does not contain u.
    All the values of graph[u] are unique.
    If graph[u] contains v, then graph[v] contains u.
 */ 

/** 
 * Disjoint sets solution 
 */

class Solution { 
    /** 
    Intiution: 
        - This is reduces to a graph coloring problem, but we have to color the graph using exactly two-colors
        
        - Using disjoint sets to do the coloring by putting adj-nodes 
        to the i-th nodes in the same group/set
        
        - To detect if given graph is not bi-partite, created groups will not be disjoint as expected if the graph is a bi-partite. 
    
        - We should check if the i-th node have same color as its j-th neighbor, if this is true then we know that sets are not disjoint and the graph is not a bi-graph. 
    */
    
    public boolean isBipartite(int[][] graph) {
        int n = graph.length; 
        DSU dsu = new DSU(n);
        
        for (int i=0; i<n; i++) { 
            int m = graph[i].length;
            
            for (int j=0; j<m; j++) { 
                // if i-th node and its j-th neighbor have the same color, then graph is not bi-graph
                if (dsu.connected(i, graph[i][j])) {
                    return false; 
                }
                
                // create a color set of i-th node neighbors by linking them all to its first neighbor in the adj-node list, as list size may varies from one node to the other, its guaranteed to find an element in index 0 if there's adj-node list to the i-th node 
                dsu.union(graph[i][0], graph[i][j]);
            }
        }
        
        return true; 
    }
    
    
    
    private static final class DSU { 
        private int[] parent; 
        private int[] sz; 
        private int components; 
        
        public DSU(int size) { 
            parent = new int[size]; 
            sz = new int[size]; 
            
            Arrays.setAll(parent, i -> { 
                sz[i] = 1; 
                return i; 
            });
            
            components = size; 
        }
        
        // recursive, with path compression 
        public int find(int x) {
            if (x != parent[x]) { 
                parent[x] = find(parent[x]);
            }
            
            return parent[x]; 
        }
        
        public int size(int x) { 
            return sz[find(x)]; 
        }
        
        public boolean connected(int x, int y) {
            return find(x) == find(y); 
        }
        
        public boolean union(int x, int y) {
            int xroot = find(x); 
            int yroot = find(y); 
            
            if (xroot == yroot) {
                return false; 
            }
            
            if (sz[xroot] >= sz[yroot]) {
                parent[yroot] = xroot; 
            } 
            else { 
                parent[xroot] = yroot; 
            }
            
            sz[xroot] += sz[yroot]; 
            sz[yroot] = sz[xroot]; 
            
            return true; 
        }
        
        public String toString() { 
            return "Parent: " + Arrays.toString(parent) 
                + "\nSize: " + Arrays.toString(sz);
        }
    }
    
}
