/** 
 *
 *
 * @link https://leetcode.com/problems/01-matrix
 * 
 */ 

/** Instead of solving for ones, solve for zeros */ 
class Solution {
    private static final int[] DIRS = {-1, 0, 1, 0, -1}; 
    private static final int DIRS_LEN = 4;
    
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length; 
        int cols = mat[0].length;
        
        Queue<int[]> q = new LinkedList<>();
        
        int[][] visited = new int[rows][cols];
        
        for (int i=0; i<rows; i++) { 
            for (int j=0; j<cols; j++) { 
                if (mat[i][j] == 0) { 
                    visited[i][j] = 1; 
                    q.add(new int[]{i, j});
                }
            }
        }
        
        int step = 1; 
        
        while(!q.isEmpty()) {
            int size = q.size(); 
            
            for (int i=0; i<size; i++) { 
                int[] curr = q.poll();
                
                for (int k=0; k<DIRS_LEN; k++) {
                    int nr = curr[0] + DIRS[k]; 
                    int nc = curr[1] + DIRS[k+1]; 
                    
                    if (nr< 0
                        || nr >= rows
                        || nc < 0 
                        || nc >= cols
                        || visited[nr][nc] == 1 
                       ) { 
                        continue; 
                    }
                    q.add(new int[]{nr,nc});
                    visited[nr][nc] = 1; 
                    mat[nr][nc] = step; 
                }
            }

            step += 1; 
        }
        
        return mat; 
    }
}


/** TLE, need memoization to get anser from neighbors */
class Solution {
    private static final int[] DIRS = {-1, 0, 1, 0, -1}; 
    private static final int DIRS_LEN = 4;
    
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length; 
        int n = mat[0].length;
        
        
        for (int i=0; i<m; i++) { 
            for (int j=0; j<n; j++) {
                if (mat[i][j] == 1) { 
                    mat[i][j] = bfs(mat, i, j);
                }
            }
        }
        
        return mat; 
    }
        
    public int bfs(int mat[][], int row, int col) {
        int distance = 0; 
        
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>(); 
        
        int m = mat.length; 
        int c = mat[0].length; 
        
        visited.add(col + m * row);
        q.add(new int[]{row, col});
        
        while(!q.isEmpty()) { 
            int sz = q.size();
            for (int jj=0; jj<sz; jj++) { 
                int[] curr = q.poll();

                if (mat[curr[0]][curr[1]] == 0) return distance;

                for (int i=0; i<DIRS_LEN; i++) {
                    int nr = curr[0] + DIRS[i]; 
                    int nc = curr[1] + DIRS[i+1];

                    if (nr < 0 
                        || nr >= m
                        || nc < 0 
                        || nc >= c
                        || visited.contains(nc+nr*m)
                        ) continue; 

                    q.add(new int[]{nr, nc});
                    visited.add(nc + nr * m); 
                }

            }
            
            distance += 1; 
        }
            
        return distance; 
    }
}

