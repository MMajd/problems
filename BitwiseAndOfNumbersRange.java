/*
 @link https://leetcode.com/problems/bitwise-and-of-numbers-range/
 @categories (bitwise-manipulation) 

 Given two integers left and right that represent the range [left, right], 
return the bitwise AND of all numbers in this range, inclusive.

Example 1:
    Input: left = 5, right = 7
    Output: 4

Example 2:
    Input: left = 0, right = 0
    Output: 0

Example 3:
    Input: left = 1, right = 2147483647
    Output: 0

Constraints:
    0 <= left <= right <= 231 - 1

*****************************************************************
**  IMPORTANT NOTE  
*****************************************************************
* Bitwise and of two numbers will always give a number thats less than or equal to the smaller one of the two, 
* So we start solving this from end of the range going down to the start of the range
*****************************************************************
*/ 
   
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        while (right > left) { 
            right = right & (right-1); 
        }

        return left & right; 
    }
}
