/*
 @link https://leetcode.com/problems/number-of-good-paths
 @categories (union-find/graph/trees)

 @link https://www.youtube.com/watch?v=_uVYiM7LmSk
 @link https://www.youtube.com/watch?v=1ayv-qCro0M

 There is a tree (i.e. a connected, undirected graph with no cycles) consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges.

You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node. You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.

A good path is a simple path that satisfies the following conditions:

The starting node and the ending node have the same value.
All nodes between the starting node and the ending node have values less than or equal to the starting node (i.e. the starting node's value should be the maximum value along the path).
Return the number of distinct good paths.

Note that a path and its reverse are counted as the same path. For example, 0 -> 1 is considered to be the same as 1 -> 0. A single node is also considered as a valid path.
 

Example 1:
    Input: vals = [1,3,2,1,3], edges = [[0,1],[0,2],[2,3],[2,4]]
    Output: 6
    Explanation: There are 5 good paths consisting of a single node.
    There is 1 additional good path: 1 -> 0 -> 2 -> 4.
    (The reverse path 4 -> 2 -> 0 -> 1 is treated as the same as 1 -> 0 -> 2 -> 4.)
    Note that 0 -> 2 -> 3 is not a good path because vals[2] > vals[0].

Example 2:
    Input: vals = [1,1,2,2,3], edges = [[0,1],[1,2],[2,3],[2,4]]
    Output: 7
    Explanation: There are 5 good paths consisting of a single node.
    There are 2 additional good paths: 0 -> 1 and 2 -> 3.

Example 3:
    Input: vals = [1], edges = []
    Output: 1
    Explanation: The tree consists of only one node, so there is one good path.
     

Constraints:
    n == vals.length
    1 <= n <= 3 * 10^4
    0 <= vals[i] <= 10^5
    edges.length == n - 1
    edges[i].length == 2
    0 <= ai, bi < n
    ai != bi
    edges represents a valid tree.

*/

class Solution {
    private static final class CustomDSU {
        private int[] parents; 
        private int[] rank; 
        private int[] vals; 

        public CustomDSU(int n, int[] vals) {
            this.vals = vals; 
            this.rank = new int[n]; 
            this.parents = new int[n]; 

            for (int i=0; i<n; i++) { 
                rank[i] = 1; 
                parents[i] = i; 
            }
        }

        // path normalized
        public int find(int u) { 
            if (u != parents[u]) { 
                parents[u] = find(parents[u]); 
            }
            return parents[u]; 
        }

        public int union(int u, int v) { 
            int res = 0; 
            int pu = find(u); 
            int pv = find(v); 

            if (pu == pv) return res; 

            if (vals[pu] == vals[pv]) { 
                res = rank[pu] * rank[pv]; 
                parents[pv] = pu; 
                rank[pu] += rank[pv]; 
            }
            else if (vals[pu] > vals[pv]) { 
                parents[pv] = pu; 
            }
            else { 
                parents[pu] = pv; 
            }


            return res; 
        }
    }

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        int ans = vals.length;
        CustomDSU dsu = new CustomDSU(n, vals);

        // sort edges based on max val in it, from smallest ot biggest
        Arrays.sort(edges, (a, b) -> Math.max(vals[a[0]], vals[a[1]]) - Math.max(vals[b[0]], vals[b[1]]));

        for (int[] e : edges) {
            ans += dsu .union(e[0], e[1]);
        }

        return ans; 
    }
}


class Solution {
    private static final class DSU {
        private int[] parents; 
        private int[] rank; 

        public DSU(int n) {
            this.rank = new int[n]; 
            this.parents = new int[n]; 

            for (int i=0; i<n; i++) { 
                rank[i] = 1; 
                parents[i] = i; 
            }
        }

        // path normalized
        public int find(int u) { 
            if (u != parents[u]) { 
                parents[u] = find(parents[u]); 
            }
            return parents[u]; 
        }

        public boolean union(int u, int v) { 
            int pu = find(u); 
            int pv = find(v); 

            if (pu == pv) return false; 

            if (rank[pu] >= rank[pv]) { 
                parents[pv] = pu; 
                rank[pu] += rank[pv]; 
            } else { 
                parents[pu] = pv; 
                rank[pv] += rank[pu]; 
            }

            return true; 
        }
    }

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        int ans = vals.length;

        DSU dsu = new DSU(n);
        Map<Integer, List<Integer>> adj = new HashMap<>(); 
        TreeMap<Integer, List<Integer>> sortedValuesToNodes = new TreeMap<>(); 
        
        for (int[] e : edges) {
            int a = e[0]; 
            int b = e[1]; 
            adj.computeIfAbsent(a, v -> new ArrayList<>()).add(b);
            adj.computeIfAbsent(b, v -> new ArrayList<>()).add(a);
        }

        for (int i=0; i<n; i++) { 
            sortedValuesToNodes.computeIfAbsent(vals[i], v -> new ArrayList<>()).add(i);
        }

        for (int value : sortedValuesToNodes.keySet()) {
            for (int node : sortedValuesToNodes.get(value)) {
                for (int neighbor : adj.getOrDefault(node, new ArrayList<>())) { 
                    if (vals[neighbor] <= vals[node]) {
                        dsu.union(node, neighbor);
                    }
                }
            }

            Map<Integer, Integer> roots = new HashMap<>();
            for (int node : sortedValuesToNodes.get(value)) {
                roots.compute(dsu.find(node), (k, v) -> v == null ? 1 : ++v);
            }

            for (int cnt : roots.values()) { 
                if (cnt > 1) ans += (cnt * (cnt-1)) / 2; 
            }
        }

        return ans; 
    }
}

