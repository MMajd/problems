/*
 @link https://leetcode.com/problems/continuous-subarray-sum
 @categories (hash-table/math/number-theory/arrays)

 Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.

An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.

Example 1:
    Input: nums = [23,2,4,6,7], k = 6
    Output: true
    Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.

Example 2:
    Input: nums = [23,2,6,4,7], k = 6
    Output: true
    Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
    42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.

Example 3:
    Input: nums = [23,2,6,4,7], k = 13
    Output: false

Constraints:
    1 <= nums.length <= 10^5
    0 <= nums[i] <= 10^9
    0 <= sum(nums[i]) <= 2^31 - 1
    1 <= k <= 2^31 - 1


** Explanantion ** 
- Basic idea, the sum of an interval of [l, r], is equal to the 
 prefix(r) - prefix(l-1), and when prefix(r)-prefix(l-1) = n * k 
 then [ prefix(r) - prefix(l-1) ] % k = 0, 
 prefix(r) % k = prefix(l-1) % k, meaning for the sum of x and y to be a divisable by k they must ahve the same reminder

 - We use a HashMap to know if we have met the reminder before or not, if we have met the reminder with a prefix different from the current prefix then have a subarray that its sum is divisable by k 

 - We initialize the HashMap by values of (0, 0) to avoid having one element subarray 
*/ 

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> hashMap = new HashMap<>(Map.of(0, 0));
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (!hashMap.containsKey(sum % k))
                hashMap.put(sum % k, i + 1); // i + 1 represents the length of sum subarray
            else if (hashMap.get(sum % k) < i) // comparing it with i, to guarantee having at least length of 2 
                return true;
        }
        return false;
    }
}
