
/** 
 *
 * @link https://leetcode.com/problems/target-sum
 *
 * There's no need to use memoization as our input range is small in all inputs,
 * thus using DFS is acceptable
 */

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, 0, target);
    }
    
    private int dfs(int[] nums, int i, int target) { 
        if (i >= nums.length) { 
            return target == 0 ? 1 : 0; 
        }
        
        int pos = dfs(nums, i+1, target+nums[i]);
        int neg = dfs(nums, i+1, target-nums[i]);
        
        return pos + neg;
    }
}

