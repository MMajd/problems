/*
  @link https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
  @categories (dp) 
  
  You have n dice and each die has k faces numbered from 1 to k.

Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.

Example 1:
    Input: n = 1, k = 6, target = 3
    Output: 1
    Explanation: You throw one die with 6 faces.
    There is only one way to get a sum of 3.

Example 2:
    Input: n = 2, k = 6, target = 7
    Output: 6
    Explanation: You throw two dice, each with 6 faces.
    There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.

Example 3:
    Input: n = 30, k = 30, target = 500
    Output: 222616187
    Explanation: The answer must be returned modulo 109 + 7.

Constraints:
    1 <= n, k <= 30
    1 <= target <= 1000
 */



class Solution {
    private static final int M = 1_000_000_007; 
    
    public int numRollsToTarget(int n, int k, int target) {
        Integer[][] memo = new Integer[n+1][target+1];
        return dp(n, k, target, memo);
    }
    
    public int dp(int n, int k, int target, Integer[][] memo) { 
        if (n == 0 || target < 0) { 
            return target == 0 ? 1 : 0; 
        }
        
        if (memo[n][target] != null) return memo[n][target]; 
        
        int res = 0; 
        
        for (int i=1; i<=k; i++) { 
            res = (res + dp(n-1, k, target-i, memo)) % M;
        }
        
        memo[n][target] = res; 
        
        return res; 
    }
    
    
    public int numRollsToTarget1(int n, int k, int target) {
        if(target<n 
           || (target/k)>=n 
           && (target%k)!=0) return 0; 
        
        int[][] dp=new int[n+1][target+1];
        
        dp[0][0]=1;
        
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=target;j++) {
                for(int w=1;w<=k&&w<=j;w++) {
                    dp[i][j]=(dp[i][j]+dp[i-1][j-w]) % M;
                }
            }
        }
        return dp[n][target];
    }
    
    public int numRollsToTarget2(int d, int f, int target) {
        int mod = 1000000007;
        int[] dp1 = new int[target + 1];
        int[] dp2 = new int[target + 1];
        dp1[0] = 1;
        for(int i = 1; i <= d; i++) {
            int prev= dp1[0];
            for(int j = 1; j <= target; j++) {
                dp2[j] = prev;
                prev= (prev+ dp1[j]) % mod;
                if(j >= f) prev= (prev- dp1[j - f] + mod) % mod;
            }
            int[] temp = dp1;
            dp1 = dp2;
            dp2 = temp;
            dp2[0] = 0;
        }
        return dp1[target];
    }    
}
