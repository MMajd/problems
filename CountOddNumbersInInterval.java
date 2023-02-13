/*
 @link https://leetcode.com/problems/count-odd-numbers-in-an-interval-range
 @categories (math) 

 Given two non-negative integers low and high. 
Return the count of odd numbers between low and high (inclusive).

Example 1:
    Input: low = 3, high = 7
    Output: 3
    Explanation: The odd numbers between 3 and 7 are [3,5,7].

Example 2:
    Input: low = 8, high = 10
    Output: 1
    Explanation: The odd numbers between 8 and 10 are [9].

Constraints:
    0 <= low <= high <= 10^9
 
*/ 

/** unoptimized solution */
class Solution {
    public int countOdds(int low, int high) {
        if (low == high) return low % 2; 
        if (low == 0) return high / 2 + high % 2; 
        // both even, or both odd 
        if ((low%2) == (high%2)) { 
            if (high % 2 == 0) return (high-low) / 2; 
            return (high - low) / 2 + 1; 
        }
        // one is odd, one is even
        int c = (high - low - 1) / 2 + (low % 2 == 0? 0: 1) + (high % 2 == 0? 0 : 1); 
        return c; 
    }
}

/** optimized solution */
class Solution {
    public int countOdds(int low, int high) {
        if ((low&0x01) == 0) { 
            low += 1; 
        }
        if (low > high) return 0;
        return (high - low) / 2 + 1; 
    }
}
