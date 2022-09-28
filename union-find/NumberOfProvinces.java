/*
 @link https://leetcode.com/problems/number-of-provinces
 @categories (union-find/graph) 

 There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.
 

Example 1:
    Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
    Output: 2

Example 2:
    Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
    Output: 3
 

Constraints:
    1 <= n <= 200
    n == isConnected.length
    n == isConnected[i].length
    isConnected[i][j] is 1 or 0.
    isConnected[i][i] == 1
    isConnected[i][j] == isConnected[j][i]
 */


class Solution {
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

        public int max() {
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

    
    
    public int findCircleNum(int[][] G) {
        int n = G.length; 
        DSU dsu = new DSU(G.length);
        
        for (int i=0; i<n; i++) { 
            for (int j=0; j<n; j++) { 
                if (G[i][j] == 1) dsu.union(i, j); 
            }
        }
        
        return dsu.components();
    }
}
