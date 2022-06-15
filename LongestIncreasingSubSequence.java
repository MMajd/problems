
/** 
 * @link leetcode.com/problems/longest-increasing-subsequence
 */

class LongestIncreasingSubSequence {
    /** 
     * Patience sort with TreeSet 
     * https://www.youtube.com/watch?v=K9M6g7BiBX4
     *
     * TreeSet keeps the natural order of elements
     */ 

    public int lengthOfLISPatience(int nums) { 
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

    public int lengthOfLIS(int[] nums) {
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
            
            if (max < dp[i]) { 
                max = dp[i]; 
            }
        }
        
        return max; 
    }
}


