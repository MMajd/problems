/*
  
@link https://leetcode.com/problems/single-number/

 Given a non-empty array of integers nums, 
every element appears twice except for one. 
Find that single one. You must implement a solution with a linear runtime 
complexity and use only constant extra space.

Example 1:
    Input: nums = [2,2,1]
    Output: 1

Example 2:
    Input: nums = [4,1,2,1,2]
    Output: 4

Example 3:
    Input: nums = [1]
    Output: 1
 
Constraints:
    1 <= nums.length <= 3 * 10^4
    -3 * 10^4 <= nums[i] <= 3 * 10^4
    Each element in the array appears twice except for one element which appears only once.

*/


class Solution {
    public int singleNumber(int[] nums) {
        if (nums.length == 1) return nums[0]; 
        
        return usingXOR(nums);
        // return usingSet(nums);
    }
    
    private int usingXOR(int[] arr) { 
        int one = 0;
        
        for (int i : arr) one ^= i; 
        
        return one; 
    }
    
    private int usingSet(int[] arr) { 
        Set<Integer> set = new HashSet<>();
        
        for (int i : arr) { 
            if (set.contains(i)) set.remove(i); 
            else set.add(i); 
        }
        
        return set.toArray(new Integer[0])[0].intValue();
    }

}
