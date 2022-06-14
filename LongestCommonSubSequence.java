
/** 
 *
 *
 * @link https://leetcode.com/problems/longest-common-subsequence
 *
 */


class LongestCommonSubSequence {
    public int longestCommonSubsequence(String text1, String text2) {
        
        return solve(text1, text2);
        
    }
    
    public int solve(String w1, String w2) { 
        int len1 = w1.length(); 
        int len2 = w2.length(); 
        int max = 0; 
        
        int[][] dp = new int[len1+1][len2+1]; 
        for (int i=0; i<len1; i++) { 
            for (int j=0; j<len2; j++) { 
                if (w1.charAt(i) == w2.charAt(j)) { 
                    dp[i+1][j+1] = dp[i][j] + 1; 
                } 
                else { 
                    dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
                }
                
                if (dp[i+1][j+1] > max) { 
                    max = dp[i+1][j+1]; 
                }
            }
        }
        
        return max; 
    }
}
