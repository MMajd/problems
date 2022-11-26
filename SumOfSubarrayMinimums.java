/*
 @link https://leetcode.com/problems/sum-of-subarray-minimums
 @categories (dp/monotonic-stack/)

 @help https://youtu.be/fDeZNRNmm1Y

 Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.

Example 1:
    Input: arr = [3,1,2,4]
    Output: 17
    Explanation: 
    Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
    Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
    Sum is 17.

Example 2:
    Input: arr = [11,81,94,43,3]
    Output: 444

Constraints:
    1 <= arr.length <= 3 * 10^4
    1 <= arr[i] <= 3 * 10^4
*/

class Solution {
    final int MOD = 1000_000_007; 

    public int sumSubarrayMins(int[] A) {
        int N = A.length;
        int top = 0; 
        int[] stack = new int[N]; 

        int[] dp = new int[N]; 
        dp[0] = A[0];

        long ans = dp[0]; 

        for (int i=1; i<N; i++) {
            while(top >= 0 && A[stack[top]] >= A[i]) top--; // poping

            if (top >= 0) dp[i] = dp[stack[top]] + A[i] * (i-stack[top]); 
            else dp[i] = A[i] * (i+1); 

            ans = (ans + dp[i]) % MOD; 
            stack[++top] = i; 
        }
        
        return (int)(ans % MOD); 
    }
}
