/**
 @link https://leetcode.com/problems/maximum-sum-circular-subarray
 @categories (dp/d&c/kadens)

 Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.

A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].

A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.

Example 1:
    Input: nums = [1,-2,3,-2]
    Output: 3
    Explanation: Subarray [3] has maximum sum 3.

Example 2:
    Input: nums = [5,-3,5]
    Output: 10
    Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.

Example 3:
    Input: nums = [-3,-2,-3]
    Output: -2
    Explanation: Subarray [-2] has maximum sum -2.

Constraints:
    n == nums.length
    1 <= n <= 3 * 10^4
    -3 * 10^4 <= nums[i] <= 3 * 10^4
*/

import static java.lang.Math.*; 

class Solution {
    int normalMax(int arr[]){
        int maxi=arr[0],res=arr[0];
        for(int i=1;i<arr.length;i++){
            maxi= max(maxi+arr[i],arr[i]);
            res= max(res,maxi);
        }
        return res;
    }
    public int maxSubarraySumCircular(int[] arr) {
        int normal_max=normalMax(arr);
        if (normal_max<0){
            return normal_max;
        }
        int arrSum=0;
        for(int i=0;i<arr.length;i++){
            arrSum+=arr[i];
            arr[i]*=-1;
        }
        int circular_max=arrSum+normalMax(arr);
        return max(normal_max,circular_max);
    }
}


/** 
 * This solution dependes on a DP solution that finds the max/min subarray sum
 *
 * DP[i] = max{A[i] + MaxSumTillIMins1, A[i]} 
 * Can be re-written as 
 * DP[i] = A[i]  + max{ DP[i], 0 }
 * ANS = max{DP[i], ANS}
 *
 *
 * Same operation is done to get the min sum subarray
 * if the array is all negative then max sum is the max element 
 *
 * else we compare the regular max sum subarray found, 
 * and circular max sum subarray by substracting min sum subarray from total sum 
 *
 */ 
class Solution {
    public int maxSubarraySumCircular(int[] A) {
        int arrSum = 0; 
        int currMax = 0; 
        int currMin = 0; 
        int maxSum = Integer.MIN_VALUE; 
        int minSum = Integer.MAX_VALUE;  
        
        for (int n : A) {
            arrSum += n; 
            
            currMax = n + max(currMax, 0); 
            maxSum = max(maxSum, currMax); 
            
            currMin = n + min(currMin, 0); 
            minSum = min(currMin, minSum); 
        }
            
        if (arrSum == minSum) return maxSum; 
        return max(maxSum, arrSum-minSum); 
    }
}
