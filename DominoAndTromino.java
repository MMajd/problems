/*
 @link https://leetcode.com/problems/domino-and-tromino-tiling/
 @categories (dp) 

 You have two types of tiles: a 2 x 1 domino shape and a tromino shape. 
 You may rotate these shapes.
 - ** Domino 2 dots tile
 - ** Tromino 3 dots tile 
   *

Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.

In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.

Example 1:
    Input: n = 3
    Output: 5
    Explanation: The five different ways are show above.

Example 2:
    Input: n = 1
    Output: 1

Constraints:
    1 <= n <= 1000
*/

class Solution {
    public int numTilings(int n) {
        if (n <= 2) return n;
        
        int MOD = (int) 1e9+7;
        long dp0 = 1; 
        long dp1 = 1; 
        long dp2 = 2; 

        for (int i = 3; i <= n; ++i) {
            long temp = (2 * dp2 + dp0) % MOD; 
            dp0 = dp1; 
            dp1 = dp2; 
            dp2 = temp; 
        }

        return (int) dp2 % MOD;
    }
}

class Solution {
    private static final int MOD = (int)(1_000_000_007);

    public int numTilings(int n) {
        if (n <= 2) return n; 
        
        long[] dp = new long[n+1];
        dp[0] = 1; 
        dp[1] = 1; 
        dp[2] = 2; 

        for (int i=3; i<=n; i++) {
            dp[i] = (2*dp[i-1] + dp[i-3]) % MOD; 
        }

        return (int) dp[n]; 
    }
}

