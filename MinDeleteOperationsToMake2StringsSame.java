/** 
 *
 * @link https://leetcode.com/problems/delete-operation-for-two-strings/ 
 *
 */


class MinDeleteOperationsToMake2StringsSame { 

    public int minDistance(String word1, String word2) {
        return solve(word1, word2);
    }

    /** This is the LongestCommon Subsequence with little tweek at the end  */
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

        // instead of returning max we return the sum of 2 strings - 2*max 
        // and this gives us the total delete operations needed to be done 
        // on the two strings to be the same
        return len1 + len2 - 2 * max;  
    }

}
