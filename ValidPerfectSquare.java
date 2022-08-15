/*

 @link https://leetcode.com/problems/valid-perfect-square/

Given a positive integer num, write a function which returns True if num is a perfect square else False.

Follow up: Do not use any built-in library function such as sqrt.


Example 1:
    Input: num = 16
    Output: true

Example 2:
    Input: num = 14
    Output: false

Constraints: 
    1 <= num <= 2^32-1

*/

class Solution {
    public boolean isPerfectSquare(int num) {
        int start=1, end=num; 
        
        while(start<=end) { 
            int mid = start + (end-start)/2; 
            if (mid == num/mid && num%mid==0) return true; 
            if (mid > num/mid) end = mid-1; 
            else start = mid+1; 
        }
        return false; 
    }
}
