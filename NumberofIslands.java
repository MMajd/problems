/** 
 *
 * @link https://leetcode.com/problems/number-of-islands/
 *
 */

class NumberOfIslands {
    /** (x, y) = (row, row+1)
      * down    =  (0, 1)
      * right   =  (1, 0)
      * up      =  (0, -1)
      * left    =  (-1, 0)
      */
    final static int[] DIRS = {0, 1, 0, -1, 0};
    final static int NUM_DIRS = 4; 
    
    int m, n; 
    
    public int numIslands(char[][] grid) {
        m = grid.length; 
        n = grid[0].length; 
        
        int ans = 0; 
        
        for (int row=0; row<m; row++) { 
            for (int col=0; col<n; col++) {
                if (grid[row][col] == '1') { 
                    /* this is always returns 1,
                       marks marks visited land by coverting them to water */
                    ans += bfs(grid, row, col); 
                }
            }
        }
        
        return ans; 
    }

    private int dfs(char[][] grid, int row, int col) { 
        if (
            row < 0
            || row >= m
            || col < 0
            || col >= n 
            || grid[row][col] == '0'
           ) return 0;

        grid[row][col] = '0'; // mark as visited
        
        dfs(grid, row+1, col); 
        dfs(grid, row, col+1); 
        dfs(grid, row-1, col); 
        dfs(grid, row, col-1); 

        return 1; 
    }

    
    private int bfs(char[][] grid, int row, int col) { 
        Queue<int[]> q = new LinkedList<>();
        
        q.add(new int[]{row, col});
        grid[row][col] = '0'; 
        
        while(!q.isEmpty()) {
            int[] top = q.poll(); 
            
            for (int i=0; i<NUM_DIRS; i++) { 
                int nextrow = top[0] + DIRS[i]; 
                int nextcol = top[1] + DIRS[i+1]; 
                
                if (
                    nextrow < 0 
                    || nextcol < 0 
                    || nextrow >= m
                    || nextcol >= n
                    || grid[nextrow][nextcol] == '0'
                   ) { 
                    continue; 
                }
                
                grid[nextrow][nextcol] = '0'; 
                
                q.add(new int[]{nextrow, nextcol});
            }
        }
        
        return 1; 
    }
}
