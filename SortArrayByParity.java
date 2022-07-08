/*
 
@link https://leetcode.com/problems/sort-array-by-parity/

Given an integer array nums, move all the even integers at 
the beginning of the array followed by all the odd integers.
Return any array that satisfies this condition.

Example 1:

Input: nums = [3,1,2,4]
Output: [2,4,3,1]
Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.


*/ 

class Solution {
    public int[] sortArrayByParity(int[] arr) {
        int w = 0; 
        for (int i=0; i<arr.length; i++) { 
            if (arr[i]%2==0) { 
                swap(arr, i, w++); 
            }
        }
        
        return arr; 
    }
    
    private void swap(int[] arr, int i, int j) { 
        int temp = arr[i]; 
        arr[i] = arr[j]; 
        arr[j] = temp; 
    }
}

