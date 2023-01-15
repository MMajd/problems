/*
 @link https://leetcode.com/problems/number-of-good-paths
 @categories (union-find/graph/trees)

 @link https://www.youtube.com/watch?v=_uVYiM7LmSk

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
    private static final class DSU {
        private int[] parents; 
        private int[] sz; 
        private int[] vals; 

        public DSU(int n, int[] vals) {
            this.vals = vals; 
            this.sz = new int[n]; 
            this.parents = new int[n]; 

            for (int i=0; i<n; i++) { 
                sz[i] = 1; 
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
                res = sz[pu] * sz[pv]; 
                parents[pv] = pu; 
                sz[pu] += sz[pv]; 
                sz[pv] = sz[pu]; 
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
        DSU dsu = new DSU(n, vals);

        // sort edges based on max val in it, from smallest ot biggest
        Arrays.sort(edges, (a, b) -> Math.max(vals[a[0]], vals[a[1]]) - Math.max(vals[b[0]], vals[b[1]]));

        for (int[] e : edges) {
            ans += dsu.union(e[0], e[1]);
        }

        return ans; 
    }
}

class Solution {
    // DisjointSetsUnion - UnionFind
    private static final class DSU { 
        private int[] sz; 
        private int[] id; 
        int sets;
        
        public DSU(int size) { 
            id = new int[size]; // IntStream.closedRange(1, size).toArray();
            sz = new int[size];
            
            for (int i=0; i<size; i++) { 
                id[i] = i; 
                sz[i] = 1; 
            }
            
            sets = size; 
        }
        
        public int find(int x) { 
            int root = x; 
            
            while (root != id[root]) { 
                root = id[root]; 
            }
            
            int next; 
            while (x != root) { 
                next = id[x]; 
                id[x] = root; 
                x = next; 
            }
            
            return root; 
        }
        
        public void union(int x, int y) { 
            int xroot = find(x);
            int yroot = find(y);
            
            if (xroot == yroot) return;
            
            if (sz[xroot] > sz[yroot]) { 
                id[yroot] = xroot;
                sz[xroot] += sz[yroot]; 
            }
            else { 
                id[xroot] = yroot;
                sz[yroot] += sz[xroot]; 
            }
            
            sets -= 1; 
        }
    }
    
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int len = vals.length;
        int ans = len; 
        
        List<Integer>[] adjMat = (ArrayList<Integer>[]) new ArrayList[len]; 
        buildAdjMat(adjMat, edges, vals);
        
        DSU dsu = new DSU(len);
        TreeMap<Integer, List<Integer>> map = new TreeMap<>(); 
        
        for (int i=0; i<len; i++) { 
            map.computeIfAbsent(vals[i], (k) -> new ArrayList<>()).add(i);
        }

        for (int key : map.keySet()) {
            List<Integer> nodes = map.get(key);
            
            for (int node : nodes) {  
                if (adjMat[node] == null) continue; 
                
                for (int neighbor : adjMat[node]) {
                    dsu.union(node, neighbor);
                }
            }
            
            Map<Integer, Integer> roots = new HashMap<>();
            
            for (int node : nodes) { 
                roots.compute(dsu.find(node), (k,v) -> v!=null? ++v : 1);
            }
            
            for (int cnt : roots.values()) { 
                if (cnt > 1) ans += (cnt * (cnt-1))/2;
            }
            
            // ans += nodes.size();
        }
        
        return ans; 
    }
    
    private void buildAdjMat(List<Integer>[] adjMat, int[][] edges, int[] vals) { 
        for (int[] e : edges) { 
            int i = e[0]; 
            int j = e[1];
            
            if (vals[i] > vals[j]) { 
                if (adjMat[i] == null) adjMat[i] = new ArrayList<>();
                adjMat[i].add(j);
            }
            else { 
                if (adjMat[j] == null) adjMat[j] = new ArrayList<>();
                adjMat[j].add(i);
            }
        }
    }
}
