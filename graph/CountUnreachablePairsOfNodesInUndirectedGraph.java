/*
 @link https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/
 @categories (dfs/bfs/union-find)

 You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1. 
You are given a 2D integer array edges where edges[i] = [ai, bi] 
denotes that there exists an undirected edge connecting nodes ai and bi.
Return the number of pairs of different nodes that are unreachable from each other.

Example 1:
    Input: n = 3, edges = [[0,1],[0,2],[1,2]]
    Output: 0
    Explanation: There are no pairs of nodes that are unreachable from each other. Therefore, we return 0.

Example 2:
    Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
    Output: 14
    Explanation: There are 14 pairs of nodes that are unreachable from each other:
    [[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
    Therefore, we return 14.

Constraints:
    1 <= n <= 10^5
    0 <= edges.length <= 2 * 10^5
    edges[i].length == 2
    0 <= ai, bi < n
    ai != bi
    There are no repeated edges.
*/

// DFS below
class Solution {
    private class DSU {
        int[] parent; 
        int[] sz; 
        int size; 

        public DSU(int size) { 
            this.size = size; 
            parent = new int[size];
            sz = new int[size];
            for (int i=0; i<size; i++) {
                parent[i] = i; 
                sz[i] = 1; 
            }
        }
        public long unreachablePairs() {
            int n = size; 
            long ans = 0; 
            for (int i=0; i<n && size>0; i++) { 
                ans += 1L * (size-sz[i]) * sz[i]; 
                size = size - sz[i]; 
            }
            return ans; 
        }
        public int find (int u) { 
            return parent[u] == u ? u : (parent[u] = find(parent[u]));
        }
        public boolean union(int u, int v) {
            int a = find(u); 
            int b = find(v); 
            if (a == b) return false; 
            if (sz[a] >= sz[b]) { 
                parent[b] = a; 
                sz[a] += sz[b]; 
                sz[b] = 0; 
            } else { 
                parent[a] = b; 
                sz[b] += sz[a];
                sz[a] = 0; 
            }
            return true; 
        }
    }

    public long countPairs(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        for (int[] edge : edges) { 
            dsu.union(edge[0], edge[1]);
        }
        return dsu.unreachablePairs(); 
    }
}

// DFS
class Solution {
    List<List<Integer>> adj = new ArrayList<>();

    public long countPairs(int n, int[][] edges) {
        for(int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }
        for(int [] edge : edges) {
            adj.get(edge[0]).add(edge[1]); 
            adj.get(edge[1]).add(edge[0]);
        }
        long res = 0, sum = n;
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++) {
            if (visited[i]) continue; 
            int curr = dfs(i, visited, 0);
            sum = sum-curr;
            res += curr*sum;
        }
        return res;
    }
    
    private int dfs(int node, boolean[] visited, int count){ 
        if(visited[node]) return count; 
        visited[node] = true;
        count += 1; 
        for(int curr : adj.get(node)) {
            count = dfs(curr, visited, count);
        }
        return count; 
    }
}
