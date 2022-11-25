/*
 @link https://leetcode.com/problems/sum-of-subarray-minimums
 @categories (dp/monotonic-stack/)

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

    public int sumSubarrayMins(int[] arr) {
        int[] stack = new int[arr.length + 1];
        int idxStack = 0;

        int[] dp = new int[arr.length];
        dp[0] = arr[0];

        long res = dp[0];
        for (int i = 1; i < arr.length; i++) {
            while (idxStack >= 0 && arr[stack[idxStack]] >= arr[i]) idxStack--; //pop
            dp[i] = idxStack < 0 ? 
                        arr[i] * (i + 1) : 
                        dp[stack[idxStack]] + arr[i] * (i - stack[idxStack]);
            res += dp[i];
            stack[++idxStack] = i; //push
        }

        return (int) (res % MOD);
    }
}
