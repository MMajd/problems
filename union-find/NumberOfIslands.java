/*
  @link https://leetcode.com/problems/number-of-islands
  @categories (union-find)
  
  Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 

Example 1:
    Input: grid = [
      ["1","1","1","1","0"],
      ["1","1","0","1","0"],
      ["1","1","0","0","0"],
      ["0","0","0","0","0"]
    ]
    Output: 1

Example 2:
    Input: grid = [
      ["1","1","0","0","0"],
      ["1","1","0","0","0"],
      ["0","0","1","0","0"],
      ["0","0","0","1","1"]
    ]
    Output: 3
 

Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 300
    grid[i][j] is '0' or '1'.
  
 */



class Solution {
    /** (x, y) = (row, row+1)
      * down    =  (0, 1)
      * right   =  (1, 0)
      * up      =  (0, -1)
      * left    =  (-1, 0)
      */
    final static int[] DIRS = {0, 1, 0, -1, 0};
    final static int NUM_DIRS = 4; 
    
    private static final class DSU {
        private int[] id; 
        private int[] sz; 
        private int sets; 
        
        public DSU(int size) {
            id = new int[size]; 
            sz = new int[size];
            
            Arrays.setAll(id, i -> {
                sz[i] = 1; 
                return i; 
            }); 
            
            sets = 0; 
        }
        
        public void addSet() { 
            sets += 1; 
        }
        
        public int sets() { 
            return sets; 
        }
        
        public int find(int x) { 
            int next=0, root = x; 
            
            while(root!=id[root]) {
                root=id[root];
            }

            while(x != root) {
                next = id[x];
                id[x] = root; 
                x = next; 
            }
            
            return root; 
        }
        
        public boolean union(int x, int y) {
            int xroot = find(x); 
            int yroot = find(y); 
            
            if (xroot == yroot) return false; 
            
            if (sz[xroot] > sz[yroot]) {
                id[yroot] = xroot; 
                sz[xroot] += sz[yroot];
            }
            else { 
                id[xroot] = yroot; 
                sz[yroot] += sz[xroot];
            }
            
            sets -= 1; 
            return true; 
        }
    }
    
    public int numIslands(char[][] grid) {
        int m = grid.length; 
        int n = grid[0].length; 
        
        DSU dsu = new DSU(m*n);
        
        for (int i=0; i<m; ++i) {
            for (int j=0; j<n; ++j) {
                if (grid[i][j] == '1') {
                    dsu.addSet();
                    
                    for (int k=0; k<NUM_DIRS; k++) { 
                        int r = i + DIRS[k]; 
                        int c = j + DIRS[k+1]; 
                        
                        if (r<0 || c<0 || r>=m || c>=n || grid[r][c] == '0') continue; 
                        
                        dsu.union(i*n+j, r*n+c); 
                    }
                }
            }
        }
        
        return dsu.sets();
    }
}
