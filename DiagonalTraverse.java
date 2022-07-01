/** 
 * @link https://leetcode.com/problems/diagonal-traverse/
 */ 

class Solution {
    
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length; 
        int n = mat[0].length; 
        int[] ans = new int[m*n]; 
        int i, row, col; 
        boolean up = true; 
        i = row = col = 0; 
        
        while(i < ans.length) { 
            if (up) { 
                while(row >= 0 && col < n) { 
                    ans[i++] = mat[row--][col++]; 
                }
                
                if (col == n) { 
                    col -= 1; 
                    row += 2;
                    
                } else { 
                    row += 1; 
                }
            } 
            else { 
                while(col >= 0 && row < m) { 
                    ans[i++] = mat[row++][col--]; 
                }
                
                if (row == m) { 
                    row -= 1; 
                    col += 2;
                    
                } else { 
                    col += 1; 
                }
            }
            
            up = !up; 
        }
        
        return ans; 
    }
    

    
    public int[] map(int[][] mat) {
        int m = mat.length; 
        int n = mat[0].length; 
        
        Map<Integer, List<Integer>> map = new HashMap<>(); 
        List<Integer> ans = new ArrayList<>(m*n);
        
        for (int i=0; i<m; i++) { 
            for (int j=0; j<n; j++) { 
                map.computeIfAbsent(i+j, (k) -> new ArrayList<Integer>()).add(mat[i][j]);
            }
        }
        
        for (int i=0; i<m+n-1; i++) { 
            if (i % 2 == 1) { 
                ans.addAll(map.get(i)); 
            } else { 
                Collections.reverse(map.get(i));
                ans.addAll(map.get(i)); 
            }
        }
        
        return ans.stream().mapToInt(i -> i).toArray();
    }
}

















