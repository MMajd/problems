/**
 *
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 */ 
class Solution {
    public int maxProduct(int[] nums) {
        int max, min, ans; 
        ans = max = min = nums[0]; 
        
        for (int i=1; i<nums.length; i++) { 
            int val = nums[i]; 
            int temp = max * val;
            max = Math.max(Math.max(max*val, min*val), val);
            min = Math.min(Math.min(temp,min*val), val);
            ans = Math.max(max, ans);
        }
        
        return ans; 
    }
}
