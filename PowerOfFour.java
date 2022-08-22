/*

 @link https://leetcode.com/problems/power-of-four/

 Given an integer n, return true if it is a power of four. Otherwise, return false.
An integer n is a power of four, if there exists an integer x such that n == 4x.
 

Example 1:
    Input: n = 16
    Output: true
Example 2:
    Input: n = 5
    Output: false

Example 3:
    Input: n = 1
    Output: true

Constraints:
    -231 <= n <= 231 - 1
     
Follow up: Could you solve it without loops/recursion?



NOTES: 

The Idea Is :
As we need to Find The Num is Power Of 4 or Not
We Can Solve It by doing Bit Manipulation
[If any n is valid power of 4 then it should be valid power of 2 as well] <= Use This Property

Let's suppose if they are asking for finding is the number is power 2 or not then what should we do 

We just trying to find is that n have only one set bit or not
if there is only one set bit exist then we can blindly say that it's a valid power of 2
so,the same idea we need here also but need to check one more condition

What is the 2nd condition would be, 
Let's find out by analysing few example

0 1 2 3 4  5   6  7   8   9   10
================================
1 2 4 8 16 32 64 128 256 592 1084 ---> Power of 2's 
1 4 16 64 256 1084 ---> Power of 4's 

We observe that power of 4's are the even numbers in the power of 2's set 
Looking at there binary representation 

4:  0b0000_0100 
16: 0b0001_0000 
64: 0b0100_0000 

Noticing that for a number to be a power of 4 it must:
    1. Have only one bit set, any other bits set means not power of 2  
    2. Have that set bit in an odd position if we count bits starting from zero 

Let's See Example :
     #          BINARY REPSENTATION    SET BITS       NO OF SET BITS         VALID POWER OF 4
     64         0 1 0 0 0 0 0 0         7                  1                        True         [Conditions Met]
     128        1 0 0 0 0 0 0 0         8                  1                        False        [2nd Condition Fail]
     18         0 0 0 1 0 0 1 0         5,2                2                        False        [1st Condition Fail]
     32         0 0 1 0 0 0 0 0         6                  1                        False        [2nd Condition Fail]

Time: O(1) only 16 iteration  
Space: O(1) 
*/

class Solution1 {
    public boolean isPowerOfFour(int n) {
        if(n<0) return false;

        // always increase by 2 no need to increase by one 
        for(int i=0; i<32; i+=2)  {
            if(n == 1<<i) return true; 
        }

        return false;
    }
}

class Solution2 {
    public boolean isPowerOfFour(int n) {
        if (n == 0) return false; 
        int y = (int)(Math.log(n) / Math.log(4)); 
        return Math.pow(4, y) == n; 
    }
}


class Solution3 {
    public boolean isPowerOfFour(int n) {
        while(n>0) {
            if (n%4 == 1) return false; 
            n /= 4; 
        }

        return true; 
    }
}
