/**
 *
 * @link https://leetcode.com/problems/rotate-array
 *
 */ 

class Solution {
    public void rotate(int[] nums, int k) {
        if (k % nums.length == 0 || k == 0) return; 
        
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
