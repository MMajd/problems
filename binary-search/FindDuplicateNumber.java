/*
 @link https://leetcode.com/problems/find-the-duplicate-number/
 @categories (binary-search/two-pointers)

 Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
There is only one repeated number in nums, 
return this repeated number.

You must solve the problem without modifying the array nums and uses only constant extra space.

Example 1:
    Input: nums = [1,3,4,2,2]
    Output: 2

Example 2:
    Input: nums = [3,1,3,4,2]
    Output: 3

Constraints:
    1 <= n <= 10^5
    nums.length == n + 1
    1 <= nums[i] <= n
    All the integers in nums appear only once except for precisely one integer which appears two or more times.

Follow up:
    How can we prove that at least one duplicate number must exist in nums?
    Can you solve the problem in linear runtime complexity?
*/

/** 
 * Loop Detection
 * Floyd's Tortise & Hare [Fast & Slow], 
 * Applicable as we have n + 1 integers where each integer is in the range [1, n] inclusive.
 */
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0]; 
        int fast = nums[0]; 

        do {
            slow = nums[slow]; 
            fast = nums[nums[fast]]; 
        } while (slow != fast);

        slow = nums[0]; 

        while (slow != fast) {
            slow = nums[slow]; 
            fast = nums[fast]; 
        }

        return slow; 
    }
}

/** 
 * Binary search, O(n*log(n)) solution
 */
class Solution {
    public int findDuplicate(int[] nums) {
        int left = 1; 
        int right = nums.length; 

        while (left <= right) { 
            int mid = left + (right - left) / 2; 
            int pivot = 0; 

            for (int x : nums) {
                pivot += (mid >= x) ? 1 : 0; 
            }

            if (pivot > mid) right = mid - 1; 
            else left = mid + 1; 
        }

        return left; 
    }
}

/** Maybe violating the constraints */
class Solution {
    public int findDuplicate(int[] nums) {
        int duplicate = -1; 
        
        for (int i=0; i<nums.length; i++) { 
            int curr = Math.abs(nums[i]);
            if (nums[curr] < 0) { 
                duplicate=curr; 
                break;
            }
                
            nums[curr] *= -1; 
        }
        
        for (int i=0; i<nums.length; i++) { 
            nums[i] = Math.abs(nums[i]);
        }
        
        return duplicate; 
    }
}
