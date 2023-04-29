/*
 @link https://leetcode.com/problems/subarray-sums-divisible-by-k/
 @categories (prefix-sum/hash-table/mapping)

 Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
A subarray is a contiguous part of an array.

Example 1:
    Input: nums = [4,5,0,-2,-3,1], k = 5
    Output: 7
    Explanation: There are 7 subarrays with a sum divisible by k = 5:
    [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

Example 2:
    Input: nums = [5], k = 9
    Output: 0

Constraints:
    1 <= nums.length <= 3 * 10^4
    -10^4 <= nums[i] <= 10^4
    2 <= k <= 10^4
 */

/** 
 * If a subarray is divisible by K, it has to be a multiple of K
 * a-b=n*k, a = running total, b = any previous subarray sum, same as original prefix sum problems
 * We want to solve for b, so using basic algebra, b=a-n*k
 * We don't know what n is, so we can get rid of n by modding every element by k
 * (b%k) = (a%k) - (n*k)%k
 * since n*k is a multiple of k and k goes into it evenly, the result of the (n *k)%k will be 0
 * therefore
 * b%k = a%k
 * is the same as the formula we defined earlier, a-b=n*k
 * where b = running total, a = any previous subarray sum
 * So we just have to see if running total mod k is equal to any previous running total mod k
 */ 
class Solution {
    public int subarraysDivByK(int[] A, int k) {
        int n = A.length; 
        int sum = 0; 
        int ans = 0; 
        int[] map = new int[k]; 
        map[0] = 1;

        for (int x : A) {
            // adding + k takes care of the negative cases 
            sum = (sum + x % k + k) % k; // what is matter the sum of prefixes, not the sum itself
            ans +=  map[sum]; 
            map[sum] += 1; 
        }

        return ans; 
    }
}
