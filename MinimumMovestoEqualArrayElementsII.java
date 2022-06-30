/** 
 * @link https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/
 */ 


class Solution {
    public int minMoves2(int[] nums) {
        if (nums.length <= 1) return 0; 
        Arrays.sort(nums);
        
        int len = nums.length;
        int median = nums[(len-1)/2]; 
        
        if (nums.length % 2 == 0) { 
            median += nums[len/2]; 
            median /= 2; 
        } 
        
        int ans = 0; 
        
        for (int i : nums) { 
            ans += Math.abs(median - i);
        }
        
        return ans; 
    }
}
