/*
 
 @link https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/

 @categories (bfs/dfs)


 You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.

Example 1:
    Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
    Output: 6
    Explanation: 
    The shortest path without eliminating any obstacle is 10.
    The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).

Example 2:
    Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
    Output: -1
    Explanation: We need to eliminate at least two obstacles to find such a walk.

Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 40
    1 <= k <= m * n
    grid[i][j] is either 0 or 1.
    grid[0][0] == grid[m - 1][n - 1] == 0

*/


class Solution {
    private final static int[] DIRS = {-1, 0, 1, 0, -1}; 
    
    public int shortestPath(int[][] grid, int K) {
        int M = grid.length; 
        int N = grid[0].length; 
        
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, 0});
        
        int[][] visitCost = new int[M][N]; // visitCost(i,j) is the cost of visiting that route
        
        Arrays.setAll(visitCost, i -> { 
            Arrays.fill(visitCost[i], Integer.MAX_VALUE);
            return visitCost[i]; 
        });
        
        int steps = 0; 
        
        
        while (!q.isEmpty()) { 
            int size = q.size(); 
            
            while (size-- > 0) {
                int[] temp = q.poll(); 
                int r = temp[0]; 
                int c = temp[1]; 
                int cost = temp[2]; 
                
                if (r == M-1 && c == N-1) return steps; 
                
                for (int d=0; d<4; d++) { 
                    int i = r + DIRS[d];
                    int j = c + DIRS[d+1]; 
                    
                    if (i<0 || i>=M || j<0 || j>=N) continue; 
                    
                    int newCost = cost + grid[i][j];
                    
                    if (newCost >= visitCost[i][j] || newCost > K) continue;
                    
                    visitCost[i][j] = newCost; 
                    q.add(new int[]{i, j, newCost});
                }
            }
            
            steps += 1; 
        }
        
        return -1; 
    }
}
