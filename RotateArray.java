/**
 *
 * @link https://leetcode.com/problems/rotate-array
 *
    Given an array, rotate the array to the right by k steps, where k is non-negative.

    Example 1:

    Input: nums = [1,2,3,4,5,6,7], k = 3
    Output: [5,6,7,1,2,3,4]
    Explanation:
    rotate 1 steps to the right: [7,1,2,3,4,5,6]
    rotate 2 steps to the right: [6,7,1,2,3,4,5]
    rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 */ 

class Solution {
    public void rotate(int[] nums, int k) {
        if (k % nums.length == 0) return; 
        
        k = k % nums.length; 
        
        reverse(nums, 0, nums.length-1);
        reverse(nums, k, nums.length-1);
        reverse(nums, 0, k-1);
    }
    
    private void reverse(int[] arr, int i, int j) { 
        while(i<j) { 
            swap(arr, i++, j--);
        }
    }
    
    private void swap(int[] arr, int i, int j) { 
        int temp = arr[i]; 
        arr[i] = arr[j]; 
        arr[j] = temp; 
    }
}
