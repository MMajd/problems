/** 
 @link https://leetcode.com/problems/sqrtx/ 

 To solve this is problem, we use the fact that sqrt is bounded in the interval from [1, x) and the fact the sqrt(x)*sqrt(x) gives x back

 therefore we processd using binary search as our search range sorted 
 and we have target which is sqrt(x) == x/sqrt(x) *discrimination techinque* 

 if the left bound overflows the right bound we should return the lesser bound which is the right one


 Given a non-negative integer x, 
 compute and return the square root of x.

 Since the return type is an integer, 
 the decimal digits are truncated, 
 and only the integer part of the result is returned.

*NOTE*: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.

Example 1:
    Input: x = 4
    Output: 2

Example 2:
    Input: x = 8
    Output: 2
    Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
 
Constraints:
    0 <= x <= 231 - 1
*/

class Solution {
    public int mySqrt(int x) {
        if (x == 0) return 0; 
        
        int start=1, end=x; 
        
        while(start<=end) {
            int mid = start + (end-start)/2; 
            
            if (mid == x/mid) return mid; 
            if (mid > x/mid) end = mid-1; 
            else start=mid+1; 
        }
        
        return end; 
    }
}
