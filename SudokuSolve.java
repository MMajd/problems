/* 
@link https://leetcode.com/problems/sudoku-solver/

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.

 

Example 1:
Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is shown below:

 

Constraints:
    board.length == 9
    board[i].length == 9
    board[i][j] is a digit or '.'.
    It is guaranteed that the input board has only one solution.


*/

class Solution {
    public void solveSudoku(char[][] grid) {
        solve(0, 0, grid);
    }
    
    private void print(char[][] grid) { 
        for (int i=0; i<9; i++) { 
            for (int j=0; j<9; j++) { 
                if (j == 0) System.out.print("| ");
                System.out.print(grid[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private boolean solve(int r, int c, char[][] grid) { 
        if (r >= 9 && c>=0) return true; 
        
        int row = r + (c+1)/9; 
        int col = (c+1)%9; 
        
        if (grid[r][c] != '.') { 
            return solve(row, col, grid);
        }
        
        for (int k=1; k<=9; k++) { 
            if (isValid(r, c, k, grid)) { 
                grid[r][c] = (char)(k+'0'); 
                if (solve(row, col, grid)) { 
                    return true; 
                }
            }
        }
            
        grid[r][c] = '.'; 
        return false; 
    }
    
    private boolean isValid(int r, int c, int val, char[][] grid) { 
        return isColValid(c, val, grid) 
            && isRowValid(r, val, grid)
            && isBoxValid(r, c, val, grid); 
    }
    
    private boolean isColValid(int c, int val, char[][] grid) { 
        for (int row=0; row<9; row++) { 
            if (grid[row][c]-'0' == val) 
                return false; 
        }
        
        return true; 
    }
    
    private boolean isRowValid(int r, int val, char[][] grid) { 
        for (int col=0; col<9; col++) { 
            if (grid[r][col]-'0' == val) 
                return false; 
        }
        
        return true; 
    }
    
    private boolean isBoxValid(int r, int c, int val, char[][] grid) { 
        r = (r/3) * 3; 
        c = (c/3) * 3; 
        
        for (int row=0; row<3; row++) { 
            for (int col=0; col<3; col++) { 
                if (grid[row+r][col+c]-'0' == val) 
                    return false; 
            }
        }
        
        return true; 
    }
}

