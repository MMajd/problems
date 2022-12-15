/* 
 @link https://leetcode.com/problems/longest-common-subsequence
 @categories (dp/string)

 Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

Example 1:
    Input: text1 = "abcde", text2 = "ace" 
    Output: 3  
    Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:
    Input: text1 = "abc", text2 = "abc"
    Output: 3
    Explanation: The longest common subsequence is "abc" and its length is 3.

Example 3:
    Input: text1 = "abc", text2 = "def"
    Output: 0
    Explanation: There is no such common subsequence, so the result is 0.

Constraints:
    1 <= text1.length, text2.length <= 1000
    text1 and text2 consist of only lowercase English characters.
*/


// solving from back
class Solution {
    public int longestCommonSubsequence(String s1, String s2) {
        int n = s1.length(); 
        int m = s2.length(); 
        
        if (m > n) { 
            return longestCommonSubsequence(s2, s1); 
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
        
        return dp[0]; 
    }
}


class Solution2 {
    public int longestCommonSubsequence(String s1, String s2) {
        int n = s1.length(); 
        int m = s2.length(); 
        
        int[][] dp = new int[n+1][m+1];
        
        for (int i=n-1; i>=0; --i) { 
            for (int j=m-1; j>=0; --j) {
                if (s1.charAt(i) == s2.charAt(j)) { 
                    dp[i][j] = dp[i+1][j+1] + 1; 
                }
                else { 
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }
        
        return dp[0][0];
    }
}


// solving from front
class Solution {
    public int longestCommonSubsequence(String s1, String s2) {
        int n = s1.length(); 
        int m = s2.length(); 
        
        if (m > n) { 
            return longestCommonSubsequence(s2, s1);
        }
        
        int[] dp = new int[m+1];
        int max = 0; 
        
        for (int i=0; i<n; i++) { 
            int[] prev = dp.clone();
            
            for (int j=0; j<m; j++) {
                if (s1.charAt(i) == s2.charAt(j)) { 
                    dp[j+1] = prev[j] + 1; 
                }
                else { 
                    dp[j+1] = Math.max(dp[j], prev[j+1]);
                }
            }
        }
    
        return dp[m]; 
    }
}


class LongestCommonSubSequence {
    public int longestCommonSubsequence(String text1, String text2) {
        return solve(text1, text2);
    }

    public int solve (String s1, String s2) {
        int m = s1.length(); 
        int n = s2.length(); 

        int[][] dp = new int[m+1][n+1]; 

        char[] S1 = s1.toCharArray(); 
        char[] S2 = s2.toCharArray(); 

        for(int i=0; i<m; i++) { 
            for (int j=0; j<n; j++) { 
                if (S1[i] == S2[j]) { 
                    dp[i+1][j+1] = 1 + dp[i][j];
                }
                else { 
                    dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        return dp[m][n]; 
    }
}
