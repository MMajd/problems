/*
 @link https://leetcode.com/problems/find-the-smallest-divisor-given-a-T
 @related (Koko Eating Bananas)
 @categories (binary-search)

 Given an array of integers A and an integer T, we will choose a positive integer divisor, divide all the array by it, and res the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to T.

Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).

The test cases are generated so that there will be an answer.

Example 1:
    Input: A = [1,2,5,9], T = 6
    Output: 5
    Explanation: We can get a res to 17 (1+2+5+9) if the divisor is 1. 
    If the divisor is 4 we can get a res of 7 (1+1+2+3) and if the divisor is 5 the res will be 5 (1+1+1+2). 

Example 2:
    Input: A = [44,22,33,11,1], T = 5
    Output: 44

Constraints:
    1 <= A.length <= 5 * 10^4
    1 <= A[i] <= 10^6
    A.length <= T <= 10^6
*/

class Solution {
    public int smallestDivisor(int[] A, int T) {
        int left = 1, right = 1000_000; 

        while(left < right){
            int res = 0;
            int d = left + (right-left)/2;

            for(int x : A) res += (x+d-1) / d; // fast integer ceiling
                                               //
            if (res > T) left = d+1;
            else right = d;
        }

        return left;
    }
}

