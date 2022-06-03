/** 
 *
 * This is solution depends on Prefix sum method 
 *
 * @link: https://leetcode.com/problems/range-sum-query-2d-immutable
 *
 */


public class MatrixSumQuery { 
    public static void main(String [] args) { 

    }
}


private class NumMatrix {     
    private int [][] sum;     
        
    public NumMatrix(int[][] m) {     
      int r = m.length;     
      int c = m[0].length;     
      sum = new int[r+1][c+1];    
     
     
      for (int i=1; i<=r; i++) {     
        for (int j=1; j<=c; j++) {     
          sum[i][j] = sum[i-1][j] + sum[i][j-1]     
            + m[i-1][j-1] - sum[i-1][j-1];    
        }    
      }    
    }    
     
    public int sumRegion(int r1, int c1, int r2, int c2) {     
        int whole = sum[r2+1][c2+1];     
        int upper = sum[r1][c2+1];     
        int left = sum[r2+1][c1];     
        int corner = sum[r1][c1];     
        
        return whole - upper - left + corner; 
    }    
}    
