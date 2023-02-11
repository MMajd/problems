/*
 @link https://leetcode.com/problems/as-far-from-land-as-possible
 @categories (bfs) 

 Given an n x n grid containing only values 0 and 1,
where 0 represents water and 1 represents land, 
find a water cell such that its distance to the nearest land cell is maximized, 
and return the distance. If no land or water exists in the grid, return -1.

 The distance used in this problem is the Manhattan distance: 
the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

Example 1:
    Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
    Output: 2
    Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.

Example 2:
    Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
    Output: 4
    Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.

Constraints:
    n == grid.length
    n == grid[i].length
    1 <= n <= 100
    grid[i][j] is 0 or 1
*/ 


/** 
 **********************************    
 ** NOTE: DP Solution at the end
 **********************************    
 *
 * Proof, why bfs gives the right answer? 
 * 1. Sense we scan the grid for all lands and consider them as starting points, 
 * thus there're are no more lands to find in the grid
 * 2. Sense there are no lands and all remainings to explore are waters, the number of steps to visited all waters 
 * is as same as the distance to visit the farthest water cell 
 *
 * We start with distances = -1, if there are no lands, or no waters in the grid then return -1; 
 * else: 
 * We start to add all neighboring waters to all lands to the queue, and make our distance = 0
 * Why 0 not 1? as not been visited yet
 * we continue performing bfs and adding new waters to the queue, 
 * till the queue is empty, our answer is the distance/steps to reach the farthest cell of water
 *
 * Why Manhattan distance required = number of steps 
 * Manhattan distances is just steps in the (i-th direction + j-th direction)
 * and this is the same as number of steps to reach some cell as we only moves in 4-directions, 
 * chaning only one coordinate either i-th, or j-th coordinate at each time, then we count a step 
 *
 * Ex: 
 *  ------------------     
 * |  1 --- 0  |  1  | 
 * |-----|--|--|-----|  
 * |  0  |  0  |  0  | (1,1) - (0,0) = 1 + 1 = 2 
 * |-----|-----|-----| same as number of steps need to reach (1,1) from (0,0)
 * |  1  |  0  |  1  | First we visit (0, 1), then we visit (1,1) 
 * ------------------- 
 *
 */ 
class Solution {
    int[] DIRS = {1, 0, -1, 0, 1}; 

    public int maxDistance(int[][] grid) {
        int n = grid.length; 
        var q = new ArrayDeque<int[]>(n); 
        for (int i=0; i<n; i++) { 
            for (int j=0; j<n; j++) { 
                if (grid[i][j] == 1) {
                    q.add(new int[]{i, j});
                }
            }
        }
        int steps = -1; 
        if (q.size() == 0 || q.size() == n*n) return steps; 

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                var cell = q.poll(); 
                int r = cell[0]; 
                int c = cell[1]; 

                for (int i=0; i<4; i++) { 
                    int x = r + DIRS[i]; 
                    int y = c + DIRS[i+1]; 

                    if (x < 0 || x >= n || y < 0 || y >= n || grid[x][y] == 2) continue; 

                    q.add(new int[]{x, y});
                    grid[x][y] = 2; 
                }
            }

            steps += 1; 
        }

        return steps; 
    }
}



class Solution {
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        final int MAX_DISTANCE = n + m + 1;

        int[][] dist = new int[n][m];
        for (int[] arr : dist) { 
            Arrays.fill(arr, MAX_DISTANCE);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    dist[i][j] = 0;
                } else {
                    int up   = j > 0 ? dist[i][j-1] + 1 : MAX_DISTANCE;
                    int left = i > 0 ? dist[i-1][j] + 1 : MAX_DISTANCE;
                    dist[i][j] = Math.min(dist[i][j], Math.min(left, up));
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int down  = j < m - 1 ? dist[i][j + 1] + 1 : MAX_DISTANCE; 
                int right = i < n - 1 ? dist[i + 1][j] + 1 : MAX_DISTANCE; 

                dist[i][j] = Math.min(dist[i][j], Math.min(right, down));
                ans = Math.max(ans, dist[i][j]);
            }
        }

        return ans == 0 || ans == MAX_DISTANCE ? -1 : ans;
    }
};
