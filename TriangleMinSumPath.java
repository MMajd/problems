
/** 
 *
 * @link https://leetcode.com/problems/triangle/
 *
 * I should read this to gain more insight from others 
 *
 */

class Solution {
    private List<List<Integer>> t; 
    private Integer[][] map;  
    
    private int solve(int i, int j) {
        if (i >= t.size()-1) { 
            map[i][j] = t.get(i).get(j);
            return map[i][j];
        }
        
        if (map[i][j] != null) return map[i][j]; 
        
        
        map[i+1][j] = solve(i+1, j);
        map[i+1][j+1] = solve(i+1, j+1);
         
        return  t.get(i).get(j) + Math.min(map[i+1][j], map[i+1][j+1]);
    }
    
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) return triangle.get(0).get(0);
        
        t = triangle; 
        
        int a = t.size(); 
        int b = t.get(a-1).size();
        
        map = new Integer[a][b];
        
        return solve(0, 0);
    }
}

