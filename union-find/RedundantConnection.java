/*
  @link https://leetcode.com/problems/redundant-connection
  @categories (union-find/dfs)

  In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.

Example 1:
    Input: edges = [[1,2],[1,3],[2,3]]
    Output: [2,3]

Example 2:
    Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
    Output: [1,4]
 
Constraints:
    n == edges.length
    3 <= n <= 1000
    edges[i].length == 2
    1 <= ai < bi <= edges.length
    ai != bi
    There are no repeated edges.
    The given graph is connected.
*/


/** DFS solution */
class Solution {    
    private int MAX_EDGE_VAL = 1000;
    private Set<Integer> seen = new HashSet();

    public int[] findRedundantConnection(int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1];
        for (int i = 0; i <= MAX_EDGE_VAL; i++) {
            graph[i] = new ArrayList();
        }

        for (int[] edge: edges) {
            seen.clear();
            if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() &&
                    dfs(graph, edge[0], edge[1])) {
                return edge;
            }
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        throw new AssertionError();
    }
    
    public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
        if (!seen.contains(source)) {
            seen.add(source);
            if (source == target) return true;
            for (int nei: graph[source]) {
                if (dfs(graph, nei, target)) return true;
            }
        }
        return false;
    }
}


/** Disjoint sets solution */
class Solution {    
    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(edges.length + 1);
        
        int[] ans = null;  
        
        for (int[] e : edges) { 
            if (!dsu.union(e[0], e[1])) return e; 
        }
        
        return ans; 
    }

    private static final class DSU {
        private int[] parent;
        private int[] sz; 
        private int components; 
        private int max; 

        private DSU(int size) {
            parent = new int[size];
            sz = new int[size];

            Arrays.setAll(parent, i-> {
                sz[i] = 1; 
                return i; 
            });

            max = 1; 
            components = size; 
        }

        public int components() {
            return components; 
        }

        public int maxComponentSize() {
            return max;
        }

        private int f(int u) {// recursive find with path compression
            if (u != parent[u]) parent[u] = f(parent[u]); 
            return parent[u]; 
        }

        public int parent(int u) { 
            return find(u); 
        }

        public int find(int u) {
            int root = u, next; 

            while(root != parent[root]) root = parent[root]; 

            while(u != root) {
                next = parent[u]; 
                parent[u] = root; 
                u = next; 
            }

            return root;            
        }

        public boolean connected(int u, int v) { 
            return find(u) == find(v);
        }

        public boolean union(int u, int v) {
            int up = find(u);
            int vp = find(v);

            if (up == vp) return false; 

            max = Math.max(max, sz[up]+sz[vp]);

            if (sz[up] >= sz[vp]) {
                parent[vp] = up; 
                sz[up] += sz[vp]; 
            } 
            else { 
                parent[up] = vp; 
                sz[vp] += sz[up]; 
            }

            components -= 1; 
            return true; 
        }
    }
}
