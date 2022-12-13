/*
 @link https://leetcode.com/problems/minimum-falling-path-sum
 @categories (dp/memization/dfs)

 Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
A falling path starts at any element in the first row and chooses the element in the next row 
that is either directly below or diagonally left/right. 
Specifically, the next element from position (row, col) will be 
- (row + 1, col - 1), 
- (row + 1, col), or 
- (row + 1, col + 1).

Example 1:
    Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
    Output: 13
    Explanation: There are two falling paths with a minimum sum as shown.

Example 2:
    Input: matrix = [[-19,57],[-40,-5]]
    Output: -59
    Explanation: The falling path with a minimum sum is shown.
 

Constraints:
    n == matrix.length == matrix[i].length
    1 <= n <= 100
    -100 <= matrix[i][j] <= 100
*/


class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int m = matrix.length; 
        int n = matrix[0].length;

        int[][] dp = new int[m][n];
        int ans = Integer.MAX_VALUE; 

        for (int i=0; i<n; i++) { 
            dp[0][i] = matrix[0][i]; 
            ans = Math.min(dp[0][i], ans);
        }

        for (int i=1; i<m; i++) { 
            int curr = Integer.MAX_VALUE; 

            for (int j=0; j<n; j++) {
                dp[i][j] = Integer.MAX_VALUE; 

                if (i-1>= 0) dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
                if (i-1>= 0 && j+1<n) dp[i][j] = Math.min(dp[i][j], dp[i-1][j+1]);
                if (i-1>= 0 && j-1>=0) dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);

                dp[i][j] += matrix[i][j];
                curr = Math.min(curr, dp[i][j]);
            }

            ans = curr; 
        }

        return ans; 
    }
}
