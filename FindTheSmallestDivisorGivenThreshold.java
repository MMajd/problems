/*
 @link https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold
 @categories (binary-search)

 Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and sum the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.

Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).

The test cases are generated so that there will be an answer.

Example 1:
    Input: nums = [1,2,5,9], threshold = 6
    Output: 5
    Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1. 
    If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2). 

Example 2:
    Input: nums = [44,22,33,11,1], threshold = 5
    Output: 44

Constraints:
    1 <= nums.length <= 5 * 10^4
    1 <= nums[i] <= 10^6
    nums.length <= threshold <= 10^6
*/

class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int lo = 1;
        int hi = Integer.MIN_VALUE;
        for(int i : nums){
            hi = Math.max(hi, i);
        }

        while(lo < hi){
            int sum = 0;
            int mid = lo + (hi - lo) / 2;
            for(int i : nums){
                // fast ceiling equation
                int quo = (i + mid - 1) / mid;
                sum += quo;
            }
            if(sum > threshold){
                    lo = mid+1;
            }else{
                hi = mid;
            }
        }
        return lo;
    }
}


class Solution2 {
    public int smallestDivisor(int[] A, int T) {
        int ans = 0; 
        int left=1, right=(int)10E6; 

        while (left <= right) {
            int res = 0; 
            int mid = left+(right-left)/2;

            for (int x : A) res += Math.ceil(1.0*x/mid); 

            if (T >= res) {
                ans = mid; 
                right = mid-1;
            }
            else left = mid+1; 
        }

        return ans; 
    }
}
