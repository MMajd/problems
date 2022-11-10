/*

 @link https://leetcode.com/problems/split-array-largest-sum/

 Given an array nums which consists of non-negative integers and an integer m, 
 you can split the array into m non-empty continuous subarrays.

Write an algorithm to minimize the largest sum among these m subarrays.

 
Example 1:
    Input: nums = [7,2,5,10,8], m = 2
    Output: 18
    Explanation:
    There are four ways to split nums into two subarrays.
    The best way is to split it into [7,2,5] and [10,8],
        where the largest sum among the two subarrays is only 18.

Example 2:
    Input: nums = [1,2,3,4,5], m = 2
    Output: 9

Example 3:
    Input: nums = [1,4,4], m = 3
    Output: 4

Constraints:
    1 <= nums.length <= 1000
    0 <= nums[i] <= 106
    1 <= m <= min(50, nums.length)
*/

/**************************************/
/*      2. DP SOLUTIONS BELOW         */
/**************************************/

class Solution {
    public int splitArray(int[] A, int m) {
        int left= 0, right=0;
        
        for (int a : A) { 
            left = Math.max(a, left);
            right += a; 
        }
        
        while(left < right) { 
            int mid = left+(right-left)/2; 
            int cnt=1, sum=0; 
            
            for (int a : A) { 
                if (a + sum > mid) { 
                    cnt += 1; 
                    sum = 0; 
                }
                sum += a; 
            }
            
            if (cnt > m) left = mid+1;
            else right = mid;
        }
        
        return left; 
    }
}

class Solution2 {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        
        int[] presum = new int[n+1];
        for (int i=0; i<n; i++) {
            presum[i+1] = presum[i] + nums[i];
        }
        
        int[][] dp = new int[n+1][m+1];
        Arrays.setAll(dp, i -> { 
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            return dp[i]; 
        });
        
        dp[0][0] = 0; 

        for (int i=1; i<=n; i++) { 
            for (int j=1; j<=Math.min(i, m); j++) { 
                for (int k=0; k<i; k++) { 
                    dp[i][j] = Math.min(Math.max(dp[k][j-1], presum[i]-presum[k]), dp[i][j]);
                }
            }
        }
        
        return dp[n][m]; 
    }
}



class Solution3 {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        
        int[] presum = new int[n + 1];
        for (int i=0; i<n; i++) {
            presum[i+1] = presum[i] + nums[i];
        }
        
        // dp[i][j]: result for partition nums[0:i] into j part (for i in [0, n-1])
        // notice that partition into 0 part is not defined, namely dp[i][0] is never used for all i in [0,n-1]
        int[][] dp = new int[n][m+1];
        
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        
        for (int i=0; i<n; i++) {
            dp[i][1] = presum[i+1];
        }
        
        for (int i=0; i<n; i++) {
            for (int j=2; j<=Math.min(m, i+1); j++) {
                // [0,i] into j part  <==> [0,k] into j-1 part  &  [k+1, i] into 1 part  for k in [0,i-1]
                for (int k=0; k<i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j-1], presum[i+1] - presum[k+1]));
                }
            }
        }
     
        return dp[n-1][m];        
    }
}
