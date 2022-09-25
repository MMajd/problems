/*
 @link https://leetcode.com/problems/delete-operation-for-two-strings
 @categories (dp[longest-common-subsequence-variant])

 Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
In one step, you can delete exactly one character in either string.


Example 1:
    Input: word1 = "sea", word2 = "eat"
    Output: 2
    Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".

Example 2:
    Input: word1 = "leetcode", word2 = "etco"
    Output: 4

Constraints:
    1 <= word1.length, word2.length <= 500
    word1 and word2 consist of only lowercase English letters.

*/ 



/** 
 * Same solution as Longest common subsequence
 */ 
class Solution {
    public int minDistance(String s1, String s2) {
        int n = s1.length(); 
        int m = s2.length(); 
        
        if (m > n) { 
            return minDistance(s2, s1);
        }
        
        int[] dp = new int[m+1]; 
        
        for (int i=n-1; i>=0; --i) { 
            int[] prev = dp.clone(); 
            
            for (int j=m-1; j>=0; --j) { 
                if (s1.charAt(i) == s2.charAt(j)) { 
                    dp[j] = prev[j+1] + 1;
                }
                else { 
                    dp[j] = Math.max(prev[j], dp[j+1]);
                }
            }
        }
        
        return (n + m - 2*dp[0]); 
    }
}



