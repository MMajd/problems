/*
 @link https://leetcode.com/problems/find-if-path-exists-in-graph
 @categories (graph/disjoint-sets/dfs)

 There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.

Example 1:
    Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
    Output: true
    Explanation: There are two paths from vertex 0 to vertex 2:
    - 0 → 1 → 2
    - 0 → 2

Example 2:
    Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
    Output: false
    Explanation: There is no path from vertex 0 to vertex 5.
 

Constraints:
    1 <= n <= 2 * 10^5
    0 <= edges.length <= 2 * 10^5
    edges[i].length == 2
    0 <= ui, vi <= n - 1
    ui != vi
    0 <= source, destination <= n - 1
    There are no duplicate edges.
    There are no self edges.
*/
class Solution {
    private static final class DSU {
        private int[] p; 
        private int[] sz; 
        private int components; 

        public DSU(int size) { 
            p = new int[size]; 
            sz = new int[size]; 
            Arrays.setAll(p, i -> {
                sz[i] = 1; 
                return i; 
            }); 
            components = size; 
        }

        public int find(int u) {
            if (p[u] != u) return find(p[u]);
            return p[u]; 
        }

        public boolean union(int u, int v) { 
            int x = find(u);
            int y = find(v);

            if (x == y) return false;
            if (sz[x] >= sz[y]) { 
                p[y] = p[x]; 
            }
            else { 
                p[x] = p[y]; 
            }
            sz[x] += sz[y]; 
            sz[y] = sz[x]; 
            components -= 1; 
            return true; 
        }

        public boolean connected(int u, int v) {
            return find(u) == find(v); 
        }
    }


    public boolean validPath(int n, int[][] edges, int s, int d) {
        DSU dsu = new DSU(n); 

        for (int[] e : edges) { 
            dsu.union(e[0], e[1]);
        }

        return dsu.connected(s, d);
    }
}
