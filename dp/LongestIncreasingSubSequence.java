
/* 
 @link leetcode.com/problems/longest-increasing-subsequence
 @categories (dp/binary-search/patience-sort)

 Given an integer array nums, return the length of the longest strictly increasing subsequence.
A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].


Example 1:
    Input: nums = [10,9,2,5,3,7,101,18]
    Output: 4
    Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
    Input: nums = [0,1,0,3,2,3]
    Output: 4

Example 3:
    Input: nums = [7,7,7,7,7,7,7]
    Output: 1
 

Constraints:
    1 <= nums.length <= 2500
    -10^4 <= nums[i] <= 10^4

Follow up: 
    Can you come up with an algorithm that runs in O(n log(n)) time complexity?
*/



class Solution1 {
    /** 
     * Patience sort with TreeSet 
     * https://www.youtube.com/watch?v=K9M6g7BiBX4
     *
     * TreeSet keeps the natural order of elements
     */ 

    public int lengthOfLIS(int nums) { 
        int len = nums.length; 
        if (len <= 1) return 1; 

        TreeSet<Integer> set = new TreeSet<>();

        for (int i=0; i<len; i++) { 
            Integer x = set.ceiling(nums[i]); 

            if (x != null) { 
                set.remove(x)
            }

            set.add(nums[i]); 
        }

        return set.size();
    }
}


/**
 * Binary search solution
 */
class Solution2 {
    public int lengthOfLIS(int[] nums) {
        int[] longestSequence = new int[nums.length];
        longestSequence[0] = nums[0];
        int pointer = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > longestSequence[pointer]) {
                pointer++;
                longestSequence[pointer] = nums[i];
            } else {
                int index = Arrays.binarySearch(longestSequence, 0, pointer + 1, nums[i]);
                if (index < 0) {
                    longestSequence[(index * -1) - 1] = nums[i];
                }
                
            }
        }
        
        return pointer + 1;
    }
}


/**
 * DP solution
 */

class Solution3 {
    public int lengthOfLIS1(int[] nums) {
        int n = nums.length; 
        if (n == 1) return 1; 
        
        int[][] dp = new int[n][2]; 

        dp[0][0] = 1; 
        int max = Integer.MIN_VALUE; 
        
        
        for (int i=1; i<n; i++) { 
            for (int j=i-1; j>=0; --j) { 
                if (nums[i] > nums[j]) { 
                    dp[i][0] = Math.max(dp[j][0]+1, dp[i][0]);
                    dp[i][1] = (dp[i][1] == dp[j][0] + 1) ? j : dp[i][1]; 
                } 
                else { 
                    dp[i][0] = Math.max(dp[i][0], 1);
                    dp[i][1] = (dp[i][1] == 0) ? i : dp[i][1]; 
                }
            }
            
            max = Math.max(dp[i][0], max);
        }
        
        return max; 
    }

    public int lengthOfLIS2(int[] nums) {
        int len = nums.length; 
        if (len <= 1) return 1;
        
        int max = 0;
        int[] dp = new int[len]; 
        int[] traceback = new int[len]; 
        
        Arrays.fill(dp, 1);
        
        for (int i=1; i<len; i++) { 
            for (int j=0; j<i; j++) { 
                if (nums[i] > nums[j]) { 
                    dp[i] =  Math.max(dp[j] + 1, dp[i]);                    
                    traceback[i] = (dp[i] == dp[j] + 1) ? j : traceback[i]; 
                }
            }
            
            max = Math.max(dp[i], max);
        }
        
        return max; 
    }

}
