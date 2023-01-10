/*
 @link https://leetcode.com/problems/majority-element
 @categories (counting/hash-table/divide-and-conquer) 

 Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times. 
You may assume that the majority element always exists in the array.

Example 1:
    Input: nums = [3,2,3]
    Output: 3

Example 2:
    Input: nums = [2,2,1,1,1,2,2]
    Output: 2

Constraints:
    n == nums.length
    1 <= n <= 5 * 10^4
    -10^9 <= nums[i] <= 10^9

Follow-up: 
    Could you solve the problem in linear time and in O(1) space?
*/
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); 
        int x = nums[0]; 

        for (int i : nums) { 
            map.merge(i, 1, Integer::sum);
            if (map.get(i) > map.get(x)) { 
                x = i; 
            }
        }

        return x; 
    }

    /** D&C solution, if not counting stack space, its linear with space of O(1) */
    private int findMajorityElement(int[] nums, int start, int curr) {
        int cnt = 1; 
        for (int i=start; i<nums.length; i++) {
            if (curr == nums[i]) cnt += 1; 
            else cnt -= 1; 

            if (cnt == -1) return findMajorityElement(nums, i+1, nums[i]);
        }

        return curr; 
    }
}
