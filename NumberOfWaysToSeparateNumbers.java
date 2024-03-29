/*

@link https://leetcode.com/problems/number-of-ways-to-separate-numbers/
@categories (dp[top-down/bottom-up]/strings)

You wrote down many positive integers in a string called num. However, you realized that you forgot to add commas to seperate the different numbers. You remember that the list of integers was non-decreasing and that no integer had leading zeros.

Return the number of possible lists of integers that you could have written down to get the string num. Since the answer may be large, return it modulo 109 + 7.

Example 1:
    Input: num = "327"
    Output: 2
    Explanation: You could have written down the numbers:
    3, 27
    327

Example 2:
    Input: num = "094"
    Output: 0
    Explanation: No numbers can have leading zeros and all numbers must be positive.

Example 3:
    Input: num = "0"
    Output: 0
    Explanation: No numbers can have leading zeros and all numbers must be positive.

Constraints:
    1 <= num.length <= 3500
    num consists of digits '0' through '9'.

*/
class Solution {
    private static final int MOD = 1_000_000_007; 
    
/** 
0 1 2 3 4 5 6
x x x x x x x           len= 4, i= 6, j=6-4+1 = 3; 
      j     i

if remaing string less than the length there's no need to check as curr number has more digits than the previous one
*/
    public int numberOfCombinations2(String s) {
        int n = s.length();
        
        // long[][] dp = new long[n][n+1]; 
        long[][] presum = new long[n][n+1]; // dp presum array to eliminate the for loop of len2 to maxPrev
        int [][] lcs = new int[n+1][n+1]; // length longest common substring that starts from i and j 
        
        
        for (int i=n-2; i>=0; --i) { 
            for (int j=i+1; j<n; j++) { 
                if (s.charAt(i) == s.charAt(j)) { 
                    lcs[i][j] = 1 + lcs[i+1][j+1]; 
                }
            }
        }
        
        for (int i=0; i<n; i++) { 
            for (int len=1; len<=i+1; len++) {
                int j = i-len+1; // start index of the last num, and length of the previous string
                long ways = 0; 
                 
                if (s.charAt(j) == '0') { 
                    // dp[i][len] = 0; 
                    ways = 0; 
                }
                else if (j == 0) { // base case, after taking whole string as the number we get 1; 
                    // dp[i][len] = 1;
                    ways = 1; 
                } 
                else { 
                    int maxPrev = 0; 
                    if (j < len) { 
                        maxPrev = j; 
                    }
                    else { 
                        int lc = lcs[j-len][j]; 
                        
                        if (lc>=len || s.charAt(j-len+lc)<s.charAt(j+lc)) {
                            maxPrev = len; 
                        }
                        else { 
                            maxPrev = len-1; 
                        }
                    }
                    // else if (s.substring(j-len, j).compareTo(s.substring(j, i+1)) <= 0) { 
                    //     maxPrev = len; 
                    // }
                    // else { 
                    //     maxPrev = len-1; 
                    // }

                    ways = presum[j-1][maxPrev]; 
                    
                    // for (int k=1; k<=maxPrev; k++) dp[i][len] = (dp[i][len] + dp[j-1][k]) % MOD; 
                    // dp[i][len] = presum[j-1][maxPrev]; 
                }
                
                presum[i][len] = (presum[i][len-1] + ways) % MOD; 
            }
        }
        
        return (int) presum[n-1][n]; 
    }
    
    
    public int numberOfCombinations(String s) {
        int n = s.length();
        
        char[] chars = s.toCharArray();
        int[][] dp = new int[n+1][n+1]; 
        int[][] lcs = new int[n+1][n+1]; 

        
        for(int i=n-1; i>=0; --i){ 
            for(int j=i; j>=0; --j) {
                if(chars[i] == chars[j]) {
                    lcs[i][j] = 1 + lcs[i+1][j+1]; 
                }
            }
        }

        
        for(int i=n-1; i>=0 ; --i){
            if (chars[i] == '0') continue;
            
            dp[i][n-i] = 1;
            
            for(int j=n-i-1; j>=1; --j) {
                
                dp[i][j] = dp[i][j+1];
                
                if (i-1 + (j<<1) >= n) continue;
                
                int r = i+j; 
                int lc = lcs[r][i]; 
                
                if (i+lc== r || r+lc==n || chars[i+lc] <= chars[r+lc]) { 
                    dp[i][j] = (dp[i][j] + dp[r][j]) % MOD;
                }
                else { 
                    dp[i][j] = (dp[i][j] + dp[r][j+1]) % MOD;
                }
            }
        }
        
        return (int) dp[0][1]; 
    }
  
    
    public int numberOfCombinations1(String s) {
        int n = s.length()+1;
        
        Integer[][] memo = new Integer[n][n]; 
        return dfs(s, 0, "", memo);
    }
    
    private int dfs(String s, int i, String prev, Integer[][] memo) {
        int plen = prev.length();
        
        if (i == s.length()) return 1;
        if (plen > s.length()-i) return 0; 
        if (s.charAt(i) == '0') return 0; 
        
        if (memo[i][plen] != null) return memo[i][plen]; 
        
        int ways = 0; 
        
        for (int j=i+1; j<=s.length(); j++) {
            String curr = s.substring(i, j); 
            
            if (plen>j-i) continue;
            if (plen<j-i || prev.compareTo(curr) <= 0) {
                ways = (ways + dfs(s, j, curr, memo)) % MOD; 
            }
        }
        
        
        return memo[i][plen] = ways; 
    }
}

