/*
 
 @link https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers
  
 @categories (math/bit-manipulation/simulation)
  
 Given an integer n, return the decimal value of the binary string formed by concatenating the binary representations of 1 to n in order, modulo 109 + 7.

Example 1:
    Input: n = 1
    Output: 1
    Explanation: "1" in binary corresponds to the decimal value 1. 

Example 2:
    Input: n = 3
    Output: 27
    After concatenating them, we have "11011", which corresponds to the decimal value 27.

Example 3:
    Input: n = 12
    Output: 505379714
    Explanation: The concatenation results in "1101110010111011110001001101010111100".
    The decimal value of that is 118505380540.
    After modulo 109 + 7, the result is 505379714.
 
Constraints:
    1 <= n <= 105
 */


class Solution {
    private static final int MOD = 1_000_000_007; 
    
    public int concatenatedBinary(int n) {
        if (n == 1) return 1; 
        
        int len = 0; 
        long ans = 0; 
        
        for (int i=1; i<=n; i++) { 
            if ((i&(i-1)) == 0) len += 1; 
            
            ans = ((ans<<len) | i) % MOD; 
        }
        
        return (int)ans; 
    }
}
