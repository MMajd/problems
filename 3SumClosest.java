/* 
 @link https://leetcode.com/problems/3sum-closest
 @categroies (sorting/two-pointers) 

 Given an integer array nums of length n and an integer target, 
 find three integers in nums such that the sum is closest to target.
Return the sum of the three integers.
You may assume that each input would have exactly one solution.
 
Example 1:
    Input: nums = [-1,2,1,-4], target = 1
    Output: 2
    Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

Example 2:
    Input: nums = [0,0,0], target = 1
    Output: 0
    Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).

Constraints:
    3 <= nums.length <= 1000
    -1000 <= nums[i] <= 1000
    -10^4 <= target <= 10^4

 */

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length <= 3) return Arrays.stream(nums).sum();
        
        Arrays.sort(nums);
        
        // a good start
        int ans = nums[0] + nums[1] + nums[nums.length-1]; 

        int sum = 0;  
        
        for (int i=0; i<nums.length-2; i++) {
            sum = 0;

            int j = i + 1; 
            int k = nums.length - 1; 
            
            while(j < k) { 
                sum = nums[i] + nums[j] + nums[k];
                
                if (target > sum) { 
                    j += 1;
                    
                    // search for new nums[j] value to get a 
                    // different sum
                    while(j < k && nums[j] == nums[j-1]) j++;
                }
                else if (target < sum) { 
                    k -= 1;

                    // search for new nums[k] value to get a 
                    // different sum
                    while(k > j && nums[k] == nums[k+1]) k--;
                }
                // if found target == sum; then return early 
                else { 
                    return target; 
                }
                
                // choose the value the smallest error
                // if ans has a bigger error than the curr sum 
                // then update the ans to equal the curr sum; 
                if (Math.abs(ans-target) > Math.abs(sum-target)) { 
                    ans = sum; 
                }
            }
        }
        
        return ans; 
    }
}
