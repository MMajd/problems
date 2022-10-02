/*
 @link https://leetcode.com/problems/decode-ways-ii
 @categories (dp/string) 

 A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

"AAJF" with the grouping (1 1 10 6)
"KJF" with the grouping (11 10 6)
Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

In addition to the mapping above, an encoded message may contain the '*' character, which can represent any digit from '1' to '9' ('0' is excluded). For example, the encoded message "1*" may represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19". Decoding "1*" is equivalent to decoding any of the encoded messages it can represent.

Given a string s consisting of digits and '*' characters, return the number of ways to decode it.

Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:
    Input: s = "*"
    Output: 9
    Explanation: The encoded message can represent any of the encoded messages "1", "2", "3", "4", "5", "6", "7", "8", or "9".
    Each of these can be decoded to the strings "A", "B", "C", "D", "E", "F", "G", "H", and "I" respectively.
    Hence, there are a total of 9 ways to decode "*".

Example 2:
    Input: s = "1*"
    Output: 18
    Explanation: The encoded message can represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19".
    Each of these encoded messages have 2 ways to be decoded (e.g. "11" can be decoded to "AA" or "K").
    Hence, there are a total of 9 * 2 = 18 ways to decode "1*".

Example 3:
    Input: s = "2*"
    Output: 15
    Explanation: The encoded message can represent any of the encoded messages "21", "22", "23", "24", "25", "26", "27", "28", or "29".
    "21", "22", "23", "24", "25", and "26" have 2 ways of being decoded, but "27", "28", and "29" only have 1 way.
    Hence, there are a total of (6 * 2) + (3 * 1) = 12 + 3 = 15 ways to decode "2*".

Constraints:
    1 <= s.length <= 105
    s[i] is a digit or '*'.
*/



class Solution {
    private static final long M = 1_000_000_007; 


    /** Space optimized */
    public int numDecodings(String s) {
        int n = s.length();

        long first = 1; 
        long second = 1; 
        
        for (int i=n-1; i>=0; --i) { 
            char u = s.charAt(i);
            long ways = 0; 
            
            if (u == '*') { 
                ways = (9 * first) % M;
                
                if (i+1<n) { 
                    char v = s.charAt(i+1);

                    if (v == '*') {
                        ways = (ways + 15 * second) % M;
                    }
                    else if (v < '7') {
                        ways = (ways + 2 * second) % M; 
                    }
                    else { 
                        ways = (ways + second) % M; 
                    }
                }
            } 
            else if (u != '0') {
                ways = first % M; 

                if (i+1<n) { 
                    char v = s.charAt(i+1);
                    
                    if (u == '1' && v == '*') { 
                        ways = (ways + 9 * second) % M; 
                    }
                    else if (u == '2' && v == '*') { 
                        ways = (ways + 6 * second) % M; 
                    }
                    else if (u == '1' || u== '2' && v < '7') { 
                        ways = (ways + second) % M; 
                    }
                }
            }

            second = first; 
            first = ways; 
        }
        
        return (int) first; 
    }
    
    public int numDecodings(String s) {
        int n = s.length();
        Long[] dp = new Long[n+1]; 
        
        dp[n] = 1L;
        
        for (int i=n-1; i>=0; --i) { 
            char u = s.charAt(i);
            long ways = 0; 
            
            if (u == '*') { 
                ways = (9 * dp[i+1]) % M;
                
                if (i+1<n) { 
                    char v = s.charAt(i+1);

                    if (v == '*') {
                        ways = (ways + 15 * dp[i+2]) % M;
                    }
                    else if (v < '7') {
                        ways = (ways + 2 * dp[i+2]) % M; 
                    }
                    else { 
                        ways = (ways + dp[i+2]) % M; 
                    }
                }
            } 
            else if (u != '0') {
                ways = dp[i+1] % M; 

                if (i+1<n) { 
                    char v = s.charAt(i+1);
                    
                    if (u == '1' && v == '*') { 
                        ways = (ways + 9 * dp[i+2]) % M; 
                    }
                    else if (u == '2' && v == '*') { 
                        ways = (ways + 6 * dp[i+2]) % M; 
                    }
                    else if (u == '1' || u== '2' && v < '7') { 
                        ways = (ways + dp[i+2]) % M; 
                    }
                }
            }

            dp[i] = ways; 
        }
        
        return (int)(dp[0].longValue());
    }
    
    public int numDecodingsMemo(String s) {
        int n = s.length();
        Long[] memo = new Long[n+1]; 

        return (int) dp(s.toCharArray(), 0, memo);
    }

    private long dp(char[] s, int i, Long[] memo) {
        if (i == s.length) return 1L;
        if (s[i] == '0') return 0L;
        if (memo[i] != null) return memo[i]; 
        
        if (s[i] == '*') { 
            long ways = (9 * dp(s, i+1, memo)) % M;
            
            if (i+1<s.length && s[i+1] == '*') {
                ways = (ways + 15 * dp(s, i+2, memo)) % M;
            }
            else if (i+1<s.length && s[i+1] < '7') {
                ways = (ways + 2 * dp(s, i+2, memo)) % M; 
            }
            else if (i+1<s.length) { 
                ways = (ways + dp(s, i+2, memo)) % M; 
            }
            
            memo[i] = ways;
            return ways; 
        }
        
        long ways = dp(s, i+1, memo) % M; 
        
        if (i+1<s.length) { 
            if (s[i] == '1' && s[i+1] == '*') { 
                ways = (ways + 9 * dp(s, i+2, memo)) % M; 
            }
            else if (s[i] == '2' && s[i+1] == '*') { 
                ways = (ways + 6 * dp(s, i+2, memo)) % M; 
            }
            else if (s[i] == '1' || s[i] == '2' && s[i+1] < '7') { 
                ways = (ways + dp(s, i+2, memo)) % M; 
            }
        }
        
        memo[i] = ways; 
        
        return ways; 
    }
}
