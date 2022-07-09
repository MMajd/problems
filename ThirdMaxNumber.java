/** 
 
  @link https://leetcode.com/problems/third-maximum-number/
  
Given an integer array nums, return the third distinct maximum number in this array. If the third maximum does not exist, return the maximum number.

Example 3:
    Input: nums = [2,2,3,1]
    Output: 1
    Explanation:
    The first distinct maximum is 3.
    The second distinct maximum is 2 (both 2's are counted together since they have the same value).
    The third distinct maximum is 1.


*/ 


/* O(n) solution, no extra space */

class Solution {
    public int thirdMax(int[] nums) {
        long first=Long.MIN_VALUE, 
             second=Long.MIN_VALUE, 
             third=Long.MIN_VALUE;
        
        for (int x : nums) {
            if (x>first) { 
                third = second; 
                second = first; 
                first = x;
            }
            else if (x!=first && x>second) { 
                third = second; 
                second = x; 
            }
            else if (x!=first && x!=second && x>third) { 
                third = x; 
            }
        }
        
        return (int)(third != Long.MIN_VALUE ? third : first); 
    }
}


/** nlogn, set based solution, O(n) extra space */ 

class Solution {
    public int thirdMax(int[] nums) {
        int first=Integer.MIN_VALUE, 
            second=Integer.MIN_VALUE, 
            third=Integer.MIN_VALUE;
        
        Set<Integer> s = new HashSet<>(); 
        for (int i=0; i<nums.length; i++) {
            if (s.contains(nums[i])) continue; 
            
            if (nums[i]>first) { 
                third = second; 
                second = first; 
                first = nums[i];
            }
            else if (nums[i]>second) { 
                third = second; 
                second = nums[i]; 
            }
            else if (nums[i]>third) { 
                third = nums[i]; 
            }
            s.add(nums[i]);
        }
        
        return s.size() >= 3 ? third : first; 
    }
 

}
