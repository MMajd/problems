/*
 @link https://leetcode.com/problems/shortest-common-supersequence
 @categories (dp[LCS]/arrays/string)
  
 Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.

A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.

Example 1:
    Input: str1 = "abac", str2 = "cab"
    Output: "cabac"
    Explanation: 
    str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
    str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
    The answer provided is the shortest such string that satisfies these properties.

Example 2:
    Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
    Output: "aaaaaaaa"

Constraints:
    1 <= str1.length, str2.length <= 1000
    str1 and str2 consist of lowercase English letters.
*/




class Solution {
    private int M, N; 

    public String shortestCommonSupersequence(String s1, String s2) {
        if (s1.equals(s2)) return s1; 

        M = s1.length();
        N = s2.length(); 

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int[][] dp = new int[M+1][N+1];

        StringBuilder seq = new StringBuilder();

        for(int i=1; i<=M; i++) {
            for(int j=1; j<=N; j++) {
                if (c1[i-1] == c2[j-1]) { 
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else { 
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        int i=M, j=N;

        // construction will be in reverse order 
        while(i>0 && j>0) {
            if (dp[i][j] == dp[i-1][j]) seq.append(c1[--i]);
            else if (dp[i][j] == dp[i][j-1]) seq.append(c2[--j]); 
            else { 
                seq.append(c1[--i]); 
                --j; 
            }
        }
        
        while (i>0) seq.append(c1[--i]);
        while (j>0) seq.append(c2[--j]);

        return seq.reverse().toString();
    }
}


class Solution2 {
    private int M, N; 

    public String shortestCommonSupersequence(String s1, String s2) {
        if (s1.equals(s2)) return s1; 

        M = s1.length();
        N = s2.length(); 

        int i = 0; 
        int j = 0;


        String lcs = LCS(s1, s2);
        StringBuilder sb = new StringBuilder();

        for (int k=0; k<lcs.length(); k++) {
            while(i<M && s1.charAt(i) != lcs.charAt(k)) sb.append(s1.charAt(i++));
            while(j<N && s2.charAt(j) != lcs.charAt(k)) sb.append(s2.charAt(j++));

            sb.append(lcs.charAt(k));

            i += 1; 
            j += 1; 
        }

        sb.append(s1.substring(i)).append(s2.substring(j));

        return sb.toString();
    }
    
    private String LCS(String s1, String s2) {
        String[][] dp = new String[M+1][N+1];

        for (int i=0; i<=M; i++) {
            for (int j=0; j<=N; j++) dp[i][j] = "";
        }

        for (int i=1; i<=M; i++) {
            for (int j=1; j<=N; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1] + s1.charAt(i-1);
                } 
                else {
                    dp[i][j] = dp[i-1][j].length() > dp[i][j-1].length() ? dp[i-1][j] : dp[i][j-1]; 
                }
            }
        }

        return dp[M][N];
    }
}
