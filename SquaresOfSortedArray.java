/*

   Given an integer array nums sorted in non-decreasing order, 
   return an array of the squares of each number sorted in non-decreasing order.
    

   Example 1:
    Input: nums = [-4,-1,0,3,10]
    Output: [0,1,9,16,100]
    Explanation: After squaring, the array becomes [16,1,0,9,100].
    After sorting, it becomes [0,1,9,16,100].

*/

class Solution {
    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length]; 
        int n = nums.length; 
        int i=0, j=n-1; 
        
        for (int k=n-1; k>=0; k--) { 
            if (Math.abs(nums[j]) > Math.abs(nums[i])) { 
                ans[k] = nums[j] * nums[j--]; 
            } 
            else { 
                ans[k] = nums[i] * nums[i++]; 
            }
        }
        
        return ans; 
    }
}
