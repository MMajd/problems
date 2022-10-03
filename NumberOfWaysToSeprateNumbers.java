/*

@link https://leetcode.com/problems/number-of-ways-to-separate-numbers/
@categories (dp/strings)

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
    public int numberOfCombinations1(String s) {
        int n = s.length()+1;

        // need to cheet the all 1's input with more than 2000 in length
        // if charat(0) == charAt(n-1) == 1 and n > 2000 return the ans
        
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

}
