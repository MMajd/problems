/* 
 @link https://leetcode.com/problems/perfect-squares/
 @categories (dp/math/arrays) 

 Given an integer n, return the least number of perfect square numbers that sum to n.
 A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

Example 1:
    Input: n = 12
    Output: 3
    Explanation: 12 = 4 + 4 + 4.

Example 2:
    Input: n = 13
    Output: 2
    Explanation: 13 = 4 + 9.

Constraints:
    1 <= n <= 10^4
*/

/*
 * Below TLE bottom-up approach
 */

class Solution { 
    public int numSquares(int n) {
        int[] dp = new int [n + 1]; 
        Arrays.fill(dp, n+1);
        dp[0] = 0; 
        
        for(int curr=1; curr<=n; curr++) { 
            for (int s=1; s<=curr; s++) { 
                int square = s * s; 
                if (curr < square) break;
                dp[curr] = Math.min(dp[curr], 1+dp[curr-square]);
            }
        }
        
        return dp[n]; 
    }
}



// TLE 
class Solution2 {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, n+1);
        dp[0] = 0;
        dp[1] = 1;

        for (int i=2; i<=n; i++) {
            for (int j=i; j>0; j--) { 
                int sr = (int) Math.sqrt(j);
                if (j == sr * sr) {
                    dp[i] = Math.min(dp[i], dp[i-j] + 1);
                }
            }
        }

        return dp[n]; 
    }
}
