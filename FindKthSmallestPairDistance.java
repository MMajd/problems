/*
 
 @link https://leetcode.com/problems/find-k-th-smallest-pair-distance/
  
  
 The distance of a pair of integers a and b is defined as the absolute difference between a and b.

Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.

 

Example 1:
    Input: nums = [1,3,1], k = 1
    Output: 0
    Explanation: Here are all the pairs:
    (1,3) -> 2
    (1,1) -> 0
    (3,1) -> 2
    Then the 1st smallest distance pair is (1,1), and its distance is 0.

Example 2:
    Input: nums = [1,1,1], k = 2
    Output: 0

Example 3:
Input: nums = [1,6,1], k = 3
Output: 5
 
Constraints:
    n == nums.length
    2 <= n <= 10^4
    0 <= nums[i] <= 10^6
    1 <= k <= n * (n - 1) / 2

*/

class Solution {
    public int smallestDistancePair(int[] arr, int k) {
        Arrays.sort(arr);
        
        int n = arr.length; 
        int low = 0; 
        int high = arr[n-1]-arr[0]; 
        
        while (low < high) {
            int mid = low + (high-low)/2; 
            
            int count=0, left=0; 
            for (int right=0; right<n; ++right) { 
                while(arr[right]-arr[left] > mid) left++; 
                
                count += right-left; 
            }
            
            if (count >= k) high = mid; 
            else low = mid+1; 
        }
        
        return low; 
    }
}
