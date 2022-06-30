
/** 
 * @ https://leetcode.com/problems/find-pivot-index/
 */ 

class Solution {
    public int pivotIndex(int[] nums) {
        int len = nums.length; 
        
        if (len == 1) return 0; 
        
        int ans = -1;
        
        for (int i=1; i<len; i++) { 
            nums[i] += nums[i-1]; 
        }
        
        for (int i=0; i<len; i++) { 
            if (i > 0)  { 
                if (nums[i-1] == nums[len-1]-nums[i]) return i;
            } else { 
                if (0 == nums[len-1]-nums[i]) return i;
            }
        }
        
        return -1; 
    }
}
