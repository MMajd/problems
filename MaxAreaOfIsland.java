/*
  @link https://leetcode.com/problems/max-area-of-island
  @categories (union-find/dfs/bfs) 
  
  You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.

Example 1:
    Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
    Output: 6
    Explanation: The answer is not 11, because the island must be connected 4-directionally.

Example 2:
    Input: grid = [[0,0,0,0,0,0,0,0]]
    Output: 0
 
Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 50
    grid[i][j] is either 0 or 1.
*/

class Solution {
    int m, n; 

    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        m = grid.length; 
        n = grid[0].length; 
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) { 
                if (grid[i][j] == 1) { 
                    ans = Math.max(dfs(grid, i, j), ans);
                }
            }
        }
        
        return ans; 
    }
    
    public int dfs(int[][] grid, int r, int c) {
        if (r<0 || r>=m || c<0 || c>=n || grid[r][c] == 0) return 0; 
        
        grid[r][c] = 0; 
        
        return 1 + dfs(grid, r+1, c) 
          + dfs(grid, r-1, c) 
          + dfs(grid, r, c+1)
          + dfs(grid, r, c-1);
    }
}


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

            max = 0; 
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

            if (up == vp) {
                max = Math.max(max, sz[up]);
                return false; 
            }

            if (sz[up] >= sz[vp]) {
                parent[vp] = up; 
            } 
            else { 
                parent[up] = vp; 
            }
            
            sz[vp] += sz[up]; 
            sz[up] = sz[vp]; 
            
            max = Math.max(max, sz[up]);

            components -= 1; 
            return true; 
        }
    }

    
    final static int[] DIRS = {-1, 0, 1, 0, -1}; 
    
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length; 
        int n = grid[0].length;
        
        DSU dsu = new DSU(m*n);
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) { 
                if (grid[i][j] == 1) {
                    int p = i*n+j;
                    dsu.union(p, p); // found at least one island 
                    
                    for (int k=0; k<4; k++) {
                        int r = i + DIRS[k]; 
                        int c = j + DIRS[k+1]; 
                        
                        if (r<0 || r>=m || c<0 || c>=n 
                            || grid[r][c] == 0) continue; 
                        
                        int q = r*n + c; 
                        dsu.union(p, q);
                    }
                }
            }
        }
        
        return dsu.maxComponentSize();
    }
}
