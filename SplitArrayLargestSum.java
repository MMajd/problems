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
/*        DP SOLUTIONS BELOW          */
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
