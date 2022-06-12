
/** 
 *
 * @link https://leetcode.com/problems/maximum-erasure-value/  
 *
 * Sliding window
 *
 * Statement: 
 *
 * You are given an array of positive integers nums and want to erase a subarray containing unique elements. 
 * The score you get by erasing the subarray is equal to the sum of its elements.
 *
 * Return the maximum score you can get by erasing exactly one subarray.
 *
 * An array b is called to be a subarray of a if it forms a contiguous subsequence of a, 
 * that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).
 *
 * Ex: 
 * Input: nums = [4,2,4,5,6]
 * Output: 17
 * Explanation: The optimal subarray here is [2,4,5,6].
 *
 * Input: nums = [5,2,1,2,5,2,1,2,5]
 * Output: 8
 * Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
 *
 *
 */


class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int ans = 0; 
        int[] sum = new int[nums.length + 1];
        
        Map<Integer, Integer> map = new HashMap<>();
            
        for (int i=1; i<sum.length; i++) { 
            sum[i] = sum[i-1] + nums[i-1];
        }
        
        for (int i=0, j=0; i<nums.length; i++) { 
            if (map.containsKey(nums[i])) { 
                j = Math.max(j, map.get(nums[i]));
            }
            
            ans = Math.max(ans, sum[i+1] - sum[j]);
            map.put(nums[i], i+1);
        }
        
        return ans; 
    }
}
