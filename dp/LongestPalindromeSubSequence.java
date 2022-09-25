/* 
 @link https://leetcode.com/problems/longest-palindromic-subsequence
 @categories (dp[longest-common-subsequence-variant]) 

 Given a string s, find the longest palindromic subsequence's length in s.
A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.


Example 1:
    Input: s = "bbbab"
    Output: 4
    Explanation: One possible longest palindromic subsequence is "bbbb".

Example 2:
    Input: s = "cbbd"
    Output: 2
    Explanation: One possible longest palindromic subsequence is "bb".

Constraints:
    1 <= s.length <= 1000
    s consists only of lowercase English letters.
 */ 

class Solution {
    public int longestPalindromeSubseq(String s1) {
        int n = s1.length(); 
        
        int[] dp = new int[n+1]; 
        
        for (int i=n-1; i>=0; --i) { 
            int[] prev = dp.clone();
            
            for (int j=n-1; j>=0; --j) { 
                if (s1.charAt(i) == s1.charAt(n-j-1)) { 
                    dp[j] = prev[j+1] + 1; 
                }
                else { 
                    dp[j] = Math.max(prev[j], dp[j+1]);
                }
            }
        }
        
        return dp[0]; 
    }
}
