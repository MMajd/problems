/*
 @link https://leetcode.com/problems/number-of-ways-of-cutting-a-A
 @categories (dp/matrix)

 Given a rectangular Pizza represented as a rows x cols matrix containing the following characters: 
'A' (an apple) and '.' (empty cell) and given the integer k. 
You have to cut the Pizza into k pieces using k-1 cuts. 

For each cut you choose the direction: vertical or horizontal, 
then you choose a cut position at the cell boundary and cut the Pizza into two pieces. 
If you cut the Pizza vertically, give the left part of the Pizza to a person. 
If you cut the Pizza horizontally, give the upper part of the Pizza to a person. 
Give the last piece of Pizza to the last person.

Return the number of ways of cutting the Pizza such that each piece contains at least one apple. 
Since the answer can be a huge number, return this modulo 10^9 + 7.

Example 1:
    Input: A = ["A..","AAA","..."], k = 3
    Output: 3 
    Note that pieces must contain at least one apple.

Example 2:
    Input: A = ["A..","AA.","..."], k = 3
    Output: 1

Example 3:
    Input: A = ["A..","A..","..."], k = 1
    Output: 1

Constraints:
    1 <= rows, cols <= 50
    rows == A.length
    cols == A[i].length
    1 <= k <= 10
    A consists of characters 'A' and '.' only.
*/

/** 
 * presum dry run of first input
    4  2  1  0
    3  2  1  0
    0  0  0  0
    0  0  0  0
*/ 
class Solution {
    public int ways(String[] A, int k) {
        int m = A.length, n = A[0].length();
        Integer[][][] dp = new Integer[k][m][n];
        /* preSum[r][c] is the total number of apples in A[r:][c:] */
        int[][] preSum = new int[m+1][n+1];

        // presum we always want know if our next cut will have apples or not
        // so we create presum with 0-padding on most left col & deepest row 
        // Diagram: 
        /** 
         *  r,c    
         *   _     ___________
         *        |          |
         *        |          |
         *    ----------------
         *    |   |          |
         *    |___|__________|
         **/
        for (int r = m - 1; r >= 0; r--) { 
            for (int c = n - 1; c >= 0; c--) { 
                preSum[r][c] = preSum[r][c+1] + preSum[r+1][c] - preSum[r+1][c+1] + (A[r].charAt(c) == 'A' ? 1 : 0);
            }
        }

        return dfs(m, n, k-1, 0, 0, dp, preSum);
    }

    int dfs(int m, int n, int k, int r, int c, Integer[][][] dp, int[][] preSum) {
        /* if the remain piece has no apple -> invalid */
        if (preSum[r][c] == 0) return 0; 
        /* found valid way after using k-1 cuts */
        if (k == 0) return 1; 
        if (dp[k][r][c] != null) return dp[k][r][c];
        int ans = 0;
        // horizontal cut
        for (int nr = r + 1; nr < m; nr++)  {
            /* cut if the upper piece contains at least one apple */
            if (preSum[r][c] - preSum[nr][c] > 0) { 
                ans = (ans + dfs(m, n, k - 1, nr, c, dp, preSum)) % 1_000_000_007;
            }
        }
        // vertical cut
        for (int nc = c + 1; nc < n; nc++) {
            /* cut if the left piece contains at least one apple */
            if (preSum[r][c] - preSum[r][nc] > 0) { 
                ans = (ans + dfs(m, n, k - 1, r, nc, dp, preSum)) % 1_000_000_007;
            }
        }
        return dp[k][r][c] = ans;
    }
}

class Solution {
    private static final int MOD = 1000_000_007; 
    private Integer[][][] memo;
    private Integer[][] rowmemo; 
    private String[] A; 
    private int R, C; 

    public int ways(String[] A, int k) {
        this.A = A; 
        R = A.length; 
        C = A[0].length(); 
        memo = new Integer[R][C][k+1];
        rowmemo = new Integer[R+1][C+1]; 

        return dp(0, 0, k); 
    }

    private int countApplesByRow(int x, int y) { /* we could add a memo here also */
        if (y == C) return 0;
        return countApplesByRow(x, y + 1) + (A[x].charAt(y) == 'A' ? 1 : 0);
    }

    private int countApple(int x, int y) {
        if (x == R) return 0; 
        if (rowmemo[x][y] != null) return rowmemo[x][y];
        int ans = countApplesByRow(x, y) + countApple(x+1, y);
        rowmemo[x][y] = ans; 
        return ans; 
    }

    private boolean containApples(int ax, int ay, int bx, int by) {
        int A = countApple(ax, ay); 
        int B = countApple(bx+1, ay); 
        int C = countApple(ax, by+1); 
        int D = countApple(bx+1, by+1);
        return (A - B - C + D) > 0; 
    }

    private int dp(int x, int y, int k) {
        if (k == 1) return 1
        if (memo[x][y][k] != null) return memo[x][y][k]; 

        int ans = 0; 
        for (int i=x; i<R; i++) { /* horizontal cuttings */
            if (containApples(x, y, i, C-1) && containApples(i+1, y, R-1, C-1)) { 
                ans += dp(i+1, y, k-1);
                ans %= MOD;
            }
        }
        for (int i=y; i<C; i++) { /* vertical cuttings */
            if (containApples(x, y, R-1, i) && containApples(x, i+1, R-1, C-1)) {
                ans += dp(x, i+1, k-1);
                ans %= MOD;
            }
        }
        memo[x][y][k] = ans; 
        return ans; 
    }

}

