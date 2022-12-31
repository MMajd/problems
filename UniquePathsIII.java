/*
 @link https://leetcode.com/problems/unique-paths-iii/
 @categories (backtracking/matrix)

 You are given an m x n integer array grid where grid[i][j] could be:
- representing the starting square. There is exactly one starting square.
- 2 representing the ending square.  There is exactly one ending square.
- 0 representing empty squares we can walk over.
- -1 representing obstacles that we cannot walk over.

** Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

Example 1:
    Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
    Output: 2
    Explanation: We have the following two paths: 
    1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
    2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)

Example 2:
    Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
    Output: 4
    Explanation: We have the following four paths: 
    1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
    2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
    3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
    4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)

Example 3:
    Input: grid = [[0,1],[2,0]]
    Output: 0
    Explanation: There is no path that walks over every empty square exactly once.
    Note that the starting and ending square can be anywhere in the grid.

Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 20
    1 <= m * n <= 20
    -1 <= grid[i][j] <= 2
    There is exactly one starting cell and one ending cell.
*/

class Solution {
    private static final int[] DIRS = {-1, 0, 1, 0, -1};  
    private int m, n; 

    public int uniquePathsIII(int[][] G) {
        m = G.length; 
        n = G[0].length; 
        int open = 0, x = 0, y = 0;

        for(int r = 0; r < G.length ; r++){
            for(int c = 0; c < G[0].length; c++){
                if(G[r][c] == 1){
                    x=r; y=c;
                }
                else if(G[r][c] == 0) open += 1;
            }
        }

        return solve(G, x, y, open);
    }

    private int solve(int[][] G, int x, int y, int open){
        if(x<0 || y<0 || x>=m || y>=n || G[x][y] == -1) return 0;
        if(G[x][y] == 2) return open == -1 ? 1 : 0;

        open -= 1;
        G[x][y] = -1;

        int res = 0;

        for (int k=0; k<4; k++) {
            int i = x + DIRS[k]; 
            int j = y + DIRS[k+1]; 

            res += solve(G, i, j, open);
        }

        open += 1;
        G[x][y] = 0;
        return res; 
    }
}
