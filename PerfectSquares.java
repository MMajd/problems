/** 
 *
 * @link https://leetcode.com/problems/perfect-squares/
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
