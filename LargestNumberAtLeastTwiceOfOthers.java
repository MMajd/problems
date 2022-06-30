/**
 *
 */ 

class Solution {
    public int dominantIndex(int[] nums) { 
        int max, second; 
        max = second = Integer.MIN_VALUE; 
        
        int index = -1; 
        
        for (int i=0; i<nums.length; i++) { 
            if (nums[i] > max) { 
                second = max; 
                max = nums[i]; 
                index = i; 
            } 
            else if (nums[i] > second) { 
                second = nums[i]; 
            }
        }
        
        return (max>>1) >= second ? index : -1; 
    }
}

