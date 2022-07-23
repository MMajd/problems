/*
 @link https://leetcode.com/problems/number-of-squareful-arrays/

 An array is squareful if the sum of every pair of adjacent elements is a perfect square.

Given an integer array nums, return the number of permutations of nums that are squareful.

Two permutations perm1 and perm2 are different if there is some index i such that perm1[i] != perm2[i].

 

Example 1:
    Input: nums = [1,17,8]
    Output: 2
    Explanation: [1,8,17] and [17,8,1] are the valid permutations.

Example 2:
    Input: nums = [2,2,2]
    Output: 1
 

Constraints:
    1 <= nums.length <= 12
    0 <= nums[i] <= 10^9
*/

class Solution {
    int ans = 0;
    public int numSquarefulPerms(int[] nums) {
        solve(0, nums);
        return ans; 
    }
    
    private void solve(int idx, int[] nums) { 
        if (idx == nums.length) { 
            ans +=1; 
            return; 
        }
        
        Set<Integer> set = new HashSet<>(); 
        
        for (int i=idx; i<nums.length; i++) { 
            if (set.contains(nums[i])) continue; 
            
            swap(i, idx, nums);
            
            if (idx==0 || perfectSq(nums[idx],nums[idx-1])) { 
                solve(idx+1, nums);
            }
            
            swap(i, idx, nums);
            set.add(nums[i]);
        }
    }
    
    private void swap(int i, int j, int[] nums) { 
        int t = nums[i]; 
        nums[i] = nums[j]; 
        nums[j] = t; 
    }
    
    
    private boolean perfectSq(int a, int b) {
        double sqrt = Math.sqrt(a+b);
        return (sqrt - (int)sqrt) == 0;  
    }

}
