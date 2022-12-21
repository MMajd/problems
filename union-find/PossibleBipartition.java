/*
 @link https://leetcode.com/problems/possible-bipartition/
 @categories (graph/union-find/dfs/bfs)

 We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.

 Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.
 
Example 1:
    Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
    Output: true
    Explanation: group1 [1,4] and group2 [2,3].

Example 2:
    Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
    Output: false

Example 3:
    Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
    Output: false

Constraints:
    1 <= n <= 2000
    0 <= dislikes.length <= 104
    dislikes[i].length == 2
    1 <= dislikes[i][j] <= n
    ai < bi
    All the pairs of dislikes are unique.
*/
class Solution {
    class DSU {
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
            } 
            else { 
                parent[up] = vp; 
            }
    
            sz[up] += sz[vp]; 
            sz[vp] = sz[up]; 
            
            components -= 1; 
            return true; 
        }
    }

    
    public boolean possibleBipartition(int n, int[][] dislikes) {
        DSU dsu = new DSU(n+1);
        Map<Integer, List<Integer>> adj = new HashMap<>(); 

        for (int[] e : dislikes) {
            int a = e[0], b = e[1]; 
            adj.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            adj.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }

        for (int i=0; i<n; i++) { 
            for (int j : adj.getOrDefault(i, new ArrayList<>())) {
                if (dsu.connected(i, j)) return false; 
                dsu.union(adj.get(i).get(0), j);
            }
        }

        return true; 
    }
}
