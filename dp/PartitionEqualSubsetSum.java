/*
  
 @link https://leetcode.com/problems/partition-equal-subset-sum/

 Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.


Example 1:
    Input: nums = [1,5,11,5]
    Output: true
    Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
    Input: nums = [1,2,3,5]
    Output: false
    Explanation: The array cannot be partitioned into equal sum subsets.
 
Constraints:
    1 <= nums.length <= 200
    1 <= nums[i] <= 100
*/




class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length; 
        int sum = 0; 
        
        for (int i : nums) sum += i; 
        
        if (sum % 2 != 0) return false; 
        
        int half = sum / 2;
        
        int[][] dp = new int[n][half+1];
        
        for (int i=0; i<=half; i++) { 
            if (i < nums[0]) continue; 
            dp[0][i] = nums[0];
        }
        
        for (int i=1; i<n; i++) { 
            for (int j=1; j<=half; j++) { 
                if (nums[i] > j) { 
                    dp[i][j] = dp[i-1][j]; 
                }
                else { 
                    dp[i][j] = Math.max(nums[i] + dp[i-1][j-nums[i]], dp[i-1][j]);
                }
            }
        }
        
        return half == dp[n-1][half];
    }
}
