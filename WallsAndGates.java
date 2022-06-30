/** 
 * Same as Matrix 0-1 
 * @link https://www.lintcode.com/problem/663/
 */ 

public class Solution {
    private static final int[] DIRS = {-1, 0, 1, 0, -1}; 

    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length; 
        int n = rooms[0].length; 

        int[][] visited = new int[m][n]; 
        Queue<int[]> q = new LinkedList<>(); 

        for (int i=0; i<m; i++) { 
            for (int j=0; j<n; j++) { 
                if (rooms[i][j] == 0) {
                    q.add(new int[]{i,j});
                    visited[i][j] = 1; 
                }
                if (rooms[i][j] == -1) visited[i][j] = 1; 
            }
        }

        int step = 1; 

        while(!q.isEmpty()) {
            int sz = q.size(); 

            for (int _i=0; _i<sz; _i++) { 
                int[] curr = q.poll(); 

                for (int i=0; i<4; i++) { 
                    int nr = curr[0] + DIRS[i]; 
                    int nc = curr[1] + DIRS[i+1]; 

                    if (nr < 0
                       || nr >= m 
                       || nc < 0 
                       || nc >= n 
                       || visited[nr][nc] == 1) continue; 

                    visited[nr][nc] = 1; 
                    rooms[nr][nc] = step;                     
                    q.add(new int[]{nr, nc}); 
                }
            }

            step += 1; 
        }
    }
}
