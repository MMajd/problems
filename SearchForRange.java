/*

 @link https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array

   Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
   If target is not found in the array, return [-1, -1].
   You must write an algorithm with O(log n) runtime complexity.

Example 1:
    Input: nums = [5,7,7,8,8,10], target = 8
    Output: [3,4]

Example 2:
    Input: nums = [5,7,7,8,8,10], target = 6
    Output: [-1,-1]

Example 3:
    Input: nums = [], target = 0
    Output: [-1,-1]

Constraints:
    0 <= nums.length <= 105
    -109 <= nums[i] <= 109
    nums is a non-decreasing array.
    -10^9 <= target <= 10^9

*/ 

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int N = nums.length; 
        int start=0, end=N-1; 
        
        while(start <= end) { 
            int mid = start + (end-start) / 2; 
            if (target == nums[mid]) { 
                int left = mid; 
                while(left>start && nums[mid]==nums[left-1]) left--; 
                
                int right = mid; 
                while(right<end && nums[mid]==nums[right+1]) right++; 
                
                return new int[]{left, right}; 
            }
            else if (target > nums[mid]) start = mid+1; 
            else end=mid-1; 
        }
        
        return (N==1&&nums[0]==target) 
            ? new int[]{0, 0} : new int[]{-1, -1};
    }
}
