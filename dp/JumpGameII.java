/*
 @link https://leetcode.com/problems/jump-game-ii/
 @categories (dp/greedy) 

 You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].

Example 1:
    Input: nums = [2,3,1,1,4]
    Output: 2
    Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
    Input: nums = [2,3,0,1,4]
    Output: 2

Constraints:
    1 <= nums.length <= 10^4
    0 <= nums[i] <= 1000
*/

/** 
 * Gready: 
 *  form the subset of indices that I can reach now, 
 *  choose the one that will take me to the furthest point
 */
class Solution {
    public int jump(int[] A) {
        int n = A.length; 
        int ans = 0; 
        int start=0, end=0; // for starters we can reach 0-index 

        while (end < n-1) { // we adding one for reaching index 0, as compensation for not counting the last index
            int reach = 0; 
            for (int i=start; i<=end; i++) {
                reach = Math.max(reach, i + A[i]);
            }
            start = end+1; // go to the next possible slot
            end = reach; // update slot end
            ans += 1; // add 1 for steping to start point (end+1)  
        }
        return ans; 
    }
}

/** dp: look back */
class Solution {
    public int jump(int[] nums) {
        int n = nums.length; 
        int[] dp = new int[n]; 
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; 

        for (int i=1; i<n; i++) {
            for (int j=i-1; j>=0; j--) {
                if (nums[j] + j < i) continue; 
                dp[i] = Math.min(dp[j] + 1, dp[i]); 
            }
        }

        return dp[n-1]; 
    }
}
