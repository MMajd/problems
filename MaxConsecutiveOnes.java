/** 
 *
 * @link https://leetcode.com/problems/max-consecutive-ones 
 *
 * Same basis as the template used for removing element/duplicates
 */ 

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int sz = 0; 
        int k = 0; 
        
        for (int i=0; i<nums.length; i++) { 
            if (nums[i] != 1) { 
                sz = Math.max(i-k, sz);
                k = i + 1; 
            }
        }
        return Math.max(nums.length-k, sz);
    }
}
