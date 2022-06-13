/** 
 * @link https://leetcode.com/problems/3sum-closest
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
