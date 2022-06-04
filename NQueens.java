public class NQueens { 
    public static void main(String [] args) {

    }
}

private class Solution {
    private int N;
    private char[][] board; 
    private List<List<String>> result; 
    private int diags; 
    private int antiDiags;
    private int cols; 
    
    private void solver(int row) 
    {
        if (N == row) {
            result.add(toBoard(board));
            return;
        }
        
        for (int col=0; col<N; col++) {
            int currDiag = row-col+N;
            int currAntiDiag = row+col;
            
            if ((cols & (1 << col)) != 0 
                || (diags & (1 << currDiag)) != 0 
                || (antiDiags & (1 << currAntiDiag)) != 0) continue;
            
            cols |= (1 << col);
            diags |= (1 << currDiag);
            antiDiags |= (1 << currAntiDiag);
            board[row][col] = 'Q';
            
            solver(row + 1);
            
            cols ^= (1 << col);
            diags ^= (1 << currDiag);
            antiDiags ^= (1 << currAntiDiag);
            board[row][col] = '.';
        }
    }
    
    public List<List<String>> solveNQueens(int n) { 
        this.N = n; 
        result = new ArrayList<>(); 
        board = new char[N][N];
        
        for (char[] row: board) Arrays.fill(row, '.');
        
        solver(0); 
        return result;
    }
    
    private List<String> toBoard(char[][] board) {
        List<String> newBoard = new ArrayList<>();
        for (char[] row: board) newBoard.add(new String(row));
        return newBoard;
    }
}