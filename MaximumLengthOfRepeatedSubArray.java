/*
 @link https://leetcode.com/problems/maximum-length-of-repeated-subarray
 
 @categories (dp/arrays/binary-search/rolling-hash)

 Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.

Example 1:
    Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
    Output: 3
    Explanation: The repeated subarray with maximum length is [3,2,1].

Example 2:
    Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
    Output: 5

Constraints:
    1 <= nums1.length, nums2.length <= 1000
    0 <= nums1[i], nums2[i] <= 100
 */


class Solution {
    public int findLength(int[] A, int[] B) {
        int[] dp = new int[B.length + 1]; 
        int ans = 0; 
        
        for (int i=A.length-1; i>=0; --i) { 
            int[] prev = dp.clone();
            for (int j=B.length-1; j>=0; --j) { 
                if (A[i] == B[j]) { 
                    dp[j] = prev[j+1] + 1; 
                    ans = Math.max(ans, dp[j]);
                }
                else { 
                    dp[j] = 0; 
                }
            }
        }
        
        return ans; 
    }
}

class Solution {
    public int findLength(int[] A, int[] B) {
        int[][] dp = new int[A.length+1][B.length+1]; 
       int ans = 0; 

        for (int i=A.length-1; i>=0; --i) { 
            for (int j=B.length-1; j>=0; --j) { 
                if (A[i] == B[j]) { 
                    dp[i][j] = dp[i+1][j+1]+1; 
                    ans = Math.max(dp[i][j], ans);
                }
            }
        }
        
        return ans; 
    }
}
