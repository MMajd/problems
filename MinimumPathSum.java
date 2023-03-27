/*
 @link https://leetcode.com/problems/minimum-path-sum
 @categories (dp/arrays)

 Given a m x n grid filled with non-negative numbers, 
find a path from top left to bottom right, 
which minimizes the sum of all numbers along its path.

-Note: You can only move either down or right at any point in time.

Example 1:
    Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
    Output: 7
    Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Example 2:
    Input: grid = [[1,2,3],[4,5,6]]
    Output: 12

Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 200
    0 <= grid[i][j] <= 100

*/ 

import static java.lang.Math.*; 

public class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length; 
        int n = grid[0].length;
        int[] dp = new int[n + 1];
        for (int i=0; i<=n; i+=1) dp[i] = Integer.MAX_VALUE; 
        dp[1] = 0; 
        for (int i = 1; i <= m; i++) {
            int[] pre = dp; 
            dp = new int[n+1]; 
            dp[0] = Integer.MAX_VALUE;
            for (int j = 1; j <= n; j++) {
                dp[j] = min(dp[j-1], pre[j]) + grid[i-1][j-1];
            }
        }
        return dp[n]; 
    }
}

public class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m + 1][n + 1];

        for (int i=0; i<=m; i+=1) dp[i][0] = Integer.MAX_VALUE; 
        for (int i=0; i<=n; i+=1) dp[0][i] = Integer.MAX_VALUE; 

        dp[0][1] = 0; 

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i-1][j-1];
            }
        }
        return dp[m][n];
    }
}
