
/** 
 *
 * Find longest common substring in two strings 
 *
 */

class LongestCommonSubString {
    public int minDistance(String word1, String word2) {
        return solve(word1, word2);
    }
    
    private int solve(String w1, String w2) {
        int len1 = w1.length(); 
        int len2 = w2.length(); 
        
        int maxLen = 0; 
        
        int[] dp = new int[len2]; 

        for (int i=0; i<len1; i++) { 
            for (int j=len2-1; j>=0; j--) { 
                if (i == 0 || j == 0) {
                    if (w1.charAt(i) == w2.charAt(j)) { 
                        dp[j] = 1; 
                    } else { 
                        dp[j] = 0; 
                    }
                } else { 
                    if (w1.charAt(i) == w2.charAt(j)) { 
                        dp[j] = dp[j-1] + 1; 
                    } else { 
                        dp[j] = 0; 
                    }
                }
                
                if (dp[j] > maxLen) {
                    maxLen = dp[j]; 
                }
            }
        }
        
        return (len1 - maxLen) + (len2 - maxLen); 
    }
}
