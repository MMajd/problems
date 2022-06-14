

/**
 * Problem LongestPalindromeSubstring 
 *
 * @link https://zkf85.github.io/2019/03/26/leetcode-005-longest-palindrome
 * @link https://developpaper.com/5-longest-palindromic-substring/
 *
 **/

public class LongestPalindromeSubstring { 
    public String getLongestPalindrome(String s) { 
        int strlen = s.length(); 

        String reverse = new StringBuffer(s).reverse().toString();

        int dp[] = new int [strlen]; 

        int maxLen = 0; 
        int maxEnd = 0; 

        for (int i=0; i<strlen; i++) { 
            /** j start from the end as we work on one dimension array */
            for (int j=strlen -1; j>=0 ; j--) { 
                if (s.charAt(i) == s.charAt(j)) { 
                    if (i == 0 || j == 0) { 
                        dp[j] = 1; 
                    } else { 
                        dp[j] = dp[j-1] + 1; 
                    }
                } else { 
                    dp[j] = 0; 
                }

                if (maxLen < dp[j]) { 
                    int startIndex = i - (dp[j]-1); 
                    int reverseStart = strlen - (j+1); 

                    if (startIndex == reverseStart) { 
                        maxLen = dp[j]; 
                        maxEnd = i; 
                    }
                }
            }
        }

        return s.substring(maxEnd - (maxLen - 1), maxEnd + 1); 
    }
}


/*** 
 
class Solution1:
    def longestPalindrome(self, s: str) -> str:
        if s is "":
            return ""
        
        rev = s[::-1]
        dp = [[0 for i in range(len(s))] for j in range(len(s))]
        max_len = 0
        max_end = 0
        for i in range(len(s)):
            for j in range(len(s)):
                if s[i] == rev[j]:
                    if i == 0 or j == 0:
                        dp[i][j] = 1
                    else:
                        dp[i][j] = dp[i-1][j-1] + 1
                if dp[i][j] > max_len:
                    if i-dp[i][j]+1 == len(s)-1-j:
                        max_len = dp[i][j]
                        max_end = i
                
        return s[max_end - max_len + 1: max_end + 1]

*/
