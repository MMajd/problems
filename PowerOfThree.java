/* 
 @link https://leetcode.com/problems/power-of-three/


Given an integer n, return true if it is a power of three. Otherwise, return false.

An integer n is a power of three, if there exists an integer x such that n == 3x.

Example 1:
    Input: n = 27
    Output: true

Example 2:
    Input: n = 0
    Output: false

Example 3:
    Input: n = 9
    Output: true
 

Constraints:
    -2^31 <= n <= 2^31 - 1
*/






class Solution {
    // no fancy solution for this one 
    // so my log solution is valid here 
    // take the decmial part by %1 (modulo one)
    // if the output is zero then we have power of 3 
    
    public static final double EPS = 1.0E-10; 
    
    
    public boolean isPowerOfThree(int n) {
        if (n < 1) return false; 
        while(n%3==0) n/=3; 
        return n == 1; 
    }


    public boolean isPowerOfThreeLog(int n) {
        return (Math.log10(n)/Math.log10(3) + EPS) % 1 <= 2*EPS; 
    }
}
