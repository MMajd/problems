/*

   @link https://leetcode.com/problems/word-search/

   Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

 

Example 1:
    Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
    Output: true

Example 2:
    Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
    Output: true

Example 3:
    Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
    Output: false
 

Constraints:
    m == board.length
    n = board[i].length
    1 <= m, n <= 6
    1 <= word.length <= 15
    board and word consists of only lowercase and uppercase English letters.
 

Follow up: Could you use search pruning to make your solution faster with a larger board?



Time Complexity: 
    Lower bound: O(n^2)
    Upper bound: O(n^2 * 4^k) , where k = word length; 
*/

class Solution {
    private final static int[] DIRS = {1, 0, -1, 0, 1};
    
    int m, n; 
    
    public boolean exist(char[][] board, String word) {
        m = board.length; 
        n = board[0].length; 
        
        char[] w = word.toCharArray();
        
        // this reduces the time complexity radically  
        // n^2 
        if (!isBoardGood(board, w)) { 
            return false; 
        }
        
        boolean[][] v = new boolean[m][n];
        char[] s = new char[w.length]; 


        // Upper bound: O(n*m * 4^k) = O(n^2 * 4^k); 
        for (int i=0; i<m; i++) { 
            for (int j=0; j<n; j++) { 
                // guard stack call by making sure that 
                // there's at least one char match before 
                // calling the method
                if (w[0] == board[i][j] 
                    && backtrack(i, j, board, w, s, 0, v)) { 
                    return true; 
                }
            }
        }
        
        return false; 
    }
    
    private boolean backtrack(int i, int j, char[][] b, 
            char[] w, char[] s, int idx, boolean[][] v) { 
         
        if (idx == w.length) { 
            return true; 
        }
        
        if (i<0 
            || i>=m
            || j<0 
            || j>=n
            || v[i][j] 
            || b[i][j] != w[idx]
           ) return false; 
                
        v[i][j] = true; 
        s[idx] = b[i][j];
        
        for (int k=0; k<4; k++) { 
            int x = DIRS[k]+i, y = DIRS[k+1]+j; 
            if (backtrack(x, y, b, w, s, idx+1, v)) { 
                return true; 
            }
        }
        
        v[i][j] = false; 
        return false;
    }
    
    private boolean isBoardGood(char[][] b, char[] w) { 
        int[] wfreq = new int[26];
        int[] bfreq = new int[26];
        
        for (char c : w) { 
            wfreq[Character.toUpperCase(c)-'A'] += 1;
        }
        
        for(int i=0; i<m; i++) { 
            for(int j=0; j<n; j++) { 
                bfreq[Character.toUpperCase(b[i][j])-'A'] += 1; 
            }
        }
        
        for (char c : w) { 
            c = Character.toUpperCase(c);
            if (bfreq[c-'A'] < wfreq[c-'A']) return false; 
        }
        
        return true; 
    }
}
