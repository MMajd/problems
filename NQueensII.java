/** 
 * @link: 
 */ 


public class NQueensII { 

    public static void main(String []args) {}
}

class Solution {
    private int cols; 
    private int diags; 
    private int antiDiags; 
    private int result; 
    private int N; 
    
    private void solve (int r) { 
        if (r == N) { 
            result += 1; 
            return; 
        }
        
        for (int c=0; c<N; c++) { 
            int currDiag = r-c; 
            int currAntiDiag = r+c;
            
            if ((cols & (1 << c)) != 0 
                || (diags & (1 << currDiag)) != 0 
                || (antiDiags & (1 << currAntiDiag)) != 0) continue;

            
            cols |= (1 << c);
            diags |= (1 << currDiag);
            antiDiags |= (1 << currAntiDiag);

            
            solve(r + 1); 
            
            cols ^= 1 << c; 
            diags ^= 1 << currDiag;
            antiDiags ^= 1 << currAntiDiag;
        }
    }
    
    public int totalNQueens(int n) {
        N = n; 
        solve(0); 
        return result; 
    }
}
